package chess.domain.piece;

public enum PieceType {
    PAWN(1.0, "p", MoveStrategy::testPawn),
    KNIGHT(2.5, "n", MoveStrategy::testKnight),
    BISHOP(3.0, "b", MoveStrategy::testBishop),
    ROOK(5.0, "r", MoveStrategy::testRook),
    QUEEN(9.0, "q", MoveStrategy::testQueen),
    KING(0.0, "k", MoveStrategy::testKing);

    public static final String NONSUPPORT_TO_PAWN_MESSAGE = "폰이 사용할 수 없는 메서드입니다.";
    public static final String ONLY_SUPPORT_TO_PAWN_MESSAGE = "폰만 사용할 수 있는 메서드입니다.";

    private double score;
    private String expression;
    private MoveStrategy moveStrategy;

    PieceType(double score, String expresion, MoveStrategy moveStrategy) {
        this.score = score;
        this.expression = expresion;
        this.moveStrategy = moveStrategy;
    }

    public String getAcronymToLowerCase() {
        return this.expression.toLowerCase();
    }

    public String getAcronymToUpperCase() {
        return this.expression.toUpperCase();
    }

    public boolean canMove(MoveInformation moveInformation) {
        if (moveInformation.isStartEnd()) {
            throw new IllegalArgumentException("기물은 제자리 이동을 할 수 없습니다.");
        }
        return this.moveStrategy.test(moveInformation);
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
}
