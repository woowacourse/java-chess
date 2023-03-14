package piece;

public class King extends Piece {
    
    public King(Team team, int row, char column) {
        super(team, row, column);
    }
    
    public char symbol() {
        return 'k';
    }
}
