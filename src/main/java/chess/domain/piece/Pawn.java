package chess.domain.piece;

import static chess.domain.move.Direction.DOWN;
import static chess.domain.move.Direction.LEFT;
import static chess.domain.move.Direction.RIGHT;
import static chess.domain.move.Direction.UP;

import java.util.Map;
import java.util.Set;

import chess.domain.game.Team;
import chess.domain.move.Move;

public class Pawn extends Piece {

    private static final Map<Team, Set<Move>> UNTOUCHED_MOVES = Map.of(
            Team.WHITE, Set.of(new Move(UP), new Move(UP, UP)),
            Team.BLACK, Set.of(new Move(DOWN), new Move(DOWN, DOWN))
    );
    private static final Map<Team, Set<Move>> TOUCHED_MOVES = Map.of(
            Team.WHITE, Set.of(new Move(UP)),
            Team.BLACK, Set.of(new Move(DOWN))
    );
    private static final Map<Team, Set<Move>> ATTACK_MOVES = Map.of(
            Team.WHITE, Set.of(new Move(UP, LEFT), new Move(UP, RIGHT)),
            Team.BLACK, Set.of(new Move(DOWN, RIGHT), new Move(DOWN, LEFT))
    );
    private static final int UNTOUCHED_MOVE_SIZE = 2;

    public Pawn(Team team) {
        super(team, setUpUntouchedMoves(team));
    }

    private Pawn(Team team, Set<Move> moves) {
        super(team, moves);
    }

    private static Set<Move> setUpUntouchedMoves(Team team) {
        return UNTOUCHED_MOVES.get(team);
    }

    private static Pawn createTouched(Team team) {
        return new Pawn(team, TOUCHED_MOVES.get(team));
    }

    @Override
    public Piece touch() {
        if (isUntouched()) {
            return createTouched(team);
        }
        return this;
    }

    @Override
    public double score() {
        return 1;
    }

    private boolean isUntouched() {
        return moves.size() == UNTOUCHED_MOVE_SIZE;
    }

    @Override
    public boolean hasAttackMove(Move move) {
        return getAttackMoves().stream()
                .anyMatch(it -> compareMove(it, move));
    }

    private Set<Move> getAttackMoves() {
        return ATTACK_MOVES.get(team);
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }
}
