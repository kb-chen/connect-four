/**
 * Created by kevinchen on 10/16/15.
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class ConnectFourProject
{
    public static void main(String...args)
    {
        System.out.println(System.getProperty("user.dir"));
        JFrame j = new JFrame();  //JFrame is the window; window is a depricated class
        MyPanel2 m = new MyPanel2();
        j.setSize(m.getSize());
        j.add(m); //adds the panel to the frame so that the picture will be drawn
        //use setContentPane() sometimes works better then just add b/c of greater efficiency.

        j.setVisible(true); //allows the frame to be shown.

        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes the dialog box exit when you click the "x" button.
    }

}

class MyPanel2 extends JPanel implements MouseMotionListener, MouseListener, ActionListener
{
    //variables for mouse-following circle
    private int circleX;
    private int circleY;

    private javax.swing.Timer timer;

    ConnectFour board;
    int turnCount;
    int rowDroppedTo;
    int colDroppedTo;

    MyPanel2()
    {
        setSize(1200, 900);
        setVisible(true); //it's like calling the repaint method.

        board = new ConnectFour();
        turnCount = 0;
        timer = new javax.swing.Timer(50, this);
        timer.setRepeats(false);

        addMouseMotionListener(this);
        addMouseListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,1300,900);

        drawBoard(g);
        fillBoard(g);
        g.setFont(new Font("Impact", Font.BOLD, 50));
        g.setColor(Color.BLUE);
        if(board.checkWinner() == 1)
            g.drawString("Red Wins!", 980, 700);
        if(board.checkWinner() == 2)
            g.drawString("Yellow Wins!", 950, 700);
        drawCircle(g);

    }

    public void mouseMoved(MouseEvent e)
    {
        circleX = e.getX();
        circleY = e.getY();
        repaint();
    }

    public void mouseDragged(MouseEvent e){mouseMoved(e);}

    //draw the board
    public void drawBoard(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,1300,900);
        g.setColor(Color.BLUE);
        g.fillRect(0,100,950,800);
        g.setColor(Color.WHITE);
        for(int x=0; x<7; x++)
        {
            for(int y=0; y<6; y++)
            {
                g.fillOval((55+120*x),(725-120*y),100,100);
            }
        }

        g.setColor(Color.BLUE);
        g.setFont(new Font("Impact", Font.BOLD, 50));
        g.drawString("Reset", 1015, 800);
    }

    //draws the matrix of chips
    public void fillBoard(Graphics g)
    {
        for(int r = 0; r < 6; r++)
        {
            for(int c = 0; c < 7; c++)
            {
                if(board.getColor(r,c) == 1)
                {
                    g.setColor(Color.RED);
                    g.fillOval(55 + 120 * c, 725 - 120*(5 - r), 100, 100);
                }

                if(board.getColor(r,c) == 2)
                {
                    g.setColor(Color.YELLOW);
                    g.fillOval(55 + 120 * c, 725 - 120*(5 - r), 100, 100);
                }
            }
        }
    }

    //draws the mouse-following circle
    public void drawCircle(Graphics g)
    {
        if(turnCount % 2 == 0)
            g.setColor(Color.RED);
        else
            g.setColor(Color.YELLOW);
        g.fillOval(circleX - 50, circleY - 50, 100, 100);
    }

    //buttons on various screens
    public void mousePressed(MouseEvent e)
    {
        int clickedX=e.getX();
        int clickedY=e.getY();

        if(clickedX > 0 && clickedX < 950 && clickedY > 0 && clickedY < 100 && board.checkWinner() == 0)
        {
            if(clickedX >= 0 && clickedX < 165)
                colDroppedTo = 0;

            else if(clickedX >= 165 && clickedX < 285)
                colDroppedTo = 1;

            else if(clickedX >= 285 && clickedX < 405)
                colDroppedTo = 2;

            else if(clickedX >= 405 && clickedX < 525)
                colDroppedTo = 3;

            else if(clickedX >= 525 && clickedX < 645)
                colDroppedTo = 4;

            else if(clickedX >= 645 && clickedX < 785)
                colDroppedTo = 5;

            else if(clickedX >= 785 && clickedX <= 950)
                colDroppedTo = 6;

            if(turnCount % 2 == 0)
                rowDroppedTo = board.dropRed(colDroppedTo);

            else
                rowDroppedTo = board.dropYellow(colDroppedTo);

            if(rowDroppedTo != -1)
            {
                turnCount++;
            }

            if(board.checkWinner() == 1)
                turnCount++;

            if(turnCount % 2 == 1)
            {
                if(timer.isRunning()) timer.stop();
                timer.start();
                /*ArrayList<Integer> moves = board.AI(1);
                int min = 0;
                for(int i = 1; i < 7; i++)
                {
                    if(moves.get(i) < moves.get(min))
                        min = i;
                }

                ArrayList<Integer> random = new ArrayList();
                for(int i = 0; i < 7; i++)
                {
                    if(moves.get(i) == moves.get(min))
                        random.add(i);
                }

                int randomCol = random.get((int)(Math.random() * random.size()));
                boolean loop = true;
                if(board.dropYellow(randomCol) != -1)
                    loop = false;
                while(loop){
                    if(board.dropYellow((int)(Math.random() * 7)) != -1)
                        loop = false;
                }
                //board.dropYellow(randomCol);
                turnCount++;

                //Troubleshooting
                //System.out.println(moves);
                //System.out.println(random);*/

            }

            repaint();
        }

        if(clickedX > 950 && clickedY > 750)
        {
            board.reset();
            turnCount = 0;
            repaint();
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        ArrayList<Integer> moves = board.AI(1);
        int min = 0;
        for(int i = 1; i < 7; i++)
        {
            if(moves.get(i) < moves.get(min))
                min = i;
        }

        ArrayList<Integer> random = new ArrayList();
        for(int i = 0; i < 7; i++)
        {
            if(moves.get(i) == moves.get(min))
                random.add(i);
        }

        int randomCol = random.get((int)(Math.random() * random.size()));
        boolean loop = true;
        if(board.dropYellow(randomCol) != -1)
            loop = false;
        while(loop){
            if(board.dropYellow((int)(Math.random() * 7)) != -1)
                loop = false;
        }
        //board.dropYellow(randomCol);
        turnCount++;

        //Troubleshooting
        //System.out.println(moves);
        //System.out.println(random);
    }

    public void mouseClicked(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
}


