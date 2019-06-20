package chess.domain;

import java.util.List;
import java.util.Set;

public class EmptyCell extends ChessPiece {

    private static EmptyCell instance;

    private EmptyCell() {
        super(PieceType.NONE);
    }

    public static EmptyCell getInstance() {
        if (instance == null) {
            instance = new EmptyCell();
        }
        return instance;
    }

    @Override
    Set<ChessCoordinate> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {
        throw new UnsupportedOperationException("지원되지 않는 타입입니다");
    }
}
