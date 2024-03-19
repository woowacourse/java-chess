package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;

public class Queen extends Piece{

    public Queen(Position position, Team team) {
        super(position, new PieceMeta(team, PieceType.QUEEN));
    }

    @Override
    public void move(Position position) {

    }
}
