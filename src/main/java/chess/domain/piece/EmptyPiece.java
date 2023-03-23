package chess.domain.piece;

import java.util.Collections;

import chess.domain.game.Team;

public class EmptyPiece extends Piece {

    public EmptyPiece() {
        super(Team.NEUTRAL, Collections.emptySet());
    }

    @Override
    public PieceType getType() {
        return PieceType.EMPTY;
    }
}
