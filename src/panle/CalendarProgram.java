package panle;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

import entity.Holiday;
import panle.model.Label;
import panle.model.labelLists;

import static Api.holiday.getThisMonthHolidayList;

public class CalendarProgram{
    static JLabel lblMonth, lblYear, eventLabel,topicLabel,descriptionLabel,typeLabel,holidayLabel;
    static JTextField topicTextFiled;
    static JTextArea descriptionTextArea;
    static JButton btnPrev, btnNext;
    static JTable tblCalendar;
    static JComboBox cmbYear,typeList,changeMode;
    static JFrame frmMain;
    static Container pane;
    static DefaultTableModel mtblCalendar; //Table model
    static JScrollPane stblCalendar; //The scrollpane
    static JPanel pnlCalendar;
    static int realYear, realMonth, realDay, currentYear, currentMonth;
    static JPanel notepanel;
    static JPanel labelpanel;
    static String path = "data";
    static JButton cancelButton, confirmButton;
    static labelLists labelLists =new labelLists();
    static filesystem fs;
    static JPanel weatherP;
    static  List<Holiday> holidaysList;
    static Color color, weekendColor,textColor,festivalColor,eventColor,dayColor,todayColor;
    //public static void main (String args[]){
    CalendarProgram() throws Exception {
        //Look and feel
        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (ClassNotFoundException e) {}
        catch (InstantiationException e) {}
        catch (IllegalAccessException e) {}
        catch (UnsupportedLookAndFeelException e) {}
        color = new Color(255,255,255);
        weekendColor = new Color(	3, 218, 198,87);
        textColor = new Color(0,0,0);
        dayColor = new Color(0,0,0);
        eventColor = new Color(	36, 113, 163);
        festivalColor= new Color(55, 0, 179);
        todayColor = new Color(219, 141, 155,87);
        //Prepare frame
        frmMain = new JFrame ("275 Calendar"); //Create frame
        //frmMain.setSize(330, 375); //Set size to 400x400 pixels
        frmMain.setSize(990+300, 750+50+15); //Set size to 400x400 pixels
        pane = frmMain.getContentPane(); //Get content pane
        pane.setLayout(null); //Apply null layout
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close when X is clicked

        //Create controls
        lblMonth = new JLabel ("January");
        lblYear = new JLabel ("Change year:");
        cmbYear = new JComboBox();
        btnPrev = new JButton ("<<");
        btnNext = new JButton (">>");
        mtblCalendar = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
        tblCalendar = new JTable(mtblCalendar);
        stblCalendar = new JScrollPane(tblCalendar);
        pnlCalendar = new JPanel(null);
        notepanel = new JPanel(null);
        weatherP = new JPanel(null);
        //Set border
        //Set new
        pnlCalendar.setBorder(BorderFactory.createTitledBorder("Calendar"));
        notepanel.setBorder(BorderFactory.createTitledBorder("Note"));
        //weatherP.setBorder(BorderFactory.createTitledBorder("Weather"));
        //Register action listeners
        btnPrev.addActionListener(new btnPrev_Action());
        btnNext.addActionListener(new btnNext_Action());
        cmbYear.addActionListener(new cmbYear_Action());

        //Add controls to pane
        pane.add(notepanel);
        pane.add(pnlCalendar);
        pane.add(weatherP);
        pnlCalendar.add(lblMonth);
        pnlCalendar.add(lblYear);
        pnlCalendar.add(cmbYear);
        pnlCalendar.add(btnPrev);
        pnlCalendar.add(btnNext);
        pnlCalendar.add(stblCalendar);

        //Set bounds
        //pnlCalendar.setBounds(0, 0, 320, 335);
        pnlCalendar.setBounds(0, 0, 960, 670);
        lblMonth.setBounds(320+160-lblMonth.getPreferredSize().width/2, 25, 100, 25);
        lblYear.setBounds(780, 305+335, 80, 20);
        cmbYear.setBounds(230+640, 305+335, 80, 20);
        btnPrev.setBounds(10, 25, 50, 25);
        //btnNext.setBounds(260, 25, 50, 25);
        btnNext.setBounds(900, 25, 50, 25);
        //stblCalendar.setBounds(10, 50, 300, 250);
        stblCalendar.setBounds(10, 50, 300+640, 250+335);
        notepanel.setBounds(961,0,300,670);
        weatherP.setBounds(1,670,1260,100);

        labelpanel = new JPanel(null);
        labelpanel.setBorder(BorderFactory.createTitledBorder("Add Event"));
        frmMain.add(labelpanel);
        labelpanel.setBounds(961,0,300,670);
        //Make frame visible
        frmMain.setResizable(false);
        frmMain.setVisible(true);

        setLabelPanel();
        labelpanel.setVisible(false);
        //Get real month/year
        GregorianCalendar cal = new GregorianCalendar(); //Create calendar
        realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
        realMonth = cal.get(GregorianCalendar.MONTH); //Get month
        realYear = cal.get(GregorianCalendar.YEAR); //Get year
        currentMonth = realMonth; //Match month and year
        currentYear = realYear;

        String[] modeStrings = { "Dark Mode", "Light Mode" };
        changeMode = new JComboBox(modeStrings);
        changeMode.setSelectedIndex(1);
        pnlCalendar.add(changeMode);
        changeMode.setBounds(80, 25, 150, 23);
        changeMode.addActionListener(new changeMode_Action());

        //Add headers
        String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
        for (int i=0; i<7; i++){
            mtblCalendar.addColumn(headers[i]);
        }

        tblCalendar.getParent().setBackground(tblCalendar.getBackground()); //Set background

        //No resize/reorder
        tblCalendar.getTableHeader().setResizingAllowed(false);
        tblCalendar.getTableHeader().setReorderingAllowed(false);

        //Single cell selection
        tblCalendar.setColumnSelectionAllowed(true);
        tblCalendar.setRowSelectionAllowed(true);
        tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        //Set row/column count
        tblCalendar.setRowHeight(38+55);
        mtblCalendar.setColumnCount(7);
        mtblCalendar.setRowCount(6);

        //Populate table
        for (int i=realYear-100; i<=realYear+100; i++){
            cmbYear.addItem(String.valueOf(i));
        }
        holidaysList = getThisMonthHolidayList(String.valueOf(realYear),String.valueOf(realMonth+1));
        readFromFile();
        //Refresh calendar
        System.out.println("refreshCalendar1");
        refreshCalendar (realMonth, realYear); //Refresh calendar
    }
    public void setLabelPanel(){

        typeLabel = new JLabel("Event Tyep:");
        typeLabel.setBounds(20,50,150,25);
        labelpanel.add(typeLabel);
        String[] typeStrings = { "Normal", "Festival" };
        typeList = new JComboBox(typeStrings);
        typeList.setSelectedIndex(0);
        typeList.setBounds(100, 50, 80, 20);
        labelpanel.add(typeList);


//        petList.addActionListener(this);



        topicLabel = new JLabel("Event Topic:");
        topicLabel.setBounds(20,75,150,25);
        labelpanel.add(topicLabel);
        topicTextFiled = new JTextField();
        topicTextFiled.setBounds(100,75,180,25);
        topicTextFiled.setBackground(Color.white);
        labelpanel.add(topicTextFiled);

        descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(20,150,150,25);
        labelpanel.add(descriptionLabel);

        descriptionTextArea = new JTextArea();
        descriptionTextArea.setBounds(100,150,180,180);

        labelpanel.add(descriptionTextArea);
        Border border = BorderFactory.createLineBorder(Color.lightGray);
        descriptionTextArea.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        confirmButton = new JButton("Confirm");
        confirmButton.setBounds(50, 400,80,25);
        labelpanel.add(confirmButton);


        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(180, 400,80,25);
        labelpanel.add(cancelButton);
    }

