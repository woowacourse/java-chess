package chess.domain.piece;

public enum PawnFirstMove {
    YES,
    NO,
    ;

    public PawnFirstMove checkNo() {
        if (this == YES) {
            return NO;
        }
        return this;
    }

    public boolean isYes() {
        return this == YES;
    }
}
