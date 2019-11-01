package VPLPluPlusCore.utils;

import java.io.File;
import java.io.FilenameFilter;

public class Files {

  public static final String EXTENSION_CLASS = "class";
  public static final String EXECUTION_PATH = System.getProperty("user.dir");

  public static File[] getFiles(String basePath, String fileExtension) {
    return Files.getFiles(basePath, new String[]{fileExtension});
  }

  public static File[] getFiles(File mountFolder, String fileExtension) {
    return Files.getFiles(mountFolder, new String[]{fileExtension});
  }

  public static File[] getFiles(String basePath, String[] filesByExtension) {
    return Files.getFiles(new File(basePath), filesByExtension);
  }

  public static File[] getFiles(File folderMount, String[] filesByExtension) {

    if (folderMount == null) {
      folderMount = new File(Files.EXECUTION_PATH);
    }

    System.out.println("Listing files from: " + folderMount);

    //if extensiones wasnt especified then return true
    if (filesByExtension == null || filesByExtension.length == 0) {
      return folderMount.listFiles();
    }

    //else filter by extension
    return folderMount.listFiles(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        System.out.println("  |..." + name);

        String[] parts = name.split("\\.");
        for (byte i = 0; (i < filesByExtension.length); i++) {
          if (filesByExtension[i].equalsIgnoreCase(parts[parts.length - 1])) {
            return true;
          }
        }
        return false;
      }
    });

  }

}
