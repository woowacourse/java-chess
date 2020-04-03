package chess.piece;

import chess.validator.BishopMoveValidator;

public class Bishop extends Piece {
    public Bishop(Team team) {
        super(team, "B", new BishopMoveValidator(), 3);
    }
}
