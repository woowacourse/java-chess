package chess.domain.piece.linear;

import chess.domain.board.Board;
import chess.domain.movepattern.RookMovePattern;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.piece.Type;
import chess.domain.position.Position;
import java.util.List;

public class Rook implements Piece {

    private final LinearPiece linearPiece;

    public Rook(final Side side) {
        this.linearPiece = new LinearPiece(Type.ROOK, side, List.of(RookMovePattern.values()));
    }

    @Override
    public List<Position> findMovablePosition(final Position source, final Board board) {
        return linearPiece.findMovablePosition(source, board);
    }

    @Override
    public String name() {
        return linearPiece.name();
    }

    @Override
    public Side side() {
        return linearPiece.side();
    }

    @Override
    public double price() {
        return linearPiece.price();
    }

    @Override
    public boolean isPawn() {
        return linearPiece.isPawn();
    }

    @Override
    public void changePawnMoveState() {
        linearPiece.changePawnMoveState();
    }
}