    public static void refreshCalendar(int month, int year){
        //Variables
        String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int nod, som; //Number Of Days, Start Of Month
        //Allow/disallow buttons
        btnPrev.setEnabled(true);
        btnNext.setEnabled(true);
        if (month == 0 && year <= realYear-10){btnPrev.setEnabled(false);} //Too early
        if (month == 11 && year >= realYear+100){btnNext.setEnabled(false);} //Too late
        lblMonth.setText(months[month]); //Refresh the month label (at the top)
        lblMonth.setBounds(320+160-lblMonth.getPreferredSize().width/2, 25, 180, 25); //Re-align label with calendar
        cmbYear.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box

        //Clear table
        for (int i=0; i<6; i++){
            for (int j=0; j<7; j++){
                mtblCalendar.setValueAt(null, i, j);
                tblCalendar.removeAll();
                tblCalendar.repaint();
            }
        }
        //Get first day of month and number of days
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        som = cal.get(GregorianCalendar.DAY_OF_WEEK);

        //Draw calendar
        int day = 1;
        for (int i=1; i<=nod; i++){
            int row = new Integer((i+som-2)/7);
            int column  =  (i+som-2)%7;
            mtblCalendar.setValueAt(i, row, column);
            Label[] currentLabel = panle.model.labelLists.findLabels(row, column, currentYear, currentMonth);
                if(currentYear == 2020 && currentMonth == 11){
                    for (Holiday holiday : holidaysList){
                        if (Integer.parseInt(holiday.getDate_day())==day){
                            holidayLabel = new JLabel(holiday.getName());
                            Rectangle rect = tblCalendar.getCellRect(row, column,false);
                            int x =  (int)rect.getX();
                            int y =  (int)rect.getY();
                            double height = rect.getHeight();
                            int width = (int)rect.getWidth() ;
                            tblCalendar.add(holidayLabel);
                            holidayLabel.setBounds(x+20,y+65,width-20,40);
                            holidayLabel.setForeground(festivalColor);
                        }
                    }
                }
            if(currentLabel.length > 0){
                //System.out.println(currentLabel.length);
                int labelCounts = 0;
                for(Label res:currentLabel){
                    String topic = res.getLabelTopic();
                    eventLabel = new JLabel(topic);
                    Rectangle rect = tblCalendar.getCellRect(row, column,false);
                    int x =  (int)rect.getX();
                    int y =  (int)rect.getY();
                    double height = rect.getHeight();
                    int width = (int)rect.getWidth() ;
                    if(labelCounts < 4){
                        tblCalendar.add(eventLabel);
                        eventLabel.setBounds(x+20,y-10+labelCounts*20,width-20,40);
                        eventLabel.setForeground(textColor);
                        if(res.getLabelType().equals("Festival")){
                            eventLabel.setForeground(eventColor);
                        }
                    }
                    labelCounts++;
                }
            }
            day++;
        }

        //Apply renderers
        tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), new tblCalendarRenderer());

