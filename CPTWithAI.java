import java.awt.*;
import hsa.Console;

public class CPTWithAI
{
    static Console c, d;
    static int turn = 0, fun = 0;
    static int[] [] grid = new int [7] [7];
    public static void main (String[] args) throws InterruptedException
    {
	//fonts and font sizes
	Font a = new Font ("Courier", 3, 50);
	Font e = new Font ("Courier", 3, 20);
	//single or multiplayer
	int mode = 0;
	//console for game board and console for inputing values
	c = new Console ();
	d = new Console (4, 32);
	//initial loading screen
	LoadingScreen ();
	d.clear ();
	//multiplayer
	do
	{
	    d.clear ();
	    //screen where you choose which mode you want
	    mode = mainScreen ();
	    d.clear ();
	    if (mode == 3)
	    {
		break;
	    }
	    if (mode == 2)
	    {

		do
		{

		    //resetting the grid every new game
		    turn = 0;
		    for (int i = 0 ; i < grid.length ; i++)
		    {
			for (int j = 0 ; j < grid [i].length ; j++)
			{
			    grid [j] [i] = 0;
			}
		    }

		    //initial grid
		    displayGraphics (grid);
		    //win and play
		    int win = 0, playAgain = 0;
		    //Changing Values
		    do
		    {




			ChangeValues ();


			turn++;

			if (turn == 49)
			{
			    d.println ("TIE");
			    break;
			}
			//sending mode to wincheck for computer / player 2 win
			//checking for wins
			win = WinCheck (mode);
			if (win == 1)
			{
			    d.clear ();
			    d.print ("Player 1 has won!");

			}
			else if (win == 2)
			{

			    d.clear ();

			    d.print ("Player 2 has won!");


			}

			if (win == 1 || win == 2)

			    {
				break;
			    }
			//if win is 2 or 1 it offs the loop
			d.clear ();
			//Covering turn counter
			c.setColor (Color.black);
			c.fillRect (500, 180, 100, 40);
		    }
		    while (true);

		    Thread.sleep (3000);
		    d.clear ();
		    c.clear ();
		    //setting background for win
		    c.setColor (Color.black);
		    c.fillRect (1, 1, 700, 500);
		    c.setColor (Color.white);
		    //displaying who wins
		    c.setFont (a);
		    if (win == 0)
		    {
			c.drawString ("Its A Tie!", 50, 100);
		    }
		    if (win == 1)
		    {
			c.drawString ("Player 1 Wins!!", 50, 100);
		    }
		    if (win == 2)
		    {
			c.drawString ("Player 2 Wins!!", 50, 100);
		    }
		    //asking to play again
		    c.drawString ("Would You Like To", 10, 200);
		    c.drawString ("Play Again?", 10, 250);
		    d.clear ();
		    d.println ("Yes - 1");
		    d.println ("No - 2");

		    //Checking if play again is accurate
		    do
		    {
			playAgain = d.readInt ();
			if (playAgain != 1 || playAgain != 2)
			{
			    d.clear ();
			    d.print ("You entered an invalid answer    please enter again ");
			}
			if (playAgain == 1 || playAgain == 2)
			{
			    break;
			}
		    }
		    while (playAgain > 2 || playAgain < 1);
		    if (playAgain == 2)
		    {
			break;
		    }
		    c.clear ();
		    d.clear ();

		}
		while (true);
	    }
	    //single player
	    else if (mode == 1)
	    {
		//asking for difficulty and checking to see if its within the range
		int difficulty = 0;
		do
		{
		    difficulty = DifficultlyScreen ();
		    if (difficulty != 1 || difficulty != 2 || difficulty != 3)
		    {
			d.clear ();
			d.print ("You entered an invalid answer    please enter again ");
		    }
		    if (difficulty == 1 || difficulty == 2 || difficulty != 3)
		    {
			break;
		    }
		}
		while (difficulty > 3 || difficulty < 1);


		d.clear ();
		//resetting grid
		do
		{


		    turn = 0;
		    for (int i = 0 ; i < grid.length ; i++)
		    {
			for (int j = 0 ; j < grid [i].length ; j++)
			{
			    grid [j] [i] = 0;
			}
		    }

		    //initial grid
		    displayGraphics (grid);
		    int win = 0, playAgain = 0;
		    do
		    {


			if (turn % 2 == 0)
			{


			    fun = SinglePlayerChangeValues ();
			}
			else if (turn % 2 != 0)
			{
			    //for gmode it's randomized but it won't set you up also won't block you. you can win pretty easily
			    //for easy it will block you and it might set you up but wont go for wins. You can win by trapping
			    //for difficult it wont set you up and wont block you and will try to win. Only way to win is by trapping it (You would win in 2 or more moves on the next move)
			    AI (difficulty);
			}

			turn++;

			if (turn == 49)
			{
			    d.println ("TIE");
			    break;
			}
			win = WinCheck (mode);
			if (win == 1)
			{
			    d.clear ();
			    d.print ("Player 1 has won!");


			}
			if (win == 2)
			{
			    d.clear ();
			    d.print ("The Computer has won!");

			}

			if (win == 1 || win == 2)
			{
			    break;
			}
			d.clear ();
			c.setColor (Color.black);
			c.fillRect (500, 180, 100, 40);
		    }
		    while (true);
		    Thread.sleep (3000);
		    d.clear ();
		    c.clear ();

		    c.setColor (Color.black);
		    c.fillRect (1, 1, 700, 500);
		    c.setColor (Color.white);

		    c.setFont (a);
		    if (win == 0)
		    {
			c.drawString ("Its A Tie!", 50, 100);
		    }
		    if (win == 1)
		    {
			c.drawString ("Player 1 Wins!!", 50, 100);
		    }
		    if (win == 2)
		    {
			c.drawString ("Computer Wins!!", 50, 100);
		    }
		    c.drawString ("Would You Like To", 10, 200);
		    c.drawString ("Play Again?", 10, 250);
		    d.clear ();
		    d.println ("Yes - 1");
		    d.println ("No - 2");


		    do
		    {
			playAgain = d.readInt ();
			if (playAgain != 1 || playAgain != 2)
			{
			    d.clear ();
			    d.print ("You entered an invalid answer    please enter again ");
			}
			if (playAgain == 1 || playAgain == 2)
			{
			    break;
			}
		    }
		    while (playAgain > 2 || playAgain < 1);
		    if (playAgain == 2)
		    {
			break;
		    }
		    c.clear ();
		    d.clear ();

		}
		while (true);

	    }

	}
	while (true);



    }


