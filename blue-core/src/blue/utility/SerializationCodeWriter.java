/*
 * SerializationCodeWriter.java
 *
 * Created on May 13, 2006, 4:25 PM
 */

package blue.utility;

import javax.swing.JFrame;

/**
 * 
 * @author steven
 */
public class SerializationCodeWriter extends javax.swing.JFrame {

    /** Creates new form SerializationCodeWriter */
    public SerializationCodeWriter() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        text1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        text2 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        text1.setColumns(20);
        text1.setRows(5);
        jScrollPane1.setViewportView(text1);

        jButton1.setText("Process");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        text2.setColumns(20);
        text2.setRows(5);
        jScrollPane2.setViewportView(text2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                    .addComponent(jButton1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        String text = text1.getText();
        String[] lines = text.split("\n");
        StringBuilder saveCode = new StringBuilder();
        StringBuilder loadCode = new StringBuilder();

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();

            if (line.length() == 0) {
                continue;
            }

            if (line.indexOf('=') > 0) {
                line = line.substring(0, line.indexOf('='));
            }

            System.out.println(line);

            String[] parts = line.split("\\s");

            String varType = parts[parts.length - 2];
            String varName = parts[parts.length - 1];

            loadCode.append("} else if(nodeName.equals(\"").append(varName)
                    .append("\")) {\n");

            if (varType.equals("String")) {
                saveCode.append("retVal.addElement(\"").append(varName).append(
                        "\").setText(").append(varName).append(");\n");
                loadCode.append("retVal.").append(varName).append(
                        " = nodeVal;\n");
            } else if (varType.equals("boolean")) {
                saveCode.append(
                        "retVal.addElement(XMLUtilities.writeBoolean(\"")
                        .append(varName).append("\", ").append(varName).append(
                                "));\n");
                loadCode.append("retVal.").append(varName).append(
                        " = Boolean.valueOf(nodeVal).booleanValue();\n");
            } else if (varType.equals("int")) {
                saveCode.append("retVal.addElement(XMLUtilities.writeInt(\"")
                        .append(varName).append("\", ").append(varName).append(
                                "));\n");
                loadCode.append("retVal.").append(varName).append(
                        " = Integer.parseInt(nodeVal);\n");
            } else if (varType.equals("float")) {
                saveCode.append("retVal.addElement(XMLUtilities.writeFloat(\"")
                        .append(varName).append("\", ").append(varName).append(
                                "));\n");
                loadCode.append("retVal.").append(varName).append(
                        " = Float.parseFloat(nodeVal);\n");
            } else if (varType.equals("double")) {
                saveCode
                        .append("retVal.addElement(XMLUtilities.writeDouble(\"")
                        .append(varName).append("\", ").append(varName).append(
                                "));\n");
                loadCode.append("retVal.").append(varName).append(
                        " = Double.parseDouble(nodeVal);\n");
            }

        }

        text2.setText(loadCode.toString() + "\n\n" + saveCode.toString());
    }// GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String args[]) {
        GUI.setBlueLookAndFeel();

        SerializationCodeWriter s = new SerializationCodeWriter();
        s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        s.setVisible(true);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea text1;
    private javax.swing.JTextArea text2;
    // End of variables declaration//GEN-END:variables

}