//        tblCalendar.remove(eventLabel);
    }

    static class tblCalendarRenderer extends DefaultTableCellRenderer{
        public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){

            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            if (column == 0 || column == 6){ //Week-end
                setBackground(weekendColor);
            }
            else{ //Week
                setBackground(color);
            }
            if (value != null){
                if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth && currentYear == realYear){ //Today
                    setBackground(todayColor);
                }
            }

            setBorder(null);
            setForeground(dayColor);
            return this;
        }
    }

    void insertLabel(int row, int col) {

            confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String topic = topicTextFiled.getText();
                    String text = descriptionTextArea.getText();
                    if (!topic.isEmpty() && !text.isEmpty()) {
                        eventLabel = new JLabel(topic);
                        Rectangle rect = tblCalendar.getCellRect(row, col, false);
                        int x = (int) rect.getX();
                        int y = (int) rect.getY();
                        double height = rect.getHeight();
                        int width = (int) rect.getWidth();
                        int year = currentYear;
                        int month = currentMonth;
                        int labelCounts = labelLists.findLabelsCounts(row, col, currentYear, currentMonth);


                        if (labelCounts < 4) {
                            tblCalendar.add(eventLabel);
                            eventLabel.setBounds(x+20,y-10+labelCounts*20,width-20,40);

                            System.out.println();
                            String type = "Normal";
                            if (typeList.getSelectedItem() != null){
                                type = typeList.getSelectedItem().toString();
                                eventLabel.setForeground(textColor);

                            }
                            if(type.equals("Festival")){
                                eventLabel.setForeground(eventColor);
                            }
                            String labelTopic = topic;
                            String labelNote = text;
                            Label label = new Label(labelTopic, labelNote, year, month, row, col, type);
                            labelLists.insertLabel(label);
                        }
                        descriptionTextArea.setText("");
                        topicTextFiled.setText("");
                    }
                    try {
                        writeToFile();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
//        }
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                descriptionTextArea.setText("");
                topicTextFiled.setText("");
                labelpanel.setVisible(false);
                notepanel.setVisible(true);
            }
        });
        frmMain.setVisible(true);
    }


    static class btnPrev_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (currentMonth == 0){ //Back one year
                currentMonth = 11;
                currentYear -= 1;
            }
            else{ //Back one month
                currentMonth -= 1;
            }
            tblCalendar.removeAll();
            tblCalendar.repaint();
            refreshCalendar(currentMonth, currentYear);
        }
    }
    static class btnNext_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (currentMonth == 11){ //Foward one year
                currentMonth = 0;
                currentYear += 1;
            }
            else{ //Foward one month
                currentMonth += 1;
            }
