package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public class BlackRunning extends Running {

    public BlackRunning(Board board) {
        super(board, Color.BLACK);
    }

    @Override
    public ChessState move(Position start, Position target) {
        Piece caughtPiece = board.move(start, target, color);
        if (caughtPiece.isSamePiece(PieceType.KING)) {
            return new Finished(board);
        }
        return new WhiteRunning(board);
    }

    @Override
    ChessState changeTurn() {
        return new WhiteRunning(board);
    }
}
