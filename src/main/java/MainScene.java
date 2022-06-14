import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class MainScene extends JPanel implements ActionListener {
    public static final int WIDTH_WINDOW = 800;
    public static final int HEIGHT_WINDOW = 800;
    public static final int SIZE_BOARD = 9;
    public static final String X = "X";
    public static final String O = "O";
    public static final String X_WINS_TEXT = "X wins";
    public static final String X_TURN_TEXT = "X turn";
    public static final String O_WINS_TEXT= "O wins";
    public static final String O_TURN_TEXT = "X turn";
    public static final String TITLE = "Tic-Tac-Toe";
    public static final String EMPTY = "";


    private Random random = new Random();
    private JFrame frame = new JFrame();
    private JPanel titlePanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JLabel title = new JLabel();
    private JButton[] buttons = new JButton[SIZE_BOARD];
    private boolean player1Turn;

    public MainScene(){
        frame.setSize(WIDTH_WINDOW,HEIGHT_WINDOW);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setVisible(true);

        title.setBackground(new Color(25,25,25));
        title.setForeground(new Color(25,255,0));
        title.setFont(new Font("arial", Font.BOLD, 75));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setText(TITLE);
        title.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0,0,WIDTH_WINDOW,100);

        buttonPanel.setLayout(new GridLayout(3,3));
        buttonPanel.setBackground(new Color(150,150,150));

        for (int i = 0; i < SIZE_BOARD; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("arial", Font.BOLD, 120));
            buttons[i].setBackground(Color.lightGray);
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        titlePanel.add(title);
        frame.add(titlePanel,BorderLayout.NORTH);
        frame.add(buttonPanel);
        firstTurn();
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < SIZE_BOARD; i++) {
            if(e.getSource() == buttons[i]){
                if(player1Turn){
                    if(buttons[i].getText() == ""){
                        buttons[i].setForeground(new Color(255,0,0));
                        buttons[i].setText(X);
                        player1Turn = false;
                        title.setText(O_TURN_TEXT);
                        check();

                    }
                }else {
                    if(buttons[i].getText() == EMPTY){
                        buttons[i].setForeground(new Color(0,0,255));
                        buttons[i].setText(O);
                        player1Turn = true;
                        title.setText(X_TURN_TEXT);
                        check();
                    }
                }
            }
        }
    }
    public void firstTurn() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(random.nextInt(2) == 0){
            player1Turn = true;
            title.setText("X turn");
        }else {
            player1Turn = false;
            title.setText("O turn");
        }
    }

    public void check(){
        //check X win condition
        if(buttons[0].getText() == X && buttons[1].getText() == X && buttons[2].getText() == X){
            xWins(0,1,2);
        }else if(buttons[3].getText() == X && buttons[4].getText() == X && buttons[5].getText() == X){
            xWins(3,4,5);
        }else if(buttons[6].getText() == X && buttons[7].getText() == X && buttons[8].getText() == X){
            xWins(6,7,8);
        }else if(buttons[0].getText() == X && buttons[3].getText() == X && buttons[6].getText() == X){
            xWins(0,3,6);
        }else if(buttons[1].getText() == X && buttons[4].getText() == X && buttons[7].getText() == X){
            xWins(1,4,7);
        }else if(buttons[2].getText() == X && buttons[5].getText() == X && buttons[8].getText() == X){
            xWins(2,5,8);
        }else if(buttons[0].getText() == X && buttons[4].getText() == X && buttons[8].getText() == X){
            xWins(0,4,8);
        }else if(buttons[2].getText() == X && buttons[4].getText() == X && buttons[6].getText() == X){
            xWins(2,4,6);
        }

        //check O win condition
        if(buttons[0].getText() == O && buttons[1].getText() == O && buttons[2].getText() == O){
            oWins(0,1,2);
        }else if(buttons[3].getText() == O && buttons[4].getText() == O && buttons[5].getText() == O){
            oWins(3,4,5);
        }else if(buttons[6].getText() == O && buttons[7].getText() == O && buttons[8].getText() == O){
            oWins(6,7,8);
        }else if(buttons[0].getText() == O && buttons[3].getText() == O && buttons[6].getText() == O){
            oWins(0,3,6);
        }else if(buttons[1].getText() == O && buttons[4].getText() == O && buttons[7].getText() == O){
            oWins(1,4,7);
        }else if(buttons[2].getText() == O && buttons[5].getText() == O && buttons[8].getText() == O){
            oWins(2,5,8);
        }else if(buttons[0].getText() == O && buttons[4].getText() == O && buttons[8].getText() == O){
            oWins(0,4,8);
        }else if(buttons[2].getText() == O && buttons[4].getText() == O && buttons[6].getText() == O){
            oWins(2,4,6);
        }
    }

    public void xWins(int a, int b, int c){
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for (int i = 0; i < SIZE_BOARD; i++) {
            buttons[i].setEnabled(false);
        }
        title.setText(X_WINS_TEXT);
    }

    public void oWins(int a, int b, int c){
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for (int i = 0; i < SIZE_BOARD; i++) {
            buttons[i].setEnabled(false);
        }
        title.setText(O_WINS_TEXT);

        //resetBoard();
    }
    public void resetBoard(){
        for (int i = 0; i < SIZE_BOARD; i++) {
            buttons[i].setEnabled(true);
            buttons[i].setText(EMPTY);
            buttons[i].setBackground(Color.lightGray);
            firstTurn();
        }
    }

}