    public static void AI (int difficulty) throws InterruptedException
    {
	c.setColor (Color.white);
	c.drawString ("Computer", 500, 200);
	int value = 0;
	int finalChoice = 0;
	int row = 0;
	int j = 0;
	//G - Mode
	if (difficulty == 1)
	{
	    Thread.sleep (1000);
	    row = (int) ((7 - 1 + 1) * Math.random () + 1);

	    while (grid [0] [row - 1] == 1 || grid [0] [row - 1] == 2)
	    {


		row = (int) ((7 - 1 + 1) * Math.random () + 1);





	    }
	}
	else if (difficulty == 2)
	{
	    row = -1;

	    //defense!!!
	    for (int i = 0 ; i < grid.length ; i++)
	    {
		for (j = 6 ; j >= 0 ; j--)
		{
		    //bottom row blocking
		    if (j == 6)
		    {
			//left side

			if (i > -1 && i <= 3)
			{
			    //1110
			    if (grid [j] [i] == 1 && grid [j] [i + 1] == 1 && grid [j] [i + 2] == 1)
			    {
				if (grid [j] [i + 3] == 0)
				{
				    row = i + 4;

				}
			    }
			    //1011
			    else if (grid [j] [i] == 1 && grid [j] [i + 1] == 0 && grid [j] [i + 2] == 1 && grid [j] [i + 3] == 1)
			    {
				row = i + 2;


			    }
			    //1101
			    else if (grid [j] [i] == 1 && grid [j] [i + 1] == 1 && grid [j] [i + 2] == 0 && grid [j] [i + 3] == 1)
			    {
				row = i + 3;


			    }
			}

			//rightside
			if (i < 7 && i >= 3)
			{
			    //0111
			    if (grid [j] [i] == 1 && grid [j] [i - 1] == 1 && grid [j] [i - 2] == 1)
			    {
				if (grid [j] [i - 3] == 0)
				{
				    row = i - 2;

				}
			    }
			    //1101
			    else if (grid [j] [i] == 1 && grid [j] [i - 1] == 0 && grid [j] [i - 2] == 1 && grid [j] [i - 3] == 1)
			    {
				row = i;

			    }
			    //1011
			    else if (grid [j] [i] == 1 && grid [j] [i - 1] == 1 && grid [j] [i - 2] == 0 && grid [j] [i - 3] == 1)
			    {
				row = i - 1;

			    }
			}
		    }
		    //upper horizontal blocking
		    else if (j != 6)
		    {
			//left side horizontal
			if (i >= 0 && i <= 3)
			{
			    //1110
			    if (grid [j] [i] == 1 && grid [j] [i + 1] == 1 && grid [j] [i + 2] == 1 && grid [j + 1] [i + 3] != 0 && grid [j] [i + 3] == 0)
			    {
				row = i + 4;

			    }
			    //1011
			    else if (grid [j] [i] == 1 && grid [j] [i + 1] == 0 && grid [j] [i + 2] == 1 && grid [j + 1] [i + 1] != 0 && grid [j] [i + 3] == 1)
			    {
				row = i + 2;

			    }
			    //1101
			    else if (grid [j] [i] == 1 && grid [j] [i + 1] == 1 && grid [j] [i + 2] == 0 && grid [j + 1] [i + 2] != 0 && grid [j] [i + 3] == 1)
			    {
				row = i + 3;

			    }
			}
			//right side horizontal

			if (i <= 6 && i >= 3)
			{
			    //0111
			    if (grid [j] [i] == 1 && grid [j] [i - 1] == 1 && grid [j] [i - 2] == 1 && grid [j + 1] [i - 3] != 0 && grid [j] [i - 3] == 0)
			    {
				row = i - 2;

			    }
			    //1011
			    else if (grid [j] [i] == 1 && grid [j] [i - 1] == 0 && grid [j] [i - 2] == 1 && grid [j + 1] [i - 1] != 0 && grid [j] [i - 3] == 1)
			    {
				row = i - 1;

			    }
			    //1101
			    else if (grid [j] [i] == 1 && grid [j] [i - 1] == 1 && grid [j] [i - 2] == 0 && grid [j + 1] [i - 2] != 0 && grid [j] [i - 3] == 1)
			    {
				row = i;

			    }
			}
		    }

		    //vertical
		    if (j <= 4 && j > 0)
		    {
			if (grid [j] [i] == 1 && grid [j + 1] [i] == 1 && grid [j + 2] [i] == 1 && grid [j - 1] [i] == 0)
			{
			    row = i + 1;

			}
		    }
		    //diagonal forward
		    if (i <= 3 && j >= 3)
		    {
			if (j == 6)
			{
			    //1110
			    if (grid [j] [i] == 1 && grid [j - 1] [i + 1] == 1 && grid [j - 2] [i + 2] == 1 && grid [j - 3] [i + 3] == 0 && grid [j - 2] [i + 3] != 0)
			    {
				row = i + 4;
			    }
			    //1101
			    else if (grid [j] [i] == 1 && grid [j - 1] [i + 1] == 1 && grid [j - 2] [i + 2] == 0 && grid [j - 3] [i + 3] == 1 && grid [j - 1] [i + 2] != 0)
			    {
				row = i + 3;
			    }
			    //1011
			    else if (grid [j] [i] == 1 && grid [j - 1] [i + 1] == 0 && grid [j - 2] [i + 2] == 1 && grid [j - 3] [i + 3] == 1 && grid [j] [i + 1] != 0)
			    {
				row = i + 2;
			    }
			    //0111
			    else if (grid [j] [i] == 0 && grid [j - 1] [i + 1] == 1 && grid [j - 2] [i + 2] == 1 && grid [j - 3] [i + 3] == 1)
			    {
				row = i + 1;
			    }
			}
			else if (j != 6)
			{
			    //1110
			    if (grid [j] [i] == 1 && grid [j - 1] [i + 1] == 1 && grid [j - 2] [i + 2] == 1 && grid [j - 3] [i + 3] == 0 && grid [j - 2] [i + 3] != 0)
			    {
				row = i + 4;
			    }
			    //1101
			    else if (grid [j] [i] == 1 && grid [j - 1] [i + 1] == 1 && grid [j - 2] [i + 2] == 0 && grid [j - 3] [i + 3] == 1 && grid [j - 1] [i + 2] != 0)
			    {
				row = i + 3;
			    }
			    //1011
			    else if (grid [j] [i] == 1 && grid [j - 1] [i + 1] == 0 && grid [j - 2] [i + 2] == 1 && grid [j - 3] [i + 3] == 1 && grid [j] [i + 1] != 0)
			    {
				row = i + 2;
			    }
			    //0111
			    else if (grid [j] [i] == 0 && grid [j - 1] [i + 1] == 1 && grid [j - 2] [i + 2] == 1 && grid [j - 3] [i + 3] == 1 && grid [j + 1] [i] != 0)
			    {
				row = i + 1;
			    }
			}

		    }
		    //backdiagonal
		    if (i >= 3 && j >= 3)
		    {
			if (j == 6)
			{
			    //1110
			    if (grid [j] [i] == 0 && grid [j - 1] [i - 1] == 1 && grid [j - 2] [i - 2] == 1 && grid [j - 3] [i - 3] == 1)
			    {
				row = i + 1;
			    }
			    //1101
			    else if (grid [j] [i] == 1 && grid [j - 1] [i - 1] == 0 && grid [j - 2] [i - 2] == 0 && grid [j - 3] [i - 3] == 1 && grid [j] [i - 1] != 0)
			    {
				row = i;
			    }
			    //1011
			    else if (grid [j] [i] == 1 && grid [j - 1] [i - 1] == 1 && grid [j - 2] [i - 2] == 0 && grid [j - 3] [i - 3] == 1 && grid [j - 1] [i - 2] != 0)
			    {
				row = i - 1;
			    }
			    //0111
			    else if (grid [j] [i] == 1 && grid [j - 1] [i - 1] == 1 && grid [j - 2] [i - 2] == 1 && grid [j - 3] [i - 3] == 0 && grid [j - 2] [i - 3] != 0)
			    {
				row = i - 2;
			    }
			}
			else if (j != 6)
			{
			    //1110
			    if (grid [j] [i] == 0 && grid [j - 1] [i - 1] == 1 && grid [j - 2] [i - 2] == 1 && grid [j - 3] [i - 3] == 1 && grid [j + 1] [i] != 0)
			    {
				row = i + 1;
			    }
			    //1101
			    else if (grid [j] [i] == 1 && grid [j - 1] [i - 1] == 0 && grid [j - 2] [i - 2] == 0 && grid [j - 3] [i - 3] == 1 && grid [j] [i - 1] != 0)
			    {
				row = i;
			    }
			    //1011
			    else if (grid [j] [i] == 1 && grid [j - 1] [i - 1] == 1 && grid [j - 2] [i - 2] == 0 && grid [j - 3] [i - 3] == 1 && grid [j - 1] [i - 2] != 0)
			    {
				row = i - 1;
			    }
			    //0111
			    else if (grid [j] [i] == 1 && grid [j - 1] [i - 1] == 1 && grid [j - 2] [i - 2] == 1 && grid [j - 3] [i - 3] == 0 && grid [j - 2] [i - 3] != 0)
			    {
				row = i - 2;
			    }
			}

		    }
		    if (row != -1)
		    {
			break;
		    }
		}

		if (row != -1)
		{
		    break;
		}
	    }
	    //default condition
	    if (row == -1)
	    {
		row = (int)
		    ((7 - 1 + 1) * Math.random () + 1);

		while (grid [0] [row - 1] == 1 || grid [0] [row - 1] == 2)
		{
		    row = (int) ((7 - 1 + 1) * Math.random () + 1);
		}
	    }
	}
	else if (difficulty == 3)
	{
	    row = -1;

	    //defense and offense
	    //scanner
	    for (int i = 0 ; i < grid.length ; i++)
	    {
		for (j = 6 ; j >= 0 ; j--)
		{
		    //offense
		    if (j == 6)
		    {
			//left side

			if (i >= 0 && i <= 3)
			{
			    //1110
			    if (grid [j] [i] == 2 && grid [j] [i + 1] == 2 && grid [j] [i + 2] == 2)
			    {
				if (grid [j] [i + 3] == 0)
				{
				    row = i + 4;

				}
			    }
			    //1011
			    else if (grid [j] [i] == 2 && grid [j] [i + 1] == 0 && grid [j] [i + 2] == 2 && grid [j] [i + 3] == 2)
			    {
				row = i + 2;


			    }
			    //1101
			    else if (grid [j] [i] == 2 && grid [j] [i + 1] == 2 && grid [j] [i + 2] == 0 && grid [j] [i + 3] == 2)
			    {
				row = i + 3;


			    }
			}

			//rightside
			if (i < 7 && i >= 3)
			{
			    //0111
			    if (grid [j] [i] == 2 && grid [j] [i - 1] == 2 && grid [j] [i - 2] == 2)
			    {
				if (grid [j] [i - 3] == 0)
				{
				    row = i - 2;

				}
			    }
			    //1101
			    else if (grid [j] [i] == 2 && grid [j] [i - 1] == 0 && grid [j] [i - 2] == 2 && grid [j] [i - 3] == 2)
			    {
				row = i;

			    }
			    //1011
			    else if (grid [j] [i] == 2 && grid [j] [i - 1] == 2 && grid [j] [i - 2] == 0 && grid [j] [i - 3] == 2)
			    {
				row = i - 1;

			    }
			}
		    }
		    //upper horizontal blocking
		    else if (j != 6)
		    {
			//left side horizontal
			if (i >= 0 && i <= 3)
			{
			    //1110
			    if (grid [j] [i] == 2 && grid [j] [i + 1] == 2 && grid [j] [i + 2] == 2 && grid [j + 1] [i + 3] != 0 && grid [j] [i + 3] == 0)
			    {
				row = i + 4;

			    }
			    //1011
			    else if (grid [j] [i] == 2 && grid [j] [i + 1] == 0 && grid [j] [i + 2] == 2 && grid [j + 1] [i + 1] != 0 && grid [j] [i + 3] == 2)
			    {
				row = i + 2;

			    }
			    //1101
			    else if (grid [j] [i] == 2 && grid [j] [i + 1] == 2 && grid [j] [i + 2] == 0 && grid [j + 1] [i + 2] != 0 && grid [j] [i + 3] == 2)
			    {
				row = i + 3;

			    }
			}
			//right side horizontal

			if (i <= 6 && i >= 3)
			{
			    //0111
			    if (grid [j] [i] == 2 && grid [j] [i - 1] == 2 && grid [j] [i - 2] == 2 && grid [j + 1] [i - 3] != 0 && grid [j] [i - 3] == 0)
			    {
				row = i - 2;

			    }
			    //1011
			    else if (grid [j] [i] == 2 && grid [j] [i - 1] == 0 && grid [j] [i - 2] == 2 && grid [j + 1] [i - 1] != 0 && grid [j] [i - 3] == 2)
			    {
				row = i - 1;

			    }
			    //1101
			    else if (grid [j] [i] == 2 && grid [j] [i - 1] == 2 && grid [j] [i - 2] == 0 && grid [j + 1] [i - 2] != 0 && grid [j] [i - 3] == 2)
			    {
				row = i;

			    }
			}
		    }

		    //vertical
		    if (j <= 4 && j > 0)
		    {
			if (grid [j] [i] == 2 && grid [j + 1] [i] == 2 && grid [j + 2] [i] == 2 && grid [j - 1] [i] == 0)
			{
			    row = i + 1;

			}
		    }
		    //diagonal forward
		    if (i <= 3 && j >= 3)
		    {
			if (j == 6)
			{
			    //1110
			    if (grid [j] [i] == 2 && grid [j - 1] [i + 1] == 2 && grid [j - 2] [i + 2] == 2 && grid [j - 3] [i + 3] == 0 && grid [j - 2] [i + 3] != 0)
			    {
				row = i + 4;
			    }
			    //1101
			    else if (grid [j] [i] == 2 && grid [j - 1] [i + 1] == 2 && grid [j - 2] [i + 2] == 0 && grid [j - 3] [i + 3] == 2 && grid [j - 1] [i + 2] != 0)
			    {
				row = i + 3;
			    }
			    //1011
			    else if (grid [j] [i] == 2 && grid [j - 1] [i + 1] == 0 && grid [j - 2] [i + 2] == 2 && grid [j - 3] [i + 3] == 2 && grid [j] [i + 1] != 0)
			    {
				row = i + 2;
			    }
			    //0111
			    else if (grid [j] [i] == 0 && grid [j - 1] [i + 1] == 2 && grid [j - 2] [i + 2] == 2 && grid [j - 3] [i + 3] == 2)
			    {
				row = i + 1;
			    }
			}
			else if (j != 6)
			{
			    //1110
			    if (grid [j] [i] == 2 && grid [j - 1] [i + 1] == 2 && grid [j - 2] [i + 2] == 2 && grid [j - 3] [i + 3] == 0 && grid [j - 2] [i + 3] != 0)
			    {
				row = i + 4;
			    }
			    //1101
			    else if (grid [j] [i] == 2 && grid [j - 1] [i + 1] == 2 && grid [j - 2] [i + 2] == 0 && grid [j - 3] [i + 3] == 2 && grid [j - 1] [i + 2] != 0)
			    {
				row = i + 3;
			    }
			    //1011
			    else if (grid [j] [i] == 2 && grid [j - 1] [i + 1] == 0 && grid [j - 2] [i + 2] == 2 && grid [j - 3] [i + 3] == 2 && grid [j] [i + 1] != 0)
			    {
				row = i + 2;
			    }
			    //0111
			    else if (grid [j] [i] == 0 && grid [j - 1] [i + 1] == 2 && grid [j - 2] [i + 2] == 2 && grid [j - 3] [i + 3] == 2 && grid [j + 1] [i] != 0)
			    {
				row = i + 1;
			    }
			}

		    }
		    //backdiagonal
		    if (i >= 3 && j >= 3)
		    {
			if (j == 6)
			{
			    //1110
			    if (grid [j] [i] == 0 && grid [j - 1] [i - 1] == 2 && grid [j - 2] [i - 2] == 2 && grid [j - 3] [i - 3] == 2)
			    {
				row = i + 1;
			    }
			    //1101
			    else if (grid [j] [i] == 2 && grid [j - 1] [i - 1] == 2 && grid [j - 2] [i - 2] == 0 && grid [j - 3] [i - 3] == 2 && grid [j] [i - 1] != 0)
			    {
				row = i;
			    }
			    //1011
			    else if (grid [j] [i] == 2 && grid [j - 1] [i - 1] == 2 && grid [j - 2] [i - 2] == 0 && grid [j - 3] [i - 3] == 2 && grid [j - 1] [i - 2] != 0)
			    {
				row = i - 1;
			    }
			    //0111
			    else if (grid [j] [i] == 2 && grid [j - 1] [i - 1] == 2 && grid [j - 2] [i - 2] == 2 && grid [j - 3] [i - 3] == 0 && grid [j - 2] [i - 3] != 0)
			    {
				row = i - 2;
			    }
			}
			else if (j != 6)
			{
			    //1110
			    if (grid [j] [i] == 0 && grid [j - 1] [i - 1] == 2 && grid [j - 2] [i - 2] == 2 && grid [j - 3] [i - 3] == 2 && grid [j + 1] [i] != 0)
			    {
				row = i + 1;
			    }
			    //1101
			    else if (grid [j] [i] == 2 && grid [j - 1] [i - 1] == 2 && grid [j - 2] [i - 2] == 0 && grid [j - 3] [i - 3] == 2 && grid [j] [i - 1] != 0)
			    {
				row = i;
			    }
			    //1011
			    else if (grid [j] [i] == 2 && grid [j - 1] [i - 1] == 2 && grid [j - 2] [i - 2] == 0 && grid [j - 3] [i - 3] == 2 && grid [j - 1] [i - 2] != 0)
			    {
				row = i - 1;
			    }
			    //0111
			    else if (grid [j] [i] == 2 && grid [j - 1] [i - 1] == 2 && grid [j - 2] [i - 2] == 2 && grid [j - 3] [i - 3] == 0 && grid [j - 2] [i - 3] != 0)
			    {
				row = i - 2;
			    }
			}

		    }



		    if (row != -1)
		    {
			break;
		    }
		}
		if (row != -1)
		{
		    break;
		}
	    }
	    //defense
	    for (int i = 0 ; i < grid.length ; i++)
	    {
		for (j = 6 ; j >= 0 ; j--)
		{
		    if (j == 6)
		    {
			//left side

			if (i > -1 && i <= 3)
			{
			    //1110
			    if (grid [j] [i] == 1 && grid [j] [i + 1] == 1 && grid [j] [i + 2] == 1)
			    {
				if (grid [j] [i + 3] == 0)
				{
				    row = i + 4;

				}
			    }
			    //1011
			    else if (grid [j] [i] == 1 && grid [j] [i + 1] == 0 && grid [j] [i + 2] == 1 && grid [j] [i + 3] == 1)
			    {
				row = i + 2;


			    }
			    //1101
			    else if (grid [j] [i] == 1 && grid [j] [i + 1] == 1 && grid [j] [i + 2] == 0 && grid [j] [i + 3] == 1)
			    {
				row = i + 3;


			    }
			}

			//rightside
			if (i < 7 && i >= 3)
			{
			    //0111
			    if (grid [j] [i] == 1 && grid [j] [i - 1] == 1 && grid [j] [i - 2] == 1)
			    {
				if (grid [j] [i - 3] == 0)
				{
				    row = i - 2;

				}
			    }
			    //1101
			    else if (grid [j] [i] == 1 && grid [j] [i - 1] == 0 && grid [j] [i - 2] == 1 && grid [j] [i - 3] == 1)
			    {
				row = i;

			    }
			    //1011
			    else if (grid [j] [i] == 1 && grid [j] [i - 1] == 1 && grid [j] [i - 2] == 0 && grid [j] [i - 3] == 1)
			    {
				row = i - 1;

			    }
			}
		    }
		    //upper horizontal blocking
		    else if (j != 6)
		    {
			//left side horizontal
			if (i >= 0 && i <= 3)
			{
			    //1110
			    if (grid [j] [i] == 1 && grid [j] [i + 1] == 1 && grid [j] [i + 2] == 1 && grid [j + 1] [i + 3] != 0 && grid [j] [i + 3] == 0)
			    {
				row = i + 4;

			    }
			    //1011
			    else if (grid [j] [i] == 1 && grid [j] [i + 1] == 0 && grid [j] [i + 2] == 1 && grid [j + 1] [i + 1] != 0 && grid [j] [i + 3] == 1)
			    {
				row = i + 2;

			    }
			    //1101
			    else if (grid [j] [i] == 1 && grid [j] [i + 1] == 1 && grid [j] [i + 2] == 0 && grid [j + 1] [i + 2] != 0 && grid [j] [i + 3] == 1)
			    {
				row = i + 3;

			    }
			}
			//right side horizontal

			if (i <= 6 && i >= 3)
			{
			    //0111
			    if (grid [j] [i] == 1 && grid [j] [i - 1] == 1 && grid [j] [i - 2] == 1 && grid [j + 1] [i - 3] != 0 && grid [j] [i - 3] == 0)
			    {
				row = i - 2;

			    }
			    //1011
			    else if (grid [j] [i] == 1 && grid [j] [i - 1] == 0 && grid [j] [i - 2] == 1 && grid [j + 1] [i - 1] != 0 && grid [j] [i - 3] == 1)
			    {
				row = i - 1;

			    }
			    //1101
			    else if (grid [j] [i] == 1 && grid [j] [i - 1] == 1 && grid [j] [i - 2] == 0 && grid [j + 1] [i - 2] != 0 && grid [j] [i - 3] == 1)
			    {
				row = i;

			    }
			}
		    }

		    //vertical
		    if (j <= 4 && j > 0)
		    {
			if (grid [j] [i] == 1 && grid [j + 1] [i] == 1 && grid [j + 2] [i] == 1 && grid [j - 1] [i] == 0)
			{
			    row = i + 1;

			}
		    }
		    //diagonal forward
		    if (i <= 3 && j >= 3)
		    {
			if (j == 6)
			{
			    //1110
			    if (grid [j] [i] == 1 && grid [j - 1] [i + 1] == 1 && grid [j - 2] [i + 2] == 1 && grid [j - 3] [i + 3] == 0 && grid [j - 2] [i + 3] != 0)
			    {
				row = i + 4;
			    }
			    //1101
			    else if (grid [j] [i] == 1 && grid [j - 1] [i + 1] == 1 && grid [j - 2] [i + 2] == 0 && grid [j - 3] [i + 3] == 1 && grid [j - 1] [i + 2] != 0)
			    {
				row = i + 3;
			    }
			    //1011
			    else if (grid [j] [i] == 1 && grid [j - 1] [i + 1] == 0 && grid [j - 2] [i + 2] == 1 && grid [j - 3] [i + 3] == 1 && grid [j] [i + 1] != 0)
			    {
				row = i + 2;
			    }
			    //0111
			    else if (grid [j] [i] == 0 && grid [j - 1] [i + 1] == 1 && grid [j - 2] [i + 2] == 1 && grid [j - 3] [i + 3] == 1)
			    {
				row = i + 1;
			    }
			}
			else if (j != 6)
			{
			    //1110
			    if (grid [j] [i] == 1 && grid [j - 1] [i + 1] == 1 && grid [j - 2] [i + 2] == 1 && grid [j - 3] [i + 3] == 0 && grid [j - 2] [i + 3] != 0)
			    {
				row = i + 4;
			    }
			    //1101
			    else if (grid [j] [i] == 1 && grid [j - 1] [i + 1] == 1 && grid [j - 2] [i + 2] == 0 && grid [j - 3] [i + 3] == 1 && grid [j - 1] [i + 2] != 0)
			    {
				row = i + 3;
			    }
			    //1011
			    else if (grid [j] [i] == 1 && grid [j - 1] [i + 1] == 0 && grid [j - 2] [i + 2] == 1 && grid [j - 3] [i + 3] == 1 && grid [j] [i + 1] != 0)
			    {
				row = i + 2;
			    }
			    //0111
			    else if (grid [j] [i] == 0 && grid [j - 1] [i + 1] == 1 && grid [j - 2] [i + 2] == 1 && grid [j - 3] [i + 3] == 1 && grid [j + 1] [i] != 0)
			    {
				row = i + 1;
			    }
			}

		    }
		    //backdiagonal
		    if (i >= 3 && j >= 3)
		    {
			if (j == 6)
			{
			    //1110
			    if (grid [j] [i] == 0 && grid [j - 1] [i - 1] == 1 && grid [j - 2] [i - 2] == 1 && grid [j - 3] [i - 3] == 1)
			    {
				row = i + 1;
			    }
			    //1101
			    else if (grid [j] [i] == 1 && grid [j - 1] [i - 1] == 1 && grid [j - 2] [i - 2] == 0 && grid [j - 3] [i - 3] == 1 && grid [j] [i - 1] != 0)
			    {
				row = i;
			    }
			    //1011
			    else if (grid [j] [i] == 1 && grid [j - 1] [i - 1] == 1 && grid [j - 2] [i - 2] == 0 && grid [j - 3] [i - 3] == 1 && grid [j - 1] [i - 2] != 0)
			    {
				row = i - 1;
			    }
			    //0111
			    else if (grid [j] [i] == 1 && grid [j - 1] [i - 1] == 1 && grid [j - 2] [i - 2] == 1 && grid [j - 3] [i - 3] == 0 && grid [j - 2] [i - 3] != 0)
			    {
				row = i - 2;
			    }
			}
			else if (j != 6)
			{
			    //1110
			    if (grid [j] [i] == 0 && grid [j - 1] [i - 1] == 1 && grid [j - 2] [i - 2] == 1 && grid [j - 3] [i - 3] == 1 && grid [j + 1] [i] != 0)
			    {
				row = i + 1;
			    }
			    //1101
			    else if (grid [j] [i] == 1 && grid [j - 1] [i - 1] == 0 && grid [j - 2] [i - 2] == 0 && grid [j - 3] [i - 3] == 1 && grid [j] [i - 1] != 0)
			    {
				row = i;
			    }
			    //1011
			    else if (grid [j] [i] == 1 && grid [j - 1] [i - 1] == 1 && grid [j - 2] [i - 2] == 0 && grid [j - 3] [i - 3] == 1 && grid [j - 1] [i - 2] != 0)
			    {
				row = i - 1;
			    }
			    //0111
			    else if (grid [j] [i] == 1 && grid [j - 1] [i - 1] == 1 && grid [j - 2] [i - 2] == 1 && grid [j - 3] [i - 3] == 0 && grid [j - 2] [i - 3] != 0)
			    {
				row = i - 2;
			    }
			}

		    }

		    if (row != -1)
		    {
			break;
		    }
		}
		if (row != -1)
		{
		    break;
		}
	    }


	}

	//midgame
	int no1 = -2, no2 = -2, no3 = -2, no4 = -2, no5 = -2, no6 = -2, no7 = -2, no8 = -2, no9 = -2, no10 = -2;
	int no11 = -2, no12 = -2, no13 = -2, no14 = -2, no15 = -2, no16 = -2, no17 = -2, no18 = -2, no19 = -2, no20 = -2;
	int noavailable = 0;
	if (difficulty != 2)
	{
	    if (row == -1)
	    {
		for (int i = 0 ; i < grid.length ; i++)
		{
		    for (j = 0 ; j < grid [i].length ; j++)
		    {
			//not setting up horizontal

			if (j != 6)
			{
			    //right side
			    if (i <= 6 && i >= 3)
			    {
				//0111
				if (grid [j] [i] == 1 && grid [j] [i - 1] == 1 && grid [j] [i - 2] == 1 && grid [j + 1] [i - 3] == 0 && grid [j] [i - 3] == 0)
				{
				    no1 = i - 2;

				}
				//1011
				else if (grid [j] [i] == 1 && grid [j] [i - 1] == 1 && grid [j] [i - 2] == 0 && grid [j + 1] [i - 2] == 0 && grid [j] [i - 3] == 1)
				{
				    no2 = i - 1;

				}
				//1101
				else if (grid [j] [i] == 1 && grid [j] [i - 1] == 0 && grid [j] [i - 2] == 1 && grid [j + 1] [i - 1] == 0 && grid [j] [i - 3] == 1)
				{
				    no3 = i;

				}
			    }
			    //leftside
			    if (i >= 0 && i <= 3)
			    {
				//1110
				if (grid [j] [i] == 1 && grid [j] [i + 1] == 1 && grid [j] [i + 2] == 1 && grid [j + 1] [i + 3] == 0 && grid [j] [i + 3] == 0)
				{
				    no4 = i + 4;

				}
				//1011
				else if (grid [j] [i] == 1 && grid [j] [i + 1] == 0 && grid [j] [i + 2] == 1 && grid [j + 1] [i + 1] == 0 && grid [j] [i + 3] == 1)
				{
				    no5 = i + 2;

				}
				//1101
				else if (grid [j] [i] == 1 && grid [j] [i + 1] == 1 && grid [j] [i + 2] == 0 && grid [j + 1] [i + 2] == 0 && grid [j] [i + 3] == 1)
				{
				    no6 = i + 3;

				}
			    }
			}
			//not setting up diagonal forward
			if (i <= 3 && j >= 3)
			{
			    if (j == 6)
			    {
				//1110
				if (grid [j] [i] == 1 && grid [j - 1] [i + 1] == 1 && grid [j - 2] [i + 2] == 1 && grid [j - 3] [i + 3] == 0 && grid [j - 2] [i + 3] == 0)
				{
				    no7 = i + 4;
				}
				//1101
				else if (grid [j] [i] == 1 && grid [j - 1] [i + 1] == 1 && grid [j - 2] [i + 2] == 0 && grid [j - 3] [i + 3] == 1 && grid [j - 1] [i + 2] == 0)
				{
				    no8 = i + 3;
				}
				//1011
				else if (grid [j] [i] == 1 && grid [j - 1] [i + 1] == 0 && grid [j - 2] [i + 2] == 1 && grid [j - 3] [i + 3] == 1 && grid [j] [i + 1] == 0)
				{
				    no9 = i + 2;
				}

			    }
			    else if (j < 6)
			    {
				//1110
				if (grid [j] [i] == 1 && grid [j - 1] [i + 1] == 1 && grid [j - 2] [i + 2] == 1 && grid [j - 3] [i + 3] == 0 && grid [j - 2] [i + 3] == 0)
				{
				    no10 = i + 4;
				}
				//1101
				else if (grid [j] [i] == 1 && grid [j - 1] [i + 1] == 1 && grid [j - 2] [i + 2] == 0 && grid [j - 3] [i + 3] == 1 && grid [j - 1] [i + 2] == 0)
				{
				    no11 = i + 3;
				}
				//1011
				else if (grid [j] [i] == 1 && grid [j - 1] [i + 1] == 0 && grid [j - 2] [i + 2] == 1 && grid [j - 3] [i + 3] == 1 && grid [j] [i + 1] == 0)
				{
				    no12 = i + 2;
				}
				//0111
				else if (grid [j] [i] == 0 && grid [j - 1] [i + 1] == 1 && grid [j - 2] [i + 2] == 1 && grid [j - 3] [i + 3] == 1 && grid [j + 1] [i] == 0)
				{
				    no13 = i + 1;
				}
			    }

			}
			//not setting up diagonal backwards
			if (i >= 3 && j >= 3)
			{
			    if (j == 6)
			    {

				//1101
				if (grid [j] [i] == 1 && grid [j - 1] [i - 1] == 0 && grid [j - 2] [i - 2] == 0 && grid [j - 3] [i - 3] == 2 && grid [j] [i - 1] == 0)
				{
				    no14 = i;
				}
				//1011
				else if (grid [j] [i] == 1 && grid [j - 1] [i - 1] == 1 && grid [j - 2] [i - 2] == 0 && grid [j - 3] [i - 3] == 1 && grid [j - 1] [i - 2] == 0)
				{
				    no15 = i - 1;
				}
				//0111
				else if (grid [j] [i] == 1 && grid [j - 1] [i - 1] == 1 && grid [j - 2] [i - 2] == 1 && grid [j - 3] [i - 3] == 0 && grid [j - 2] [i - 3] == 0)
				{
				    no16 = i - 2;
				}
			    }
			    else if (j != 6)
			    {
				//1110
				if (grid [j] [i] == 0 && grid [j - 1] [i - 1] == 1 && grid [j - 2] [i - 2] == 1 && grid [j - 3] [i - 3] == 1 && grid [j + 1] [i] == 0)
				{
				    no17 = i + 1;
				}
				//1101
				else if (grid [j] [i] == 1 && grid [j - 1] [i - 1] == 0 && grid [j - 2] [i - 2] == 0 && grid [j - 3] [i - 3] == 1 && grid [j] [i - 1] == 0)
				{
				    no18 = i;
				}
				//1011
				else if (grid [j] [i] == 1 && grid [j - 1] [i - 1] == 1 && grid [j - 2] [i - 2] == 0 && grid [j - 3] [i - 3] == 1 && grid [j - 1] [i - 2] == 0)
				{
				    no19 = i - 1;
				}
				//0111
				else if (grid [j] [i] == 1 && grid [j - 1] [i - 1] == 1 && grid [j - 2] [i - 2] == 1 && grid [j - 3] [i - 3] == 0 && grid [j - 2] [i - 3] == 0)
				{
				    no20 = i - 2;
				}
			    }

			}
		    }
		}
		do
		{
		    row = (int) ((7 - 1 + 1) * Math.random () + 1);
		    noavailable++;
		    if (noavailable > 700)
		    {
			row = (int) ((7 - 1 + 1) * Math.random () + 1);
			while (grid [0] [row - 1] == 1 || grid [0] [row - 1] == 2)
			{
			    row = (int) ((7 - 1 + 1) * Math.random () + 1);
			}
			break;
		    }
		    while (grid [0] [row - 1] == 1 || grid [0] [row - 1] == 2)
		    {
			row = (int) ((7 - 1 + 1) * Math.random () + 1);
		    }

		}
		while (row == no1 || row == no2 || row == no3 || row == no4 || row == no5 || row == no6 || row == no7 || row == no8 || row == no9 || row == no10 || row == no11 || row == no12 || row == no13 || row == no14 || row == no15 || row == no16 || row == no17 || row == no18 || row == no19 || row == no20);
	    }
	}

	//default condition
	if (row == -1)
	{
	    row = (int) ((7 - 1 + 1) * Math.random () + 1);


	    //can't choose full row
	    while (grid [0] [row - 1] == 1 || grid [0] [row - 1] == 2)
	    {
		row = (int) ((7 - 1 + 1) * Math.random () + 1);
	    }
	}



	int i = row - 1;
	for (j = 0 ; j < grid [i].length ; j++)
	{
	    if (j == grid.length - 1)
	    {
		break;
	    }


	    else if (grid [j + 1] [row - 1] == 1 || grid [j + 1] [row - 1] == 2)
	    {
		break;
	    }
	}


	grid [j] [row - 1] = 2;
	c.setColor (Color.yellow);
	c.fillOval (i * 65, j * 65, 63, 63);








    }




