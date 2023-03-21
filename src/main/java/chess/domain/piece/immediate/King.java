package chess.domain.piece.immediate;

import chess.domain.board.Board;
import chess.domain.movepattern.KingMovePattern;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.piece.Type;
import chess.domain.position.Position;
import java.util.List;

public class King implements Piece {

    private final ImmediatePiece immediatePiece;

    public King(final Side side) {
        this.immediatePiece = new ImmediatePiece(Type.KING, side, List.of(KingMovePattern.values()));
    }

    @Override
    public List<Position> findMovablePosition(final Position source, final Board board) {
        return immediatePiece.findMovablePosition(source, board);
    }

    @Override
    public String name() {
        return immediatePiece.name();
    }

    @Override
    public Side side() {
        return immediatePiece.side();
    }

    @Override
    public boolean isPawn() {
        return immediatePiece.isPawn();
    }

    @Override
    public void changePawnMoveState() {
        immediatePiece.changePawnMoveState();
    }
}
