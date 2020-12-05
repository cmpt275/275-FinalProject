package panle;

import jdk.jfr.Event;
import panle.model.Label;
import panle.model.labelLists;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Events extends CalendarProgram{
    static JCheckBox checkbox;
    static JLabel tpl;
    JLabel cpl;
    Events() throws IOException {
        JLabel label = new JLabel("<html><font color=red size=4><b>WARNING!</b></html>");
        label.setBounds(0,0,100,100);

        checkbox = new JCheckBox();
        pnlCalendar.add(checkbox);
        checkbox.setText("Enable Add Events");
        checkbox.setSelected(false);
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
                if (row >= 0 && col >= 0&& checkbox.isSelected()) {
                    // TODO: handle cell click ........
//                    //System.out.println(row +"，"+col);
                    labelpanel.setVisible(true);
                    notepanel.setVisible(false);
                    labelpanel.repaint();
                    insertLabel(row,col);
                    //readLabel(row,col, currentYear, currentMonth,"topic", "text");
                }else if((row >= 0 && col >= 0 && !checkbox.isSelected())){
                    notepanel.setVisible(true);
                    labelpanel.setVisible(false);
                    notepanel.removeAll();
                    notepanel.repaint();
                    int con = labelLists.findLabelsCounts(row,col,currentYear,currentMonth);
                    Label[] lbs =  labelLists.findLabels(row,col,currentYear,currentMonth);
                    //System.out.println(selectedmonth);
                    //System.out.println(con);
                    for(int i = 0;i<con;i++){
                        String topic = lbs[i].getLabelTopic();
                        String txt = lbs[i].getLabelNotes();
                        JLabel tpl = new JLabel("<html><font color=black size=4><body><p>"+txt+"</p></body></html>\"");
                        notepanel.add(tpl);
                        tpl.setBounds(10,10+i*162,280,150);
                        tpl.setBorder(BorderFactory.createTitledBorder("<html><font color=blue size=5><h>"+topic+"</h></html>\""));
                        JButton deleteButton = new JButton("Delete Event");
                        notepanel.add(deleteButton);
                        deleteButton.setBounds(125,125+i*162,150,25);
                        deleteButton.addActionListener(new delete(row,col,currentYear,currentMonth,topic,labelLists));

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
    static class delete implements ActionListener {
        private int row,col,year,month;
        private String topic;
        panle.model.labelLists labelLists;


        public delete(int row,int col,int year, int month,String topic,panle.model.labelLists labelLists) {
            this.row = row;
            this.col = col;
            this.year = year;
            this.month = month;
            this.topic = topic;
            this.labelLists = labelLists;
        }

        public void actionPerformed (ActionEvent e) {
            System.out.println(topic);
            labelLists.deleteLabel(row,col,currentYear,currentMonth,topic);

            try {
                writeToFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            
            tblCalendar.removeAll();
            tblCalendar.repaint();
            refreshCalendar(currentMonth, currentYear);

            notepanel.removeAll();
            notepanel.repaint();
            int con = labelLists.findLabelsCounts(row,col,currentYear,currentMonth);
            Label[] lbs =  labelLists.findLabels(row,col,currentYear,currentMonth);
            for(int i = 0;i<con;i++){
                String topic = lbs[i].getLabelTopic();
                String txt = lbs[i].getLabelNotes();
                JLabel tpl = new JLabel("<html><font color=black size=4><body><p>"+txt+"</p></body></html>\"");
                notepanel.add(tpl);
                tpl.setBounds(10,10+i*162,280,150);
                tpl.setBorder(BorderFactory.createTitledBorder("<html><font color=blue size=5><h>"+topic+"</h></html>\""));
                JButton deleteButton = new JButton("Delete Event");
                notepanel.add(deleteButton);
                deleteButton.setBounds(125,125+i*162,150,25);
                deleteButton.addActionListener(new delete(row,col,currentYear,currentMonth,topic,labelLists));

                // System.out.println(i);
            }


        }
    }

}
