package chess.model.service;

import chess.model.dao.ChessGameDao;
import chess.model.dao.ConnectionGeneratorImpl;
import chess.model.dao.DataBaseChessGameDao;
import chess.model.dao.DataBasePieceDao;
import chess.model.dao.PieceDao;
import chess.model.domain.board.Board;
import chess.model.domain.board.BoardFactory;
import chess.model.domain.board.Score;
import chess.model.domain.board.Turn;
import chess.model.domain.piece.Color;
import chess.model.domain.piece.Piece;
import chess.model.domain.position.Position;
import chess.model.exception.NotStartedCantExecuteException;
import java.util.List;
import java.util.Map;

public class NotStarted implements State {

    private static final ChessGameDao CHESS_GAME_DAO = new DataBaseChessGameDao(new ConnectionGeneratorImpl());
    private static final PieceDao BOARD_DAO = new DataBasePieceDao(new ConnectionGeneratorImpl());
    private static final NotStarted INSTANCE = new NotStarted();

    private NotStarted() {
    }

    public static NotStarted getInstance() {
        return INSTANCE;
    }

    @Override
    public State start() {
        final long gameId = CHESS_GAME_DAO.generateNewGame();
        final Board initialBoard = new BoardFactory().createInitialBoard();
        BOARD_DAO.saveBoard(initialBoard, gameId);
        return new Started(initialBoard, gameId);
    }

    @Override
    public State move(final Position from, final Position to) {
        throw new NotStartedCantExecuteException();
    }

    @Override
    public Map<Color, Score> status() {
        throw new NotStartedCantExecuteException();
    }

    @Override
    public State end() {
        return End.getInstance();
    }

    @Override
    public Map<Position, Piece> getBoard() {
        throw new NotStartedCantExecuteException();
    }

    @Override
    public State load(final long gameId) {
        final Turn turn = CHESS_GAME_DAO.loadTurn(gameId);
        return new Started(BOARD_DAO.loadBoard(gameId, turn), gameId);
    }

    @Override
    public List<Long> findAllId() {
        return CHESS_GAME_DAO.findAllId();
    }
}
