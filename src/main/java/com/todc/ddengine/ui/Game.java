package com.todc.ddengine.ui;

import com.todc.ddengine.util.Direction;
import com.todc.ddengine.action.Action;
import com.todc.ddengine.action.MoveAction;
import com.todc.ddengine.action.NoOpAction;
import com.todc.ddengine.ui.terminal.Terminal;
import com.todc.ddengine.world.Actor;
import com.todc.ddengine.world.Stage;
import com.todc.ddengine.world.StageBuilder;
import com.todc.ddengine.world.Terrain;
import com.todc.ddengine.world.Tile;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


/**
 * @author Tim O'Donnell (tim@timodonnell.com)
 */
/*
public class Game {

    public static void main(String... args) throws Exception {
        JFrame frame = new JFrame("my window title");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // create components and put them in the frame

        // the pack method sizes the frame so that all its contents are at or above their preferred sizes.
        // An alternative to pack is to establish a frame size explicitly by calling setSize or setBounds (which
        // also sets the frame location). In general, using pack is preferable to calling setSize, since pack
        // leaves the frame layout manager in charge of the frame size, and layout managers are good at adjusting to
        // platform dependencies and other factors that affect component size.
        frame.setBounds(50, 50, 400, 300);

        // add our custom text component
        Container container = frame.getContentPane();
        Terminal terminal = new Terminal();
        container.add(terminal);

        frame.setVisible(true);

//        ActionListener listener = event -> {
//            System.out.println("refreshing terminal");
//            terminal.refresh();
//        };
//        Timer displayTimer = new Timer(5000, listener);
//        displayTimer.start();
    }

}
*/

public class Game {


    private static int playerX = 1;
    private static int playerY = 1;


    public static void main(String... args) throws Exception {
        Actor hero = new Actor();

        Stage stage = StageBuilder.fromString(
                "##########\n" +
                "#........#\n" +
                "#........#\n" +
                "#........#\n" +
                "#........#\n" +
                "##########\n"
        );
        stage.setHero(hero);

        Terminal terminal = new Terminal(20, 10);

        Screen screen = new Screen(stage, terminal);

        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("my window title");
            window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            // center window in monitor
            window.setLocationRelativeTo(null);

            // we'll explicitly paint our UI as needed
            window.setIgnoreRepaint(true);

            window.add(terminal);
            window.pack();
            window.setVisible(true);

            window.repaint();
        });

        boolean quit = false;
        while (!quit) {
            if (!terminal.hasFocus()) {
                terminal.requestFocusInWindow();
            }

            Integer key = terminal.getKeyPressed();
            if (key == null) continue;

            Action action = new NoOpAction();

            switch (key) {
                case KeyEvent.VK_Q:
                    quit = true;
                    break;
                case KeyEvent.VK_LEFT:
                    action = new MoveAction(stage, stage.getHero(), Direction.W);
                    break;
                case KeyEvent.VK_RIGHT:
                    action = new MoveAction(stage, stage.getHero(), Direction.E);
                    break;
                case KeyEvent.VK_UP:
                    action = new MoveAction(stage, stage.getHero(), Direction.N);
                    break;
                case KeyEvent.VK_DOWN:
                    action = new MoveAction(stage, stage.getHero(), Direction.S);
                    break;
            }

            action.perform();
            terminal.repaint();
        }
    }

}