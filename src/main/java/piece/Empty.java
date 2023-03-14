package piece;

public class Empty extends Piece {
    public Empty(Team team, int row, char column) {
        super(team, row, column);
    }
    
    @Override
    public char symbol() {
        return '.';
    }
}
