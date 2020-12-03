package panle;

import jdk.jfr.Event;
import panle.model.Label;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Events extends CalendarProgram{
    static JCheckBox checkbox;
    static filesystem fs;
    JLabel tpl;
    Events() throws IOException {
        JLabel label = new JLabel("<html><font color=red size=4><b>WARNING!</b></html>");
        label.setBounds(0,0,100,100);

        checkbox = new JCheckBox();
        pnlCalendar.add(checkbox);
        checkbox.setText("Enable Add Events");
        checkbox.setSelected(true);
        checkbox.setBounds(750, 25, 150, 25);

       // notepanel.add(label);
        readFromFile();
        //Make frame visible
        //frmMain.setResizable(false);
       // frmMain.setVisible(true);
        tblCalendar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tblCalendar.rowAtPoint(evt.getPoint());
                int col = tblCalendar.columnAtPoint(evt.getPoint());
//                int month =
                if (row >= 0 && col >= 0&& !checkbox.isSelected()) {
                    // TODO: handle cell click ........
//                    System.out.println(row +"，"+col);
                    insertLabel(row,col);
                    //readLabel(row,col, currentYear, currentMonth,"topic", "text");
                }else if((row >= 0 && col >= 0 && checkbox.isSelected())){

                   // int selectedyear = Integer.parseInt((String) cmbYear.getItemAt(cmbYear.getSelectedIndex()));
                   // int selectedmonth= findmonth(lblMonth.getText());
                    int con = labelLists.findLabelsCounts(row,col,currentYear,currentMonth);
                    Label[] lbs =  labelLists.findLabels(row,col,currentYear,currentMonth);
                    //System.out.println(selectedmonth);
                    //System.out.println(con);
                    for(int i = 0;i<con;i++){
                        String topic = lbs[i].getLabelTopic();
                        String txt = lbs[i].getLabelNotes();
                         tpl = new JLabel(topic);
                        tpl.setBounds(0,0,100,100);
                        notepanel.add(tpl);
                        frmMain.setVisible(true);
                        System.out.println(i);
                    }

                }

            }

        });

    }
    public int findmonth(String m){
        int i;
        String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        for( i =0;i< months.length;i++){
            if(m == months[i]){
                return i+1;
            }
        }
        return 0;
    }

    public void readFromFile()throws IOException {
        fs = new filesystem();
        fs.createFile("test.txt");
        fs.BufferedReaderDemo("test.txt");
        for(int i = 0; i < fs.count;i++){
            String[] tokens = fs.content[i].split("[|]");
            //   2020|12|1|1|topic1|text1
            readLabel(Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3]),Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),tokens[4],tokens[5]);
        }
    }
    void readLabel(int row, int col,int ye, int m,String topic, String text) {

        String labelTopic = topic;
        String labelNote = text;
        Label label = new Label(labelTopic,labelNote,ye,m,row,col);
        labelLists.insertLabel(label);
        System.out.println("readLable");
    }

}
