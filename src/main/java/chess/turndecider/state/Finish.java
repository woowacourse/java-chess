package chess.turndecider.state;

import chess.domain.piece.Piece;

public class Finish implements State{
    @Override
    public boolean isSameColor(Piece sourcePiece) {
        return false;
    }

    @Override
    public State nextState(boolean isGameFinished) {
        throw new UnsupportedOperationException("[ERROR] 지원하지 않는 기능입니다.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
