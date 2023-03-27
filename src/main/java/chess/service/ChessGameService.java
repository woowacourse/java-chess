package chess.service;

import chess.dao.ChessMovementDao;
import chess.dao.Movement;
import chess.model.game.ChessGame;
import chess.model.piece.Camp;
import chess.model.piece.score.PieceScore;
import chess.model.position.Position;
import chess.model.position.PositionConverter;
import chess.view.dto.ChessBoardResponse;
import java.util.List;

public class ChessGameService {

    private final ChessGame chessGame;
    private final ChessMovementDao chessMovementDao;

    public ChessGameService(final ChessGame chessGame, final ChessMovementDao chessMovementDao) {
        this.chessGame = chessGame;
        this.chessMovementDao = chessMovementDao;
    }

    public void initializeChessGame() {
        chessMovementDao.delete();
        chessGame.initialChessGame();
    }

    public void loadChessGame() {
        chessGame.initialChessGame();

        final List<Movement> movements = chessMovementDao.findAll();

        for (Movement movement : movements) {
            final Position source = PositionConverter.convert(movement.getSource());
            final Position target = PositionConverter.convert(movement.getTarget());

            chessGame.move(source, target);
        }
    }

    public void move(final Position source, final Position target) {
        chessGame.move(source, target);
        chessMovementDao.save(source, target);
    }

    public boolean isGameOnGoing() {
        return chessGame.isGameOnGoing();
    }

    public void clear() {
        chessMovementDao.delete();
    }

    public PieceScore getScoreByCamp(final Camp camp) {
        return chessGame.getScoreByCamp(camp);
    }

    public Camp getCurrentCamp() {
        return chessGame.getCurrentCamp();
    }

    public Camp getWinnerCamp() {
        return chessGame.getWinnerCamp();
    }

    public ChessBoardResponse getChessBoard() {
        return new ChessBoardResponse(chessGame.getChessBoard());
    }
}
