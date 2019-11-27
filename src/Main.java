import java.io.File;
import java.text.DecimalFormat;

public class Main {

  public static void main(String[] args) {

    String path = "H:\\Projects".replaceAll("\\\\", "/");
    // Читал, что слэши от виндоувс убирают кроссплатформенность, поэтому заменил их
    long size = 0;

    try {
      File folder = new File(path);
      if (folder.isDirectory()) {
        size += getSize(folder);
        convertToReadability(size);
      } else {
        System.out.println("Указанный путь: " + path + " не ведет к папке.");
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private static long getSize(File folder) {

    long size = 0;
    File[] files = folder.listFiles();

    for (File file : files) {
      if (file.isDirectory()) {
        size += getSize(file);
      } else {
        size += file.length();
      }
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
