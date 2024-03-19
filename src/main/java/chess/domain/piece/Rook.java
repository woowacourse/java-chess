package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;

public class Rook extends Piece {
//    private static final List<Direction> directions = List.of(Direction.N, Direction.E, Direction.S, Direction.W);

    public Rook(Position position, Team team) {
        super(position, new PieceMeta(team, PieceType.ROOK));
    }

    @Override
    public void move(Position position) {

    }
}
