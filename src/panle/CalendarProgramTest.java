package panle;

import org.junit.jupiter.api.Test;
import panle.model.Label;
import panle.model.labelLists;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalendarProgramTest {
    static filesystem fs;
    static String path = "data";
    static labelLists labelLists =new labelLists();

    @Test
    void test() throws IOException {
        readFromFile();
        int count = labelLists.findLabelsCounts(1,5,2020,11);
        assertEquals(4 ,count);

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

    @Test
    void findLabelstest() throws IOException {
        readFromFile();
        ArrayList<Label> expect=new ArrayList<Label>();
        Label[] rtn = labelLists.findLabels(2,5,2020,11);
        assertEquals(4,rtn.length);
    }

}