    public static int SinglePlayerChangeValues () throws InterruptedException
    {
	int row = 0;
	Font e = new Font ("Courier", 3, 20);
	c.setFont (e);
	c.setColor (Color.white);

	c.drawString ("Player 1", 500, 200);


	d.print ("Enter the column you would like to drop a token in: ");
	row = d.readInt ();


	int j = 0;

	while (row > 7 || row < 1)
	{
	    d.clear ();
	    d.print ("This column does not exist.");
	    Thread.sleep (1000);
	    d.clear ();
	    d.print ("Enter the column you would like to drop a token in: ");
	    row = d.readInt ();
	}


	while (grid [0] [row - 1] == 1 || grid [0] [row - 1] == 2)
	{

	    d.clear ();
	    d.print ("This column is full!");
	    Thread.sleep (1000);
	    d.clear ();
	    d.print ("Enter the column you would like to drop a token in: ");
	    row = d.readInt ();
	    while (row > 7 || row < 1)
	    {
		d.clear ();
		d.print ("This column does not exist.");
		Thread.sleep (1000);
		d.clear ();
		d.print ("Enter the column you would like to drop a token in: ");
		row = d.readInt ();

	    }
	}


	int i = row - 1;
	for (j = 0 ; j < grid [i].length ; j++)
	{
	    if (j == grid.length - 1)
	    {
		break;
	    }
	    else if (grid [j + 1] [row - 1] == 1 || grid [j + 1] [row - 1] == 2)
	    {
		break;
	    }

	}


	grid [j] [row - 1] = 1;
	c.setColor (Color.red);
	c.fillOval (i * 65, j * 65, 63, 63);
	return (row);



    }


