package chess.model.service;

import chess.model.dao.ChessGameDao;
import chess.model.dao.DataBaseChessGameDao;
import chess.model.dao.DataBasePieceDao;
import chess.model.dao.PieceDao;
import chess.model.domain.board.Board;
import chess.model.domain.board.Score;
import chess.model.domain.piece.Color;
import chess.model.domain.piece.Piece;
import chess.model.domain.position.Position;
import chess.model.exception.NotStartedCantExecuteException;
import chess.model.exception.StartedCantExecuteException;
import java.util.List;
import java.util.Map;

public class Started implements State {

    private static final ChessGameDao CHESS_GAME_DAO = DataBaseChessGameDao.getInstance();
    private static final PieceDao BOARD_DAO = DataBasePieceDao.getInstance();

    private final Board board;
    private final long gameId;

    Started(final Board board, final long gameId) {
        this.board = board;
        this.gameId = gameId;
    }

    @Override
    public State start() {
        throw new StartedCantExecuteException();
    }

    @Override
    public State move(final Position from, final Position to) {
        board.move(from, to);
        CHESS_GAME_DAO.updateTurn(board.getTurn(), gameId);
        BOARD_DAO.updatePiecePosition(from, to, gameId);
        if (board.isKingDead()) {
            BOARD_DAO.deleteBoard(gameId);
            CHESS_GAME_DAO.deleteGame(gameId);
            return End.getInstance();
        }
        return this;
    }

    @Override
    public Map<Color, Score> status() {
        return board.calculateScore();
    }

    @Override
    public State end() {
        return End.getInstance();
    }

    @Override
    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }

    @Override
    public State load(final long id) {
        throw new NotStartedCantExecuteException();
    }

    @Override
    public List<Long> findAllId() {
        return CHESS_GAME_DAO.findAllId();
    }
}
