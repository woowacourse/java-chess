package chess.domain.piecerefactor;

import chess.domain.board.Board;
import chess.domain.movepattern.KnightMovePattern;
import chess.domain.piece.Side;
import chess.domain.piece.Type;
import chess.domain.position.Position;
import java.util.List;

public class Knight implements Piece {

    private final ImmediatePiece immediatePiece;

    public Knight(final Side side) {
        this.immediatePiece = new ImmediatePiece(Type.KNIGHT, side, List.of(KnightMovePattern.values()));
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
}
