package com.wtalleur.controls;

import com.wtalleur.cell.Generations;
import com.wtalleur.life.LifePanel;
import com.wtalleur.shapes.ShapeSet;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author William Talleur <wtalleur@callutheran.edu>
 * @date Apr 7, 2015
 */
public class LifeControls extends JPanel {

    private static final long serialVersionUID = 2686574310474969808L;

    private JButton startButton;
    private JButton stopButton;
    private JButton stepButton;
    private JButton clearButton;
    private JButton saveButton;
    private JButton loadButton;
    private JFileChooser loadFile;
    private JLabel shapesLabel;
    private JComboBox<ShapeSet> shapes;
    private JLabel generationsLabel;
    private JComboBox<Generations> generations;

    public LifeControls(LifePanel lifePanel) {
        setLayout(new MigLayout("insets 10 10 10 10"));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        startButton = new JButton("Start Game");
        startButton.setPreferredSize(new Dimension(110, 30));
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                lifePanel.startLifeThread();
            }
        });

        stopButton = new JButton("Stop Game");
        stopButton.setPreferredSize(new Dimension(110, 30));
        stopButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                lifePanel.stopLifeThread();
            }
        });

        clearButton = new JButton("Clear Game");
        clearButton.setPreferredSize(new Dimension(110, 30));
        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                lifePanel.resetCells();
                lifePanel.repaint();
            }
        });

        loadButton = new JButton("Load Game");
        loadButton.setPreferredSize(new Dimension(110, 30));
        loadButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                loadFile = new JFileChooser();
                loadFile.setCurrentDirectory(new File("./resources/gamesaves/"));

                int optionSelected = loadFile.showOpenDialog(lifePanel);
                if (optionSelected == JFileChooser.APPROVE_OPTION) {
                    lifePanel.getLifeGrid().load(loadFile.getSelectedFile().getName(), false);
                    lifePanel.repaint();
                }
            }
        });

        saveButton = new JButton("Save Game");
        saveButton.setPreferredSize(new Dimension(110, 30));
        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                lifePanel.getLifeGrid().save("game");
                JOptionPane.showMessageDialog(lifePanel, "Your game was successfully saved, use the load function to view it again.");
            }
        });

        stepButton = new JButton("Next Step");
        stepButton.setPreferredSize(new Dimension(110, 30));
        stepButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                // TODO:
            }
        });

        shapesLabel = new JLabel("Predefined Shapes");

        shapes = new JComboBox<>();
        shapes.setModel(new DefaultComboBoxModel<>(ShapeSet.values()));
        shapes.setPreferredSize(new Dimension(110, 30));
        shapes.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                switch (shapes.getSelectedIndex()) {
                    case 0:
                        lifePanel.addShape(ShapeSet.GLIDER);
                        break;
                    case 1:
                        lifePanel.addShape(ShapeSet.EXPLODER);
                        break;
                    case 2:
                        lifePanel.addShape(ShapeSet.BLINKER);
                        break;
                    case 3:
                        lifePanel.addShape(ShapeSet.PULSAR);
                        break;
                    case 4:
                        lifePanel.addShape(ShapeSet.SPACESHIP);
                        break;
                }
            }
        });

        generationsLabel = new JLabel("Generation Speeds");

        generations = new JComboBox<>();
        generations.setModel(new DefaultComboBoxModel<>(Generations.values()));
        generations.setPreferredSize(new Dimension(110, 30));
        generations.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                switch (generations.getSelectedIndex()) {
                    case 0:
                        lifePanel.setGenerationSpeed(Generations.SLOW.getSpeed());
                        break;
                    case 1:
                        lifePanel.setGenerationSpeed(Generations.FAST.getSpeed());
                        break;
                    case 2:
                        lifePanel.setGenerationSpeed(Generations.HYPER.getSpeed());
                        break;
                }
            }
        });

        add(startButton, "wrap 20");
        add(stopButton, "wrap 20");
        add(clearButton, "wrap 20");
        add(loadButton, "wrap 20");
        add(saveButton, "wrap 20");
        add(stepButton, "wrap 10");
        add(shapesLabel, "wrap 10, align center");
        add(shapes, "wrap 10");
        add(generationsLabel, "wrap 10, align center");
        add(generations, "wrap");
    }
}