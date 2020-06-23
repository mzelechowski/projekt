package com.lotto;

import com.sun.jdi.connect.spi.Connection;

import javax.swing.text.Document;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    URL oracle = null;
    try {
      oracle = new URL("https://www.lotto.pl/lotto/wyniki-i-wygrane");
      BufferedReader in = new BufferedReader(
              new InputStreamReader(oracle.openStream()));
      String inputLine;
      Pattern p = Pattern.compile("scoreline-item circle" data-v-([a-zA-Z0-9]+)>\\s+([0-9][0-9]|[0-9])");
      while ((inputLine = in.readLine()) != null)
        System.out.println(inputLine);

      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }



  }
}

//scoreline-item circle" data-v-([a-zA-Z0-9]+)>\s+([0-9][0-9]|[0-9])
//  Pattern p = Pattern.compile("<img [^>]*src=[\"|']([^(\"|')]+)[\"|'][^>]*>");
//  Matcher m = p.matcher(cnt);
//while(m.find()) {
//        System.out.println(m.group(1));
//        }
//        String in = "num 123 num 1 num 698 num 19238 num 2134";
//        Pattern p = Pattern.compile(".*num ([0-9]+)");
//        Matcher m = p.matcher(in);
//        if(m.find()) {
//        System.out.println(m.group(1));
//        }