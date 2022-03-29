package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public class WhiteRunning extends Running {

    public WhiteRunning(Board board) {
        super(board, Color.WHITE);
    }

    @Override
    public ChessState move(Position start, Position target) {
        Piece caughtPiece = board.move(start, target, color);
        if (caughtPiece.isSamePiece(PieceType.KING)) {
            return new Finished(board);
        }
        return new BlackRunning(board);
    }

    @Override
    ChessState changeTurn() {
        return new BlackRunning(board);
    }
}
