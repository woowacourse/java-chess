package chess.service;

import chess.controller.dto.request.MoveRequest;
import chess.controller.dto.response.ChessGameResponse;
import chess.controller.dto.response.PieceResponse;
import chess.controller.dto.response.StatusResponse;
import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.ChessGame;
import chess.domain.GameState;
import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.board.strategy.CreateCompleteBoardStrategy;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChessService {

    private static final int ROW_INDEX = 0;
    private static final int COLUMN_INDEX = 1;

    private static final String NOT_HAVE_GAME = "해당하는 게임이 없습니다.";

    private final GameDao gameDao;
    private final PieceDao pieceDao;

    private final Map<Long, ChessGame> chessGames;

    public ChessService(GameDao gameDao, PieceDao pieceDao) {
        this.gameDao = gameDao;
        this.pieceDao = pieceDao;
        this.chessGames = new HashMap<>();
    }

    public ChessGameResponse createOrLoadGame(long gameId) {
        return loadGame(gameId);
    }

    public ChessGameResponse loadGame(long gameId) {
        Optional<GameState> maybeGameState = gameDao.load(gameId);
        if (maybeGameState.isEmpty()) {
            return createGame(gameId);
        }
        GameState gameState = maybeGameState.get();
        Map<Position, Piece> pieces = new HashMap<>();
        for (PieceResponse pieceResponse : pieceDao.findAll(gameId)) {
            Position position = parseStringToPosition(pieceResponse.getPosition());
            Piece piece = pieceResponse.toPiece();
            pieces.put(position, piece);
        }
        Board board = new Board(() -> pieces);
        chessGames.put(gameId, new ChessGame(board, gameState));
        return new ChessGameResponse(getChessGame(gameId));
    }

    private ChessGame getChessGame(long gameId) {
        if (!chessGames.containsKey(gameId)) {
            throw new IllegalArgumentException(NOT_HAVE_GAME);
        }
        return chessGames.get(gameId);
    }

    private ChessGameResponse createGame(long gameId) {
        ChessGame chessGame = new ChessGame(new Board(new CreateCompleteBoardStrategy()));
        gameDao.save(gameId);
        saveBoard(gameId, chessGame.getBoard());
        chessGames.put(gameId, chessGame);
        return new ChessGameResponse(chessGame);
    }

    public ChessGameResponse restartGame(long gameId) {
        gameDao.delete(gameId);
        return createGame(gameId);
    }

    public void saveBoard(long gameId, Map<Position, Piece> board) {
        board.forEach((position, piece) -> savePiece(gameId, position, piece));
    }

    public void savePiece(long gameId, Position position, Piece piece) {
        if (pieceDao.find(gameId, position).isEmpty()) {
            pieceDao.save(gameId, position, piece);
        }
    }

    public ChessGameResponse startGame(long gameId) {
        ChessGame chessGame = getChessGame(gameId);
        chessGame.start();
        gameDao.updateState(gameId, chessGame.getGameState());
        return new ChessGameResponse(chessGame);
    }

    public ChessGameResponse move(long gameId, MoveRequest moveRequest) {
        ChessGame chessGame = getChessGame(gameId);
        Position start = parseStringToPosition(moveRequest.getStart());
        Position target = parseStringToPosition(moveRequest.getTarget());
        chessGame.move(start, target);
        if (pieceDao.find(gameId, target).isPresent()) {
            pieceDao.delete(gameId, target);
        }
        pieceDao.updatePosition(gameId, start, target);
        gameDao.updateState(gameId, chessGame.getGameState());
        return new ChessGameResponse(chessGame);
    }

    public StatusResponse status(long gameId) {
        ChessGame chessGame = getChessGame(gameId);
        return new StatusResponse(chessGame.createStatus());
    }

    public ChessGameResponse end(long gameId) {
        ChessGame chessGame = getChessGame(gameId);
        chessGame.end();
        gameDao.updateState(gameId, chessGame.getGameState());
        return new ChessGameResponse(chessGame);
    }

    private Position parseStringToPosition(final String rawPosition) {
        final String[] separatedPosition = rawPosition.split("");
        final Column column = Column.from(separatedPosition[ROW_INDEX]);
        final Row row = Row.from(separatedPosition[COLUMN_INDEX]);
        return new Position(column, row);
    }
}
