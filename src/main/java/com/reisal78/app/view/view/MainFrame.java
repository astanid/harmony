package com.reisal78.app.view.view;

import com.reisal78.app.service.dto.DataContainer;
import com.reisal78.app.view.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;

/**
 * Created by Astanid on 21.03.2016.
 */
public class MainFrame extends JFrame implements Observer {

    StatusPanel statusPanel = new StatusPanel();

    public MainFrame(String title, WindowListener windowListener) throws HeadlessException {
        super(title);
        setSize(600, 400);
        setVisible(true);
        JPanel mainPanel = new JPanel(new BorderLayout(5,5));
        setContentPane(mainPanel);

        JPanel leftPanel = new JPanel(new FlowLayout());
        mainPanel.add(leftPanel, BorderLayout.WEST);
        leftPanel.add(statusPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(windowListener);
    }

    @Override
    public void update(DataContainer data) {
        statusPanel.setCurrentCo2(data.getCurrentCo2());
        statusPanel.setCurrentSpeed(data.getCurrentSpeed());
        statusPanel.setStatus(data.isStatus());
    }
}
