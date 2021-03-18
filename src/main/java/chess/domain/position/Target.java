package chess.domain.position;

import chess.domain.board.ChessBoard;

import java.util.Objects;

public class Target {
    private final Position position;

    private Target(final Position position) {
        this.position = position;
    }

    public static Target valueOf(final Source source, final Position target, final ChessBoard chessBoard) {
        validateTarget(source, target, chessBoard);
        return new Target(target);
    }

    private static void validateTarget(final Source source, final Position target, final ChessBoard chessBoard) {
        if (isEmpty(target, chessBoard)) {
            return;
        }
        if (source.isSameColor(chessBoard.findPiece(target))) {
            throw new IllegalArgumentException(String.format("같은 색깔의 기물 위치로는 이동할 수 없습니다. 입력 위치: %s", target));
        }
        if (source.isSamePosition(target)) {
            throw new IllegalArgumentException(String.format("source위치와 같은 위치로는 이동할 수 없습니다. 입력 위치: %s", target));
        }
    }

    private static boolean isEmpty(Position target, ChessBoard chessBoard) {
        return Objects.isNull(chessBoard.findPiece(target));
    }

    public Position getPosition() {
        return position;
    }
}
