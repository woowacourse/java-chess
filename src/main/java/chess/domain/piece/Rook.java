package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;

public class Rook extends Piece {
    private static final String SYMBOL = "Rr";

    private Rook(final String piece, final Position position, final Color color) {
        super(piece, position, color);
    }

    public static Rook from(final String piece, final Position position) {
        validate(piece);
        if (isBlack(piece)) {
            return new Rook(piece, position, Color.BLACK);
        }
        return new Rook(piece, position, Color.WHITE);
    }

    @Override
    public void move(final Position position, final ChessBoard chessBoard) {

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
