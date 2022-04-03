package chess.domain;

import chess.dao.ChessPieceDao;
import chess.dao.RoomDao;
import chess.dao.StatusDao;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.ChessBoardFactory;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Color;
import chess.domain.position.Position;
import chess.dto.ChessPieceDto;
import chess.dto.RoomDto;
import chess.dto.StatusDto;
import chess.result.EndResult;
import chess.result.MoveResult;
import chess.result.StartResult;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ChessGame {

    private ChessBoard chessBoard;
    private GameStatus gameStatus;

    public ChessGame(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.gameStatus = GameStatus.READY;
    }

    private ChessGame(final ChessBoard chessBoard, final GameStatus gameStatus) {
        this.chessBoard = chessBoard;
        this.gameStatus = gameStatus;
    }

    public static ChessGame from(final String roomName) {
        final RoomDao roomDao = new RoomDao();
        final RoomDto roomDto = roomDao.findByName(roomName);
        if (Objects.isNull(roomDto)) {
            roomDao.save(roomName);
        }

        final ChessPieceDao chessPieceDao = new ChessPieceDao();
        Map<Position, ChessPiece> pieceByPosition = chessPieceDao.findAllByRoomName(roomName)
                .stream()
                .collect(Collectors.toMap(
                        ChessPieceDto::getPosition,
                        ChessPieceDto::getChessPiece
                ));

        if (pieceByPosition.isEmpty()) {
            pieceByPosition = ChessBoardFactory.createInitPieceByPosition();
        }

        final StatusDao statusDao = new StatusDao();
        final StatusDto statusDto = statusDao.findByRoomName(roomName);

        Color currentTurn = null;
        GameStatus gameStatus = null;
        if (Objects.isNull(statusDto)) {
            currentTurn = Color.WHITE;
            gameStatus = GameStatus.READY;
        }
        if (Objects.nonNull(statusDto)) {
            currentTurn = statusDto.getCurrentTurn();
            gameStatus = statusDto.getGameStatus();
        }
        return new ChessGame(new ChessBoard(pieceByPosition, currentTurn), gameStatus);
    }

    public void updateDB(final String roomName) {
        final ChessPieceDao chessPieceDao = new ChessPieceDao();
        chessPieceDao.deleteAllByRoomName(roomName);
        chessPieceDao.saveAll(roomName, chessBoard.findAllPiece());

        updateStatus(roomName);
    }

    public void updateStatus(final String roomName) {
        final StatusDao statusDao = new StatusDao();
        statusDao.deleteByRoomName(roomName);
        statusDao.saveByRoomName(roomName, gameStatus, chessBoard.currentTurn());
    }

    public MoveResult move(final Position from, final Position to) {
        gameStatus.checkPlaying();

        final MoveResult result = chessBoard.move(from, to);
        if (chessBoard.isKingDie()) {
            gameStatus = GameStatus.KING_DIE;
        }
        return result;
    }

    public Score calculateScore() {
        gameStatus.checkPlaying();
        return chessBoard.calculateScore();
    }

    public StartResult start() {
        gameStatus.checkReady();
        if (gameStatus.isEnd()) {
            chessBoard = ChessBoardFactory.createChessBoard();
        }
        gameStatus = GameStatus.PLAYING;
        return new StartResult(chessBoard.findAllPiece());
    }

    public EndResult end() {
        gameStatus = GameStatus.END;
        final Score score = new Score(chessBoard.findAllPiece());
        return new EndResult(score);
    }

    public boolean canPlay() {
        return !gameStatus.isEnd();
    }

    public Map<Position, ChessPiece> findAllPiece() {
        gameStatus.checkPlaying();
        return chessBoard.findAllPiece();
    }

    public Color findCurrentTurn() {
        gameStatus.checkPlaying();
        return chessBoard.currentTurn();
    }

    public Color findWinColor() {
        gameStatus.checkEnd();
        return chessBoard.findWinColor();
    }
}
