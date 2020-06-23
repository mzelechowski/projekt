package com.lotto;

import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    String pathName = "C:/!!baza/";
    String fileName = "bazalotto.txt";
    String fileName1 = "bazalotto12.txt";
    String fileName2 = "bazalotto3.txt";
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

  }
}