    public static void LoadingScreen () throws InterruptedException
    {
	int x = 0;
	c.setColor (Color.blue);
	c.fillRect (1, 1, 700, 500);
	c.setColor (Color.black);
	c.fillRect (220, 140, 200, 70);
	c.setColor (Color.yellow);
	c.fillRect (230, 150, 180, 50);
	c.setColor (Color.white);
	Font a = new Font ("Courier", 3, 70);
	c.setFont (a);
	c.drawString ("Loading", 170, 300);
	do
	{
	    c.setColor (Color.red);
	    c.fillRect (230, 150, x, 50);
	    x++;
	    Thread.sleep (20);
	}


	while (x < 181)
	    ;




    }


    public static int mainScreen () throws InterruptedException
    {
	int mode = 0;
	c.setColor (Color.blue);
	c.fillRect (1, 1, 700, 500);
	c.setColor (Color.yellow);
	Font a = new Font ("Courier", 3, 70);
	c.setFont (a);
	c.drawString ("Connect Four", 50, 100);
	Font b = new Font ("Courier", 3, 40);
	c.setFont (b);
	c.drawString ("Multiplayer - 2", 50, 400);
	c.drawString ("Single Player - 1", 50, 350);
	c.drawString ("Exit Game - 3", 50, 450);
	d.println ("What gamemode do you want to    play?");
	mode = d.readInt ();
	return (mode);

    }


