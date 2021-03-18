package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import chess.domain.position.Target;

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
    public void move(final Target target, final ChessBoard chessBoard) {
        //todo : 위치를 움직일 수 있는지 체크한다.
        changePosition(target.getPosition());
        chessBoard.changePiecePosition(this, target.getPosition());
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
