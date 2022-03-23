package chess.dto;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;

public class PositionDto {
    private static final int MAX_RANK = 8;
    private static final char SMALL_LETTER_A = 'a';

    private final int x;
    private final int y;

    public PositionDto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static PositionDto of(Position position) {
        Rank rank = position.getRank();
        File file = position.getFile();

        int newX = MAX_RANK - rank.getValue();
        int newY = file.name().charAt(0) - SMALL_LETTER_A;

        return new PositionDto(newX, newY);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        PositionDto that = (PositionDto)o;

        if (getX() != that.getX())
            return false;
        return getY() == that.getY();
    }

    @Override
    public int hashCode() {
        int result = getX();
        result = 31 * result + getY();
        return result;
    }
}
