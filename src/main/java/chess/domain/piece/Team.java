package chess.domain.piece;

import chess.domain.board.coordinate.Coordinate;

public enum Team {
    BLACK, WHITE, EMPTY;
    
    private static final int MAX_ROW_NUMBER_OF_WHITE_TEAM = 2;
    private static final int MIN_ROW_NUMBER_BLACK_TEAM = 7;
    
    public static Team from(Coordinate coordinate) {
        if (coordinate.isRowNumLessOrEqualTo(MAX_ROW_NUMBER_OF_WHITE_TEAM)) {
            return WHITE;
        }
        
        if (coordinate.isRowNumOverOrEqualTo(MIN_ROW_NUMBER_BLACK_TEAM)) {
            return BLACK;
        }
        return EMPTY;
    }
    
    public boolean isSameTeam(Team otherTeam) {
        return this == otherTeam;
    }
    
    public boolean isDifferentTeam(Team otherTeam) {
        if (this == BLACK) {
            return otherTeam == WHITE;
        }
        
        return otherTeam == BLACK;
    }
    
    public Team nextTeam() {
        if (this == WHITE) {
            return BLACK;
        }
        
        return WHITE;
    }
}
