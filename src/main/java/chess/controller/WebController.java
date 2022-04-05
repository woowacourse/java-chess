package chess.controller;

import chess.model.ChessGame;
import chess.model.board.Board;
import chess.model.board.BoardFactory;
import chess.model.dto.WebBoardDto;

public class WebController {
    public WebBoardDto start() {
        Board board = BoardFactory.create();
        ChessGame chessGame = new ChessGame(board);

        return WebBoardDto.from(board);
    }
}
