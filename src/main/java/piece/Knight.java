package piece;

public class Knight extends Piece {
    public Knight(Team team, int row, char column) {
        super(team, row, column);
    }
    
    @Override
    public char symbol() {
        return 'n';
    }
}
