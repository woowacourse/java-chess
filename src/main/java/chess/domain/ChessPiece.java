package chess.domain;

import java.util.List;

public abstract class ChessPiece {
    private PieceType type;

    protected ChessPiece(PieceType type) {
        this.type = type;
    }

    public PieceType getType() {
        return type;
    }

    abstract List<ChessCoordinate> getMovableCoordinates(ChessBoard board, ChessCoordinate from);
}
