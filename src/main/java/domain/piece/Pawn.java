package domain.piece;

import domain.ChessVector;
import domain.Team;
import domain.square.Rank;
import domain.square.Square;

import java.util.List;
import java.util.Objects;

public class Pawn extends Piece {

    private static final List<ChessVector> BLACK_ATTACK_VECTORS = List.of(new ChessVector(1, -1),
            new ChessVector(-1, -1));
    private static final List<ChessVector> WHITE_ATTACK_VECTORS = List.of(new ChessVector(-1, 1),
            new ChessVector(1, 1));
    private static final ChessVector BLACK_START_VECTOR = new ChessVector(0, -2);
    private static final ChessVector WHITE_START_VECTOR = new ChessVector(0, 2);
    private static final ChessVector BLACK_MOVE_VECTOR = new ChessVector(0, -1);
    private static final ChessVector WHITE_MOVE_VECTOR = new ChessVector(0, 1);
    private static final Rank BLACK_START_RANK = Rank.SEVEN;
    private static final Rank WHITE_START_RANK = Rank.TWO;

    public Pawn(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        final ChessVector chessVector = target.calculateVector(source);

        Rank startRank = BLACK_START_RANK;
        ChessVector startChessVector = BLACK_START_VECTOR;
        ChessVector moveChessVector = BLACK_MOVE_VECTOR;

        if (team == Team.WHITE) {
            startRank = WHITE_START_RANK;
            startChessVector = WHITE_START_VECTOR;
            moveChessVector = WHITE_MOVE_VECTOR;
        }

        return (source.isRank(startRank) && startChessVector.equals(chessVector))
                || chessVector.equals(moveChessVector);
    }

    @Override
    public boolean canAttack(final Square source, final Square target) {
        final ChessVector chessVector = target.calculateVector(source);

        List<ChessVector> attackChessVectors = BLACK_ATTACK_VECTORS;

        if (team == Team.WHITE) {
            attackChessVectors = WHITE_ATTACK_VECTORS;
        }

        return attackChessVectors.contains(chessVector);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final Pawn piece)) {
            return false;
        }
        return this.team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, Pawn.class);
    }
}
