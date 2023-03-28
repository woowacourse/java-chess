package chess.domain.game;

import chess.domain.Position;
import chess.domain.board.strategy.BoardStrategy;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;

import java.util.Map;

import static chess.view.ErrorMessage.NO_PIECE_ERROR_MESSAGE;

public class ChessGame {
    private final ChessBoard chessBoard;
    private GameStatus gameStatus;

    public ChessGame(BoardStrategy boardStrategy, GameStatus gameStatus) {
        this.chessBoard = new ChessBoard(boardStrategy.generate());
        this.gameStatus = gameStatus;
    }

    public ChessGame(ChessBoard chessBoard, GameStatus gameStatus) {
        this.chessBoard = chessBoard;
        this.gameStatus = gameStatus;
    }

    public ChessBoard move(Position start, Position end) {
        if (chessBoard.getChessBoard().get(start) instanceof EmptyPiece) {
            throw new IllegalArgumentException(NO_PIECE_ERROR_MESSAGE.getErrorMessage());
        }
        chessBoard.move(start, end);

        return chessBoard;
    }

    public Map<Position, Piece> getChessBoardMap() {
        return chessBoard.getChessBoard();
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
}
