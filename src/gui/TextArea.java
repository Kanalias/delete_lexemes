package gui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

class TextArea extends JTextArea {

    private boolean flagChange = false;

    TextArea(){
        processTextArea();
    }

    private void processTextArea(){
        setLineWrap(true);
        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                flagChange = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                flagChange = true;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    boolean isFlagChange() {
        return flagChange;
    }

    void setFlagChange(boolean flagChange) {
        this.flagChange = flagChange;
    }
}
