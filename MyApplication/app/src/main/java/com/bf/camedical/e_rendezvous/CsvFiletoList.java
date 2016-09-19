package com.bf.camedical.e_rendezvous;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class CsvFiletoList{
    private InputStream inputStream;

    public CsvFiletoList(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public ArrayList<String[]> read(){
        ArrayList<String[]> resultList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            int i=1;
            while ((csvLine = reader.readLine()) != null) {
                if(i>1) {
                    String[] row = csvLine.split(";");
                    resultList.add(row);
                }
                i++;
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+e);
            }
        }
        return resultList;
    }
}
