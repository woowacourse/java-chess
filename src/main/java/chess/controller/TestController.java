package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.dto.BoardInitializeDto;

public class TestController {

    public BoardInitializeDto run() {
        ChessGame chessGame = new ChessGame();
        chessGame.settingBoard();
        Board board = chessGame.getBoard();
        return new BoardInitializeDto(board);
    }
}
