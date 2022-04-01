package chess.view;

import java.util.Scanner;

public final class Enter implements Enterable {

    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public String enter() {
        return SCANNER.nextLine();
    }
}

