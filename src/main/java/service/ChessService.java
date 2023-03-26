package service;

import dao.ChessDao;
import domain.chessboard.ChessBoard;
import domain.chessgame.ChessGame;
import domain.piece.Color;
import domain.position.Position;
import domain.position.PositionFactory;
import dto.MoveDto;

import java.util.List;

public final class ChessService {

    private final ChessGame chessGame;
    private final ChessDao chessDao;

    public ChessService(final ChessGame chessGame, final ChessDao chessDao) {
        this.chessGame = chessGame;
        this.chessDao = chessDao;
    }

    public void startNewGame() {
        chessDao.deleteAll();
    }

    public void initializeBoard() {
        chessGame.initializeBoard();
    }

    public void startLoadGame() {
        loadGame();
    }

    public void move(final String source, final String target) {
        chessGame.move(PositionFactory.createPosition(source), PositionFactory.createPosition(target));
        chessDao.save(new MoveDto(source, target));
    }

    public boolean isKingAlive() {
        return chessGame.isKingAlive();
    }

    public ChessBoard getChessBoard() {
        return chessGame.getChessBoard();
    }

    public double getWhiteScore() {
        return chessGame.calculateScore(Color.WHITE);
    }

    public double getBlackScore() {
        return chessGame.calculateScore(Color.BLACK);
    }

    private void loadGame() {
        final List<MoveDto> allMove = chessDao.findAllMove();

        for (MoveDto move : allMove) {
            final Position source = PositionFactory.createPosition(move.getSource());
            final Position target = PositionFactory.createPosition(move.getTarget());

            chessGame.move(source, target);
        }
    }

}
