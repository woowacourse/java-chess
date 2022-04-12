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
import java.util.HashMap;
import java.util.Map;

public class ChessService {

    private static final String BLACK_TURN = "blackturn";
    private static final String WHITE_TURN = "whiteturn";
    private static final String TERMINATE = "terminate";
    private static final String COMPLETE = "complete";

    private final ChessGameDao chessGameDao;
    private final BoardDao boardDao;

    public ChessService(ChessGameDao chessGameDao, BoardDao boardDao) {
        this.chessGameDao = chessGameDao;
        this.boardDao = boardDao;
    }

    public int saveGame(String roomId) {
        int gameId = findByRoomId(roomId);
        if (gameId == 0) {
            ChessGame chessGame = new Ready();
            chessGameDao.save(chessGame, roomId);
            return findByRoomId(roomId);
        }
        return gameId;
    }

    public int findByRoomId(String roomId) {
        return chessGameDao.findByRoomId(roomId);
    }

    public Board ready(int gameId) {
        ChessGame chessGame = getChessGame(gameId);
        return chessGame.getBoard();
    }

    private ChessGame getChessGame(int gameId) {
        String state = chessGameDao.findById(gameId);
        Board board = new Board(boardDao.findGame(gameId));

        if (BLACK_TURN.equals(state)) {
            return new BlackTurn(board);
        }
        if (WHITE_TURN.equals(state)) {
            return new WhiteTurn(board);
        }
        return new Ready();
    }

    public Board start(int gameId) {
        ChessGame chessGame = getChessGame(gameId);
        chessGame = chessGame.initBoard();
        Map<Position, Piece> board = chessGame.getBoard().getBoard();
        for (Position position : board.keySet()) {
            Piece piece = board.get(position);
            boardDao.save(position.toString(), piece.getName(), piece.getColorValue(), gameId);
        }
        chessGameDao.update(gameId, chessGame);
        return chessGame.getBoard();
    }

    public Board move(int gameId, String from, String to) {
        ChessGame chessGame = getChessGame(gameId);
        chessGame = chessGame.movePiece(Position.valueOf(from), Position.valueOf(to));
        Map<Position, Piece> board = chessGame.getBoard().getBoard();
        Piece piece = board.get(Position.valueOf(to));
        boardDao.update(from, to, piece.getName(), piece.getColorValue(), gameId);
        chessGameDao.update(gameId, chessGame);
        return chessGame.getBoard();
    }

    public Map<String, Double> showStatus(int gameId) {
        ChessGame chessGame = getChessGame(gameId);
        Map<String, Double> scoreStatus = new HashMap<>();
        scoreStatus.put(Color.WHITE.name(), chessGame.calculateScore(Color.WHITE));
        scoreStatus.put(Color.BLACK.name(), chessGame.calculateScore(Color.BLACK));
        return scoreStatus;
    }

    public void terminate(int gameId) {
        ChessGame chessGame = getChessGame(gameId);
        chessGame = chessGame.end();
        chessGameDao.update(gameId, chessGame);
    }

    public Color complete(int gameId) {
        ChessGame chessGame = getChessGame(gameId);
        return chessGame.judgeWinner();
    }

    public boolean isComplete(int gameId) {
        ChessGame chessGame = getChessGame(gameId);
        return chessGame.isFinish() && !chessGame.isTerminate();
    }

    public Map<String, Object> showBoard(int gameId) {
        ChessGame chessGame = getChessGame(gameId);
        return chessGame.getBoard().toMap();
    }
}
