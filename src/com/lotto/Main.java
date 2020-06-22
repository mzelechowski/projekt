package com.lotto;

public class Main {

  public static void main(String[] args) {
    String pathName = "D:/!!!!!!_PROGRAMOWANIE/projekt/storage/";
    String fileName = "bazalotto.txt";
    String fileName1 = "bazalotto.txt";
    CustomFileProvider customFileProvider = new CustomFileProvider(pathName);
    customFileProvider.readerFileBasic(fileName);

    ///////////////////// Zapis do pliku
    customFileProvider.writeFileBasic(fileName1);


  }
}
