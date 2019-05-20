package gui;

import system.CAgent;
import system.CBase;
import system.CEnvironement;
import system.CNourriture;
import system.CZoneAEviter;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

public class CMainPanel extends JPanel implements Observer,MouseListener  {
    private static final int BASE_COUNT = 3;
    private static final int AGENTS_COUNT = 30;
    private static final int NOURRITURE_COUNT = 2;
   // private static final int AGENT_WIDTH = 3;
   // private static final int AGENT_HEIGHT = 3;

    private static final int TIMER_DELAY = 0;
    private static final int TIMER_PERIOD = 10;
    int incrColor =0;
    public Color[] mArrayColor = {Color.BLUE,Color.CYAN,Color.GRAY,Color.GREEN,Color.LIGHT_GRAY,Color.MAGENTA,Color.ORANGE,Color.PINK,Color.RED,Color.WHITE,Color.YELLOW};
    
    private Timer mTimer;
    boolean mInProgress = false;
    private TimerTask mTask;
    private CEnvironement mEnv;


    public CMainPanel() {
        // Fond noir.
    	this.setBackground(new Color(128, 128, 128));
    	this.addMouseListener(this);
    }
    
    public void launch() {
        mEnv = CEnvironement.getInstance();
        mEnv.init(BASE_COUNT, AGENTS_COUNT, getWidth(), getHeight(),NOURRITURE_COUNT);
        mEnv.addObserver(this);
        mTimer = new Timer();
        mTask = new TimerTask()
        {
        	@Override
        	public void run() {mEnv.update();}
        };
        mTimer.scheduleAtFixedRate(mTask, TIMER_DELAY, TIMER_PERIOD);
        		
    }

    @Override
    public void update(Observable pObservable, Object pArg) 
    {
    	this.repaint();
    }

    @Override
    public void paintComponent(Graphics pG) {
        super.paintComponent(pG);
        for(CBase b : mEnv.mBaseList)
        {
        	b.afficherBase(pG);
        	b.afficherAgents(pG);
        }
        for(CNourriture n : mEnv.mNourritureList)
        {
        	n.afficher(pG);
        }   
    }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {    	
    	System.out.println("ouai");
    	if(incrColor > 10)
    		incrColor=0;
    	else
    	{
    		mEnv.mBaseList.add(new CBase(e.getX(), e.getY(), AGENTS_COUNT, mArrayColor[incrColor], 10));
    		incrColor++;
    	}
    }
    
    @Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
    @Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
    
    
}
