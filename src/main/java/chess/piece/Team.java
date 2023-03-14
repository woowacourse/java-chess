package chess.piece;

public enum Team {
    BLACK,WHITE,EMPTY;
    
    
    public boolean isWhiteTeam() {
        return this == WHITE;
    }
}
