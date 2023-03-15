package chess.domain.chessboard.state;

import chess.domain.chessboard.state.piece.Piece;

public final class Empty implements PieceState {

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Team getTeam() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isSameTeam(final Piece piece){
        return false;
    }
}
