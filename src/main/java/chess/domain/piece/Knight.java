package chess.domain.piece;

import java.util.Set;

import chess.domain.game.Team;
import chess.domain.move.Direction;
import chess.domain.move.Move;

public class Knight extends Piece {

    public Knight(Team team) {
        super(team, setUpMoves());
    }

    private static Set<Move> setUpMoves() {
        return Set.of(
                new Move(Direction.RIGHT, Direction.UP, Direction.UP),
                new Move(Direction.RIGHT, Direction.RIGHT, Direction.UP),
                new Move(Direction.RIGHT, Direction.RIGHT, Direction.DOWN),
                new Move(Direction.RIGHT, Direction.DOWN, Direction.DOWN),
                new Move(Direction.LEFT, Direction.DOWN, Direction.DOWN),
                new Move(Direction.LEFT, Direction.LEFT, Direction.DOWN),
                new Move(Direction.LEFT, Direction.LEFT, Direction.UP),
                new Move(Direction.LEFT, Direction.UP, Direction.UP)
        );
    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }
}
