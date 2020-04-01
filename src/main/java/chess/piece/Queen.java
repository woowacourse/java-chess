package chess.piece;

import chess.validator.QueenMoveValidator;

public class Queen extends Piece {
    public Queen(Team team) {
        super(team, "Q", new QueenMoveValidator(), 9);
    }
}
