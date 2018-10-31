package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUIMain extends JFrame {

    public GUIMain() {
        JFrame frame = new JFrame("Delete templates");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // чтобы процесс завершался после закрытия окна
        frame.setBounds(180, 100, 1000, 600);
        frame.setResizable(false);

        TextArea textArea = new TextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel panelLeft = new JPanel();
        JPanel panelRight = new JPanel();

        Bar bar = new Bar(textArea);
        MenuBar menuBar = new MenuBar(frame, textArea);

        frame.getContentPane().add(BorderLayout.SOUTH, bar);
        frame.getContentPane().add(BorderLayout.WEST, panelLeft);
        frame.getContentPane().add(BorderLayout.EAST, panelRight);
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                menuBar.exitProgram();
            }
        });
    }
}
