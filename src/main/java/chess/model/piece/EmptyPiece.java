package chess.model.piece;

import chess.model.Coordinate;

import java.util.List;

public class EmptyPiece implements Piece {
    @Override
    public boolean isMovePossible(List<Coordinate> asList) {
        return false;
    }

    @Override
    public void signalMoved() {

    }

    // TODO: 2019-06-18 Optional 적용하기
}
