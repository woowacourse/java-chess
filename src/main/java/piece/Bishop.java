package piece;

public class Bishop extends Piece {
    public Bishop(Team team, int row, char column) {
        super(team, row, column);
    }
    
    @Override
    public char symbol() {
        return 'b';
    }
}
