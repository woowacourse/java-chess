package chess.piece;

import chess.validator.RookMoveValidator;

public class Rook extends Piece {
    public Rook(Team team) {
        super(team, "R", new RookMoveValidator(), 5);
    }
}
