package chess.View;

import chess.domain.chessBoard.ChessBoard;

import java.util.Scanner;

public class ChessInputView {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static String inputCommand(ChessBoard chessBoard) {
        System.out.printf("%s의 턴 command: ", chessBoard.getPlayerColor());
        return SCANNER.nextLine();
    }

    public static String inputCommand() {
        System.out.print("command: ");
        return SCANNER.nextLine();
    }
}
