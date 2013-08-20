/*
 * blue - object composition environment for csound
 * Copyright (C) 2013
 * Steven Yi <stevenyi@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package blue.score.layers.audio.ui;

import blue.score.TimeState;
import blue.score.layers.LayerGroupDataEvent;
import blue.score.layers.LayerGroupListener;
import blue.score.layers.audio.core.AudioLayerGroup;
import blue.ui.core.score.layers.LayerGroupPanel;
import blue.ui.core.score.layers.SelectionMarquee;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

/**
 *
 * @author stevenyi
 */
public class AudioLayersPanel extends JPanel implements LayerGroupListener,
        PropertyChangeListener, LayerGroupPanel {

    private static final Color PATTERN_COLOR = new Color(198, 226, 255);
    private AudioLayerGroup layerGroup;
    private final TimeState timeState;

    public AudioLayersPanel(AudioLayerGroup layerGroup, TimeState timeState) {
        this.layerGroup = layerGroup;
        this.timeState = timeState;

        layerGroup.addLayerGroupListener(this);
        timeState.addPropertyChangeListener(this);

//        final Dimension d = checkSize();
//        this.setSize(d);
        this.setBackground(Color.BLACK);

        this.addMouseWheelListener(new MouseWheelListener() {

            @Override
            public void mouseWheelMoved(MouseWheelEvent mwe) {
                mwe.getComponent().getParent().dispatchEvent(mwe);
            }
        });

        AudioLayerPanelMouseListener listener =
                new AudioLayerPanelMouseListener(this, layerGroup, timeState);
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        layerGroup.removeLayerGroupListener(this);
        timeState.removePropertyChangeListener(this);
    }

    @Override
    public void layerGroupChanged(LayerGroupDataEvent event) {
//        checkSize();
        repaint();
    }

//    protected Dimension checkSize() {
//        int w = (layerGroup.getMaxAudio() + 16) * 
//                layerGroup.getAudioBeatsLength() * 
//                timeState.getPixelSecond();
//        int h = layerGroup.getSize() * Layer.LAYER_HEIGHT;
//        final Dimension d = new Dimension(w, h);
//        this.setPreferredSize(d);
//        return d;
//    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource() == timeState) {
            if (evt.getPropertyName().equals("pixelSecond")) {
//                checkSize();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
//        Rectangle bounds = g.getClipBounds();
//
//        if (bounds == null) {
//            bounds = getBounds();
//        }
//
//        g.setColor(Color.BLACK);
//        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
//
//        int width = getWidth();
//        int height = getHeight();
//        int bottom = (height - 1);
//        int maxX = (int) bounds.getMaxX();
//        int maxY = (int) bounds.getMaxY();
//        int x = 0;
//        int pixelSecond = timeState.getPixelSecond();
//
//        int patternBeatsLength = layerGroup.getAudioBeatsLength();
//        int patternWidth = patternBeatsLength * pixelSecond;
//        int startLayerIndex = (bounds.y / Layer.LAYER_HEIGHT);
//        int startAudioIndex = (int) (bounds.x / (float) (patternBeatsLength * pixelSecond));
//
//        //Draw lines and pattern states
//
//        g.setColor(Color.DARK_GRAY);
//
//        for (int i = startLayerIndex; i < layerGroup.getSize(); i++) {
//            int y = i * Layer.LAYER_HEIGHT;
//
//            if (y > maxY) {
//                break;
//            }
//
//            AudioLayer layer = (AudioLayer) layerGroup.getLayerAt(i);
//            AudioData data = layer.getAudioData();
//
//            g.setColor(PATTERN_COLOR);
//
//            x = (startAudioIndex * patternBeatsLength) * pixelSecond;
//            for (int j = startAudioIndex; x < maxX; j++) {
//                x = (j * patternBeatsLength) * pixelSecond;
//                if (data.isAudioSet(j)) {
//                    //System.out.println("pattern set: " + j);
//                    g.fillRect(x, y, patternWidth, Layer.LAYER_HEIGHT);
//                }
//            }
//
//            g.setColor(Color.DARK_GRAY);
//            g.drawLine(bounds.x, y, maxX, y);
//
//        }
//
//        g.setColor(Color.DARK_GRAY);
//
//        if (bottom < maxY) {
//            g.drawLine(bounds.x, bottom, maxX, bottom);
//        }
//
//        x = (startAudioIndex * patternBeatsLength) * pixelSecond;
//
//        for (int i = startAudioIndex; x < maxX; i++) {
//            x = (i * patternBeatsLength) * pixelSecond;
//            g.drawLine(x, bounds.y, x, maxY);
//        }

    }

    @Override
    public void marqueeSelectionPerformed(SelectionMarquee marquee) {
        // ignore as this panel does not handle this event
    }

    @Override
    public void paintNavigatorView(Graphics2D g2d) {
//        int pixelSecond = timeState.getPixelSecond();
//
//        int patternBeatsLength = layerGroup.getAudioBeatsLength();
//        int patternWidth = patternBeatsLength * pixelSecond;
//        
//        for (int i = 0; i < layerGroup.getSize(); i++) {
//            int y = i * Layer.LAYER_HEIGHT;
//            int x = 0; 
//            AudioLayer layer = (AudioLayer) layerGroup.getLayerAt(i);
//            AudioData data = layer.getAudioData();
//
//            g2d.setColor(PATTERN_COLOR);
//
//            for (int j = 0; x < getBounds().getMaxX(); j++) {
//                x = (j * patternBeatsLength) * pixelSecond;
//                if (data.isAudioSet(j)) {
//                    //System.out.println("pattern set: " + j);
//                    g2d.fillRect(x, y, patternWidth, Layer.LAYER_HEIGHT);
//                }
//            }
//        }
    }
}