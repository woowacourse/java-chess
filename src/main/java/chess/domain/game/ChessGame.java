package chess.domain.game;

import chess.domain.Position;
import chess.domain.board.strategy.BoardStrategy;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;

import java.util.Map;

import static chess.view.ErrorMessage.NO_PIECE_ERROR_MESSAGE;

public class ChessGame {
    private final ChessBoard chessBoard;

    public ChessGame(BoardStrategy boardStrategy) {
        this.chessBoard = new ChessBoard(boardStrategy.generate());
    }

    public ChessGame(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void move(Position start, Position end) {
        if (chessBoard.getChessBoard().get(start) instanceof EmptyPiece) {
            throw new IllegalArgumentException(NO_PIECE_ERROR_MESSAGE.getErrorMessage());
        }
        chessBoard.move(start, end);
    }

    public Map<Position, Piece> getChessBoardMap() {
        return chessBoard.getChessBoard();
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
