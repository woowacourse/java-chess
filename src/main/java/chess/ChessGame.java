package chess;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;

public class ChessGame {

    private Board board;

    public void initSetting() {
        BoardFactory boardFactory = new BoardFactory();
        board = boardFactory.getBoard();
    }

    public Board getBoard() {
        return board;
    }
}
