package chess.domain.piece;

public enum Camp {
    BLACK,
    WHITE,
    NONE;

    public boolean isOpposite(Camp otherCamp) {
        if (otherCamp == NONE) {
            return false;
        }

        return this != otherCamp;
    }
}
