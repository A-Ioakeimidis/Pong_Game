package com.tasos.pong_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class Ball implements Runnable {
	
	// global variables
	int x, y;
	int xDirection, yDirection;
	
	//score
	int p1Score, p2Score;
	
	Paddle p1 = new Paddle(15, 140, 1);
	Paddle p2 = new Paddle(470, 140, 2);
	
	Rectangle ball;
	
	
	public Ball(int x, int y) {
		p1Score = 0;
		p2Score = 0;
		this.x = x;
		this.y = y;
		// set ball moving randomly
		Random r = new Random();
		int rDir = r.nextInt(2);
		if(rDir == 0)
			rDir--;
		setXDirection(rDir);
		int yrDir = r.nextInt(2);
		if(yrDir == 0)
			yrDir--;
		setYDirection(yrDir);
		//create ball
		ball = new Rectangle(this.x, this.y, 7, 7);
		
	}
	
	
	// Methods to set the x and y direction of the ball
	public void setXDirection(int xdir) {
		xDirection = xdir;
	}
	
	public void setYDirection(int ydir) {
		yDirection = ydir;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(ball.x, ball.y, ball.width, ball.height);
	}
	
	public void collision() {
		if(ball.intersects(p1.paddle))
			setXDirection(+1);
		if(ball.intersects(p2.paddle))
			setXDirection(-1);
	}
	
	public void move() {
		collision();
		ball.x += xDirection;
		ball.y += yDirection;
		//Bounce ball when edge is detected
		if(ball.x <= 0) {
			setXDirection(+1);
			p2Score++;
		}
		
		if(ball.x >= 480) {
			setXDirection(-1);
			p1Score++;
		}
		
		if(ball.y <= 20) 
			setYDirection(+1);
		if(ball.y >= 385) 
			setYDirection(-1);
			
	}

	
	@Override
	public void run() {
		try {
			while(true) {
				move();
				Thread.sleep(3);
				
			}
		
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
	}

}