    public static void ChangeValues () throws InterruptedException
    {
	int row = 0;
	Font e = new Font ("Courier", 3, 20);
	c.setFont (e);
	c.setColor (Color.white);
	if (turn % 2 == 0)
	{
	    c.drawString ("Player 1", 500, 200);
	}


	c.setColor (Color.white);
	if (turn % 2 != 0)
	{
	    c.drawString ("Player 2", 500, 200);
	}


	d.print ("Enter the row you would like to drop a token in: ");
	row = d.readInt ();


	int j = 0;

	while (row > 7 || row < 1)
	{
	    d.clear ();
	    d.print ("This row does not exist.");
	    Thread.sleep (1000);
	    d.clear ();
	    d.print ("Enter the row you would like to drop a token in: ");
	    row = d.readInt ();
	}


	while (grid [0] [row - 1] == 1 || grid [0] [row - 1] == 2)
	{

	    d.clear ();
	    d.print ("This row is full!");
	    Thread.sleep (1000);
	    d.clear ();
	    d.print ("Enter the row you would like to drop a token in: ");
	    row = d.readInt ();
	    while (row > 7 || row < 1)
	    {
		d.clear ();
		d.print ("This row does not exist.");
		Thread.sleep (1000);
		d.clear ();
		d.print ("Enter the row you would like to drop a token in: ");
		row = d.readInt ();

	    }
	}


	int i = row - 1;
	for (j = 0 ; j < grid [i].length ; j++)
	{
	    if (j == grid.length - 1)
	    {
		break;
	    }
	    else if (grid [j + 1] [row - 1] == 1 || grid [j + 1] [row - 1] == 2)
	    {
		break;
	    }

	}


	if (turn % 2 == 0)
	{
	    grid [j] [row - 1] = 1;
	    c.setColor (Color.red);
	    c.fillOval (i * 65, j * 65, 63, 63);
	}


	else
	{
	    grid [j] [row - 1] = 2;
	    c.setColor (Color.yellow);
	    c.fillOval (i * 65, j * 65, 63, 63);
	}
    }


