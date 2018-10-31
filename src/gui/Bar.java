package gui;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;
import java.awt.*;

class Bar extends JPanel {

    private JLabel countRow;
    private JLabel countColumn;

    Bar(TextArea textArea){
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        JLabel nameRow = new JLabel("Row");
        countRow = new JLabel("1");
        JLabel nameColumn = new JLabel("Column");
        countColumn = new JLabel("1");
        add(nameRow);
        add(countRow);
        add(nameColumn);
        add(countColumn);

        textArea.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                try {
                    int caretPos = textArea.getCaretPosition();
                    int rowNum = (caretPos == 0) ? 1 : 0;
                    for (int offset = caretPos; offset > 0; ) {
                        offset = Utilities.getRowStart(textArea, offset) - 1;
                        rowNum++;
                    }
                    int offset = Utilities.getRowStart(textArea, caretPos);
                    int colNum = caretPos - offset + 1;

                    countRow.setText(String.valueOf(rowNum));
                    countColumn.setText(String.valueOf(colNum));
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
