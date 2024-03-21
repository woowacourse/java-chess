package chess.view;

import chess.domain.Point;
import chess.domain.piece.Piece;
import java.util.Map;

public class OutputView {

    private static final String ERROR_SUFFIX = "[ERROR]";

    public void printBoard(Map<Point, Piece> board) {
        StringBuilder builder = new StringBuilder();

        for (int rank = 8; rank > 0; rank--) {
            for (char file = 'a'; file <= 'h'; file++) {
                Piece piece = board.get(new Point(file, rank));
                if (piece == null) {
                    builder.append(".");
                    continue;
                }
                builder.append(piece.getName());
            }
            builder.append(System.lineSeparator());
        }

        System.out.println(builder);
    }

    public void printGameStart() {
        System.out.println("""
                > 체스 게임을 시작합니다.
                > 게임 시작 : start
                > 게임 종료 : end
                > 게임 이동 : move source위치 target위치 - 예. move b2 b3""");
    }

    public void printErrorMessage(String errorMessage) {
        System.out.printf("%s %s%n", ERROR_SUFFIX, errorMessage);
    }
}
