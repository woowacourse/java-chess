package chess.domain.piece;

import java.util.List;

public enum Team {
    BLACK,
    WHITE,
    EMPTY;

    private static final List<String> BLACK_TEAM_ROW_NUMS = List.of("1", "2");
    private static final List<String> WHITE_TEAM_ROW_NUMS = List.of("7", "8");
    private static final List<String> ROW_NUMS = List.of("1", "2", "3", "4", "5", "6", "7", "8");

    public static Team from(String rowNum) {
        validateOutOfRangeRowNum(rowNum);
        if (isBlackTeamRowNum(rowNum)) {
            return BLACK;
        }

        if (isWhiteTeamRowNum(rowNum)) {
            return WHITE;
        }
        return EMPTY;
    }

    private static boolean isBlackTeamRowNum(String rowNum) {
        return BLACK_TEAM_ROW_NUMS.contains(rowNum);
    }

    private static boolean isWhiteTeamRowNum(String rowNum) {
        return WHITE_TEAM_ROW_NUMS.contains(rowNum);
    }

    private static void validateOutOfRangeRowNum(String rowNum) {
        if (isOutOfRangeRowNum(rowNum)) {
            throw new IllegalArgumentException("행의 번호는 1~8의 범위를 벗어날 수 없습니다.");
        }
    }

    private static boolean isOutOfRangeRowNum(String rowNum) {
        return !ROW_NUMS.contains(rowNum);
    }

    public boolean isSameTeam(Team otherTeam) {
        return this == otherTeam;
    }
}
