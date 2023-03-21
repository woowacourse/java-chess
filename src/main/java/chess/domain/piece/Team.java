package chess.domain.piece;

import chess.domain.piece.coordinate.Coordinate;

public enum Team {
    BLACK, WHITE, EMPTY;
    
    private static final int MAX_ROW_NUMBER_OF_BLACK_TEAM = 2;
    private static final int MIN_ROW_NUMBER_WHITE_TEAM = 7;
    
    public static Team from(Coordinate coordinate) {
        if (coordinate.isRowNumLessOrEqualTo(MAX_ROW_NUMBER_OF_BLACK_TEAM)) {
            return BLACK;
        }
        
        if (coordinate.isRowNumOverOrEqualTo(MIN_ROW_NUMBER_WHITE_TEAM)) {
            return WHITE;
        }
        return EMPTY;
    }
    
    public boolean isSameTeam(Team otherTeam) {
        return this == otherTeam;
    }
    
    public Team nextTeam() {
        if (this == WHITE) {
            return BLACK;
        }
        
        return WHITE;
    }
}
