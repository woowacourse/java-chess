package chess.view;

import chess.domain.Position;
import chess.domain.piece.character.Character;
import java.util.Map;

public class OutputView {
    public static final String EMPTY_POSITION = ".";

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : %s".formatted(Command.START.getValue()));
        System.out.println("> 게임 종료 : %s".formatted(Command.END.getValue()));
        System.out.println("> 게임 이동 : %s source위치 target위치 - 예. %s b2 b3"
                .formatted(Command.MOVE.getValue(), Command.MOVE.getValue()));
    }

    public static void printChessBoard(Map<Position, Character> piecesCharacter) {
        for (int row = 8; row >= 1; row--) {
            printChessRow(piecesCharacter, row);
        }
        System.out.println();
    }

    private static void printChessRow(Map<Position, Character> piecesCharacter, int row) {
        for (int column = 1; column <= 8; column++) {
            System.out.print(pieceToString(piecesCharacter, Position.of(row, column)));
        }
        System.out.println();
    }

    private static String pieceToString(Map<Position, Character> piecesCharacter, Position position) {
        if (piecesCharacter.containsKey(position)) {
            Character character = piecesCharacter.get(position);
            return CharacterViewer.convertToString(character);
        }
        return EMPTY_POSITION;
    }

    public static void printCheck() {
        System.out.println("체크 !" + System.lineSeparator());
    }
}
