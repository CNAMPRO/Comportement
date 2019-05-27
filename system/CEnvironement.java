package system;

import java.awt.Color;
import java.util.*;

public class CEnvironement extends Observable {
	private static CEnvironement sInstance = null;
    protected Random mRandomGen;
    public double mWidth;
    public double mHeight;
    public ArrayList<CBase> mBaseList;
    public ArrayList<CNourriture> mNourritureList;
    public ArrayList<CZoneAEviter> mZoneAEviterList;
    
    //protected int mIterCounts = 0;

    private CEnvironement() {
    	mBaseList = new ArrayList<CBase>();
    	mNourritureList = new ArrayList<CNourriture>();
    	mZoneAEviterList = new ArrayList<CZoneAEviter>();
    
        // Création du générateur aléatoire.
        mRandomGen = new Random();
    }
    
    public static CEnvironement getInstance() {
		if(sInstance == null) {
			sInstance = new CEnvironement();
		}
		return sInstance;
	}
        
    public CNourriture catchNourriture(CNourriture pNourrite) {
        	pNourrite.decreaseSize();
            // TODO CNAM : 2 lignes de code Ã  ajouter ici.
        	return pNourrite;
    }
    
    public void init(int _nbBase, int _nbAgents, int x, int y, int _nbNourriture) 
    {  	
    	mWidth = x;
    	mHeight = y;
    	mBaseList.clear();
    	for(int i = 0; i < _nbBase; i++)
    	{
    		mBaseList.add(new CBase(x/10.0, y/10.0, _nbAgents, Color.RED, 10));
    	}
    		
    	mNourritureList.clear();
    	for(int i = 0; i < _nbNourriture; i++)
    	{
    		mNourritureList.add(new CNourriture(x/2.0, y/2.0, Color.BLACK, 2.0));
    	}
    	mZoneAEviterList.clear();
    }
    
    public void update()
    {
    	for(CBase b : mBaseList)
    	{
    		b.bougerAgents();
    	}
    	combatAgent();
    	setChanged();
    	notifyObservers();
    }
    
    public void combatAgent() {
    	for(CBase b : mBaseList) {
    		for(CAgent agent : b.fourmiz) {
    			searchAgentColision(b,agent);
    		}
    	}
    }
    
    public void searchAgentColision(CBase base, CAgent mAgent) {
    	for(CBase b : mBaseList) {
    		if(base!= b) {
    			for(CAgent agent : b.fourmiz) {
        			if((mAgent.posX == agent.posX) && (mAgent.posY == agent.posY)) {
        				if(mAgent.mCombat > agent.mCombat) {
        					b.killAgents(agent);
        				}
        				else if(mAgent.mCombat < agent.mCombat) {
        					b.killAgents(mAgent);
        				}
        				else {
        					b.killAgents(mAgent);
        					b.killAgents(agent);
        				}
        			}
        		}		
			}
    	}
    }
    
}