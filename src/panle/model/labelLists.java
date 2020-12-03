package panle.model;
import java.util.List;
import java.util.ArrayList;

public class labelLists {
        private static List<Label> Labels;

        public labelLists() {
            this.Labels = new ArrayList<Label>();
        }
        public void insertLabel(Label label){
            System.out.println("insertLabel col" + label.getCol());
            System.out.println("insertLabel row" + label.getRow());
            Labels.add(label);
        }
        public static int findLabels(int row, int col, int year, int month){
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
        public void display() {
            for (Label label : Labels) {
                System.out.println("col" + label.getCol());
                System.out.println("row" + label.getRow());

            }
        }
    }
