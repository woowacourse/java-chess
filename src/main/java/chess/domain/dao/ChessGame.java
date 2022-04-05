package chess.domain.dao;

import chess.domain.board.BoardGenerator;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardGenerator;

public class ChessGame {

    private String name;
    private boolean isEnd;
    private ChessBoard chessBoard;

    public ChessGame(BoardGenerator boardGenerator) {
        this.chessBoard = new ChessBoard(boardGenerator);
    }

    public static ChessGame initChessGame() {
        ChessGame chessGame = new ChessGame(new ChessBoardGenerator());
        return chessGame;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }
}
