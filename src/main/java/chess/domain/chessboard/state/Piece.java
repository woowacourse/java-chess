package chess.domain.chessboard.state;

public abstract class Piece implements PieceState{

    private final Team team;

    protected Piece(final Team team) {
        this.team = team;
    }

    @Override
    public boolean isEmpty(){
        return false;
    }
}
