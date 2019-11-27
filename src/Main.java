import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {

    String path = "H:\\Projects".replaceAll("\\\\", "/");
    // Читал, что слэши от виндоувс убирают кроссплатформенность, поэтому заменил их
    long size = 0;

    try {
      File folder = new File(path);
      if (folder.isDirectory()) {
        //size += getSizeOld(folder);
        size += getSize(path);
        convertToReadability(size);
      } else {
        System.out.println("Указанный путь: " + path + " не ведет к папке.");
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private static long getSize(String path) {

    long size = 0;

    try {
      List<Long> collect = Files.walk(Paths.get(path))
          .filter(Files::isRegularFile)
          .map(Path::toFile)
          .map(File::length)
          .collect(Collectors.toList());

      for (Long element : collect) {
        size += element;
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    return size;
  }

  private static long getSizeOld(File folder) {

    long size = 0;
    try {
      File[] files = folder.listFiles();
      for (File file : files) {
        if (file.isDirectory()) {
          size += getSizeOld(file);
        } else {
          size += file.length();
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return size;
  }

  private static void convertToReadability(long size) {

    if (size < Math.pow(2, 10)) {
      System.out.println("Размер всех файлов в папке равен: " + size + " байт");
    } else if (size < Math.pow(2, 20)) {
      System.out.println("Размер всех файлов в папке равен: " + new DecimalFormat("#0.00")
          .format(size / Math.pow(2, 10)) + " килобайт");
    } else if (size < Math.pow(2, 30)) {
      System.out.println("Размер всех файлов в папке равен: " + new DecimalFormat("#0.00")
          .format(size / Math.pow(2, 20)) + " мегабайт");
    } else if (size < Math.pow(2, 40)) {
      System.out.println("Размер всех файлов в папке равен: " + new DecimalFormat("#0.00")
          .format(size / Math.pow(2, 30)) + " гигабайт");
    } else if (size < Math.pow(2, 50)) {
      System.out.println("Размер всех файлов в папке равен: " + new DecimalFormat("#0.00")
          .format(size / Math.pow(2, 40)) + " терабайт");
    } else if (size < Math.pow(2, 60)) {
      System.out.println("Размер всех файлов в папке равен: " + new DecimalFormat("#0.00")
          .format(size / Math.pow(2, 50)) + " петабайт");
    } else {
      System.out.println("Очень много");
    }
  }
}
