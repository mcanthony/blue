/*
 * blue - object composition environment for csound
 * Copyright (c) 2001-2004 Steven Yi (stevenyi@gmail.com)
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

import blue.noteProcessor.NoteProcessorChain;
import blue.score.Score;
import blue.score.layers.LayerGroup;
import blue.soundObject.PolyObject;
import blue.ui.components.IconFactory;
import blue.ui.utilities.UiUtilities;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.WeakHashMap;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

/**
 * Title: blue Description: an object composition environment for csound
 * Copyright: Copyright (c) 2001 Company: steven yi music
 * 
 * @author steven yi
 * @version 1.0
 */
public final class ScoreObjectBar extends JComponent implements ActionListener {

    MouseListener popupListener;

    private final JPopupMenu popup = new ScoreObjectBarPopup();

    WeakHashMap<Score, ScoreBarState> scoreBarList = new WeakHashMap<Score,ScoreBarState>();
    
    ScoreBarState currentScoreBarState = null;
    
    private ScoreBarListener listener = null;

    private static ScoreObjectBar instance = null;

    public static ScoreObjectBar getInstance() {
        if(instance == null) {
            instance = new ScoreObjectBar();
        }
        return instance;
    }
    
    public void setScoreBarListener(ScoreBarListener listener) {
        this.listener = listener;
    }

    public LayerGroup getCurrentLayerGroup() {
        return currentScoreBarState.getCurrentLayerGroupButton().layerGroup;
    }
    
    private ScoreObjectBar() {
        this.setLayout(new javax.swing.BoxLayout(this,
                javax.swing.BoxLayout.X_AXIS));

        popupListener = new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                if (UiUtilities.isRightMouseButton(e)) {
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        };


    }

    public boolean isEditingScore() {
        return currentScoreBarState.layerGroupButtons.size() == 1;
    }
    
    protected void resetNames() {
        for (int i = 0; i < getComponentCount(); i++) {
            LayerGroupButton btn = (LayerGroupButton) getComponent(i);
            btn.resetName();
        }
    }
    
    protected void setScore(Score score) {
        ScoreBarState state = scoreBarList.get(score);
        if(state == null) {
            state = new ScoreBarState();
            state.score = score;
            scoreBarList.put(score, state);
            
            LayerGroupButton btn = new LayerGroupButton(score);
            btn.addActionListener(this);
            btn.addMouseListener(popupListener);
            state.layerGroupButtons.add(btn);
        }
        currentScoreBarState = state;
        
        this.removeAll();
        for(LayerGroupButton btn : state.layerGroupButtons) {
            this.add(btn);
        }
        
        if(listener != null) {
            LayerGroupButton btn = state.getCurrentLayerGroupButton();

            if(btn.getScore() != null) {
                listener.scoreBarScoreSelected(btn.getScore(), btn.xVal, btn.yVal);
            } else {
                listener.scoreBarLayerGroupSelected(btn.getLayerGroup(), btn.xVal, btn.yVal);
            }
        }
        
        this.repaint();
    }

    protected void addLayerGroup(LayerGroup layerGroup) {
        LayerGroupButton btn = findLayerGroup(layerGroup);

        if (btn != null) {
            scoreBarRefocus(btn);
            return;
        }

        btn = new LayerGroupButton(layerGroup);

        btn.addMouseListener(popupListener);

        btn.addActionListener(this);

        if (getComponentCount() > 0) {
            int scrollBarXVal = ScoreTopComponent.findInstance().getHorizontalScrollValue();
            int scrollBarYVal = ScoreTopComponent.findInstance().getVerticalScrollValue();

            LayerGroupButton temp = (LayerGroupButton)getComponent(getComponentCount() - 1);
                    
            temp.setXVal(scrollBarXVal);
            temp.setYVal(scrollBarYVal);
        }

        this.add(btn);
        currentScoreBarState.layerGroupButtons.add(btn);
        this.revalidate();
        this.repaint();
        
        if(listener != null) {
            listener.scoreBarLayerGroupSelected(layerGroup, btn.xVal, btn.yVal);
        }

    }

    private LayerGroupButton findLayerGroup(LayerGroup layerGroup) {
        for (int i = 0; i < getComponentCount(); i++) {
            LayerGroupButton btn = (LayerGroupButton)getComponent(i);
            if (btn.layerGroup == layerGroup) {
                return btn;
            }
        }
        return null;
    }

