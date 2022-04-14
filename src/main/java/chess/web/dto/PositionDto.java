package chess.web.dto;

import chess.domain.piece.position.File;
import chess.domain.piece.position.Position;
import chess.domain.piece.position.Rank;

public class PositionDto {
    private static final int MAX_RANK = 8;
    private static final char UPPER_CASE_A = 'A';

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
        int newY = file.name().charAt(0) - UPPER_CASE_A;

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
