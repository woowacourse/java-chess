package chess.domain.piece;

public record Weight(int rankWeight, char fileWeight) {

    public Weight(int rankWeight, int fileWeight) {
        this(rankWeight, (char) fileWeight);
    }
}
