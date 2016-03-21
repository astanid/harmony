package com.reisal78.app.view.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Igor Simagin on 21.03.2016.
 */
public class LogPanel extends JPanel {
    private JList<String> logList = new JList<>();


    public LogPanel() {

        setLayout(new FlowLayout());
        logList.setLayoutOrientation(JList.VERTICAL);
        logList.setSize(new Dimension(300, 400));

        add(logList);
    }

    public void setLogList(List<String> logList) {
            this.logList.setListData(logList.toArray(new String [logList.size()]));
    }
}
