package backend;

import java.io.*;

public class FileHandler {

    public static void save(Transaction t){

        try{
            BufferedWriter bw =
                    new BufferedWriter(
                    new FileWriter("transactions.txt",true));

            bw.write(t.toString());
            bw.newLine();
            bw.close();

        }catch(Exception e){
            System.out.println("File Error");
        }
    }
}