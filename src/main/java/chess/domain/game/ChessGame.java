package chess.domain.game;

import chess.domain.Position;
import chess.domain.boardStrategy.BoardStrategy;
import chess.domain.piece.Piece;

import java.util.Map;

public class ChessGame {
    private final ChessBoard chessBoard;

    public ChessGame(BoardStrategy boardStrategy) {
        this.chessBoard = new ChessBoard(boardStrategy.generate());
    }

    public void move(Position start, Position end) {
        chessBoard.move(start, end);
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard.getChessBoard();
    }
}
