package chess.view;

import java.util.Scanner;
import chess.Position;
import chess.request.StartPosAndEndPos;

public class GameView {
    private static final Scanner scanner = new Scanner(System.in);

    public static StartPosAndEndPos askMove() {
        Position startPos = askPositionOfWillMovePiece();
        Position endPos = askWherePositionToMove();
        return new StartPosAndEndPos(startPos, endPos);
    }

    private static Position askPositionOfWillMovePiece() {
        System.out.println("움직일 기물의 위치를 입력해주세요 (예시: a3)");
        String rawPosition = scanner.nextLine();
        return Position.from(rawPosition);
    }

    private static Position askWherePositionToMove() {
        System.out.println("움직이려는 위치를 입력해주세요");
        String rawPosition = scanner.nextLine();
        return Position.from(rawPosition);
    }
}
