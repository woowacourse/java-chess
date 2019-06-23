package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.Type;

public class Blank extends Piece {
    public Blank() {
        super(Team.BLANK, Type.BLANK);
    }
}
