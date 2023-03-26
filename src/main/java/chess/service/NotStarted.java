package chess.service;

import chess.dao.BoardDao;
import chess.dao.ChessGameDao;
import chess.dao.DataBaseBoardDao;
import chess.dao.DataBaseChessGameDao;
import chess.domain.board.BoardFactory;
import chess.domain.board.Score;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public class NotStarted implements State {

    private static final ChessGameDao CHESS_GAME_DAO = new DataBaseChessGameDao();
    private static final BoardDao BOARD_DAO = new DataBaseBoardDao();
    private static final String NOT_STARTED_CANT_EXECUTE_START_MESSAGE =
            "시작되지 않은 상태에선 해당 명령을 실행할 수 없습니다.";
    private static final NotStarted INSTANCE = new NotStarted();

    private NotStarted() {
    }

    public static NotStarted getInstance() {
        return INSTANCE;
    }

    @Override
    public State start() {
        return new Started(new BoardFactory().createInitialBoard(), CHESS_GAME_DAO.generateNewGame());
    }

    @Override
    public State move(final Position from, final Position to) {
        throw new UnsupportedOperationException(NOT_STARTED_CANT_EXECUTE_START_MESSAGE);
    }

    @Override
    public Map<Color, Score> status() {
        throw new UnsupportedOperationException(NOT_STARTED_CANT_EXECUTE_START_MESSAGE);
    }

    @Override
    public State end() {
        return End.getInstance();
    }

    @Override
    public Map<Position, Piece> getBoard() {
        throw new UnsupportedOperationException(NOT_STARTED_CANT_EXECUTE_START_MESSAGE);
    }

    @Override
    public State load(final long gameId) {
        return new Started(BOARD_DAO.loadBoard(gameId), gameId);
    }
}