    void scoreBarRefocus(LayerGroupButton layerGroupButton) {
        
        if(layerGroupButton.getScore() != null) {
            for(int i = getComponentCount() - 1; i > 0; i--) {
                remove(i);
                currentScoreBarState.layerGroupButtons.remove(i);
            }
        } else {
            while(layerGroupButton != currentScoreBarState.getCurrentLayerGroupButton()) {
                int index = getComponentCount() - 1;
                remove(index);
                currentScoreBarState.layerGroupButtons.remove(index - 1);

               //FIXME - remove mouse listener on button
                //tempPObjButton.removeMouseListener(popupListener);

            }            
        }
        
        this.repaint();

        if(listener != null) {

            if(layerGroupButton.getScore() != null) {
                listener.scoreBarScoreSelected(layerGroupButton.getScore(), 
                        layerGroupButton.xVal, layerGroupButton.yVal);
            } else {
                listener.scoreBarLayerGroupSelected(layerGroupButton.getLayerGroup(),
                        layerGroupButton.xVal, layerGroupButton.yVal);
            }
        }
        
    }

    public void actionPerformed(ActionEvent e) {
        scoreBarRefocus((LayerGroupButton) (e.getSource()));
    }

   
    /**
     * Inner JButton Class to hold extra information
     */
    class LayerGroupButton extends JButton {

        int xVal = 0;

        int yVal = 0;

        private LayerGroup layerGroup = null;
        private Score score = null;

        private LayerGroupButton() {
            this.setFocusable(false);
            this.setIcon(IconFactory.getRightArrowIcon());
            this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            this.setBorderPainted(false);
        }
        
        public LayerGroupButton(LayerGroup layerGroup) {
            this();
            this.layerGroup = layerGroup;
            resetName();
        }
        
        public LayerGroupButton(Score score) {
            this();
            this.score = score;
            resetName();
        }

        public void resetName() {
            
            String name = null;
            
            if(this.score != null) {
                name = "root";
                
                if(score.getNoteProcessorChain().size() > 0) {
                    name = "*" + name;
                }
                
                
                for(int i = 0; i < score.getLayerGroupCount(); i++) {
                    LayerGroup group = score.getLayerGroup(i);
                    NoteProcessorChain npc = group.getNoteProcessorChain();
                    
                    if(npc.size() > 0) {
                        name += " [*]";
                        break;
                    }
                }
                
                
            } else if (this.layerGroup != null) {
                name = layerGroup.getName();

                NoteProcessorChain npc = layerGroup.getNoteProcessorChain();
                
                if (npc != null && npc.size() > 0) {
                    name = "*" + name;
                }
                
            }
            
            final String finalName = name;

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    LayerGroupButton.this.setText(finalName);
                }
            });            

        }

        public void setXVal(int xVal) {
            this.xVal = xVal;
        }

        public int getXVal() {
            return xVal;
        }

        public void setYVal(int yVal) {
            this.yVal = yVal;
        }

        public int getYVal() {
            return this.yVal;
        }

        public LayerGroup getLayerGroup() {
            return this.layerGroup;
        }
        
        public Score getScore() {
            return this.score;
        }
        
        public void removeNotify() {
            super.removeNotify();
            this.removeMouseListener(popupListener);
        }
    }
    
    class ScoreObjectBarPopup extends JPopupMenu {
        
        public void show(Component invoker, int x, int y) {
            LayerGroupButton b = (LayerGroupButton) invoker;
            Score score = b.getScore();
            LayerGroup layerGroup = b.getLayerGroup();
            
            this.removeAll();
            
            if(score != null) {
                
                this.add(createLayerGroupMenuItem(score.getNoteProcessorChain()));
                
                this.addSeparator();
                
                for(int i = 0; i < score.getLayerGroupCount(); i++) {
                    LayerGroup group = score.getLayerGroup(i);
                    NoteProcessorChain npc = group.getNoteProcessorChain();
                    
                    String name = "";
                    if(npc.size() > 0) {
                        name += "*";
                    }
                    name += (i + 1) + ") " + group.getName();
                    JMenu menu = new JMenu(name);
                    menu.add(createLayerGroupMenuItem(group.getNoteProcessorChain()));
                    this.add(menu);
                }
                
            } else if (layerGroup != null) {
                this.add(createLayerGroupMenuItem(
                        layerGroup.getNoteProcessorChain()));
            }
            
            
            super.show(invoker, x, y);
        }
        

        private Action createLayerGroupMenuItem(final NoteProcessorChain npc) {
            Action editProperties = new AbstractAction("Edit NoteProcessors") {

            public void actionPerformed(ActionEvent e) {
                    if (npc != null) {
                        // JOptionPane.showMessageDialog(null, "Not yet
                        // implemented.");

                        NoteProcessorDialog npcDialog = NoteProcessorDialog
                                .getInstance();
                        npcDialog.setNoteProcessorChain(npc);
                        npcDialog.show(true);
                        resetNames();
                    }
                }
            };
            return editProperties;
        }
    
    }
    
    static class ScoreBarState {
        public Score score;
        public ArrayList<LayerGroupButton> layerGroupButtons = new ArrayList<LayerGroupButton>();
        
        public LayerGroupButton getCurrentLayerGroupButton() {
            return layerGroupButtons.get(layerGroupButtons.size() - 1);
        }
    }
}