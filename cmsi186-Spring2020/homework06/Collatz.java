/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  Collatz.java
 * Purpose    :  Find the "nth" Fibonacci number given an argument, using BrobInt class
 * @author    :  B.J. Johnson
 * Date       :  2017-04-17
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-17  B.J. Johnson  Initial writing and begin coding
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Collatz {

   private static final String usageMessage = "\n  You must enter a valid Brobdingnagian Integer....." +
                                              "\n    Please try again!" +
                                              "\n  USAGE: java Collatz <required_brobdingnagian_integer>\n\n";
   private int     stepCount  = 0;
   private int     working    = 15000;
   private BrobInt calcValue  = null;
   private BrobInt startValue = null;
   private static final boolean DEBUG_ON = false;
   private static final boolean INFO_ON  = false;

  /**
   * Constructor doesn't really do anything
   */
   public Collatz() {
      // just to see if we're grown-ups....
   }

  /**
   * main method to run the program
   * @param args String array of arguments from the command line
   */
   public static void main( String[] args ) {
      Collatz c = new Collatz();
      Halver h = new Halver();

      System.out.println( "\n\n   Welcome to the Collatz sequence step counter!\n" );
      if( 0 == args.length ) {
         System.out.println( usageMessage );
         System.exit( -1 );
      }
      try {
         c.startValue = new BrobInt( args[0] );
         c.calcValue  = new BrobInt( args[0] );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n   Sorry, that does not compute!!" + usageMessage );
         System.exit( -2 );
      }

      System.out.println( "\n\n   Finding steps in the Collatz sequence" +
                          " for Brobdingangian Integer: " + c.startValue.toString() + "\n\n" );

      while( ! c.calcValue.equals( BrobInt.ONE )) {
         if( INFO_ON ) { System.out.println( "  At top of loop....." ); }

         // determine if this is even or odd; done by halving the number, then multiplying the result
         //   by TWO.  If the result is equal to the original, it's EVEN, if not it's ODD.
         if( c.calcValue.equals( new BrobInt( h.halve( c.calcValue.toString() ) ).multiply( BrobInt.TWO ) ) ) {
            if( DEBUG_ON ) { System.out.println( "    EVEN: Remainder of " + c.calcValue.toString() +
                                " and BrobInt.TWO: "  + c.calcValue.remainder( BrobInt.TWO ).toString() ); }
            c.calcValue = new BrobInt( h.halve( c.calcValue.toString() ) );
         } else {
            if( DEBUG_ON ) { System.out.println( "    ODD: Remainder of " + c.calcValue.toString() +
                                   " and BrobInt.TWO: "  + c.calcValue.remainder( BrobInt.TWO ).toString() ); }

               c.calcValue = c.calcValue.multiply( BrobInt.THREE );
            if( DEBUG_ON ) { System.out.println( "    ODD: multiplied by three, value is: " + c.calcValue.toString() ); }
               c.calcValue = c.calcValue.add( BrobInt.ONE );
            if( DEBUG_ON ) { System.out.println( "    ODD: added one, value is: " + c.calcValue.toString() ); }
         }

         c.stepCount++;
         if( INFO_ON ) { System.out.println( "     end of loop: calcValue is : " + c.calcValue.toString() + " and stepCount is: " + c.stepCount ); }
      }
      System.out.println( "   The calculation took " + c.stepCount + " steps to reach '1'...\n\n" );
      System.exit( 0 );
   }
}
