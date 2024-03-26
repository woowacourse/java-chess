package chess.view;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.piece.Type;
import chess.domain.point.Point;

import java.util.Map;

public class OutputView {
    private static final String ERROR_SUFFIX = "[ERROR]";
    private static final Map<Type, String> NAME_CLASSIFIER = Map.of(
            Type.KING, "K",
            Type.QUEEN, "Q",
            Type.ROOK, "R",
            Type.BISHOP, "B",
            Type.KNIGHT, "N",
            Type.PAWN, "P",
            Type.EMPTY, "."
    );

    // TODO
    public void printBoard(Map<Point, Piece> board) {
        final StringBuilder builder = new StringBuilder();

        for (int rank = 8; rank > 0; rank--) {
            for (char file = 'a'; file <= 'h'; file++) {
                final Piece piece = board.get(new Point(file, rank));
                builder.append(findNameOf(piece));
            }
            builder.append(System.lineSeparator());
        }

        System.out.println(builder);
    }

    private String findNameOf(Piece piece) {
        final Type pieceType = piece.getType();
        final String name = NAME_CLASSIFIER.get(pieceType);

        final Team team = piece.getTeam();
        if (team.isWhite()) {
            return name.toLowerCase();
        }
        return name;
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
