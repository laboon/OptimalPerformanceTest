import java.util.ArrayList;
import java.io.*;

public class Bill {

    public static void main(String[] args) {
	System.out.println("........................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................\nStart: 2105460472");

	String line;
	ArrayList<String> fileData = new ArrayList<String>();
	
	try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));

            while((line = bufferedReader.readLine()) != null) {
		fileData.add(line);
            }   

        }
        catch(Exception ex) {
            System.out.println("Problem reading file: " + args[0]);
	}

	int toReturn = 0;
	int lineTotal = 0;
	int len;
	int offset = 0;

	
	StringBuilder sb = new StringBuilder();
    	for (String s: fileData) {
	    len = s.length();
	    if (len != 0) {
		toReturn = 0;

		for (int j = 0; j < len; j++) {

		    sb.append(".");
		    if (offset != 0) {
			toReturn += ((byte) s.charAt(j));
		    } else {
			offset = 1;
			toReturn += ((byte) s.charAt(j)) + 99;
		    }

		}
		sb.append("... done!\n");
		lineTotal += toReturn % len;
	    }
	}

	sb.append(lineTotal % 100);
	
	System.out.println(sb.toString());

	
    }
    
}
