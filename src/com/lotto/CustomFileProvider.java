package com.lotto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.OpenOption;
import java.util.List;

public class CustomFileProvider {
  private final String path;

  public CustomFileProvider(String path) {
    this.path = path;
  }

  public void readerFileBasic(String fileName) {
    BufferedReader bufferedReader;
    try {
//      FileReader fileReader = new FileReader(path + fileName);
//      bufferedReader = new BufferedReader(fileReader);
//       nie tworzymy fileReader tylko tworzymy ANONIOMOWO bezpośrednio w BufferedReader bo już więcej niepotrzebujemy  fileReader
      bufferedReader = new BufferedReader(new FileReader(path + fileName));
      String line = bufferedReader.readLine();
      while (line != null) {
        System.out.println(line);
        line = bufferedReader.readLine();
      }
      bufferedReader.close(); ///////////// zamykanie strumienia plików

    } catch (IOException e) {
      //System.out.println(e.getMessage());
      System.out.println("\n\n!!! error !!! - sprawdź poprawność sciężki\n\n");
      e.printStackTrace(); //drukuje stack błędu
    }
     }

  public void writeFileBasic(String fileName) {
    File file = new File(path + fileName);

    try {
      if (file.createNewFile()) {
        System.out.println("file created: " + file.getAbsolutePath());
      }else{
        System.out.println("Nie utworzo pliku");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  /** zapisywanie do pliku z podanie wartosci w formie Listr<String> */
  public void createFileFromCollection(String filename, List<String> input, OpenOption option){

  }
}
