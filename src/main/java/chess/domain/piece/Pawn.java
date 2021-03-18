package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;

public class Pawn extends Piece {
    private static final String SYMBOL = "Pp";

    private Pawn(final String piece, final Position position, final Color color) {
        super(piece, position, color);
    }

    public static Pawn from(final String piece, final Position position) {
        validate(piece);
        if (isBlack(piece)) {
            return new Pawn(piece, position, Color.BLACK);
        }
        return new Pawn(piece, position, Color.WHITE);
    }

    @Override
    public void move(final Position target, final ChessBoard chessBoard) {
        validateTarget(target, chessBoard);
        changePosition(target);
    }

    private void validateTarget(final Position target, final ChessBoard chessBoard) {
        Piece targetPiece = chessBoard.findPiece(target);
        if (this.isSameColor(targetPiece)) {
            throw new IllegalArgumentException(String.format("같은 색깔의 기물 위치로는 이동할 수 없습니다. 입력 위치: %s", target));
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    private static void validate(final String piece) {
        if (!SYMBOL.contains(piece)) {
            throw new IllegalArgumentException(String.format("옳지 않은 기물입니다! 입력 값: %s", piece));
        }
        if (piece.length() > 1) {
            throw new IllegalArgumentException(String.format("옳지 않은 기물입니다! 입력 값: %s", piece));
        }
    }
}
