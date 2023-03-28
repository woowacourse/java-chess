package chess.service;

import chess.database.dao.ChessDao;
import chess.database.dao.Notation;
import chess.domain.board.Position;
import chess.domain.game.ChessGame;
import chess.domain.game.Score;
import java.util.List;

public class ChessGameService {

    private final ChessGame chessGame;
    private final ChessDao chessDao;

    public ChessGameService(final ChessGame chessGame, final ChessDao chessDao) {
        this.chessGame = chessGame;
        this.chessDao = chessDao;
    }

    public ChessGame loadGame() {
        List<Notation> notations = chessDao.readNotation();
        chessGame.initChessGame();
        for (Notation notation : notations) {
            chessGame.move(Position.from(notation.getSource()), Position.from(notation.getTarget()));
        }
        return chessGame;
    }

    public ChessGame movePiece(Position source, Position destination) {
        chessGame.move(source, destination);
        chessDao.addNotation(source, destination);
        return chessGame;
    }

    public boolean gameOver() {
        if (chessGame.isGameOver()) {
            chessDao.deleteNotation();
            return true;
        }
        return false;
    }

    public Score getScore() {
        return chessGame.calculateScore();
    }
}
