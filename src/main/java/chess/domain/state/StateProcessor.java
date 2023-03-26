package chess.domain.state;

import chess.cache.PieceCache;
import chess.dao.GameDao;
import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.dto.CommandDto;
import chess.dto.GameDto;

import java.sql.SQLException;
import java.util.Map;

public final class StateProcessor {
    private State state;
    private final GameDao gameDao;
    private final int gameId;

    private StateProcessor(final State state, final GameDao gameDao, final int gameId) {
        this.state = state;
        this.gameDao = gameDao;
        this.gameId = gameId;
    }

    public static StateProcessor from(GameDao gameDao) throws SQLException {
        GameDto gameDto = gameDao.findByLastGame();

        if (gameDto.isEnd()) {
            final State state = new Ready(ChessGame.create());
            gameDao.createGame(Board.from(PieceCache.create()).getBoard());
            return new StateProcessor(state, gameDao, gameDto.getId() + 1);
        }

        Board board = Board.from(gameDao.findByLastGameBoard(gameDto.getId()));
        final State state = new Ready(ChessGame.restart(board, Color.valueOf(gameDto.getColor())));
        return new StateProcessor(state, gameDao, gameDto.getId());
    }

    public Map<Position, Piece> getBoard() {
        return state.chessGame
                .getBoard();
    }

    public void changeState(State state) {
        this.state = state;
    }

    public boolean isNotEnd() {
        return !state.isEnd();
    }

    public boolean isGameEnd() {
        return state.isGameEnd();
    }

    public boolean isNotGameEnd() {
        return !state.isGameEnd();
    }

    public State move(final CommandDto commandDto) {
        State newState = state.move(commandDto.getSource(), commandDto.getTarget());
        if (newState.isGameEnd()) {
            gameDao.updateGameStatus(true, gameId);
        }

        gameDao.movePiece(state.chessGame.getBoard(), gameId, state.getColor());
        return newState;
    }

    public State start() {
        return state.start();
    }

    public State end() {
        return state.end();
    }

    public State identity() {
        return state;
    }

    public double status(Color color) {
        return state.calculateScore(color);
    }

    public Color getColor() {
        return state.getColor();
    }
}
