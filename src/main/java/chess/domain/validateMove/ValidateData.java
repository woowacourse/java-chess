package chess.domain.validateMove;

import chess.domain.board.Chessboard;
import chess.domain.board.Square;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public class ValidateData {
    private final Square source;
    private final Square target;
    private final Chessboard chessboard;

    public ValidateData(Square source, Square target, Chessboard chessboard) {
        this.source = source;
        this.target = target;
        this.chessboard = chessboard;
    }

    private Piece getSourcePiece() {
        return chessboard.getPieceAt(source);
    }

    private Piece getTargetPiece() {
        return chessboard.getPieceAt(target);
    }

    public boolean canMove() {
        return getSourcePiece().canMove(source, target);
    }

    public boolean isTypeOf(PieceType pieceType) {
        return getSourcePiece().getPieceType() != pieceType;
    }

    public boolean isSameFile() {
        return target.isSameFile(source);
    }

    public boolean isOpposite() {
        return getSourcePiece().isOpposite(getTargetPiece());
    }

    public boolean isNotSameCamp() {
        return getSourcePiece().isNotSameCamp(getTargetPiece());
    }

    public boolean isEmptyInRoute() {
        return chessboard.isEmptyInRoute(source, target);
    }
}
