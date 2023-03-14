package piece;

public class Pawn extends Piece {
    public Pawn(Team team, int row, char column) {
        super(team, row, column);
    }
    
    @Override
    public char symbol() {
        return 'p';
    }
}
