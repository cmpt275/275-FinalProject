package panle.model;


public class Label {

    private  String labelTopic;
    private static String labelNotes;
    private int year;
    private int month;
    private int row;
    private int col;

    public Label(String labelTopic, String labelNotes, int year, int month, int row, int col) {
        this.labelTopic = labelTopic;
        this.labelNotes = labelNotes;
        this.year = year;
        this.month = month;
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getMonth() {
        return month;
    }

    public String getLabelNotes() {
        return labelNotes;
    }

    public int getYear() {
        return year;
    }

    public String getLabelTopic() {
        return labelTopic;
    }
}
