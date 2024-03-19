package chess.domain;

public abstract class ChessPiece implements Piece {
    private PieceInfo pieceInfo;
    private MoveStrategy moveStrategy;

    @Override
    public boolean move(Position newPosition, Board board) {
        return false;
    }
}
