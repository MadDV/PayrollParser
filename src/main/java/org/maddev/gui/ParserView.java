/*
 * Created by JFormDesigner on Tue May 09 11:21:07 CDT 2017
 */

package org.maddev.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

import org.maddev.EmployeeParser;
import org.maddev.MoneyExporter;
import org.maddev.models.Payroll;
import org.maddev.util.DateUtil;

/**
 * @author unknown
 */
public class ParserView extends JFrame {

    private Payroll payroll;

    public ParserView() {
        initComponents();
    }

    private void uploadButtonClicked(ActionEvent e) {

       final String date = dateInput.getText();
       if(date == null || !DateUtil.isValid(date.trim()))
       {
           System.out.println("Invalid date.");
           return;
       }
       JFileChooser chooser = new JFileChooser();
       int value = chooser.showOpenDialog(this);
       if(value != JFileChooser.APPROVE_OPTION) {
           return;
       }
       final File file = chooser.getSelectedFile();
       if(file == null) return;
       final String cachedText = dateInput.getText();
       uploadPayroll.setVisible(false);
       uploadPayrollLabel.setVisible(false);
       selectedDate.setText("Selected Date: " + dateInput.getText());
       selectedDate.setVisible(true);
       dateInput.setVisible(false);
       dateInstructions.setVisible(false);
       dateInstructions2.setVisible(false);
       final EmployeeParser parser = new EmployeeParser(file.getPath());
       this.payroll = parser.parseToPayroll();
        this.payroll.setDate(cachedText);
       totalAmount.setText("Total Amount: $" + payroll.getTotal());
       totalEmployees.setText("Total Employees: " + payroll.getEmployees().size());
       totalAmount.setVisible(true);
       totalEmployees.setVisible(true);
       exportMoneyButton.setVisible(true);
    }

    private void exportMoneyClicked(ActionEvent e) {
        if(this.payroll == null) return;
        new MoneyExporter(this.payroll).exportFile();
        exportMoneyButton.setVisible(false);
        totalEmployees.setVisible(false);
        totalAmount.setVisible(false);
        selectedDate.setVisible(false);
        exportedFileLabel.setText("<html><body>Successfully exported ExportedPayroll.qif onto Desktop<br>please open Microsoft Money<br>and select import in the file menu.</body></html>");
        exportedFileLabel.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Mad Dev
        uploadPayroll = new JButton();
        uploadPayrollLabel = new JLabel();
        totalAmount = new JLabel();
        totalEmployees = new JLabel();
        exportMoneyButton = new JButton();
        exportedFileLabel = new JLabel();
        dateInstructions = new JLabel();
        dateInput = new JTextField();
        dateInstructions2 = new JLabel();
        selectedDate = new JLabel();

        //======== this ========
        setResizable(false);
        setMinimumSize(new Dimension(460, 300));
        setName("Payroll Parser");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- uploadPayroll ----
        uploadPayroll.setText("Upload Payroll");
        uploadPayroll.addActionListener(e -> uploadButtonClicked(e));
        contentPane.add(uploadPayroll);
        uploadPayroll.setBounds(45, 165, 380, 50);

        //---- uploadPayrollLabel ----
        uploadPayrollLabel.setText("Click the button below to upload payroll.");
        contentPane.add(uploadPayrollLabel);
        uploadPayrollLabel.setBounds(90, 125, 275, uploadPayrollLabel.getPreferredSize().height);

        //---- totalAmount ----
        totalAmount.setText("Total Amount: ");
        totalAmount.setVisible(false);
        contentPane.add(totalAmount);
        totalAmount.setBounds(50, 145, 220, 45);

        //---- totalEmployees ----
        totalEmployees.setText("Total Employees:");
        totalEmployees.setVisible(false);
        contentPane.add(totalEmployees);
        totalEmployees.setBounds(50, 185, 220, 45);

        //---- exportMoneyButton ----
        exportMoneyButton.setText("Export To Money File");
        exportMoneyButton.setVisible(false);
        exportMoneyButton.addActionListener(e -> exportMoneyClicked(e));
        contentPane.add(exportMoneyButton);
        exportMoneyButton.setBounds(45, 75, 380, 50);

        //---- exportedFileLabel ----
        exportedFileLabel.setText("text");
        exportedFileLabel.setVisible(false);
        contentPane.add(exportedFileLabel);
        exportedFileLabel.setBounds(35, 35, 395, 210);

        //---- dateInstructions ----
        dateInstructions.setText("Enter a date to upload payroll with. ");
        contentPane.add(dateInstructions);
        dateInstructions.setBounds(85, 30, 315, dateInstructions.getPreferredSize().height);

        //---- dateInput ----
        dateInput.setText("DD/MM/YYYY");
        contentPane.add(dateInput);
        dateInput.setBounds(85, 70, 280, dateInput.getPreferredSize().height);

        //---- dateInstructions2 ----
        dateInstructions2.setText("Be sure to use the DD/MM/YYYY format");
        contentPane.add(dateInstructions2);
        dateInstructions2.setBounds(new Rectangle(new Point(85, 50), dateInstructions2.getPreferredSize()));

        //---- selectedDate ----
        selectedDate.setText("Total Employees:");
        selectedDate.setVisible(false);
        contentPane.add(selectedDate);
        selectedDate.setBounds(55, 215, 220, 45);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Mad Dev
    private JButton uploadPayroll;
    private JLabel uploadPayrollLabel;
    private JLabel totalAmount;
    private JLabel totalEmployees;
    private JButton exportMoneyButton;
    private JLabel exportedFileLabel;
    private JLabel dateInstructions;
    private JTextField dateInput;
    private JLabel dateInstructions2;
    private JLabel selectedDate;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
