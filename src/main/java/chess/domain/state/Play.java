package chess.domain.state;

import chess.domain.board.Pieces;
import chess.domain.piece.Color;
import chess.domain.piece.Position;

public class Play extends WinnerBlank {

    public Play(final Color color, final Pieces pieces) {
        super(color, pieces);
    }

    @Override
    public State movePiece(final Position sourcePosition, final Position targetPosition) {
        pieces.movePiece(sourcePosition, targetPosition, this);
        if (pieces.isKillKing()) {
            return new Finish(color, pieces);
        }
        color = color.next();
        return this;
    }
}
