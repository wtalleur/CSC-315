package com.wtalleur;

import com.wtalleur.controls.LifeControls;
import com.wtalleur.life.LifePanel;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;

import javax.swing.*;
import java.awt.*;

/**
 * @author William Talleur
 * @date April 7, 2015
 */
public class LifeApp extends JFrame {

    private static final long serialVersionUID = -6549768299360735847L;

    private LifePanel lifePanel;
    private LifeControls lifeControls;

    public LifeApp() {
        /* Setup frame */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.GAME_CONTAINER_SIZE);
        setCenteredTitle(Constants.GAME_TITLE);
        setResizable(false);
        setCenteredFrame();

		/* The application consists of a main frame, lifePanel (contains lifeGrid), and lifeControls */
        lifePanel = new LifePanel();
        lifeControls = new LifeControls(lifePanel);

        getContentPane().add(lifePanel, BorderLayout.CENTER);
        getContentPane().add(lifeControls, BorderLayout.EAST);
        setVisible(true);
    }

    /**
     * Centers the title on a swing GUI.
     *
     * @param title The title to center
     */
    public void setCenteredTitle(String title) {
        Font font = new Font("System", Font.PLAIN, 16);
        FontMetrics metrics = getFontMetrics(font);
        int xCoord = metrics.stringWidth(title);
        int yCoord = metrics.stringWidth(" ");
        int zCoord = ((getWidth() / 2) - (xCoord / 2)) - 40;
        int width = zCoord / yCoord;
        String padding = "";
        padding = String.format("%" + width + "s", padding);
        setTitle(padding + title);
    }

    /**
     * Centers the frame on all operating systems based on screen dimensions.
     */
    public void setCenteredFrame() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int xCoord = (int) ((dimension.getWidth() - getWidth()) / 2);
        int yCoord = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(xCoord, yCoord);
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new LifeApp();
    }
}