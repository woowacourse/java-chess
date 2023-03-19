package chess.domain.piece;

public enum Team {
    BLACK, WHITE, EMPTY;
    
    private static final int MAX_ROW_NUMBER_OF_BLACK_TEAM = 2;
    private static final int MIN_ROW_NUMBER_WHITE_TEAM = 7;
    private static final int MIN_ROW_NUM = 1;
    private static final int MAX_ROW_NUM = 8;
    
    public static Team from(int rowNum) {
        validateOutOfRangeRowNum(rowNum);
        if (isBlackTeamRowNum(rowNum)) {
            return BLACK;
        }
        
        if (isWhiteTeamRowNum(rowNum)) {
            return WHITE;
        }
        return EMPTY;
    }
    
    private static boolean isBlackTeamRowNum(int rowNum) {
        return rowNum <= MAX_ROW_NUMBER_OF_BLACK_TEAM;
    }
    
    private static boolean isWhiteTeamRowNum(int rowNum) {
        return rowNum >= MIN_ROW_NUMBER_WHITE_TEAM;
    }
    
    private static void validateOutOfRangeRowNum(int rowNum) {
        if (isOutOfRangeRowNum(rowNum)) {
            throw new IllegalArgumentException("행의 번호는 1~8의 범위를 벗어날 수 없습니다.");
        }
    }
    
    private static boolean isOutOfRangeRowNum(int rowNum) {
        return rowNum < MIN_ROW_NUM || rowNum > MAX_ROW_NUM;
    }
    
    public boolean isSameTeam(Team otherTeam) {
        return this == otherTeam;
    }
}
