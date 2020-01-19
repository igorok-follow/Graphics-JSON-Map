package ml.javalearn.app;

import javax.swing.*;
import java.awt.*;

class PopupWindow extends JPanel {

    private JButton showPopup;
    private JTextArea info;
    private boolean checker = true;
    private int width, height;

    PopupWindow(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        setBounds(x, y, width, height);
        setLayout(null);
        popupArea();
    }

    void setText(String str) {
        info.setText(str);
    }

    private void popupArea() {
        ImageIcon rightIterator = new ImageIcon("closed.png");
        ImageIcon downIterator = new ImageIcon("opened.png");

        showPopup = new JButton();
        showPopup.setIcon(downIterator);
        showPopup.setBounds(0, 0, 20, 20);

        info = new JTextArea();
        info.setBounds(30, 0, width - 30, height);
        info.setFont(new Font("Arial", Font.PLAIN, 14));
        info.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        info.setText("area");

        showPopup.addActionListener(e -> {
            if (checker) {
                remove(info);
                showPopup.setIcon(rightIterator);
                checker = false;
                repaint();
            } else {
                add(info);
                showPopup.setIcon(downIterator);
                checker = true;
                repaint();
            }
        });

        add(showPopup);
        add(info);
    }

}