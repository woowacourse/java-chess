package chess.repository;

import chess.domain.ChessGame;

public class InMemoryBoardDao implements BoardDao{

    private ChessGame chessGame;

    public InMemoryBoardDao(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public void saveChessGame(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public ChessGame selectChessGame() {
        return this.chessGame;
    }

    @Override
    public void deleteAll() {
        this.chessGame = null;
    }
}
