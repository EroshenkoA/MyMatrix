package ru.mipt.cs.matrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by 1 on 14.03.14.
 */
public class Dialog extends JFrame{
    public Matrix m;
    public Dialog(int rows,int columns){
        super("matrix input");
        m=new Matrix(rows,columns);
        final JTextField field[][] = new JTextField[rows][columns];
        //JPanel panels[][] = new JPanel[rows][columns];
        try  {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e) {
        }
        setSize(400, 400);
        Container c = getContentPane();
        JPanel centerPanel = new JPanel(new GridLayout(rows, columns));
        int i=0; int j=0;
        for (i=0; i<rows; i++){
            for (j=0; j<columns; j++){
                field[i][j]=new JTextField(5);
                centerPanel.add(field[i][j]);
            }
        }
        centerPanel.setBorder(BorderFactory.createEtchedBorder());
        JButton btn = new JButton("Save");
        c.add(centerPanel, BorderLayout.CENTER);
        c.add(btn, BorderLayout.SOUTH);
        btn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    int i=0; int j=0;
                    for (i=0; i<m.rows; i++){
                        for (j=0; j<m.columns; j++){
                            m.Set(i + 1, j + 1, Double.parseDouble(field[i][j].getText()));
                            //field[i][j].requestFocus();
                        }
                    }
                    m.PrintConsole();
                }
            });
        WindowListener wndCloser = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(wndCloser);

        setVisible(true);
    }
}
