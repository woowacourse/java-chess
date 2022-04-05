package chess.service;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.game.state.ChessGame;
import chess.domain.game.state.Ready;

public class ChessService {
    private ChessGame chessGame;

    public ChessService() {
        chessGame = new Ready();
    }

    public void start() {
        chessGame = chessGame.initBoard();
    }

    public void move(String from, String to) {
        chessGame = chessGame.movePiece(Position.valueOf(from), Position.valueOf(to));
    }

    public Board getBoard() {
        return chessGame.getBoard();
    }
}
