package console;

import java.util.Scanner;

public class Input {
    private final Scanner scanner = new Scanner(System.in);

    public String read(){
        return scanner.nextLine();
    }
}
