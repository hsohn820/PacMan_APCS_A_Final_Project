import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;



/**
 *  GameFrame represents the separate window that displays the game and scoreboard.
 *
 *  @author Bedri, Richie
 *  @version 2021. 5. 27.
 */
public class GameFrame extends JFrame
{
    private Stage stage;
    private PacManGame game;
    private GamePanel gamePanel;
    private ScoreBoardPanel scorePanel;
    private JLabel nameScore = new JLabel ();
    private JLabel highest = new JLabel("Best Score: " + 0);
    private String pacDirection;
    private String name;
    private JLabel gameOver = new JLabel("GAME OVER : Press Space to Restart");
    private int highestScore = 0;
    private Timer timer;
    private TimerTask tt;
    private boolean over = false;


    /**
     * Create a new GameFrame object.
     * @param title Title of window
     * @param username name of the player
     */
    public GameFrame(String title, String username)
    {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLayout(new BorderLayout());
        setLocation(100, 100);
        setResizable(false);

        highestScore = 0;
        start(username);
    }

    /**
     * Set all the components and start the game.
     * @param username name of the player
     */
    private void start(String username)
    {
        stage = new Stage();
        game = new PacManGame(stage);

        gamePanel = new GamePanel(stage);
        gamePanel.update(stage);
        gamePanel.setLayout(new BorderLayout());
        gamePanel.setPreferredSize(new Dimension(stage.getSize() * 25, stage.getSize() * 25));
        getContentPane().add(BorderLayout.CENTER,gamePanel);

        scorePanel = new ScoreBoardPanel(game.getLife());
        scorePanel.setLayout(new BorderLayout());
        scorePanel.setPreferredSize(new Dimension(100, 200));

        name = username;
        nameScore.setText(name + " : 0");
        nameScore.setForeground(Color.WHITE);
        scorePanel.add(BorderLayout.NORTH, nameScore);
        highest.setForeground(Color.WHITE);
        scorePanel.add(BorderLayout.SOUTH, highest);
        scorePanel.update(game.getLife());

        getContentPane().add(BorderLayout.EAST, scorePanel);

        gameOver.setBackground(Color.RED);
        gameOver.setVisible(false);
        gameOver.setPreferredSize(new Dimension(30, 50));
        getContentPane().add(BorderLayout.SOUTH, gameOver);

        pack();
        setVisible(true);

        addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode()==KeyEvent.VK_SPACE && over)
                {
                    over = false;
                    gameOver.setVisible(true);
                    getContentPane().removeAll();
                    start(name);
                }
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyTyped(KeyEvent e)
            {
                char c = e.getKeyChar();
                if(c == 'w' || c == 'W')
                {
                    pacDirection = "up";
                }
                else if(c == 's' || c == 'S')
                {
                    pacDirection = "down";
                }
                else if(c == 'd' || c == 'D')
                {
                    pacDirection = "right";
                }
                else if(c == 'a' || c == 'A')
                {
                    pacDirection = "left";
                }
            }

        });

        timer = new Timer();

        process();
    }

    /**
     * process (repeatedly update) the game
     */
    private void process()
    {
        game.resetObjects();
        pacDirection = "down";

        tt = new TimerTask()
        {
            @Override
            public void run() {
                boolean caught = game.stateUpdate(pacDirection);
                if(caught)
                {
                    stage.locate();
                    gamePanel.update(stage); // update one more frame to display that pacman is caught.
//                    playSound();

                    timer.cancel();
                    timer.purge();
                    if(game.getLife() > 0)
                    {
                        timer = new Timer();
                        process();
                    }
                    else
                    {
                        over();
                    }
                }
                stage.locate();

                if(game.ateAll())
                {
                    timer.cancel();
                    timer.purge();
                    done();
                }

                gamePanel.update(stage);
                scorePanel.update(game.getLife());
                nameScore.setText(name + " : " + game.getScore());
            }
        };

        timer.schedule(tt, 2000, 250);
    }

    /**
     * display the game over sign and be ready for restart.
     */
    public void over()
    {
        over  = true;
        if(highestScore < game.getScore())
        {
            highestScore = game.getScore();
            highest.setText("Best Score: " + highestScore);
        }
        gameOver.setText("GAME OVER : Press Space to Restart");
        gameOver.setVisible(true);
    }

    /**
     * display the game complete sign and be ready for restart.
     */
    public void done()
    {
        over = true;
        if(highestScore < game.getScore())
        {
            highestScore = game.getScore();
            highest.setText("Best Score: " + highestScore);
        }
        gameOver.setText("GAME COMPLETE : Press Space to Restart");
        gameOver.setVisible(true);
    }
}
