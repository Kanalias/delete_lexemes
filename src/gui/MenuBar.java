package gui;

import deleting.DeletingTemplates;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class MenuBar extends JMenuBar {

    private String fileName = "";
    private TextArea textArea;
    private JFrame frame;

    MenuBar(JFrame frame, TextArea textArea){
        this.frame = frame;
        this.textArea = textArea;

        JMenu menuFile = new JMenu("File");
        JMenu menuEdit = new JMenu("Edit");
        add(menuFile);
        add(menuEdit);

        processItemFileOpen(menuFile);
        processItemFileSave(menuFile);
        menuFile.addSeparator();
        processItemProgramExit(menuFile);

        processItemCut(menuEdit);
        processItemCopy(menuEdit);
        processItemPaste(menuEdit);
        processItemDelete(menuEdit);
        menuEdit.addSeparator();
        processItemSelectAll(menuEdit);
        menuEdit.addSeparator();
        processItemDeletingTemplates(menuEdit);
    }

    private void saveText(){
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(fileName);
            textArea.write(fileWriter);
            fileWriter.close();
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(frame, "Error saving file");
            return;
        }
    }

    private void createSaveDialog(){
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Preservation");
        jFileChooser.setFileFilter(new FileChooserFilter());
        int result = jFileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            if (fileName.equals("")) {
                fileName = jFileChooser.getSelectedFile().toString();
                if (!fileName.endsWith(".txt")) {
                    fileName += ".txt";
                }
            }
            saveText();
            textArea.setFlagChange(false);
        }
    }

    private void openFile(){
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Opening");
        jFileChooser.setFileFilter(new FileChooserFilter());
        int result = jFileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION){
            FileReader fileReader;
            try {
                fileName = jFileChooser.getSelectedFile().toString();
                fileReader = new FileReader(fileName);
                textArea.read(fileReader, null);
                fileReader.close();
            } catch (IOException e1){
                JOptionPane.showMessageDialog(frame, "Error reading file. " +
                        "This file may not exist.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
    }

    void exitProgram(){
        if (textArea.isFlagChange()) {
            int response = JOptionPane.showConfirmDialog(frame, "Save changes?",
                    "Save", JOptionPane.YES_NO_CANCEL_OPTION);
            switch (response){
                case JOptionPane.YES_OPTION:
                    createSaveDialog();
                    if(!textArea.isFlagChange()) {
                        frame.dispose();
                    }
                    break;
                case JOptionPane.NO_OPTION:
                    frame.dispose();
                    break;
            }
        } else {
            frame.dispose();
        }
    }

    private void processItemFileOpen(JMenu menuFile){
        JMenuItem itemOpenFile = new JMenuItem("Open file", KeyEvent.VK_O);
        itemOpenFile.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        menuFile.add(itemOpenFile);

        itemOpenFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textArea.isFlagChange()) {
                    int response = JOptionPane.showConfirmDialog(null, "Save changes?",
                            "Save", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (response == JOptionPane.YES_OPTION){
                        createSaveDialog();
                        if (!textArea.isFlagChange()) {
                            openFile();
                        }
                    } else if (response == JOptionPane.NO_OPTION){
                        openFile();
                    }
                } else {
                    openFile();
                }

            }
        });
    }

    private void processItemFileSave(JMenu menuFile){
        JMenuItem itemSaveFile = new JMenuItem("Save file", KeyEvent.VK_S);
        itemSaveFile.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        menuFile.add(itemSaveFile);

        itemSaveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileName.length() == 0){
                    createSaveDialog();
                } else {
                    saveText();
                    textArea.setFlagChange(false);
                }

            }
        });
    }

    private void processItemProgramExit(JMenu menuFile){
        JMenuItem itemExit = new JMenuItem("Exit");
        menuFile.add(itemExit);

        itemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitProgram();
            }
        });
    }

    private void processItemCut(JMenu menuEdit){
        JMenuItem itemСut = new JMenuItem("Cut out", KeyEvent.VK_X);
        itemСut.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        menuEdit.add(itemСut);

        itemСut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.cut();
            }
        });
    }

    private void processItemCopy(JMenu menuEdit){
        JMenuItem itemCopy = new JMenuItem("Copy", KeyEvent.VK_C);
        itemCopy.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        menuEdit.add(itemCopy);

        itemCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.copy();
            }
        });
    }

    private void processItemPaste(JMenu menuEdit){
        JMenuItem itemPaste = new JMenuItem("Insert", KeyEvent.VK_V);
        itemPaste.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        menuEdit.add(itemPaste);

        itemPaste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.paste();
            }
        });
    }

    private void processItemDelete(JMenu menuEdit){
        JMenuItem itemDelete = new JMenuItem("Del");
        menuEdit.add(itemDelete);

        itemDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.replaceSelection("");
            }
        });
    }

    private void processItemSelectAll(JMenu menuEdit){
        JMenuItem itemSelectAll = new JMenuItem("Select all", KeyEvent.VK_A);
        itemSelectAll.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        menuEdit.add(itemSelectAll);

        itemSelectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.selectAll();
            }
        });
    }

    private void processItemDeletingTemplates(JMenu menuEdit){
        JMenuItem itemDeletingTemplates = new JMenuItem("Delete words");
        menuEdit.add(itemDeletingTemplates);

        itemDeletingTemplates.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String response = JOptionPane.showInputDialog(frame, "Enter the words to be deleted, separated by a space.",
                        "Input", JOptionPane.INFORMATION_MESSAGE);
                if(response != null) {
                    if (response.length() == 0) {
                        JOptionPane.showMessageDialog(frame, "You have not entered a single word.",
                                "Warning", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        DeletingTemplates deletingTemplates = new DeletingTemplates(textArea.getText(), response);
                        textArea.setText(deletingTemplates.getFinalText());
                    }
                }
            }
        });
    }

}
