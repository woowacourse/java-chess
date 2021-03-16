package chess.view;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;
import java.util.TreeMap;

public class OutputView {

    public static void printStartGameMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
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
}
