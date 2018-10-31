package gui;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class FileChooserFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.getName().endsWith(".txt")){
            return  true;
        }
        if (f.isDirectory()){
            return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return ".txt";
    }
}
