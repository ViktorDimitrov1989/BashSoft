package IO;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class OutputWriter{
    public static void writeMessage(String message){
        System.out.print(message);
    }

    public static void writeMessageOnNewLine(String message){
        System.out.println(message);
    }

    public static void writeEmptyLine(){
        System.out.println();
    }

    public static void displayException(String message){
        System.out.println(message);
    }

    public static void traverseDirectory(String path){
        LinkedList<File> subFolders = new LinkedList<File>();
        File root = new File(path);

        subFolders.add(root);

        while (!subFolders.isEmpty()){
            File currentFolder = subFolders.removeFirst();

            if(currentFolder.listFiles() != null){
                for (File file: currentFolder.listFiles()){
                    if(file.isDirectory()){
                        for (File fileInDir: file.listFiles()){
                            subFolders.add(fileInDir);
                        }
                    }
                }
            }
            System.out.println(currentFolder.toString());
        }
    }

    public static void printStudent(String name, List<Integer> marks){
        String output = String.format("%s - %s", name, marks.toString());
        OutputWriter.writeMessageOnNewLine(output);
    }
}
