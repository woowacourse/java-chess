package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.RelativePosition;

public class EmptyPiece extends Piece {

    public EmptyPiece() {
        super(PieceType.EMPTY, Team.EMPTY);
    }


    @Override
    public boolean isMobile(RelativePosition relativePosition, Piece target) {
        return false;
    }
}
