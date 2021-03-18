package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import chess.domain.position.Target;

public class King extends Piece {
    private static final String SYMBOL = "Kk";

    private King(final String piece, final Position position, final Color color) {
        super(piece, position, color);
    }

    public static King from(final String piece, final Position position) {
        validate(piece);
        if (isBlack(piece)) {
            return new King(piece, position, Color.BLACK);
        }
        return new King(piece, position, Color.WHITE);
    }


    @Override
    public void move(final Target target, final ChessBoard chessBoard) {

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
