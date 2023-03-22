package chess.domain.position;

import chess.domain.position.Position;

public final class PositionConverter {
    private static final char FIRST_FILE = 'a';
    private static final char LAST_FILE = 'h';
    private static final char FIRST_RANK = '1';
    private static final char LAST_RANK = '8';
    private static final int POSITION_LENGTH = 2;
    public static final int FILE_INDEX = 0;
    public static final int RANK_INDEX = 1;

    public static Position convert(final String position) {
        validate(position);
        final int file = position.charAt(FILE_INDEX);
        final int rank = position.charAt(RANK_INDEX);
        return new Position(rank - FIRST_RANK, file - FIRST_FILE);
    }

    private static void validate(final String position) {
        validateLength(position);
        validateRange(position);
    }

    private static void validateLength(final String position) {
        if (position.length() != POSITION_LENGTH) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    private static void validateRange(final String position) {
        final String lowerCasePosition = position.toLowerCase();
        if (lowerCasePosition.charAt(FILE_INDEX) < FIRST_FILE || lowerCasePosition.charAt(FILE_INDEX) > LAST_FILE) {
            throw new IllegalArgumentException("이동 위치 열은 a~h 사이여야 합니다.");
        }
        if (lowerCasePosition.charAt(RANK_INDEX) < FIRST_RANK || lowerCasePosition.charAt(RANK_INDEX) > LAST_RANK) {
            throw new IllegalArgumentException("이동 위치 행은 1~8 사이여야 합니다.");
        }
    }
}
