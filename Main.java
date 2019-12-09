// package edu.gcccd.csis;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * Finds the largest file using DFS.
 */
public class Main {

    /**
     * If no start location is given, the we start the search in the current dir
     *
     * @param args {@link String}[] start location for the largest file search.
     */
    public static void main(final String[] args) {
        final Path path = Paths.get(args.length < 1 ? "." : args[0]);
        try {
        System.out.println("Please enter the path of the folder: \t");
        Scanner input = new Scanner(System.in);
        final File ex = findExtremeFile(Paths.get(new URI("file:///" + input.nextLine())));
        input.close();
        System.out.printf("Starting at : %s, the largest file was found here:\n%s\n its size is: %d\n",
                path.toAbsolutePath().toString(),
                ex.getAbsolutePath(),
                ex.length());
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Identifies the more extreem of two given files. Modifying this method
     * allows to search for other extreems, like smallest, oldest, etc.
     *
     * @param f1 {@link File} 1st file
     * @param f2 {@link File} 2nd file
     * @return {@link File} the more extreme of the two given files.
     */
    static File extreme(final File f1, final File f2) {
        if (f1.length() > f2.length()) {
            return f1;
        } else {
            return f2;
        }
    }

    /**
     * DFS for the most extreme file, starting the search at a given directory
     * path.
     *
     * @param p {@link Path} path to a directory
     * @return {@link File} most extreme file in the given path
     */
    static File findExtremeFile(final Path path) {
       File extFile = null; 
        final LinkedList<File> stack = new LinkedList<>(); 
        stack.push(path.toFile());
        
        while (! stack.isEmpty()){
            File dir = stack.pop();
            final File[] fileArr = dir.listFiles();
            if (fileArr == null) {
                continue; 
            }

            for(File x : fileArr){
                if(x.isDirectory()) { 
                    stack.push(x);
                    continue;
                }
                
                if(extFile != null) {
                    extFile = extreme(extFile, x);
                }
                else {
                    extFile =  x; 
                }
            }
        }
        return extFile;
    }
}
