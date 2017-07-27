package tps.tp4.game;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Game {

    private JFrame frame = new JFrame();
    private JPanel main_panel;


    private int players = 5;

    private Deck deck;
    private Board board;
    private JButton btn_next;
    private JPanel panel_player1;
    private JPanel panel_player2;
    private JPanel panel_player3;
    private JPanel panel_player4;
    private JPanel panel_player5;

    public Game() {
        $$$setupUI$$$();
        this.initialize(2);
        this.frame.setContentPane(main_panel);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();
        this.listeners();
        this.frame.setVisible(true);
    }

    private void initialize(int players) {

    }

    private void on_left_click(Point point) {
        if (!this.board.empty(point)) {
            return;
        }

        Tile tile = deck.tile();

        if (tile == null) {
            on_game_over();
            return;
        }

        boolean eval = this.board.tile(tile, point);

        if (!eval) {
            return;
        }

        this.deck.next();
    }

    private void on_right_click(Point point) {
        this.board.marker(point);
    }

    private void on_middle_click(Point point) {
        this.board.players().next();
    }

    private void on_game_over() {
        // TODO
        this.board.end();
    }

    private void listeners() {
        board.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    on_left_click(new Point(e.getX(), e.getY()));
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    on_right_click(new Point(e.getX(), e.getY()));
                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    on_middle_click(new Point(e.getX(), e.getY()));
                }
            }
        });

        btn_next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                on_middle_click(null);
            }
        });
    }

    public void createUIComponents() {
        if (this.players < 1 || this.players > Board.MAX_PLAYERS) {
            this.players = 2;
        }

        Color[] colors = new Color[]{
                Color.RED,
                Color.YELLOW,
                Color.GREEN,
                Color.BLUE,
                Color.CYAN
        };

        ArrayList<Player> players = new ArrayList<Player>();

        for (int i = 0; i < this.players; i++) {
            players.add(new Player(colors[i]));
        }

        this.board = new Board(players);

        this.panel_player1 = new JPanel();
        this.panel_player2 = new JPanel();
        this.panel_player3 = new JPanel();
        this.panel_player4 = new JPanel();
        this.panel_player5 = new JPanel();
        this.panel_player1.setBackground(colors[0]);
        this.panel_player2.setBackground(colors[1]);
        this.panel_player3.setBackground(colors[2]);
        this.panel_player4.setBackground(colors[3]);
        this.panel_player5.setBackground(colors[4]);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        main_panel = new JPanel();
        main_panel.setLayout(new GridLayoutManager(6, 3, new Insets(0, 0, 0, 0), -1, -1));
        final JToolBar toolBar1 = new JToolBar();
        main_panel.add(toolBar1, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setEnabled(true);
        main_panel.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(200, 100), new Dimension(200, -1), null, 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder("Player #1"));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panel1.add(panel_player1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        main_panel.add(panel2, new GridConstraints(1, 2, 5, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(200, -1), new Dimension(200, -1), null, 0, false));
        panel2.setBorder(BorderFactory.createTitledBorder("Deck"));
        deck = new Deck();
        panel2.add(deck, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 70), null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel2.add(spacer2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        btn_next = new JButton();
        btn_next.setText("Next");
        panel2.add(btn_next, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setEnabled(true);
        main_panel.add(panel3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(200, 100), new Dimension(200, -1), null, 0, false));
        panel3.setBorder(BorderFactory.createTitledBorder("Player #2"));
        final Spacer spacer3 = new Spacer();
        panel3.add(spacer3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panel3.add(panel_player2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.setEnabled(true);
        main_panel.add(panel4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(200, 100), new Dimension(200, -1), null, 0, false));
        panel4.setBorder(BorderFactory.createTitledBorder("Player #3"));
        final Spacer spacer4 = new Spacer();
        panel4.add(spacer4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panel4.add(panel_player3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel5.setEnabled(true);
        main_panel.add(panel5, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(200, 100), new Dimension(200, -1), null, 0, false));
        panel5.setBorder(BorderFactory.createTitledBorder("Player #4"));
        final Spacer spacer5 = new Spacer();
        panel5.add(spacer5, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panel5.add(panel_player4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel6.setEnabled(true);
        main_panel.add(panel6, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(200, 100), new Dimension(200, -1), null, 0, false));
        panel6.setBorder(BorderFactory.createTitledBorder("Player #5"));
        final Spacer spacer6 = new Spacer();
        panel6.add(spacer6, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panel6.add(panel_player5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        main_panel.add(board, new GridConstraints(1, 1, 5, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(1400, 980), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return main_panel;
    }
}
