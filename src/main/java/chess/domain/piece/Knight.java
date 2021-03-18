package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;

public class Knight extends Piece {
    private static final String SYMBOL = "Nn";

    private Knight(final String piece, final Position position, final Color color) {
        super(piece, position, color);
    }

    public static Knight from(final String piece, final Position position) {
        validate(piece);
        if (isBlack(piece)) {
            return new Knight(piece, position, Color.BLACK);
        }
        return new Knight(piece, position, Color.WHITE);
    }

    @Override
    public void move(final Position position, final ChessBoard chessBoard) {

    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
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
