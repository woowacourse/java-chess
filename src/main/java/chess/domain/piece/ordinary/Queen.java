package chess.domain.piece.ordinary;

import static chess.domain.piece.PieceType.QUEEN;

import chess.domain.Team;
import chess.domain.piece.ordinary.OrdinaryPiece;

public class Queen extends OrdinaryPiece {
    public Queen(final Team team) {
        super(team, QUEEN);
    }
}