    public static int CheckHorizontal () throws InterruptedException
    {
	int win = 0;

	for (int i = 0 ; i < grid.length - 3 ; i++)
	{
	    for (int j = 0 ; j < grid [i].length ; j++)
	    {
		if (grid [j] [i] == 1 && grid [j] [i + 1] == 1 && grid [j] [i + 2] == 1 && grid [j] [i + 3] == 1)
		{
		    win = 1;



		    break;
		}

	    }
	}


	for (int i = 0 ; i < grid.length - 3 ; i++)
	{
	    for (int j = 0 ; j < grid [i].length ; j++)
	    {
		if (grid [j] [i] == 2 && grid [j] [i + 1] == 2 && grid [j] [i + 2] == 2 && grid [j] [i + 3] == 2)
		{
		    win = 2;




		    break;
		}
	    }
	}


	return win;
    }


    public static int CheckVertical () throws InterruptedException
    {
	int win = 0;
	for (int i = 0 ; i < grid.length ; i++)
	{
	    for (int j = 0 ; j < grid [i].length - 3 ; j++)
	    {
		if (grid [j] [i] == 1 && grid [j + 1] [i] == 1 && grid [j + 2] [i] == 1 && grid [j + 3] [i] == 1)
		{
		    win = 1;




		    break;
		}

	    }



	}


	for (int i = 0 ; i < grid.length ; i++)
	{
	    for (int j = 0 ; j < grid [i].length - 3 ; j++)
	    {
		if (grid [j] [i] == 2 && grid [j + 1] [i] == 2 && grid [j + 2] [i] == 2 && grid [j + 3] [i] == 2)
		{
		    win = 2;




		    break;
		}

	    }



	}


	return win;
    }


