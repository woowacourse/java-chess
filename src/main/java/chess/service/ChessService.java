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

    private final GameDao gameDao;
    private final PieceDao pieceDao;

    private ChessGame chessGame;

    public ChessService(GameDao gameDao, PieceDao pieceDao) {
        this.gameDao = gameDao;
        this.pieceDao = pieceDao;
    }

    public ChessGameResponse createOrLoadGame() {
        return loadGame();
    }

    public ChessGameResponse loadGame() {
        Optional<GameState> gameStateOptional = gameDao.load(1);
        if (gameStateOptional.isEmpty()) {
            return createGame();
        }
        GameState gameState = gameStateOptional.get();
        Map<Position, Piece> pieces = new HashMap<>();
        for (PieceResponse pieceResponse : pieceDao.findAll(1)) {
            Position position = parseStringToPosition(pieceResponse.getPosition());
            Piece piece = pieceResponse.toPiece();
            pieces.put(position, piece);
        }
        Board board = new Board(() -> pieces);
        this.chessGame = new ChessGame(board, gameState);
        return new ChessGameResponse(chessGame);
    }

    private ChessGameResponse createGame() {
        this.chessGame = new ChessGame(new Board(new CreateCompleteBoardStrategy()));
        gameDao.save(1);
        saveBoard(1, chessGame.getBoard());
        return new ChessGameResponse(chessGame);
    }

    public ChessGameResponse restartGame() {
        pieceDao.deleteAll(1);
        gameDao.delete(1);
        return createGame();
    }

    public void saveBoard(long gameId, Map<Position, Piece> board) {
        board.forEach((position, piece) -> savePiece(gameId, position, piece));
    }

    public void savePiece(long gameId, Position position, Piece piece) {
        if (pieceDao.find(gameId, position).isEmpty()) {
            pieceDao.save(gameId, position, piece);
        }
    }

    public ChessGameResponse startGame() {
        chessGame.start();
        gameDao.updateState(1, chessGame.getGameState());
        return new ChessGameResponse(chessGame);
    }

    public ChessGameResponse move(final MoveRequest moveRequest) {
        Position start = parseStringToPosition(moveRequest.getStart());
        Position target = parseStringToPosition(moveRequest.getTarget());
        chessGame.move(start, target);
        if (pieceDao.find(1, target).isPresent()) {
            pieceDao.delete(1, target);
        }
        pieceDao.updatePosition(1, start, target);
        gameDao.updateState(1, chessGame.getGameState());
        return new ChessGameResponse(chessGame);
    }

    public StatusResponse status() {
        return new StatusResponse(chessGame.createStatus());
    }

    public ChessGameResponse end() {
        chessGame.end();
        gameDao.updateState(1, chessGame.getGameState());
        return new ChessGameResponse(chessGame);
    }

    private Position parseStringToPosition(final String rawPosition) {
        final String[] separatedPosition = rawPosition.split("");
        final Column column = Column.from(separatedPosition[ROW_INDEX]);
        final Row row = Row.from(separatedPosition[COLUMN_INDEX]);
        return new Position(column, row);
    }
}
