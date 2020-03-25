package chess.domain;

import chess.domain.movingstrategy.*;
import chess.domain.position.Position;

import java.util.function.Predicate;

public enum PieceType {
    PAWN(1.0, "p", new PawnMovingStrategy()),
    KNIGHT(2.5, "n", new KnightMovingStrategy()),
    BISHOP(3.0, "b", new BishopMovingStrategy()),
    ROOK(5.0, "r", new RookMovingStrategy()),
    QUEEN(9.0, "q", new QueenMovingStrategy()),
    KING(0.0, "k", new KingMovingStrategy());

    public static final String NONSUPPORT_TO_PAWN_MESSAGE = "폰이 사용할 수 없는 메서드입니다.";
    public static final String ONLY_SUPPORT_TO_PAWN_MESSAGE = "폰만 사용할 수 있는 메서드입니다.";

    private double score;
    private String acronym;
    private Predicate<Variables> movingStrategy;

    PieceType(double score, String acronym, Predicate<Variables> movingStrategy) {
        this.score = score;
        this.acronym = acronym;
        this.movingStrategy = movingStrategy;
    }

    public boolean canMove(Position fromPosition, Position toPosition, Team team) {
        return this.movingStrategy.test(new Variables(fromPosition, toPosition, team));
    }

    public double score(boolean mustChange) {
        if (this != PAWN) {
            throw new UnsupportedOperationException(ONLY_SUPPORT_TO_PAWN_MESSAGE);
        }
        return scoring(mustChange);
    }

    private double scoring(boolean mustChange) {
        if (mustChange) {
            return 0.5;
        }
        return score;
    }

    public double score() {
        if (this == PAWN) {
            throw new UnsupportedOperationException(NONSUPPORT_TO_PAWN_MESSAGE);
        }
        return score;
    }

    public String getAcronymToUpperCase() {
        return acronym.toUpperCase();
    }


    public String getAcronymToLowerCase() {
        return acronym.toLowerCase();
    }
}
