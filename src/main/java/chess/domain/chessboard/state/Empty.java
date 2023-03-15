package chess.domain.chessboard.state;

public final class Empty implements PieceState {

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Team getTeam() {
        throw new UnsupportedOperationException();
    }
}
