package chess.model.piece;

import chess.model.position.ChessPosition;
import java.util.List;

public class Empty extends Piece {
    public Empty() {
        super(Side.EMPTY);
    }

    @Override
    public String getText() {
        return ".";
    }

    @Override
    public List<ChessPosition> findPath(
            final ChessPosition source, final ChessPosition target, final Piece targetPiece
    ) {
        return List.of();
    }
}
