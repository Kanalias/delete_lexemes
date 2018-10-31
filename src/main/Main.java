package main;

import gui.GUIMain;

import javax.swing.*;

class Main{

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUIMain();
            }
        });
    }
}
