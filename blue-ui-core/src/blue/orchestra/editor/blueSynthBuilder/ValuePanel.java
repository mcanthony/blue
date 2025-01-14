/*
 * blue - object composition environment for csound
 * Copyright (c) 2012 Steven Yi (stevenyi@gmail.com)
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
package blue.orchestra.editor.blueSynthBuilder;

import blue.utility.NumberUtilities;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 * Panel that switches between a label and a text field for editing
 *
 * @author stevenyi
 */
public class ValuePanel extends JPanel {

    JLabel valueDisplay = new JLabel("0.0");
    JTextField valueField = new JTextField();
    CardLayout cards = new CardLayout();

    public ValuePanel() {
        valueDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        valueDisplay.setBorder(new LineBorder(Color.gray, 1));

        valueDisplay.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() >= 2) {
                    editField();
                }
            }
        });

        valueField.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setValueFromField();
            }
        });

        valueField.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent e) {
                cards.show(ValuePanel.this, "display");
            }
        });

        this.setLayout(cards);
        this.add(valueDisplay, "display");
        this.add(valueField, "edit");
        cards.show(this, "display");
    }

    public void setValue(String value) {
        valueDisplay.setText(value);
        valueDisplay.setToolTipText(value);
    }
    
    public String getPendingValue() {
        return valueField.getText();
    }

    protected void editField() {
        String strVal = valueDisplay.getText();

        valueField.setText(strVal);
        cards.show(this, "edit");

        valueField.requestFocus();
        valueField.setCaretPosition(strVal.length());
        valueField.moveCaretPosition(0);
    }

    protected void setValueFromField() {
        firePropertyChange("value", valueDisplay.getText(), valueField.getText());
        cards.show(this, "display");
    }
}
