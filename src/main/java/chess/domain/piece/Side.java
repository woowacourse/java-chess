package chess.domain.piece;

public enum Side {

    BLACK,
    WHITE,
    NEUTRALITY;

    public boolean isNeutrality() {
        return this == NEUTRALITY;
    }
}
