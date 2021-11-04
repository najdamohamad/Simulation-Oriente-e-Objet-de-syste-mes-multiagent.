    

    import java.awt.* ;
    
    public class TestBalls {

    public static void main ( String[] args){
      Point p1 = new Point(1,1) ;
      Point p2 = new Point(1,2) ;
      Point p3 = new Point(1,3) ;
      Point p4 = new Point(4,1) ;
      Point p5 = new Point(2,1) ;
      Point p0 = new Point(3,1) ;
      
      Balls b = new Balls( );
      b.nbballs = 6 ;
      b.p = new Point[6];
      b.p[1] = p1 ;
      b.p[2] = p2 ;
      b.p[3] = p3 ;
      b.p[4] = p4 ;
      b.p[5] = p5 ;
      b.p[0] = p0 ;

      System.out.println(p1.toString()) ;
      System.out.println(p2.toString()) ;
      System.out.println(p3.toString()) ;

      // b.translate(5,5);

      // System.out.println(p1.toString()) ;
      // System.out.println(p2.toString()) ;
      // System.out.println(p3.toString()) ;

      // b.reInit();

      // System.out.println(p1.toString()) ;
      // System.out.println(p2.toString()) ;
      // System.out.println(p3.toString()) ;

      System.out.println(b.toString()) ; 


    }

}





      
      