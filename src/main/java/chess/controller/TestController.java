package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.dto.BoardDto;

public class TestController {
    public BoardDto run() {
        ChessGame chessGame = new ChessGame();
        chessGame.settingBoard();
        Board board = chessGame.getBoard();
        return new BoardDto(board);
    }
}
