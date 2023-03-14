package chess.piece;

public enum Team {
    BLACK, WHITE, EMPTY;
    
    private static final int MAX_ROW_NUMBER_OF_BLACK_TEAM = 2;
    private static final int MIN_ROW_NUMBER_WHITE_TEAM = 7;
    private static final int MIN_ROW_NUM = 1;
    private static final int MAX_ROW_NUM = 8;
    
    public static Team from(int rowNum) {
        validateOutOfRangeRowNum(rowNum);
        if (rowNum <= MAX_ROW_NUMBER_OF_BLACK_TEAM) {
            return BLACK;
        }
        
        if (rowNum >= MIN_ROW_NUMBER_WHITE_TEAM) {
            return WHITE;
        }
        
        return EMPTY;
    }
    
    private static void validateOutOfRangeRowNum(int rowNum) {
        if (rowNum < MIN_ROW_NUM || rowNum > MAX_ROW_NUM) {
            throw new IllegalArgumentException("행의 번호는 1~8의 범위를 벗어날 수 없습니다.");
        }
    }
    
    public boolean isWhiteTeam() {
        return this == WHITE;
    }
}
