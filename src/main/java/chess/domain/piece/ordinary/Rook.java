package chess.domain.piece.ordinary;

import static chess.domain.piece.PieceType.ROOK;

import chess.domain.Team;
import chess.domain.piece.ordinary.OrdinaryPiece;

public class Rook extends OrdinaryPiece {
    public Rook(final Team team) {
        super(team, ROOK);
    }

}
