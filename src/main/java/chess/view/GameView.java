package chess.view;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import chess.Color;
import chess.Position;
import chess.gameboard.GameBoard;
import chess.piece.Bishop;
import chess.piece.Piece;
import chess.request.StartPosAndEndPos;

public class GameView {
    private static final Scanner scanner = new Scanner(System.in);

    public static StartPosAndEndPos askMove() {
        Position startPos = askPositionOfWillMovePiece();
        Position endPos = askWherePositionToMove();
        return new StartPosAndEndPos(startPos, endPos);
    }

    private static Position askPositionOfWillMovePiece() {
        System.out.println("움직일 기물의 위치를 입력해주세요 (예시: a2)");
        String rawPosition = scanner.nextLine();
        return Position.from(rawPosition);
    }

    private static Position askWherePositionToMove() {
        System.out.println("움직이려는 위치를 입력해주세요");
        String rawPosition = scanner.nextLine();
        return Position.from(rawPosition);
    }

    public static void printBoard(GameBoard gameBoard) {
        List<String> cols = List.of("A", "B", "C", "D", "E", "F", "G", "H");
        List<String> rows = List.of("8", "7", "6", "5", "4", "3", "2", "1");

        Map<Position, Piece> board = gameBoard.getBoard();
        for (String row : rows) {
            for (String col : cols) {
                Position currentPos = Position.from(col + row);
                Piece piece = board.get(currentPos);
                if (piece == null) {
                    System.out.print("x");
                    continue;
                }
                System.out.print(piece.toString());
            }
            System.out.println();
        }
    }

    public static boolean doYouWantToPlayMore() {
        System.out.println("계속 하시겠습니까? (y/n)");
        return scanner.nextLine().trim().equalsIgnoreCase("y");
    }
}
