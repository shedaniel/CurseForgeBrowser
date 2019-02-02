package me.shedaniel.ui;

import javax.swing.*;
import java.awt.event.*;

public class LoadingDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonAbort;
    private JLabel text;
    private JLabel message;
    
    public LoadingDialog(String message) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonAbort);
        
        this.message.setText(message);
        buttonAbort.addActionListener(actionEvent -> onCancel());
        
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }
    
    public JLabel getText() {
        return text;
    }
    
    public JLabel getMessage() {
        return message;
    }
    
    private void onCancel() {
        dispose();
        System.exit(0);
    }
}
