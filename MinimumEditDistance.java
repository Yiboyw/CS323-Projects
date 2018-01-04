public class MinimumEditDistance
//referenced from http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/DynProg/Progs/MED/MED_dp.java

{
    static int[][] T; 

    static int compT(String x, String y)
    {
       int sol1, sol2, sol3;
       int i, j;


       /* ---------------------------
	  Base cases
	  --------------------------- */
       for ( i = 0; i <= x.length(); i++ )
          T[i][0] = i;
       for ( j = 0; j <= y.length(); j++ )
          T[0][j] = j;


       /* ------------------------
       Divide step
       ------------------------ */
       for ( i = 1; i <= x.length(); i++ )
          for ( j = 1; j <= y.length(); j++ )
	  {
             if ( x.charAt(i-1) == y.charAt(j-1) )
	     {
		sol1 = T[i-1][j-1];

                T[i][j] = sol1;
	     }
	     else
	     {
                /* ------------------------
                   Divide step
                   ------------------------ */
                sol1 = T[i-1][j];    // Try delete step as last
                sol2 = T[i][j-1];    // Try insert step as last
                sol3 = T[i-1][j-1];  // Try replace step as last           
         
                /* ---------------------------------------
                   Conquer: solve original problem using
                   solution from smaller problems
                   --------------------------------------- */
                sol1 = sol1 + 1;
                sol2 = sol2 + 1;
                sol3 = sol3 + 1;
         
                if ( sol1 <= sol2 && sol1 <= sol3 )
                   T[i][j] = sol1;
         
                if ( sol2 <= sol1 && sol2 <= sol3 )
                   T[i][j] = sol2;
         
                if ( sol3 <= sol1 && sol3 <= sol2 )
                   T[i][j] = sol3;
	     }
	  }
      
       return(T[x.length()][y.length()]);   
   }


   public static void main(String[] args)
   {

//     String x = "man";
//     String y = "moon";

       String x = "kitten";
       String y = "sitting";

       int i, j, r;

       T = new int[x.length() + 1][y.length() + 1];

       r = compT(x, y);

       System.out.println("Min. Edit Distance = " + r);

       System.out.println();
       System.out.println();

       System.out.println("T[][]:");
       System.out.print("     ");
       for (j = 0; j < y.length()+1; j++)
          System.out.print("  " + j);
       System.out.println();
       System.out.println("==================================");

       for (i = 0; i < x.length()+1; i++)
       {
          if ( i < 10 )
             System.out.print(" ");
          System.out.print(" " + i + "  ");

          for (j = 0; j < y.length()+1; j++)
             System.out.print("  " + T[i][j]);
          System.out.println();
       }

   }
}