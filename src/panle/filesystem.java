package panle;

import java.io.*;

public class filesystem {

    public String[] content;
    public int count;
    public void createFile(String filename) throws IOException {
        File file=new File(filename);
        if(!file.exists())
            file.createNewFile();


    }

    public void BufferedReaderDemo(String path) throws IOException{
        content = new String[20];
        File file=new File(path);
        if(!file.exists()||file.isDirectory())
            throw new FileNotFoundException();
        BufferedReader br=new BufferedReader(new FileReader(file));
        String temp=null;
        temp=br.readLine();
        count = 0;
        while(temp!=null && count <20){
            content[count] = (temp);
            temp=br.readLine();
            count++;
        }

    }

    public void BufferedWriterDemo(String path) throws IOException{
        File file=new File(path);
        if(!file.exists()||file.isDirectory())
            throw new FileNotFoundException();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for(int i = 0;i<count;i++){
            bw.write(content[i]+"\n");
        }
        bw.flush();
        bw.close();

    }
}
