/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Component;
import knn.*;
import nb.*;
        
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Edwin
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DataSetChooser = new javax.swing.JFileChooser();
        DataTestChooser = new javax.swing.JFileChooser();
        AlgorithmOptionGroup = new javax.swing.ButtonGroup();
        MethodOptionGroup = new javax.swing.ButtonGroup();
        ResultPane = new javax.swing.JScrollPane();
        ResultArea = new javax.swing.JTextArea();
        ResultLabel = new javax.swing.JLabel();
        StartButton = new javax.swing.JButton();
        DataSourcePanel = new javax.swing.JPanel();
        ChooseDataSet = new javax.swing.JButton();
        ChooseDataTest = new javax.swing.JButton();
        DataSetPath = new javax.swing.JTextField();
        DataTestPath = new javax.swing.JTextField();
        AlgorithmPanel = new javax.swing.JPanel();
        NBOption = new javax.swing.JRadioButton();
        KNNOption = new javax.swing.JRadioButton();
        AllAlgorithmOption = new javax.swing.JRadioButton();
        KNNValue = new javax.swing.JTextField();
        KLabel = new javax.swing.JLabel();
        MethodPanel = new javax.swing.JPanel();
        FullTrainingOption = new javax.swing.JRadioButton();
        CrossValidationOption = new javax.swing.JRadioButton();
        AllMethodOption = new javax.swing.JRadioButton();
        FoldsValue = new javax.swing.JTextField();
        FoldsLabel = new javax.swing.JLabel();

        DataSetChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DataSetChooserActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Naive Bayes - KNN Simple Interface");
        setName("MainFrame"); // NOI18N
        setResizable(false);

        ResultArea.setEditable(false);
        ResultArea.setColumns(20);
        ResultArea.setRows(5);
        ResultPane.setViewportView(ResultArea);

        ResultLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ResultLabel.setText("Result");

        StartButton.setText("Start");
        StartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartButtonActionPerformed(evt);
            }
        });

        DataSourcePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Source"));

        ChooseDataSet.setText("Choose DataSet");
        ChooseDataSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChooseDataSetActionPerformed(evt);
            }
        });

        ChooseDataTest.setText("Choose DataTest");
        ChooseDataTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChooseDataTestActionPerformed(evt);
            }
        });

        DataSetPath.setEnabled(false);

        DataTestPath.setEnabled(false);
        DataTestPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DataTestPathActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DataSourcePanelLayout = new javax.swing.GroupLayout(DataSourcePanel);
        DataSourcePanel.setLayout(DataSourcePanelLayout);
        DataSourcePanelLayout.setHorizontalGroup(
            DataSourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataSourcePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DataSourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ChooseDataSet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ChooseDataTest, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                .addGroup(DataSourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DataSourcePanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(DataSetPath))
                    .addGroup(DataSourcePanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(DataTestPath)))
                .addContainerGap())
        );
        DataSourcePanelLayout.setVerticalGroup(
            DataSourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataSourcePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DataSourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChooseDataSet)
                    .addComponent(DataSetPath, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(DataSourcePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChooseDataTest)
                    .addComponent(DataTestPath, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AlgorithmPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Algorithm"));

        AlgorithmOptionGroup.add(NBOption);
        NBOption.setSelected(true);
        NBOption.setText("Naive Bayes");
        NBOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NBOptionActionPerformed(evt);
            }
        });

        AlgorithmOptionGroup.add(KNNOption);
        KNNOption.setText("KNN");
        KNNOption.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                KNNOptionStateChanged(evt);
            }
        });
        KNNOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KNNOptionActionPerformed(evt);
            }
        });

        AlgorithmOptionGroup.add(AllAlgorithmOption);
        AllAlgorithmOption.setText("Use all available algorithms");
        AllAlgorithmOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AllAlgorithmOptionActionPerformed(evt);
            }
        });

        KNNValue.setEnabled(false);

        KLabel.setText("K :");

        javax.swing.GroupLayout AlgorithmPanelLayout = new javax.swing.GroupLayout(AlgorithmPanel);
        AlgorithmPanel.setLayout(AlgorithmPanelLayout);
        AlgorithmPanelLayout.setHorizontalGroup(
            AlgorithmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AlgorithmPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AlgorithmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AlgorithmPanelLayout.createSequentialGroup()
                        .addComponent(AllAlgorithmOption)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(AlgorithmPanelLayout.createSequentialGroup()
                        .addComponent(NBOption)
                        .addGap(18, 18, 18)
                        .addComponent(KNNOption, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6)
                        .addComponent(KLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(KNNValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))))
        );
        AlgorithmPanelLayout.setVerticalGroup(
            AlgorithmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AlgorithmPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AlgorithmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NBOption)
                    .addComponent(KNNOption)
                    .addComponent(KNNValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(KLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                .addComponent(AllAlgorithmOption))
        );

        MethodPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Method"));

        MethodOptionGroup.add(FullTrainingOption);
        FullTrainingOption.setSelected(true);
        FullTrainingOption.setText("Full Training");
        FullTrainingOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FullTrainingOptionActionPerformed(evt);
            }
        });

        MethodOptionGroup.add(CrossValidationOption);
        CrossValidationOption.setText("Cross Validation");
        CrossValidationOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrossValidationOptionActionPerformed(evt);
            }
        });

        MethodOptionGroup.add(AllMethodOption);
        AllMethodOption.setText("Use all available methods");
        AllMethodOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AllMethodOptionActionPerformed(evt);
            }
        });

        FoldsValue.setEnabled(false);

        FoldsLabel.setText("Folds :");

        javax.swing.GroupLayout MethodPanelLayout = new javax.swing.GroupLayout(MethodPanel);
        MethodPanel.setLayout(MethodPanelLayout);
        MethodPanelLayout.setHorizontalGroup(
            MethodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MethodPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MethodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AllMethodOption)
                    .addGroup(MethodPanelLayout.createSequentialGroup()
                        .addComponent(FullTrainingOption)
                        .addGap(18, 18, 18)
                        .addComponent(CrossValidationOption)
                        .addGap(10, 10, 10)
                        .addComponent(FoldsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FoldsValue, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        MethodPanelLayout.setVerticalGroup(
            MethodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MethodPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MethodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FullTrainingOption)
                    .addComponent(CrossValidationOption)
                    .addComponent(FoldsValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FoldsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AllMethodOption))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ResultLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DataSourcePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(AlgorithmPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(MethodPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(StartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ResultPane, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(DataSourcePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AlgorithmPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MethodPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(StartButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addComponent(ResultLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ResultPane, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DataSetChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DataSetChooserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DataSetChooserActionPerformed

    private void ChooseDataSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChooseDataSetActionPerformed
        // TODO add your handling code here:
        
        int returnVal = DataSetChooser.showOpenDialog(this);
        
        //clear ResultArea
        try {
            ResultArea.getDocument().remove(0,ResultArea.getDocument().getLength());
        } catch (BadLocationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // process selected file
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = DataSetChooser.getSelectedFile();
            DataSetPath.setText(file.getAbsolutePath());
        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_ChooseDataSetActionPerformed

    private void ChooseDataTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChooseDataTestActionPerformed
        // TODO add your handling code here:
        int returnVal = DataTestChooser.showOpenDialog(this);
        
        //clear ResultArea
        try {
            ResultArea.getDocument().remove(0,ResultArea.getDocument().getLength());
        } catch (BadLocationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // process selected file
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = DataTestChooser.getSelectedFile();
            DataTestPath.setText(file.getAbsolutePath());
        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_ChooseDataTestActionPerformed

    private void DataTestPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DataTestPathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DataTestPathActionPerformed

    private void StartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartButtonActionPerformed
        // TODO add your handling code here:
        
        PrintStream StandardOut;
        
        //set printstream to ResultArea
        PrintStream printStream = new PrintStream(new CustomOutputStream(ResultArea));
        StandardOut = System.out;
        System.setOut(printStream);
        System.setErr(printStream);

        //print result to ResultArea
        String[] argv = new String[6];
        argv[0] = DataSetPath.getText(); // data set file path
        argv[1] = DataTestPath.getText(); // data test file path
        argv[2] = this.getSelectedButtonText(AlgorithmOptionGroup); // algorithm option
        argv[3] = this.getSelectedButtonText(MethodOptionGroup); // method option
        argv[4] = KNNValue.getText(); // KNN's K value
        argv[5] = FoldsValue.getText(); // Cross Validation's folds value 
        
        //check options validity
        int result = checkOptionsValidity(argv);
        
        if(result == 1){
            try {
                //clear ResultArea
                try {
                    ResultArea.getDocument().remove(0,ResultArea.getDocument().getLength());
                } catch (BadLocationException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }

                //execute
                Driver.main(argv);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_StartButtonActionPerformed

    private void KNNOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KNNOptionActionPerformed
        // TODO add your handling code here:
        KNNValue.setEnabled(true);
    }//GEN-LAST:event_KNNOptionActionPerformed

    private void KNNOptionStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_KNNOptionStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_KNNOptionStateChanged

    private void NBOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NBOptionActionPerformed
        // TODO add your handling code here:
        KNNValue.setEnabled(false);
    }//GEN-LAST:event_NBOptionActionPerformed

    private void AllAlgorithmOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AllAlgorithmOptionActionPerformed
        // TODO add your handling code here:
        KNNValue.setEnabled(true);
        if(KNNValue.getText().isEmpty()){
            KNNValue.setText("3");
        }
    }//GEN-LAST:event_AllAlgorithmOptionActionPerformed

    private void FullTrainingOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FullTrainingOptionActionPerformed
        // TODO add your handling code here:
        FoldsValue.setEnabled(false);
        ChooseDataTest.setEnabled(true);
    }//GEN-LAST:event_FullTrainingOptionActionPerformed

    private void AllMethodOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AllMethodOptionActionPerformed
        // TODO add your handling code here:
        FoldsValue.setEnabled(true);
        if(FoldsValue.getText().isEmpty()){
            FoldsValue.setText("10");
        }
        ChooseDataTest.setEnabled(true);
    }//GEN-LAST:event_AllMethodOptionActionPerformed

    private void CrossValidationOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrossValidationOptionActionPerformed
        // TODO add your handling code here:
        FoldsValue.setEnabled(true);
        ChooseDataTest.setEnabled(false);
        DataTestPath.setText("");
    }//GEN-LAST:event_CrossValidationOptionActionPerformed

    /*Additional method*/
    //get selected radio button
    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }
    
    // check if all options are  inserted correctly
    public int checkOptionsValidity(String[] argv){
        int result = 1;
        // check data set path
        if(argv[0].isEmpty()){
            JOptionPane.showMessageDialog(this,"Error, data set path is empty !","Error Data Set",JOptionPane.ERROR_MESSAGE);
            result = 0;
        }
        //check data test path
        if(argv[3].equals("Full Training") || argv[3].equals("Use all available methods")){
            if(argv[1].isEmpty()){
                JOptionPane.showMessageDialog(this,"Error, data test path is empty !","Error Data Test",JOptionPane.ERROR_MESSAGE);
                result = 0;
            }
        }
        // check KNN's K value
        if(argv[2].equals("KNN") || argv[2].equals("Use all available algorithms")){
            if(argv[4].isEmpty()){
                JOptionPane.showMessageDialog(this,"Error, KNN's K value is empty !","Error KNN's K value",JOptionPane.ERROR_MESSAGE);
                result = 0;
            }  
        }
        // check folds value
        if(argv[3].equals("Cross Validation") || argv[3].equals("Use all available algorithms")){
            if(argv[5].isEmpty()){
                JOptionPane.showMessageDialog(this,"Error, folds value is empty !","Error folds value",JOptionPane.ERROR_MESSAGE);
                result = 0;
            }  
        }
        return result;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup AlgorithmOptionGroup;
    private javax.swing.JPanel AlgorithmPanel;
    private javax.swing.JRadioButton AllAlgorithmOption;
    private javax.swing.JRadioButton AllMethodOption;
    private javax.swing.JButton ChooseDataSet;
    private javax.swing.JButton ChooseDataTest;
    private javax.swing.JRadioButton CrossValidationOption;
    private javax.swing.JFileChooser DataSetChooser;
    private javax.swing.JTextField DataSetPath;
    private javax.swing.JPanel DataSourcePanel;
    private javax.swing.JFileChooser DataTestChooser;
    private javax.swing.JTextField DataTestPath;
    private javax.swing.JLabel FoldsLabel;
    private javax.swing.JTextField FoldsValue;
    private javax.swing.JRadioButton FullTrainingOption;
    private javax.swing.JLabel KLabel;
    private javax.swing.JRadioButton KNNOption;
    private javax.swing.JTextField KNNValue;
    private javax.swing.ButtonGroup MethodOptionGroup;
    private javax.swing.JPanel MethodPanel;
    private javax.swing.JRadioButton NBOption;
    private javax.swing.JTextArea ResultArea;
    private javax.swing.JLabel ResultLabel;
    private javax.swing.JScrollPane ResultPane;
    private javax.swing.JButton StartButton;
    // End of variables declaration//GEN-END:variables
}