    public static int CheckDiagonalForward () throws InterruptedException
    {
	int win = 0;
	for (int i = 0 ; i < grid.length - 3 ; i++)
	{
	    for (int j = 0 ; j < grid [i].length - 3 ; j++)
	    {
		if (grid [j] [i] == 1 && grid [j + 1] [i + 1] == 1 && grid [j + 2] [i + 2] == 1 && grid [j + 3] [i + 3] == 1)
		{
		    win = 1;




		    break;
		}


	    }
	}


	for (int i = 0 ; i < grid.length - 3 ; i++)
	{
	    for (int j = 0 ; j < grid [i].length - 3 ; j++)
	    {
		if (grid [j] [i] == 2 && grid [j + 1] [i + 1] == 2 && grid [j + 2] [i + 2] == 2 && grid [j + 3] [i + 3] == 2)
		{
		    win = 2;




		    break;
		}


	    }
	}


	return win;
    }


    public static int CheckDiagonalBackwards () throws InterruptedException
    {
	int win = 0;
	for (int i = 6 ; i > 2 ; i--)
	{
	    for (int j = 0 ; j < grid [i].length - 3 ; j++)
	    {
		if (grid [j] [i] == 1 && grid [j + 1] [i - 1] == 1 && grid [j + 2] [i - 2] == 1 && grid [j + 3] [i - 3] == 1)
		{
		    win = 1;




		    break;
		}


	    }
	}


	for (int i = 6 ; i > 2 ; i--)
	{
	    for (int j = 0 ; j < grid [i].length - 3 ; j++)
	    {
		if (grid [j] [i] == 2 && grid [j + 1] [i - 1] == 2 && grid [j + 2] [i - 2] == 2 && grid [j + 3] [i - 3] == 2)
		{
		    win = 2;


		    break;
		}


	    }
	}


	return win;
    }


