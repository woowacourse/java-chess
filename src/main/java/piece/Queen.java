package piece;

public class Queen extends Piece {
    public Queen(Team team, int row, char column) {
        super(team, row, column);
    }
    
    @Override
    public char symbol() {
        return 'q';
    }
}
