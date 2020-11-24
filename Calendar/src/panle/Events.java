package panle;

import jdk.jfr.Event;

import javax.swing.*;

public class Events extends CalendarProgram{

    Events(){
        JLabel label = new JLabel("<html><font color=red size=4><b>WARNING!</b></html>");
        label.setBounds(0,0,100,100);

        tblCalendar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tblCalendar.rowAtPoint(evt.getPoint());
                int col = tblCalendar.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    // TODO: handle cell click ........
                    System.out.println(row +"，"+col);

                }
            }
        });
    }

}
