package chess.domain.piece;

import java.util.Set;

import chess.domain.game.Team;
import chess.domain.move.Direction;
import chess.domain.move.Move;

public class Bishop extends InfinitePiece {

    public Bishop(Team team) {
        super(team, setUpMoves());
    }

    private static Set<Move> setUpMoves() {
        return Set.of(
                new Move(Direction.UP, Direction.LEFT),
                new Move(Direction.UP, Direction.RIGHT),
                new Move(Direction.DOWN, Direction.LEFT),
                new Move(Direction.DOWN, Direction.RIGHT)
        );
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }
}
