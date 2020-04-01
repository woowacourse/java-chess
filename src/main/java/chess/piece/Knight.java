package chess.piece;

import chess.validator.KnightMoveValidator;

public class Knight extends Piece {
    public Knight(Team team) {
        super(team, "N", new KnightMoveValidator(), 2.5);
    }
}
