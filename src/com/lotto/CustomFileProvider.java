package com.lotto;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
/**Tworzenie nowego pliku*/
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
    Path path =  Paths.get(this.path + filename);
    try{
      if(Files.notExists(path)){
        Files.createFile(path);
      }
      Files.write(path, input, StandardCharsets.UTF_8, option);
    }catch  (IOException e){
      e.printStackTrace();
    }
  }
  public List<String> createDataFormFile(String fileName){
/**buffered to klasa czytają znak po zank z uwzglednieenm konca linii*/
    BufferedReader bufferedReader;
    List<String> output = new ArrayList<>();
    try {
      bufferedReader = new BufferedReader(new FileReader(path + fileName));
      output.add("Początek kopiowania");
      String line = bufferedReader.readLine();
      while (line != null) {
        output.add(line);
        line=bufferedReader.readLine();
      }
      output.add("Koniec kopiowania");
      bufferedReader.close(); ///////
    } catch (IOException e) {
      e.printStackTrace();
    }
    return output;
  }
//  private void getPagebyUrl(String input){
//    URL url = new URL(input);
//  }
}
