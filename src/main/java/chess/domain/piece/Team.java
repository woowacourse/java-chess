package chess.domain.piece;

import java.util.List;

public enum Team {
    BLACK(List.of("1", "2")),
    WHITE(List.of("7", "8")),
    EMPTY(List.of("1", "2", "3", "4", "5", "6", "7", "8"));

    private final List<String> rowNums;

    Team(List<String> rowNums) {
        this.rowNums = rowNums;
    }

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
        return BLACK.rowNums.contains(rowNum);
    }

    private static boolean isWhiteTeamRowNum(String rowNum) {
        return WHITE.rowNums.contains(rowNum);
    }

    private static void validateOutOfRangeRowNum(String rowNum) {
        if (isOutOfRangeRowNum(rowNum)) {
            throw new IllegalArgumentException("행의 번호는 1~8의 범위를 벗어날 수 없습니다.");
        }
    }

    private static boolean isOutOfRangeRowNum(String rowNum) {
        return !EMPTY.rowNums.contains(rowNum);
    }

    public boolean isSameTeam(Team otherTeam) {
        return this == otherTeam;
    }

    public static Team winnerOf(double blackPoint, double whitePoint) {
        if (blackPoint > whitePoint) {
            return Team.BLACK;
        }
        if (blackPoint < whitePoint) {
            return Team.WHITE;
        }
        return Team.EMPTY;
    }
}
