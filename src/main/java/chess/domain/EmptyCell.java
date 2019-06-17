package chess.domain;

import java.util.List;

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
    List<ChessCoordinate> getMovableCoordinates(ChessBoard board, ChessCoordinate from) {
        throw new UnsupportedOperationException("지원되지 않는 타입입니다");
    }
}
