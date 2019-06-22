package chess.domain;

import java.util.Set;

public class EmptyCell extends ChessPiece {

    private static EmptyCell instance;

    private EmptyCell() {
        super(PieceType.NONE);
    }

    static EmptyCell getInstance() {
        if (instance == null) {
            instance = new EmptyCell();
        }
        return instance;
    }

    @Override
    Set<CoordinatePair> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, CoordinatePair from) {
        throw new UnsupportedOperationException("이동할 수 없는 타입입니다");
    }
}
