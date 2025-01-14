/*
 * blue - object composition environment for csound
 * Copyright (c) 2000-2007 Steven Yi (stevenyi@gmail.com)
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
package blue.soundObject.editor.jmask;

import blue.soundObject.jmask.Quantizer;
import javax.swing.SpinnerNumberModel;

/**
 * 
 * @author steven
 */
public class QuantizerEditor extends javax.swing.JPanel implements DurationSettable {
    private Quantizer quantizer;

    public QuantizerEditor(Quantizer quantizer) {
        initComponents();
        
        gridSizeTableEditor.setTable(quantizer.getGridSizeTable());
        strengthTableEditor.setTable(quantizer.getStrengthTable());
        strengthTableEditor.setMinMaxEnabled(false);
        offsetTableEditor.setTable(quantizer.getOffsetTable());
        
        gridSizeSpinner.setModel(
                new SpinnerNumberModel(quantizer.getGridSize(), Double.MIN_VALUE,
                Double.POSITIVE_INFINITY, 0.1));
        strengthSpinner.setModel(
                new SpinnerNumberModel(quantizer.getStrength(), 0.0,
                1.0, 0.1));
        offsetSpinner.setModel(
                new SpinnerNumberModel(quantizer.getOffset(), 0.0,
                Double.POSITIVE_INFINITY, 0.1));
                
        gridSizeTypeComboBox.setSelectedIndex(quantizer.isGridSizeTableEnabled() ? 1 : 0);
        strengthTypeComboBox.setSelectedIndex(quantizer.isStrengthTableEnabled() ? 1 : 0);
        offsetTypeComboBox.setSelectedIndex(quantizer.isOffsetTableEnabled() ? 1 : 0);       
        
        this.quantizer = quantizer;
        
        updateDisplay();
    }

    private void updateDisplay() {
        gridSizeSpinner.setVisible(!this.quantizer.isGridSizeTableEnabled());
        gridSizeTableEditor.setVisible(this.quantizer.isGridSizeTableEnabled());
        strengthSpinner.setVisible(!this.quantizer.isStrengthTableEnabled());
        strengthTableEditor.setVisible(this.quantizer.isStrengthTableEnabled());
        offsetSpinner.setVisible(!this.quantizer.isOffsetTableEnabled());
        offsetTableEditor.setVisible(this.quantizer.isOffsetTableEnabled());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gridSizeButtonGroup = new javax.swing.ButtonGroup();
        strengthButtonGroup = new javax.swing.ButtonGroup();
        offsetButtonGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        gridSizeTableEditor = new blue.soundObject.editor.jmask.TableEditor();
        strengthSpinner = new javax.swing.JSpinner();
        strengthTableEditor = new blue.soundObject.editor.jmask.TableEditor();
        gridSizeSpinner = new javax.swing.JSpinner();
        offsetSpinner = new javax.swing.JSpinner();
        offsetTableEditor = new blue.soundObject.editor.jmask.TableEditor();
        gridSizeTypeComboBox = new javax.swing.JComboBox();
        strengthTypeComboBox = new javax.swing.JComboBox();
        offsetTypeComboBox = new javax.swing.JComboBox();

        jLabel1.setText("Quantizer");

        strengthSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                strengthSpinnerStateChanged(evt);
            }
        });

        gridSizeSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                gridSizeSpinnerStateChanged(evt);
            }
        });

        offsetSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                offsetSpinnerStateChanged(evt);
            }
        });

        gridSizeTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Grid Size (Constant)", "Grid Size (Table)" }));
        gridSizeTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gridSizeTypeComboBoxActionPerformed(evt);
            }
        });

        strengthTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Strength (Constant)", "Strength (Table)" }));
        strengthTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                strengthTypeComboBoxActionPerformed(evt);
            }
        });

        offsetTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Offset (Constant)", "Offset (Table)" }));
        offsetTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                offsetTypeComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(strengthTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(strengthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(gridSizeTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gridSizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addComponent(gridSizeTableEditor, javax.swing.GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)
                    .addComponent(strengthTableEditor, javax.swing.GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(offsetTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(offsetSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(offsetTableEditor, javax.swing.GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {gridSizeTypeComboBox, offsetTypeComboBox, strengthTypeComboBox});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(gridSizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gridSizeTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gridSizeTableEditor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(strengthTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(strengthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(strengthTableEditor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(offsetTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(offsetSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(offsetTableEditor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void strengthSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_strengthSpinnerStateChanged
        if (this.quantizer != null) {
            this.quantizer.setStrength(((Double)strengthSpinner.getValue()).doubleValue());
        }
}//GEN-LAST:event_strengthSpinnerStateChanged

    private void gridSizeSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_gridSizeSpinnerStateChanged
        if (this.quantizer != null) {
            this.quantizer.setGridSize(((Double)gridSizeSpinner.getValue()).doubleValue());
        }
}//GEN-LAST:event_gridSizeSpinnerStateChanged

    private void offsetSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_offsetSpinnerStateChanged
        if (this.quantizer != null) {
            this.quantizer.setOffset(((Double)offsetSpinner.getValue()).doubleValue());
        }
}//GEN-LAST:event_offsetSpinnerStateChanged

private void gridSizeTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gridSizeTypeComboBoxActionPerformed
    if(this.quantizer != null) {
        this.quantizer.setGridSizeTableEnabled(gridSizeTypeComboBox.getSelectedIndex() == 1);
        updateDisplay();
    }
}//GEN-LAST:event_gridSizeTypeComboBoxActionPerformed

private void strengthTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_strengthTypeComboBoxActionPerformed
    if(this.quantizer != null) {
        this.quantizer.setStrengthTableEnabled(strengthTypeComboBox.getSelectedIndex() == 1);
        updateDisplay();
    }
}//GEN-LAST:event_strengthTypeComboBoxActionPerformed

private void offsetTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_offsetTypeComboBoxActionPerformed
    if(this.quantizer != null) {
        this.quantizer.setOffsetTableEnabled(offsetTypeComboBox.getSelectedIndex() == 1);
        updateDisplay();
    }
}//GEN-LAST:event_offsetTypeComboBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup gridSizeButtonGroup;
    private javax.swing.JSpinner gridSizeSpinner;
    private blue.soundObject.editor.jmask.TableEditor gridSizeTableEditor;
    private javax.swing.JComboBox gridSizeTypeComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.ButtonGroup offsetButtonGroup;
    private javax.swing.JSpinner offsetSpinner;
    private blue.soundObject.editor.jmask.TableEditor offsetTableEditor;
    private javax.swing.JComboBox offsetTypeComboBox;
    private javax.swing.ButtonGroup strengthButtonGroup;
    private javax.swing.JSpinner strengthSpinner;
    private blue.soundObject.editor.jmask.TableEditor strengthTableEditor;
    private javax.swing.JComboBox strengthTypeComboBox;
    // End of variables declaration//GEN-END:variables

    public void setDuration(double duration) {
        gridSizeTableEditor.setDuration(duration);
        offsetTableEditor.setDuration(duration);
        strengthTableEditor.setDuration(duration);
    }

}
