package chess.view;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    public static final int FILE_SIZE = 8;
    public static final int FILE_END_NUMBER = 7;

    public static void printStartMessage() {
        System.out.println("체스 게임을 시작합니다." + NEW_LINE +
            "게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printBoard(final Map<Position, Piece> board) {
        final List<Position> pieces = new ArrayList<>(board.keySet());
        for (int i = 0; i < board.size(); i++) {
            final Piece piece = board.get(pieces.get(i));
            System.out.print(piece.getName());
            separateRank(i);
        }
        System.out.println();
    }

    private static void separateRank(final int index) {
        if (index % FILE_SIZE == FILE_END_NUMBER) {
            System.out.println();
        }
    }
}
