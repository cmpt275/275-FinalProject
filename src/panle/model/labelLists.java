package panle.model;
import java.util.List;
import java.util.ArrayList;

public class labelLists {
        private static List<Label> Labels;

        public labelLists() {
            this.Labels = new ArrayList<Label>();
        }
        public void insertLabel(Label label){
            Labels.add(label);
        }
        public static int findLabelsCounts(int row, int col, int year, int month){
            int labelCounts = 0;
//            System.out.println("lable" +row +"，"+col);
            for(Label label:Labels){
//                System.out.println("lableList" + label.getCol());
                if(label.getYear() == year && label.getMonth() == month && label.getCol() == col && label.getRow() == row){
//                    System.out.println("lableList" + label.getRow() +"，"+label.getCol());
                    labelCounts ++;
//                    System.out.println(labelCounts);
                }
            }
            return labelCounts;
        }
        public static Label[] findLabels(int row, int col, int year, int month) {
            ArrayList<Label> rtn=new ArrayList<Label>();
            for (Label label : Labels) {
                if (label.getYear() == year && label.getMonth() == month && label.getCol() == col && label.getRow() == row) {
                    rtn.add(label);
                }
            }
            Label[] rtnLabel=new Label[rtn.size()];
            rtn.toArray(rtnLabel);
            return rtnLabel;
        }

        public static Label[] getLabels(){
            ArrayList<Label> rtn=new ArrayList<Label>();
            for (Label label : Labels) {
                    rtn.add(label);
            }
            Label[] rtnLabel=new Label[rtn.size()];
            rtn.toArray(rtnLabel);
            return rtnLabel;
        }

        public static void deleteLabel(int row, int col, int year, int month, String topic) {
            for (Label label : Labels) {
                if(label.getYear() == year && label.getMonth() == month && label.getCol() == col && label.getRow() == row && label.getLabelTopic()==topic ){
                    Labels.remove(label);
                    return;
                }
            }
        }

    }
