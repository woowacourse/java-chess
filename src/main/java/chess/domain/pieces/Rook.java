package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.Type;

public class Rook extends Piece {
    public Rook(Team team) {
        super(team, Type.ROOK);
    }
}