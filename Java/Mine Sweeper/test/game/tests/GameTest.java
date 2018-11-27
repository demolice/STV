/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tests;

import junit.framework.Assert;
import mine.sweeper.GameLogic.Game;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*; 

/**
 *
 * @author daniil.barabashev
 */
public class GameTest {
    
    public GameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    
     @Test
     public void isNewPiecesSameLenght() {
         int x = 10;
         
         Game g = new Game(x, 10, Game.EASY);
         int piecesCount = g.getPieces().length;
         
         assertEquals(x, piecesCount);
     }
     
     @Test
     public void isClickInRightTile() {
         
         
         double x = 1.5;
         double y = 1.5;
         
         Game g = new Game(10, 10, Game.EASY);
         
         g.canvasClick(x, y);
         
        
         
     }
}
