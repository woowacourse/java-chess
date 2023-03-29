package chess.service;

import chess.chessgame.ChessGame;

public class ChessGameService {

    public boolean isGameOver(ChessGame chessGame) {
        return chessGame.isGameOver();
    }
}
