package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.movepattern.QueenMovePattern;
import chess.domain.position.Position;
import java.util.List;

public class Queen implements Piece {

    private final LinearPiece linearPiece;

    public Queen(final Side side) {
        this.linearPiece = new LinearPiece(Type.QUEEN, side, List.of(QueenMovePattern.values()));
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
    public boolean isPawn() {
        return linearPiece.isPawn();
    }

    @Override
    public void changePawnMoveState() {
        linearPiece.changePawnMoveState();
    }
}
