import java.util.ArrayList;
import java.io.*;

public class Bill {

    // Everything is in one method to reduce method calls, which
    // reduce performance by a small amount (since they involve
    // manipulation of the stack
    
    public static void main(String[] args) {

	// Minimize any String concatenation by constantly adding to
	// a StringBuilder.  note there's only one System.out.println
	// call; appending to a StringBuilder is much faster than
	// multiple outputs
	
	StringBuilder sb = new StringBuilder();
	
	// We know exactly how many .'s will be shown, and what the
	// final "Start" value will be; let's just hardcode them and append
	
	sb.append("........................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................\nStart: 2105460472\n");

	// I feel like there might be a better data structure to
	// use here than an ArrayList, but darned if I could think of
	// one.  Probably room for improvement here!  Can't be a
	// regular ol' Java array because we don't know the size of the
	// file, and reading it first to determine size would mean reading
	// the file twice which is certainly slower than any data
	// structure change.
	
	String line;
	ArrayList<String> fileData = new ArrayList<String>();

	// Read in the file.  Very little we can do here.
	
	try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));

            while((line = bufferedReader.readLine()) != null) {
		fileData.add(line);
            }   

        }
        catch(Exception ex) {
            System.out.println("Problem reading file: " + args[0]);
	}

	// Keeping all of these variables and ensuring we reset them
	// is going to be faster than constantly creating/deleting
	// them from the stack!
	
	int toReturn = 0;
	int lineTotal = 0;
	int len;
	int offset = 0;

	// I saw no measurable difference between using this
	// and using a for (int j =0; j < x; j++)-style loop

	// Looks like in general it doesn't matter, although for
	// some data types the for-each is faster: see
	// http://stackoverflow.com/questions/2113216/which-is-more-efficient-a-for-each-loop-or-an-iterator
	
    	for (String s: fileData) {

	    // Calling methods should be minimized.  We use
	    // s.length() twice, so instead call it once and
	    // cache the value in len.
	    
	    len = s.length();

	    // Recall that if the length of the line was 0,
	    // then we returned 0 (since ordinarily we returned
	    // the "value" of the line modulo the length of the
	    // line, but if the length of the line is 0, just return 0.
	    // Since the main goal is to get the final value of all
	    // the lines (being stored in lineTotal variable), we just
	    // don't do anything for that line; it's the same as
	    // adding 0 to the total.
	    
	    if (len != 0) {
		toReturn = 0;

		for (int j = 0; j < len; j++) {
		    
		    // For each char in the line, append another "."
		    
		    sb.append(".");

		    // This is the remnants of the BillUtil.value()
		    // method.  The first time the method was called,
		    // it would return 99; every other time, 0.
		    // So just use the offset flag and add 99 to
		    // the line's value (using the algorithm found
		    // in StrategyOne)

		    // On that note, StrategyTwo was a red herring!
		    // It produced different values because it
		    // ignored the value() method.

		    // switch statements can be faster than
		    // if..else statements, although the difference
		    // was very, very slight and may not be statistically
		    // significant
		    
		    switch (offset) {
		    case 1:
			toReturn += ((byte) s.charAt(j));
		    default:
			offset = 1;
			toReturn += ((byte) s.charAt(j)) + 99;
		    }

		}

		// Append our final done call along with a carriage return
		sb.append("... done!\n");

		// Increment lineTotal with this line's value modulo
		// the length of the line.  Remember we know at this
		// point that len != 0 (see the if statement above)
		// so we won't get an error.
		
		lineTotal += toReturn % len;
	    }
	}

	// The final value is printed modulo 100
	
	sb.append(lineTotal % 100);

	// Our one and only output!
	
	System.out.println(sb.toString());

	
    }
    
}
