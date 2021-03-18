package chess.view;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;
import java.util.TreeMap;

public class OutputView {

    private static final String MESSAGE_PREFIX = "> ";
    private static final String ERROR_PREFIX = "[ERROR] ";

    public static void printStartGameMessage() {
        System.out.println(MESSAGE_PREFIX + "체스 게임을 시작합니다.");
        System.out.println(MESSAGE_PREFIX + "게임 시작 : start");
        System.out.println(MESSAGE_PREFIX + "게임 종료 : end");
        System.out.println(MESSAGE_PREFIX + "게임 이동 : move source 위치 target 위치 - 예. move b2 b3");
    }

    public static void printCurrentBoard(final Map<Position, Piece> chessBoard) {
        final Map<Position, Piece> board = new TreeMap<>(chessBoard);
        int lastVerticalValue = 8;
        for (final Position position : board.keySet()) {
            lastVerticalValue = updateLastVerticalValue(lastVerticalValue, position);
            System.out.print(board.get(position).getName());
        }
        System.out.println();
    }

    private static int updateLastVerticalValue(final int before, final Position position) {
        int newValue = before;
        if (position.getVertical().getValue() != before) {
            newValue = position.getVertical().getValue();
            System.out.println();
        }
        return newValue;
    }

    public static void printErrorMessage(final String message) {
        System.out.println(ERROR_PREFIX + message);
    }
}
