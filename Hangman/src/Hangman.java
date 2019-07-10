import java.util.Scanner;
import java.util.Random;

public class Hangman {

	public static void main(String[] args) {
		System.out.println("HANGMAN");
		
    	Scanner in = new Scanner(System.in);
		Hangman game = new Hangman();
		int turn = 1;
		int wrong = 0;
		int right = 0;
		
		// Word
		String word = word().toLowerCase();
		int length = word.length();
		String[] versions = new String[length+1];
		versions[0] = word;
		
		// Display
		String display = "";
	    for(int i=1;i<=length;i++){  
	        display += "_ ";
	    }  
	    System.out.println(display);
	    String[] displays = new String[length+1];
	    displays[0] = display;
	    
	    // Start Turn
	    while (wrong<6) {
		    if (!(game.turn(turn,wrong,in,versions,displays,right))) {
		    	wrong += 1;
		    }
		    else {
		    	right += 1;
		    }
		    turn += 1;
		    if (wrong == 6) {
		    	System.out.println("YOU LOSE");
		    }
		    if (versions[right].equals(word.toUpperCase())) {
		    	System.out.println("CONGRATS - YOU WIN!");
		    	break;
		    }
	    }
	    

    	in.close();
	    
	}
	// Choose Word
	public static String word() {
		String[] list = {"hello", "world", "couch", "school", "jazz", "apple", "night", "zebra", "broccoli", "television", "star"};
		Random rand = new Random();
		int index = rand.nextInt(list.length);
		return list[index];
	}
	
	// Draw Man
	public static void man(int count, boolean only) {
	    if (!only) {
	    	if (count==2 || count==3) {
		   		man(count-1,false);
		   	}
		   	if (count==4) {
		   		man(count-2,false);
		   	}
	    }
	    	
	    switch(count) {
	    case 1: //head
	    	System.out.println(" ____");
	    	System.out.println("|    |");
	    	System.out.println(" \\__/");
	    	break;
	    case 2: //body
	    	System.out.println("   |");
	    	System.out.println("   |");
	    	break;
	    case 3: //left leg
	    	System.out.println("  /");
	    	System.out.println("_/");
	    	break;
	    case 4: //both legs
	    	System.out.println("  / \\");
	    	System.out.println("_/   \\_");
	    	break;
	    case 5: //left arm
	    	man(1,true);
	    	System.out.println(" __|");
	    	System.out.println("/  |");
	    	man(4,true);
	    	break;
	    case 6: //both arms
	    	man(1,true);
	    	System.out.println(" __|__");
	    	System.out.println("/  |  \\");
	    	man(4,true);
	    	break;
	    		
	    }
	 }
	 
	 // Executes Turn
	 public boolean turn(int turn, int wrong, Scanner in, String[] versions, String[] displays, int right) {
	  	System.out.println("Guess a letter: ");
	  	String guess = in.nextLine();
   		String word = versions[right];
   		String display = displays[right];
   		String[] result = new String[word.length()];
   		StringBuilder stringBuilder = new StringBuilder();
	   	if (word.contains(guess)) {
	  		String newWord = word.replace(guess, guess.toUpperCase());
	  		for(int i = 0, n = newWord.length() ; i < n ; i++) { 
	  		    String c = "" + newWord.charAt(i);
	  		    if (c.equals(c.toLowerCase())) {
	  		    	result[i] = "_ ";
	  		    	stringBuilder.append(result[i]);
	  		    }
	  		    else {
	  		    	result[i] = (""+ word.charAt(i) + " ").toUpperCase();
	  		    	stringBuilder.append(result[i]);
	  		    }
	  		}
	  		String newDisplay = stringBuilder.toString();
	   		System.out.println(newDisplay);
	   		versions[right+1] = newWord;
	   		displays[right+1] = newDisplay;
	   		return(true);
	   	}
	   	else {
	   		wrong += 1;
	    	man(wrong,false);
	   		System.out.println(display);
	    	return(false);
	   	}
	 }
	

}
