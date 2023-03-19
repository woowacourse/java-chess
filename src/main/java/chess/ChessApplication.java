package chess;

import chess.view.InputView;
import java.util.Scanner;

public class ChessApplication {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final InputView inputView = new InputView(scanner);
    }
}
