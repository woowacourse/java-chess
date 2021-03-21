package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.InitBoardGenerator;

public class ChessGameController {
    public void run() {
        Board board = new Board(InitBoardGenerator.initLines());
        ChessGame chessGame = new ChessGame(board);
    }
}
