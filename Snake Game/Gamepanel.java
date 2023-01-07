import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;
import java.util.Random;

public class Gamepanel extends JPanel implements KeyListener, ActionListener {

    ImageIcon snaketitle=new ImageIcon(getClass().getResource("snaketitle.jpg"));
    ImageIcon downmouth=new ImageIcon(getClass().getResource("downmouth.png"));
    ImageIcon upmouth=new ImageIcon(getClass().getResource("upmouth.png"));
    ImageIcon leftmouth=new ImageIcon(getClass().getResource("leftmouth.png"));
    ImageIcon rightmouth=new ImageIcon(getClass().getResource("rightmouth.png"));
    ImageIcon enemy=new ImageIcon(getClass().getResource("enemy.png"));
    ImageIcon snakeimage=new ImageIcon(getClass().getResource("snakeimage.png"));

    int[] xpos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    int[] ypos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};

    Random random=new Random();
    int enemyx=150,enemyy=200;
    int[] snakexlength=new int[750];
    int[] snakeylength=new int[750];
    boolean left=false;
    boolean right=true;
    boolean up=false;
    boolean down=false;
    boolean gameover=false;
    int move=0;
    int lengthOfsnake=3;
    int score=0;
    Timer time;
    int delay=150;
    Gamepanel()
    {
        addKeyListener(this);
        setFocusable(true);
        time=new Timer(delay, this);
        time.start();
    }
    public void newEnemy()
    {
        enemyx=xpos[random.nextInt(34)];
        enemyy=ypos[random.nextInt(23)];
        for(int i=lengthOfsnake-1;i>=0;i--)
        {
            if(snakexlength[i]==enemyx && snakeylength[i]==enemyy)
            {
                newEnemy();
            }
        }
    }

    public void collidewithenemy()
    {
        if(snakexlength[0]==enemyx && snakeylength[0]==enemyy)
        {
            newEnemy();
            lengthOfsnake++;
            score++;
            snakexlength[lengthOfsnake-1]=snakexlength[lengthOfsnake-2];
            snakeylength[lengthOfsnake-1]=snakeylength[lengthOfsnake-2];

        }
    }
    public void collidewithbody()
    {
        for(int i=lengthOfsnake-1;i>0;i--)
        {
            if(snakexlength[i]==snakexlength[0] && snakeylength[i]==snakeylength[0]) {
                time.stop();
                gameover=true;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.white);
        g.drawRect(24,10,851,55);
        g.drawRect(24,74,851,576);

        snaketitle.paintIcon(this,g,25,11);
        g.setColor(Color.BLACK);
        g.fillRect(25,75,851,576);
        if(move==0)
        {
            snakexlength[0]=100;
            snakexlength[1]=75;
            snakexlength[2]=50;

            snakeylength[0]=100;
            snakeylength[1]=100;
            snakeylength[2]=100;
            move++;
        }
        if(left)
        {
            leftmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
        }
        if(right)
        {
            rightmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
        }
        if(up)
        {
            upmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
        }
        if(down)
        {
            downmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
        }

        for(int i=1;i<lengthOfsnake;i++)
        {
            snakeimage.paintIcon(this,g,snakexlength[i],snakeylength[i]);
        }
        enemy.paintIcon(this,g,enemyx,enemyy);
        if(gameover)
        {
            g.setColor(Color.white);
            g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
            g.drawString("Game Over",300,300);
            g.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,10));
            g.drawString("Press the space to restart",330,360);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",Font.PLAIN,15));
        g.drawString("Score :"+score,750,30);
        g.drawString("Length :"+lengthOfsnake,750,50);
        g.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
      if(e.getKeyCode()==KeyEvent.VK_SPACE && gameover)
      {
          restart();
      }
      if(e.getKeyCode()==KeyEvent.VK_LEFT && (!right))
      {
          left=true;
          right=false;
          up=false;
          down=false;
          move++;
      }
      if(e.getKeyCode()==KeyEvent.VK_RIGHT && (!left))
      {
          left=false;
          right=true;
          up=false;
          down=false;
          move++;
      }
      if(e.getKeyCode()==KeyEvent.VK_DOWN && (!up))
      {
          left=false;
          right=false;
          up=false;
          down=true;
          move++;
      }
      if(e.getKeyCode()==KeyEvent.VK_UP && (!down))
      {
          left=false;
          right=false;
          up=true;
          down=false;
          move++;
      }
    }

    private void restart() {
        gameover=false;
        move=0;
        lengthOfsnake=3;
        score=0;
        left=false;
        right=true;
        down=false;
        up=false;
        time.start();
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=lengthOfsnake-1;i>0;i--)
        {
            snakexlength[i]=snakexlength[i-1];
            snakeylength[i]=snakeylength[i-1];
        }
        if(left)
        {
            snakexlength[0]=snakexlength[0]-25;
        }
        if(right)
        {
            snakexlength[0]=snakexlength[0]+25;
        }
        if(down)
        {
            snakeylength[0]=snakeylength[0]+25;
        }
        if(up)
        {
            snakeylength[0]=snakeylength[0]-25;
        }
        if(snakexlength[0]>850) snakexlength[0]=25;
        if(snakexlength[0]<25) snakexlength[0]=850;
        if(snakeylength[0]>625) snakeylength[0]=75;
        if(snakeylength[0]<75) snakeylength[0]=625;
        collidewithenemy();
        collidewithbody();
        repaint();
    }
}
