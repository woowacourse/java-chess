package chess.service;

import chess.dao.BoardDao;
import chess.dao.ChessGameDao;
import chess.domain.Board;
import chess.domain.Position;
import chess.domain.game.state.BlackTurn;
import chess.domain.game.state.ChessGame;
import chess.domain.game.state.Ready;
import chess.domain.game.state.WhiteTurn;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import spark.Session;

public class ChessService {

    private static final String BLACK_TURN = "blackturn";
    private static final String WHITE_TURN = "whiteturn";
    private static final String TERMINATE = "terminate";
    private static final String COMPLETE = "complete";

    private ChessGame chessGame;
    private final ChessGameDao chessGameDao;
    private final BoardDao boardDao;

    public ChessService(ChessGameDao chessGameDao, BoardDao boardDao) {
        this.chessGameDao = chessGameDao;
        this.boardDao = boardDao;
        chessGame = new Ready();
    }

    public int saveGame(String roomId) {
        if (isExistGame(roomId)) {
            return findByRoomId(roomId);
        }
        chessGameDao.save(roomId);
        return findByRoomId(roomId);
    }

    public int findByRoomId(String roomId) {
        return chessGameDao.findByRoomId(roomId);
    }

    private boolean isExistGame(String roomId) {
        int gameExistFlag = chessGameDao.isExistGame(roomId);
        return gameExistFlag == 1;
    }

    public Board ready(Session session) {
        int gameId = chessGameDao.findRecentGame();
        String state = null;
        try {
            state = chessGameDao.findById(gameId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (gameId != 0 && !isEnded(state)) {
            Board board = getBoard(gameId);
            chessGame = toChessGame(board, state);
            session.attribute("id", gameId);
            return board;
        }
        return chessGame.getBoard();
    }

    private Board getBoard(int gameId) {
        Board board = null;
        try {
            board = new Board(boardDao.findGame(gameId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return board;
    }

    private ChessGame toChessGame(Board board, String state) {
        if (BLACK_TURN.equals(state)) {
            return new BlackTurn(board);
        }
        if (WHITE_TURN.equals(state)) {
            return new WhiteTurn(board);
        }
        return new Ready();
    }

    private boolean isEnded(String state) {
        return state.equals(TERMINATE) || state.equals(COMPLETE);
    }

    public Board start(Session session) {
        chessGame = chessGame.initBoard();
        Map<Position, Piece> board = chessGame.getBoard().getBoard();
        int id = session.attribute("id");
        for (Position position : board.keySet()) {
            Piece piece = board.get(position);
            boardDao.save(position.toString(), piece.getName(), piece.getColorValue(), id);
        }
        chessGameDao.update(id, chessGame);
        return chessGame.getBoard();
    }

    public Board move(Session session, String from, String to) {
        chessGame = chessGame.movePiece(Position.valueOf(from), Position.valueOf(to));
        Map<Position, Piece> board = chessGame.getBoard().getBoard();
        int id = session.attribute("id");
        Piece piece = board.get(Position.valueOf(to));
        boardDao.update(from, to, piece.getName(), piece.getColorValue(), id);
        chessGameDao.update(id, chessGame);
        return chessGame.getBoard();
    }

    public Map<String, Double> showStatus() {
        Map<String, Double> scoreStatus = new HashMap<>();
        scoreStatus.put(Color.WHITE.name(), chessGame.calculateScore(Color.WHITE));
        scoreStatus.put(Color.BLACK.name(), chessGame.calculateScore(Color.BLACK));
        return scoreStatus;
    }

    public void terminate(Session session) {
        chessGame = chessGame.end();
        int id = session.attribute("id");
        chessGameDao.update(id, chessGame);
    }

    public Color complete() {
        return chessGame.judgeWinner();
    }

    public boolean isComplete() {
        return chessGame.isFinish() && !chessGame.isTerminate();
    }

    public Map<String, Object> showBoard() {
        return chessGame.getBoard().toMap();
    }
}
