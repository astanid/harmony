package com.reisal78.app.view.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Igor Simagin on 21.03.2016.
 */
public class StatusPanel extends JPanel {

    private JLabel currentCo2 = new JLabel();
    private JLabel currentSpeed = new JLabel();
    private JLabel status = new JLabel();

    public StatusPanel() {
        setLayout(new GridLayout(3, 1));
        add(new JLabel("Текущее CO2: "));
        add(currentCo2);

        add(new JLabel("Текущаяя скорость: "));
        add(currentSpeed);

        add(new JLabel("Текущий статус: "));
        add(status);
    }

    public void setCurrentCo2(int co2) {
        currentCo2.setText(String.valueOf(co2));
    }

    public void setCurrentSpeed(int speed) {
        currentSpeed.setText(String.valueOf(speed));
    }

    public void setStatus(boolean status) {
        if (status) {
            this.status.setText("On");
        } else {
            this.status.setText("Off");
        }
    }
}
