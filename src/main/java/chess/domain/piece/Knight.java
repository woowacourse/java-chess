package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;

public class Knight extends Piece {

    public Knight(Position position, Team team) {
        super(position, new PieceMeta(team, PieceType.KNIGHT));
    }

    @Override
    public void move(Position position) {

    }
}
