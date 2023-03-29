package chess.domain.validateMove;

import chess.domain.board.Chessboard;
import chess.domain.board.Square;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public class ValidateData {
    private final Square source;
    private final Square target;
    private final Chessboard chessboard;

    public ValidateData(final Square source, final Square target, final Chessboard chessboard) {
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

    public boolean isSourceNotTypeOf(final PieceType pieceType) {
        return !getSourcePiece().getPieceType().equals(pieceType);
    }

    public boolean isTargetTypeOf(final PieceType pieceType) {
        return getTargetPiece().getPieceType().equals(pieceType);
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
