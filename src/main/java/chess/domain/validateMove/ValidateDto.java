package chess.domain.validateMove;

import chess.domain.board.Chessboard;
import chess.domain.board.Square;
import chess.domain.piece.Piece;

public class ValidateDto {
    private final Square source;
    private final Square target;
    private final Chessboard chessboard;

    public ValidateDto(Square source, Square target, Chessboard chessboard) {
        this.source = source;
        this.target = target;
        this.chessboard = chessboard;
    }

    public Square getSource() {
        return source;
    }

    public Square getTarget() {
        return target;
    }

    public Chessboard getChessboard() {
        return chessboard;
    }

    public Piece getSourcePiece() {
        return chessboard.getPieceAt(source);
    }

    public Piece getTargetPiece() {
        return chessboard.getPieceAt(target);
    }
}
