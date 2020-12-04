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
        //Make frame visible
        //frmMain.setResizable(false);
       // frmMain.setVisible(true);
        tblCalendar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tblCalendar.rowAtPoint(evt.getPoint());
                int col = tblCalendar.columnAtPoint(evt.getPoint());
//                int month =
                if (row >= 0 && col >= 0&& checkbox.isSelected()) {
                    // TODO: handle cell click ........
//                    System.out.println(row +"，"+col);
                    insertLabel(row,col);
                    //readLabel(row,col, currentYear, currentMonth,"topic", "text");
                }else if((row >= 0 && col >= 0 && !checkbox.isSelected())){
                    notepanel.removeAll();
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
                        notepanel.add(tpl);
                        tpl.setBounds(10,10,100,100);

                       // System.out.println(i);
                    }

                }
                frmMain.setVisible(true);

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


}