//            tblCalendar.remove(eventLabel);
            tblCalendar.removeAll();
            tblCalendar.repaint();
            refreshCalendar(currentMonth, currentYear);
        }
    }
    static class cmbYear_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            System.out.println(e);
            if (cmbYear.getSelectedItem() != null){
                String b = cmbYear.getSelectedItem().toString();
                currentYear = Integer.parseInt(b);
                System.out.println("refreshCalendar4");
                refreshCalendar(currentMonth, currentYear);
            }
        }
    }

    static void readFromFile()throws IOException {
        fs = new filesystem();
        fs.createFile(path);
        fs.BufferedReaderDemo(path);
        if(fs.count > 0){
            for(int i = 0; i < fs.count;i++){
                String[] tokens = fs.content[i].split("[|]");
                readLabel(Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3]),Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),tokens[4],tokens[5],tokens[6]);
            }
        }
    }
    static void readLabel(int row, int col,int ye, int m,String topic, String text,String type) {

        String labelTopic = topic;
        String labelNote = text;
        Label label = new Label(labelTopic,labelNote,ye,m,row,col,type);
        labelLists.insertLabel(label);
    }

    static void writeToFile() throws IOException {
         //2020|11|1|3|t3|x3

        fs.count = 0;
        for (Label label : labelLists.getLabels()) {
               String[] tokens1 = label.getLabelNotes().split("[\n]");
               String text ="";
              for(int i = 0;i<tokens1.length;i++){
                text = text + " "+tokens1[i];
               }
                String txt = label.getYear()+"|"+label.getMonth()+"|"+label.getRow()+"|"+label.getCol()+"|"+label.getLabelTopic()+"|"+text+"|"+label.getLabelType();
                fs.content[fs.count] = txt;
                fs.count++;

        }
        fs.BufferedWriterDemo(path);
    }

    static class changeMode_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            System.out.println(e);
            if(changeMode.getSelectedItem() != null) {
                System.out.println(changeMode.getSelectedItem());
                if (changeMode.getSelectedItem().equals("Dark Mode")) {
                    color = new Color(18, 18, 18);
                    weekendColor = new Color(51, 41, 64);
                    textColor = new Color(187, 134, 252);
                    festivalColor = new Color(	207, 102, 121);
                    dayColor = new Color(255,255,255);
                    todayColor = new Color(	55, 0, 179);
                    refreshCalendar(currentMonth, currentYear);
                }else if (changeMode.getSelectedItem().equals("Light Mode")){
                    color = new Color(255,255,255);
                    weekendColor = new Color(	3, 218, 198,87);
                    textColor = new Color(0,0,0);
                    dayColor = new Color(0,0,0);
                    eventColor = new Color(	36, 113, 163);
                    festivalColor= new Color(55, 0, 179);
                    todayColor = new Color(	219, 141, 155,87);

                    refreshCalendar(currentMonth, currentYear);

                }
            }

        }
    }
}
