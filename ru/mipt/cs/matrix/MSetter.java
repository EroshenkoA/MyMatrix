package ru.mipt.cs.matrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by 1 on 18.03.14.
 */
public class MSetter {
    public static void consoleSet(Matrix M, int rows, int columns){
        M.setMatrix(rows,columns);
        int i=1;int j=1;
        Scanner s=new Scanner(System.in);
        while (i<=rows){
            while (j<=columns){
                M.set(i, j, s.nextDouble());
                j++;
            }
            j=1;
            i++;
        }
    }
    public static void dialogSet(Matrix M, int rows, int columns){
        M.setMatrix(rows,columns);
        Dialog d=new Dialog(rows,columns,M);
    }
    public static void fileSet(Matrix M){//not ready
        try {
            FileInputStream fileStream = new FileInputStream("test.txt");
            Scanner scanner = new Scanner(fileStream);
            ArrayList<String> lines = new ArrayList();
            int t=0;
            int s=0;
            int i=0;
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
                if (i==0) {
                    t= countSpace(lines.get(0));
                }else{
                    if (countSpace(lines.get(i))!=t){
                        System.out.println("file matrix is disproportional");
                    }
                }
                i++;
            }
            int j=0;
            int k=0;
            M.setMatrix(i, t + 1);
            for (j=0;j<i;j++){
                for (k=0; k<t+1; k++){
                    M.set(j + 1, k + 1, 1);//////////////////////////here is the place, i can't read double from string
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static int countSpace(String s){
        int t=0;
        for(int i=0; i < s.length(); i++) {
            if(s.charAt(i) == ' ') {
                t++;
                if ((i>0)&&(s.charAt(i-1)==' ')){
                    t--;
                }//count result from "asd asdsd         asdasd" must be 2
            }
        }
        return t;
    }
    private static class Dialog extends JFrame {
        public Dialog(int rows,int columns,final Matrix m){
            super("matrix input");
            final JTextField field[][] = new JTextField[rows][columns];
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
                            m.set(i + 1, j + 1, Double.parseDouble(field[i][j].getText()));
                            //field[i][j].requestFocus();
                        }
                    }
                    m.printConsole();
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
    // AP: про файлы матрице знать не зачем - это лучше в лргой класс вынести

}
