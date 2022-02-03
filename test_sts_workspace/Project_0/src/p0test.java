
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p0test {
	static BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

	
	public static void main(String[] args) {
		boolean isRunning = true;
		
		while(isRunning) {
			System.out.println("Project 0 Course Registration\n" + "1. Test Option 1\n" + "2. Test Option 2\n" + "3. Test Option 3\n" + "---- ");
			
			try {
				String input = consoleReader.readLine();
				
				switch(input) {
				case "1":
					System.out.println("Test option 1 selected");
					break;
				case "2":
					System.out.println("Test option 2 selected");
					break;
				case "3":
					System.out.println("Test option 3 selected");
					break;
				default:
					System.out.println("Invalid selection. Exiting program");
					break;
				}
				
			} catch (IOException e){
				e.printStackTrace();
				isRunning = false;
			} //Can add finally block to send text every selection
		}
		
		try {
			consoleReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
