package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;

public class Bishop extends Piece{
    public Bishop(Position position, Team team) {
        super(position, new PieceMeta(team, PieceType.BISHOP));
    }

    @Override
    public void move(Position position) {

    }
}
