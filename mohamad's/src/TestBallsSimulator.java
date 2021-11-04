import gui.GUISimulator ;
import java.awt.Color ;
import java.awt.* ; 
import gui.Rectangle ;
import gui.Oval ;

public class TestBallsSimulator {
    public static void main(String[] args) {
        Point p1 = new Point(100,10) ;
        Point p2 = new Point(100,200) ;
        Point p3 = new Point(100,300) ;
        Point p4 = new Point(400,100) ;
        Point p5 = new Point(200,100) ;
        Point p0 = new Point(300,400) ;
        Point[] p = {p0 , p1 ,p2 ,p3 ,p4 ,p5 } ;
        int nb_balls = 6 ;
        Balls b = new Balls(p,nb_balls);
        
        BallsSimulator bs = new BallsSimulator(b);

    }
}