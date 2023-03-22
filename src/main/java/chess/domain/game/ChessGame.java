package chess.domain.game;

import chess.boardstrategy.BoardStrategy;
import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.piece.type.Piece;

import java.util.Map;

public class ChessGame {
    private final ChessBoard chessBoard;

    public ChessGame() {
        this.chessBoard = new ChessBoard();
    }

    public void start(BoardStrategy boardStrategy) {
        if(isStarted()){
            throw new IllegalArgumentException("게임이 이미 시작되었습니다");
        }
        this.chessBoard.initialize(boardStrategy.generate());
    }

    public void move(Position start, Position end) {
        if(!isStarted()) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다");
        }
        chessBoard.move(start, end);
    }

    private boolean isStarted() {
        return chessBoard.isInitialized();
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard.getChessBoard();
    }

}
