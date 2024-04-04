package snakegame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import javax.swing.JPanel;
public class GamePanel extends JPanel implements ActionListener {
static final int SCREEN_WIDTH = 1200;
static final int SCREEN_HEIGHT = 600;
static final int UNIT_SIZE = 25;
static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
  static final int DELAY = 130;
  static final int DELAY_1 =90;
final int x[] = new int[GAME_UNITS];
final int y[] = new int[GAME_UNITS];
int bodyParts = 3;
int applesEaten;
int appleX;
int appleY;
int rectx;
int recty;
int na;
int nb;
int ya;
int yb;
char direction ='D';
boolean running = false;
Timer timer;
Random random;
GamePanel(){
random = new Random();
this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
this.setBackground(Color.black);
this.setFocusable(true);
this.addKeyListener(new MyKeyAdapter());

}

public void startGame() {
newApple();
if((applesEaten%3==0)) {
newoval();
}
if(applesEaten%2==0) {
out();
}
if((bodyParts>=15) && (bodyParts%5==0)) {
	newsmall();
}
running = true;
	timer = new Timer (DELAY,this);
	timer.restart();

}
public void paintComponent(Graphics g) {
super.paintComponent(g);
draw(g);
if((applesEaten%3==0 )&& (applesEaten!=0)) {
draw_1(g);
}
if((applesEaten>=3)&&(applesEaten%2==0)) {
draw_2(g);
}
if((bodyParts>=15) && (bodyParts%5==0)) {
	draw_3(g);
}
}
public void draw_3(Graphics g) {
	if(running) {
		
		g.setColor(Color.yellow);
	g.fillRect(ya, yb,UNIT_SIZE,UNIT_SIZE);
		for(int k=0;k<bodyParts;k++) {
			if(k==0) {
				g.setColor(Color.cyan);
				g.fillRect(x[k], y[k], UNIT_SIZE, UNIT_SIZE);			
	}
		
else { 
	g.setColor(Color.pink);
    g.fillRect(x[k], y[k], UNIT_SIZE, UNIT_SIZE);
}
}
g.setColor(Color.red);

g.setFont( new Font("Ink Free",Font.BOLD, 40));
FontMetrics metrics = getFontMetrics(g.getFont());
g.drawString("Score: "+applesEaten, (SCREEN_WIDTH -
metrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
		}
	
else {
gameOver(g);
}
}
public void draw_2(Graphics g) {
	if(running) {
		
		g.setColor(Color.red);
	g.fillOval(na, nb,UNIT_SIZE,UNIT_SIZE);
		for(int i=0;i<bodyParts;i++) {
			if(i==0) {
				g.setColor(Color.pink);
				g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);			
	}
		
else { 
	g.setColor(Color.cyan);
    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
}
}
g.setColor(Color.red);

g.setFont( new Font("Ink Free",Font.BOLD, 40));
FontMetrics metrics = getFontMetrics(g.getFont());
g.drawString("Score: "+applesEaten, (SCREEN_WIDTH -
metrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
		}
	
else {
gameOver(g);
}
}
public void draw_1(Graphics g) {
	if(running) {
		
		g.setColor(Color.GREEN);
		g.fillOval(rectx,recty, UNIT_SIZE,UNIT_SIZE);
		for(int i=0;i<bodyParts;i++) {
			if(i==0) {
				g.setColor(Color.gray);
				g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);			
	}
		
else { 
	g.setColor(Color.orange);
    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
}
}
g.setColor(Color.red);

g.setFont( new Font("Ink Free",Font.BOLD, 40));
FontMetrics metrics = getFontMetrics(g.getFont());
g.drawString("Score: "+applesEaten+'\n'+"      Length: "+bodyParts, (SCREEN_WIDTH -
metrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
		}
	
else {
gameOver(g);
}
}
public void draw(Graphics g) {
	if(running) {
		
		g.setColor(Color.blue);
		g.fillRect(appleX, appleY, UNIT_SIZE,UNIT_SIZE);
		for(int i=0;i<bodyParts;i++) {
			if(i==0) {
				g.setColor(Color.cyan);
				g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);			
	}
		
else { 
	g.setColor( Color.gray);
    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
}
}
g.setColor(Color.red);

g.setFont( new Font("Ink Free",Font.BOLD, 40));
FontMetrics metrics = getFontMetrics(g.getFont());
g.drawString("Score: "+applesEaten+"      Length: "+bodyParts, (SCREEN_WIDTH -
metrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
		}
	
else {
gameOver(g);
}
}

public void newApple() {
appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
}
public void newoval() {
	rectx= random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
	recty=random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
	
}
public void out() {
	na= random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
	nb=random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
}
public void newsmall() {
	ya= random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
	yb=random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
}
public void move() {
for(int i = bodyParts;i>0;i--) {
x[i] = x[i-1];
y[i] = y[i-1];
}
switch(direction) {
case 'U':
y[0] = y[0] - UNIT_SIZE;
break;
case 'D':
y[0] = y[0] + UNIT_SIZE;
break;
case 'L':
x[0] = x[0] - UNIT_SIZE;
break;
case 'R':
x[0] = x[0] + UNIT_SIZE;
break;
}
}
public void checkApple() {
	
	
if((x[0] == appleX) && (y[0] == appleY)) {
	bodyParts=bodyParts+1;
    applesEaten++;	
    newApple();
}

}
public void checkOval() {
	if((x[0] == rectx) && (y[0] == recty)) {
		bodyParts=bodyParts+2;
	    applesEaten=applesEaten+3;	
	    newoval();
}
}
public void checkout() {
	if((x[0] == na) && (y[0] ==nb)) {
		running = false;
	}
}
public void checklength() {
	if((x[0] == ya) && (y[0] ==yb)) {
		bodyParts=4;
		applesEaten=applesEaten+1;
		newsmall();
	
	}
}
private boolean amp(boolean b) {
	// TODO Auto-generated method stub
	return false;
}
public void checkCollisions() {
//checks if head collides with body
for(int i = bodyParts;i>0;i--) {
if((x[0] == x[i] && (y[0] == y[i]))){

running = false;
}
}
//check if head touches left boreder
if(x[0] < 0) {
running = false;
}
//check if head touches right border
if(x[0] > SCREEN_WIDTH) {
running = false;
}
//check if head touches top border
if(y[0] < 0) {
running = false;
}
//check if head touches bottom border
if(y[0] > SCREEN_HEIGHT) {
running = false;
}
if(!running) {
timer.stop();
}
}
public void gameOver(Graphics g) {
//Score
g.setColor(Color.ORANGE);
g.setFont( new Font("Ink Free",Font.BOLD, 40));
FontMetrics metrics1 = getFontMetrics(g.getFont());
g.drawString("Score:"+applesEaten+"    Length"+bodyParts, (SCREEN_WIDTH -
metrics1.stringWidth("Score:"+applesEaten))/2, g.getFont().getSize());

//Game Over text
g.setColor(Color.RED);
g.setFont( new Font("Ink Free",Font.BOLD, 75));
FontMetrics metrics2 = getFontMetrics(g.getFont());
g.drawString("Start Game", (SCREEN_WIDTH - metrics2.stringWidth("Start Game"))/2, SCREEN_HEIGHT/4);
//restart the game
g.setColor(Color.pink);
g.setFont(new Font("Ink Free",Font.BOLD,55));
FontMetrics metrics3 = getFontMetrics(g.getFont());
g.drawString("PRESS KEYS TO START THE GAME", (SCREEN_WIDTH - metrics3.stringWidth("RESTART THE GAME"))/3, SCREEN_HEIGHT/2);

}
public void checkspeed() {
	if(applesEaten>=3) {
		timer= new Timer(90,this);
		timer.restart();
		
	}

}
@Override
public void actionPerformed(ActionEvent e) {
if(running) {
move();
checkApple();
checkOval();
checkout();
checkCollisions();
checklength();
}


repaint();
}
public class MyKeyAdapter extends KeyAdapter{
@Override
public void keyPressed(KeyEvent e) {
	if(e.getKeyCode()==KeyEvent.VK_1) {
		running=true;
		applesEaten=0;
		bodyParts=3;
		
		startGame();
		repaint();
		
	}
	if(e.getKeyCode()==KeyEvent.VK_2) {
		running = true;
		if(direction != 'D') {
			direction ='U';
			}
		else if(direction !='L') {
			direction ='R';
		}
		else if(direction != 'U') {
			direction = 'D';
		}
		else {
			direction='L';
		}
		startGame();
		repaint();
	
	}
	if(e.getKeyCode()==KeyEvent.VK_3) {
		applesEaten=0;
		bodyParts=3;
		repaint();
		
	}
	if(e.getKeyCode()==KeyEvent.VK_4) {
		timer.stop();	
		
	}
	if(e.getKeyCode()==KeyEvent.VK_5) {
		timer.start();		
	}
	if(e.getKeyCode() == KeyEvent.VK_ENTER)
    {
    if(!running) {
    running = true;
    applesEaten=0;
	bodyParts=3;
	if(direction != 'D') {
		direction ='U';
		}
	else if(direction !='L') {
		direction ='R';
	}
	else if(direction != 'U') {
		direction = 'D';
	}
	else {
		direction='L';
	}
	new GamePanel();
	startGame();
	move();
	checkApple();
	checkOval();
	checkout();
	checkCollisions();
	checklength();
    repaint ();
    }
    }
    
	
switch(e.getKeyCode()) {
case KeyEvent.VK_LEFT:

if(direction != 'R' ) {
	direction ='L';
}
break;
case KeyEvent.VK_RIGHT:
if(direction !='L') {
	direction ='R';
}
break;
case KeyEvent.VK_UP:
if(direction != 'D') {
direction ='U';
}
break;
case KeyEvent.VK_DOWN:
if(direction != 'U') {
direction = 'D';
}
break;
case KeyEvent.VK_0:
	running = false;
	break;

}


}
}
}