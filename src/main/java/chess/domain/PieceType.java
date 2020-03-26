package chess.domain;

import chess.domain.position.Position;
import chess.domain.routeFinder.*;

public enum PieceType {
    PAWN(1.0, "p", new PawnRouteFinder()),
    KNIGHT(2.5, "n", new KnightRouteFinder()),
    BISHOP(3.0, "b", new BishopRouteFinder()),
    ROOK(5.0, "r", new RookRouteFinder()),
    QUEEN(9.0, "q", new QueenRouteFinder()),
    KING(0.0, "k", new KingRouteFinder());

    public static final String NONSUPPORT_TO_PAWN_MESSAGE = "폰이 사용할 수 없는 메서드입니다.";
    public static final String ONLY_SUPPORT_TO_PAWN_MESSAGE = "폰만 사용할 수 있는 메서드입니다.";

    private double score;
    private String acronym;
    private RouteFinder routeFinder;

    PieceType(double score, String acronym, RouteFinder routeFinder) {
        this.score = score;
        this.acronym = acronym;
        this.routeFinder = routeFinder;
    }

    public Route findRoute(Position fromPosition, Position toPosition, Team team) {
        return this.routeFinder.findRoute(fromPosition, toPosition, team);
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
//        if (this == PAWN) {
//            throw new UnsupportedOperationException(NONSUPPORT_TO_PAWN_MESSAGE);
//        }
        return score;
    }

    public String getAcronymToUpperCase() {
        return acronym.toUpperCase();
    }


    public String getAcronymToLowerCase() {
        return acronym.toLowerCase();
    }
}