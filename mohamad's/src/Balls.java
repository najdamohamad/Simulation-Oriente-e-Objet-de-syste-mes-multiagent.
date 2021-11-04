import java.awt.* ;
import gui.Oval ;
import gui.GUISimulator ;
import java.awt.Color ;


class Balls{

    int nbballs ;
    Point[] p ;
    int initx = 0 ;
    int inity = 0 ;
    int dir_dx ;
    int dir_dy ;
    int[] f1 = {1,-1,-1,-1,1,1};
    int[] f2  = {1,1,-1,1,-1,-1};

    public void set_f()
    {
       // f1 = new int[nbballs];
       // f2 = new int[nbballs];
        for(int i = 0; i < nbballs; i++) {
            f1[i] = 1;
            f2[i] = 1;
        }
     System.out.println(f1.length);
    }
    public Balls(Point[] p , int nbballs)
    {
        //set_f();
        this.p = p;
        this.nbballs = nbballs ;
        this.initx = 0 ;
        this.inity = 0 ;
    }

    public Balls( Balls b)
    {
       // set_f();
        this.p = b.p ;
        this.nbballs = b.nbballs ;
        initx = 0;
        inity = 0;
    }



    public void translate(int dx , int dy)
    {
        for(int i = 0 ; i< nbballs ; i++ )
        {
                if( p[i].getX() == 795 || p[i].getX() == 0)
            {
                f1[i] = -f1[i];
            }
                if( p[i].getY() == 490 || p[i].getY() == 0)
            {
                f2[i] = -f2[i];
            }

            if(f1[i]==1) 
                p[i].x = p[i].x + dx ;
            else 
                p[i].x = p[i].x - dx ;
            
            if(f2[i]==1)
                p[i].y = p[i].y + dy ;
            else
                p[i].y = p[i].y - dy ;
                
        }
      
     }

    // public void init_single_ball(int dx ,int dy)
    // {
    //     SingleBall sb[] = new SingleBall[nbballs];
    //     for(int i = 0; i< nbballs ; i++)
    //     {
    //         sb[i] = new SingleBall(p[i],dx,dy) ;
    //     }


    // }


    // public void translate(int dx ,int dy) 
    // {
    //     //init_single_ball(10,10);
    //     for(int i = 0 ; i < nbballs  ; i++)
    //     {
    //         if( p[i].getX() == 800 || p[i].getX() == 0)
    //         {
    //             dx = -dx ;
    //         }
    //         if( p[i].getY() == 490 || p[i].getY() == 0)
    //         {
    //             dy = -dy ;
    //         }

    //         sb[i] =  new SingleBall(p[i],dx,dy) ;
    //         sb[i].translate_single_ball(dx,dy) ;
           
    //     }
    // }

    public void reInit()
    {
        for(int i = 0 ; i < nbballs ;  i++ )
        {
            p[i].setLocation(initx,inity) ;
        }
        return ;
    }

    @Override
    public String toString()
    {

        String s = " ( " ;
        for(int i = 0 ; i< nbballs ; i++ )
        {
            s = s + p[i].toString()  ;
        }

        s = s + " ) " + " \n " ;
        return s ;
    }




    

}