package domain.piece;

import domain.ChessVector;
import domain.Rank;
import domain.Square;
import domain.Team;

import java.util.List;
import java.util.Objects;

public class Pawn extends Piece {
    private static final List<ChessVector> BLACK_ATTACK_SQUARE_VECTORS = List.of(new ChessVector(1, -1),
            new ChessVector(-1, -1));
    private static final List<ChessVector> WHITE_ATTACK_SQUARE_VECTORS = List.of(new ChessVector(-1, 1),
            new ChessVector(1, 1));
    private static final ChessVector BLACK_START_SQUARE_VECTOR = new ChessVector(0, -2);
    private static final ChessVector WHITE_START_SQUARE_VECTOR = new ChessVector(0, 2);
    private static final ChessVector BLACK_MOVE_SQUARE_VECTOR = new ChessVector(0, -1);
    private static final ChessVector WHITE_MOVE_SQUARE_VECTOR = new ChessVector(0, 1);

    public Pawn(final Team color) {
        super(color);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        final ChessVector chessVector = target.calculateVector(source);

        Rank rank = Rank.SEVEN;
        ChessVector startChessVector = BLACK_START_SQUARE_VECTOR;
        ChessVector moveChessVector = BLACK_MOVE_SQUARE_VECTOR;

        if (team == Team.WHITE) {
            rank = Rank.TWO;
            startChessVector = WHITE_START_SQUARE_VECTOR;
            moveChessVector = WHITE_MOVE_SQUARE_VECTOR;
        }

        return (source.isRank(rank) && startChessVector.equals(chessVector))
                || chessVector.equals(moveChessVector);
    }

    @Override
    public boolean canAttack(final Square source, final Square target) {
        final ChessVector chessVector = target.calculateVector(source);

        List<ChessVector> attackChessVectors = BLACK_ATTACK_SQUARE_VECTORS;

        if (team == Team.WHITE) {
            attackChessVectors = WHITE_ATTACK_SQUARE_VECTORS;
        }

        return attackChessVectors.stream().anyMatch(attackVector -> attackVector.equals(chessVector));
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
