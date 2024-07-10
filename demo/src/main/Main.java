package com.main;

import javax.swing.JFrame;


/* Author: Rutger Beugeling
 *
 * Welcome! This is my final form of the Calco Office RPG. This project originated from the JAVA Deep Dive.
 * this project is the sole reason why I never finished the Dive myself.. Anyways!
 *
 * I started Calco on Nov. 1st, 2022, and started this project when the Java Deep Dive dove into JFrames and GUIs.
 * This entire game is based on the JFrame, 2DGraphics and Java programming logic. IN this current state the game is a
 * huge mess of object oriented methods and classes, all without comments. I'll try to clarify all, if not most
 * Methods with Comments. this game has no UML, which it really should have.
 *
 *
 *
 * Here we find the Main Method. it initiates the JFrame the game resides in and initiates
 * and starts the GamePanel/Thread, which I will elaborate on elsewhere.
 *
 *
 *
 */
public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Calco Office RPG");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();

        gamePanel.startGameThread();

    }

}
