/*
 * blue - object composition environment for csound
 * Copyright (c) 2000-2004 Steven Yi (stevenyi@gmail.com)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by  the Free Software Foundation; either version 2 of the License or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; see the file COPYING.LIB.  If not, write to
 * the Free Software Foundation Inc., 59 Temple Place - Suite 330,
 * Boston, MA  02111-1307 USA
 */
package blue.ui.core.score;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import blue.BlueSystem;
import blue.score.TimeState;
import blue.soundObject.PolyObject;

class TimelinePropertiesPanel extends JComponent {

    JCheckBox snapEnabledBox = new JCheckBox(BlueSystem
            .getString("scoreGUI.snapEnabled"));

    JTextField snapValue = new JTextField();

    JTextField timeUnit = new JTextField();

    JRadioButton timeDisplayTime = new JRadioButton(BlueSystem
            .getString("scoreGUI.timeline.time"));

    JRadioButton timeDisplayNumber = new JRadioButton(BlueSystem
            .getString("scoreGUI.timeline.number"));

    boolean isUpdating = false;

    TimeState timeState;

    public TimelinePropertiesPanel() {

        snapEnabledBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (timeState != null) {
                    timeState.setSnapEnabled(snapEnabledBox.isSelected());
                }
            }
        });

        snapValue.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (timeState == null || isUpdating) {
                    return;
                }
                isUpdating = true;

                try {
                    float val = Float.parseFloat(snapValue.getText());
                    if (val < 0) {
                        return;
                    }
                    timeState.setSnapValue(val);
                } catch (NumberFormatException nfe) {
                    snapValue.setText(Float.toString(timeState.getSnapValue()));
                }

                isUpdating = false;
            }

        });

        ActionListener timeActionListener = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (!isUpdating) {
                    if (e.getSource() == timeDisplayTime) {
                        timeState.setTimeDisplay(PolyObject.DISPLAY_TIME);
                    } else if (e.getSource() == timeDisplayNumber) {
                        timeState.setTimeDisplay(PolyObject.DISPLAY_NUMBER);

                    }
                }
            }

        };

        timeDisplayTime.addActionListener(timeActionListener);
        timeDisplayNumber.addActionListener(timeActionListener);

        ButtonGroup bg = new ButtonGroup();
        bg.add(timeDisplayTime);
        bg.add(timeDisplayNumber);

        timeUnit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    int val = Integer.parseInt(timeUnit.getText());

                    if (val < 1) {
                        timeUnit.setText(Integer.toString(timeState.getTimeUnit()));
                        return;
                    }

                    timeState.setTimeUnit(val);

                } catch (NumberFormatException nfe) {
                    timeUnit.setText(Integer.toString(timeState.getTimeUnit()));
                }

            }
        });

        // SET UP MENU

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel snapPanel = new JPanel();
        snapPanel.setBorder(BorderFactory.createTitledBorder(BlueSystem
                .getString("scoreGUI.snap")));
        snapPanel.setLayout(new GridLayout(2, 1));
        snapPanel.setMaximumSize(new Dimension(150, 150));

        snapPanel.add(snapEnabledBox);
        snapPanel.add(snapValue);

        JPanel timeDisplayPanel = new JPanel();
        timeDisplayPanel.setBorder(BorderFactory.createTitledBorder(BlueSystem
                .getString("scoreGUI.timeline.timeDisplay")));
        timeDisplayPanel.setLayout(new GridLayout(3, 1));
        timeDisplayPanel.setMaximumSize(new Dimension(150, 200));
        timeDisplayPanel.add(timeDisplayTime);
        timeDisplayPanel.add(timeDisplayNumber);
        timeDisplayPanel.add(timeUnit);

        this.add(snapPanel);
        this.add(timeDisplayPanel);

        this.add(Box.createVerticalGlue());

    }

    public void setTimeState(TimeState timeState) {
        isUpdating = true;

        this.timeState = timeState;

        snapEnabledBox.setSelected(timeState.isSnapEnabled());
        snapValue.setText(Float.toString(timeState.getSnapValue()));
        timeUnit.setText(Integer.toString(timeState.getTimeUnit()));

        if (timeState.getTimeDisplay() == TimeState.DISPLAY_TIME) {
            timeDisplayTime.setSelected(true);
        } else {
            timeDisplayNumber.setSelected(true);
        }

        isUpdating = false;
    }

}