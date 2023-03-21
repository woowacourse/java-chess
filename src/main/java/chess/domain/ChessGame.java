package chess.domain;

import chess.domain.board.Chessboard;
import chess.domain.board.Square;
import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.validateMove.SourceMoveValidator;
import chess.domain.validateMove.ValidateData;

public class ChessGame {
    private final Chessboard chessboard;
    private Camp turn;

    public ChessGame() {
        chessboard = new Chessboard();
        BoardInitializer.initializeBoard(chessboard);
        turn = Camp.WHITE;
    }

    public void move(Square source, Square target) {
        validateTurn(source);
        if (canMove(source, target)) {
            chessboard.swapPiece(source, target);
            turn = turn.getOpposite();
            return;
        }
        throw new IllegalArgumentException("이동할 수 없는 위치입니다");
    }

    private void validateTurn(Square square) {
        Piece pieceAtSquare = chessboard.getPieceAt(square);

        if (pieceAtSquare.isOpposite(turn)) {
            throw new IllegalArgumentException("해당 위치에는 당신의 Piece가 없습니다.");
        }
    }

    private boolean canMove(Square source, Square target) {

        if (source.equals(target)) {
            return false;
        }
        return new SourceMoveValidator().validate(new ValidateData(source, target, chessboard));
    }

    public Chessboard getChessboard() {
        return chessboard;
    }
}
