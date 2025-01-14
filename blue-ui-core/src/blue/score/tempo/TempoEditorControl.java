/*
 * TempoEditorControl.java
 *
 * Created on March 6, 2008, 9:28 PM
 */
package blue.score.tempo;

import blue.ui.components.IconFactory;
import blue.score.tempo.Tempo;
import blue.soundObject.PolyObject;
import java.awt.Dimension;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author  syi
 */
public class TempoEditorControl extends javax.swing.JPanel {

    Vector listeners = null;
    
    boolean closed = true;
    private Tempo tempo;
    private PolyObject pObj;

    /** Creates new form TempoEditorControl */
    public TempoEditorControl() {
        initComponents();
    }
    public void setTempo(Tempo tempo) {
        this.tempo = null;
        
        this.useTempoCheckBox.setSelected(tempo.isEnabled());
        setTempoVisible(tempo.isVisible());
        this.tempo = tempo;
    }
    
    public void setPolyObject(PolyObject pObj) {
        this.pObj = pObj;
        setVisible(this.pObj.isRoot());
    }
    
    public void setTempoVisible(boolean tempoVisible) {
        if (tempoVisible) {
            this.setPreferredSize(new Dimension(1, 100));
            this.setSize(this.getWidth(), 100);
        } else {
            this.setPreferredSize(new Dimension(1, 20));
            this.setSize(this.getWidth(), 20);
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layerHeightButton = new javax.swing.JButton();
        useTempoCheckBox = new javax.swing.JCheckBox();

        layerHeightButton.setIcon(IconFactory.getDownArrowIcon());
        layerHeightButton.setFocusPainted(false);
        layerHeightButton.setFocusable(false);
        layerHeightButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        layerHeightButton.setMaximumSize(new java.awt.Dimension(17, 16));
        layerHeightButton.setPreferredSize(new java.awt.Dimension(17, 16));
        layerHeightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                layerHeightButtonActionPerformed(evt);
            }
        });

        useTempoCheckBox.setText("Use Tempo");
        useTempoCheckBox.setBorder(null);
        useTempoCheckBox.setFocusPainted(false);
        useTempoCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useTempoCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(useTempoCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(layerHeightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layerHeightButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(useTempoCheckBox)
        );
    }// </editor-fold>//GEN-END:initComponents
    private void layerHeightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_layerHeightButtonActionPerformed
        if(this.tempo != null) {
            tempo.setVisible(!tempo.isVisible());
            setTempoVisible(tempo.isVisible());
        }
    }//GEN-LAST:event_layerHeightButtonActionPerformed

    private void useTempoCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useTempoCheckBoxActionPerformed
        if(this.tempo != null) {
            tempo.setEnabled(useTempoCheckBox.isSelected());
        }
}//GEN-LAST:event_useTempoCheckBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton layerHeightButton;
    private javax.swing.JCheckBox useTempoCheckBox;
    // End of variables declaration//GEN-END:variables
}
