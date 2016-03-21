package com.reisal78.app.view.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Igor Simagin on 21.03.2016.
 */
public class LogPanel extends JPanel {
    private JLabel label = new JLabel();

    public LogPanel() {

        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setFont(new Font(label.getFont().getName(), Font.PLAIN, label.getFont().getSize()));
        label.setVerticalAlignment(JLabel.TOP);

        JScrollPane scrollPane = new JScrollPane(label);
        scrollPane.setPreferredSize(new Dimension(400, 400));

        add(scrollPane);
    }

    public void setLogList(List<String> logList) {
        StringBuilder builder = new StringBuilder();
        builder.append("<html>");
        for (int i = logList.size()-1; i >= 0 ; i--) {
            builder.append(logList.get(i) + "<br>");
        }
        builder.append("</html>");
        label.setText(builder.toString());

    }
}
