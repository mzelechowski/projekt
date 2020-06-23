package com.lotto;

public class Main {

  public static void main(String[] args) {
    String pathName = "C:/!!baza/";
    String fileName = "bazalotto.txt";
    String fileName1 = "bazalotto12.txt";
    CustomFileProvider customFileProvider = new CustomFileProvider(pathName);
    customFileProvider.readerFileBasic(fileName);

    ///////////////////// Zapis do pliku
    customFileProvider.writeFileBasic(fileName1);

  }
}
