
import gui.Simulable ;
import java.awt.* ;
import gui.Oval ;
import gui.GUISimulator ;
import java.awt.Color ;
import gui.Rectangle ;






public class BallsSimulator extends GUISimulator implements Simulable {
    Balls b ;
    

    // public void init()
    // {

    //     BallsSimulator b1 = new BallsSimulator(500, 500, Color.BLACK);
    //     b1.setSimulable(new BallsSimulator(b.p,b.nbballs)) ;
    // }
    public void ballsinit()
    {

        for(int i = 0 ; i < b.p.length ; i++)
        {
            addGraphicalElement(new Oval( (int)b.p[i].getX() , (int)b.p[i].getY() ,Color.RED,Color.RED,20)) ;
        }
    }

    public BallsSimulator(Balls b)
    {
        super(500,500,Color.BLACK) ;
        this.b = new Balls(b) ;
        this.ballsinit() ;
        setSimulable();
        // b.init_single_ball(10,10);
    }
    
    @Override
    public void next()
    {
        
        this.eraseballs() ;

        b.translate(10,10);
        

        for(int i = 0 ; i < b.p.length ; i++)
        {
            addGraphicalElement(new Oval( (int)b.p[i].getX() , (int)b.p[i].getY() ,Color.RED,Color.RED,1)) ;
        }
         System.out.println(b.toString()) ;
    }

    @Override
    public void restart()
    {
        eraseballs();
        ballsinit() ;

        System.out.println(b.toString()) ;
    }

    public void eraseballs()
    {

        reset();
        
    }


}