    public static void displayGraphics (int[] [] grid)
    {
	c.setColor (Color.black);
	c.fillRect (1, 1, 700, 500);
	c.setColor (Color.blue);
	c.fillRect (1, 1, grid.length * 65, grid.length * 65);
	c.setColor (Color.black);
	for (int i = 0 ; i < grid.length ; i++)
	{
	    for (int j = 0 ; j < grid [i].length ; j++)
	    {
		c.fillOval (i * 65, j * 65, 63, 63);
	    }
	}


	Font a = new Font ("Courier", 3, 38);
	c.setFont (a);
	c.setColor (Color.white);
	c.drawString ("1", 20, 485);
	c.drawString ("2", 85, 485);
	c.drawString ("3", 150, 485);
	c.drawString ("4", 215, 485);
	c.drawString ("5", 280, 485);
	c.drawString ("6", 345, 485);
	c.drawString ("7", 410, 485);

    }


    public static int WinCheck (int mode) throws InterruptedException
    {
	int win = 0;
	for (int i = 0 ; i < 1 ; i++)
	{
	    win = CheckHorizontal ();
	    if (win == 1 || win == 2)
	    {

		break;
	    }
	    win = CheckVertical ();
	    if (win == 1 || win == 2)
	    {

		break;
	    }
	    win = CheckDiagonalForward ();
	    if (win == 1 || win == 2)
	    {

		break;
	    }
	    win = CheckDiagonalBackwards ();
	    if (win == 1 || win == 2)
	    {

		break;
	    }
	    if (win == 1)
	    {
		d.print ("Player 1 has won!");
		Thread.sleep (1000);
	    }
	    if (win == 2)
	    {
		if (mode == 1)
		{
		    d.print ("The Computer has won!");
		}
		else
		{
		    d.print ("Player 2 has won!");
		}
		Thread.sleep (1000);
	    }

	}


	return win;

    }


    public static int DifficultlyScreen ()
    {
	int difficulty = 0;
	c.setColor (Color.blue);
	c.fillRect (1, 1, 700, 500);
	c.setColor (Color.yellow);
	Font a = new Font ("Courier", 3, 70);
	c.setFont (a);
	c.drawString ("Difficulty", 50, 100);
	Font b = new Font ("Courier", 3, 40);
	c.setFont (b);
	c.drawString ("Easy - 2", 50, 400);
	c.drawString ("G Mode - 1", 50, 350);
	c.drawString ("Difficult - 3", 50, 450);
	d.println ("Choose your difficulty");
	difficulty = d.readInt ();
	return (difficulty);
    }
}


