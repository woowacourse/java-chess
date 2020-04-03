package chess.piece;

import chess.validator.KingMoveValidator;

public class King extends Piece {
    public King(Team team) {
        super(team, "K", new KingMoveValidator(), 0);
    }
}
