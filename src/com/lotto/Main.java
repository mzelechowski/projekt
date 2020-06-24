package com.lotto;

import com.sun.jdi.connect.spi.Connection;

import javax.swing.text.Document;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String pathName = "C:/!!baza/";
        String fileName = "bazalotto.txt";
        String fileName1 = "bazalotto12.txt";
        String fileName2 = "bazalotto3.txt";
        String fileName3 = "bazalotto3zpliku.txt";
        String fileName4 = "lotekWyniki.txt";
        CustomFileProvider customFileProvider = new CustomFileProvider(pathName);
        customFileProvider.readerFileBasic(fileName);

        ///////////////////// Zapis do pliku
        customFileProvider.writeFileBasic(fileName1);

        ///////////////////zapis listy do pliku
        List<String> wpis = new ArrayList<>();
        wpis.add("Maciek#duzylotek#5,8,12,18,20,31");
        wpis.add("Maciek#duzylotek#1,2,3,4,5,6");
        wpis.add("Maciek#duzylotekplus#1,2,3,4,5,6");
        customFileProvider.createFileFromCollection(fileName2, wpis, StandardOpenOption.APPEND);
        customFileProvider.createFileFromCollection(fileName3, customFileProvider.createDataFormFile(fileName), StandardOpenOption.APPEND);

//////////////////////////////
        URL httpPath = null;
        try {
            httpPath = new URL("https://www.lotto.pl/lotto/wyniki-i-wygrane");
            BufferedReader in = new BufferedReader(new InputStreamReader(httpPath.openStream()));
            String inputLine;
            Pattern pLotto = Pattern.compile("<div class=\"scoreline-item circle\" data-v-([a-zA-Z0-9]+)>");
            Pattern pName = Pattern.compile("<p class=\"result-item__name\" data-v-([a-zA-Z0-9]+)>(Lot*[a-zA-Z\\s]+)</p>");
            Pattern pLosNumber = Pattern.compile("<p class=\"result-item__number\" data-v-([a-zA-Z0-9]+)>\\d{4}</p>");
            Pattern pEnd=Pattern.compile("</div> </div> <!----> <!----></div> <!----></div>");
            Pattern pData = Pattern.compile("\\d{1,2}\\.\\d{1,2}\\.\\d{4}");
            PrintWriter writer = new PrintWriter(new FileWriter(pathName + fileName4));
            int linia = 0;
            String outputLine = "";
            String dataLos="";
            while ((inputLine = in.readLine()) != null) {
                Matcher mData = pData.matcher(inputLine);
                if (mData.find()) {
                    dataLos=mData.group(0);
                    outputLine = dataLos;
                }
                /// znajdywanie nazwy
                Matcher mName = pName.matcher(inputLine);
                if (mName.find()) {
                    String pomName = "";
                    if (mName.group(0).contains("Lotto Plus")) {
                        //writer.println("Lotto Plus");
                        pomName = System.getProperty("line.separator")+dataLos +"#Lotto Plus#";
                    } else if (mName.group(0).contains("Lotto")) {
                        // writer.println("Lotto");
                        pomName = "#Lotto#";
                    }
                    outputLine = outputLine + pomName;
                }
                ////Znajdowanie numeru losowania
                Matcher mNumber = pLosNumber.matcher(inputLine);
                if (mNumber.find()) {
                    String pom = mNumber.group(0);
                    pom = pom.replaceAll("<p class=\"result-item__number\" data-v-[a-zA-Z0-9]+>", "");
                    pom = pom.replaceAll("</p>", "");
                    //writer.println(pom);
                    if(outputLine.endsWith("#"))
                            outputLine = outputLine + pom+"#";
                }
                ////znajdowanie liczb wylosowanych
                Matcher mLotto = pLotto.matcher(inputLine);
                if (mLotto.find()) {
                    inputLine = in.readLine();
                    for (int i = 0; i < 50; i++) inputLine = inputLine.replace(" ", "");
                    //writer.println(inputLine);
                    if(!outputLine.endsWith("#"))
                        outputLine = outputLine + ",";
                    outputLine = outputLine  + inputLine;
                }
                /////////////// send to file
                Matcher mEnd = pEnd.matcher(inputLine);
                if (mEnd.find()) {
                    writer.println(outputLine);
                    //System.out.println(outputLine);
                    outputLine="";
                }
                //writer.println(inputLine);
            }
            in.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}