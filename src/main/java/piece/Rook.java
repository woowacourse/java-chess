package piece;

public class Rook extends Piece {
    public Rook(Team team, int row, char column) {
        super(team, row, column);
    }
    
    @Override
    public char symbol() {
        return 'r';
    }
}
