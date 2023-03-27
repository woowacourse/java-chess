package chess.service;

import chess.dao.BoardDao;
import chess.dao.ChessGameDao;
import chess.dao.DataBaseBoardDao;
import chess.dao.DataBaseChessGameDao;
import chess.domain.board.Board;
import chess.domain.board.Score;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class Started implements State {

    private static final ChessGameDao CHESS_GAME_DAO = new DataBaseChessGameDao();
    private static final BoardDao BOARD_DAO = new DataBaseBoardDao();
    private static final String STARTED_CANT_EXECUTE_START_MESSAGE = "시작된 상태에선 해당 명령을 실행할 수 없습니다.";

    private final Board board;
    private final long gameId;

    Started(final Board board, final long gameId) {
        this.board = board;
        this.gameId = gameId;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException(STARTED_CANT_EXECUTE_START_MESSAGE);
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
        throw new UnsupportedOperationException(STARTED_CANT_EXECUTE_START_MESSAGE);
    }

    @Override
    public List<Long> findAllId() {
        return CHESS_GAME_DAO.findAllId();
    }
}
