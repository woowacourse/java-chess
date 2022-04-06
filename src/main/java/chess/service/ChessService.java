package chess.service;

import chess.dao.ChessPieceDao;
import chess.dao.RoomDao;
import chess.domain.ChessGame;
import chess.domain.GameStatus;
import chess.domain.Score;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.ChessBoardFactory;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Color;
import chess.domain.position.Position;
import chess.dto.ChessPieceDto;
import chess.dto.CurrentTurnDto;
import chess.dto.RoomStatusDto;
import chess.result.EndResult;
import chess.result.MoveResult;
import chess.result.StartResult;
import chess.view.JsonGenerator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ChessService {

    private final ChessPieceDao chessPieceDao;
    private final RoomDao roomDao;

    public ChessService() {
        this.chessPieceDao = new ChessPieceDao();
        this.roomDao = new RoomDao();
    }

    public JsonGenerator findAllPiece(final String roomName) {
        final JsonGenerator result = JsonGenerator.create();
        try {
            checkRoomExist(roomName);
            final Map<Position, ChessPiece> pieceByPosition = chessPieceDao.findAllByRoomName(roomName).stream()
                    .collect(Collectors.toMap(
                            ChessPieceDto::getPosition,
                            ChessPieceDto::getChessPiece
                    ));
            result.addAllPiece(pieceByPosition);
        } catch (IllegalArgumentException e) {
            result.addError(e.getMessage());
        }
        return result;
    }

    public JsonGenerator startGame(final String roomName) {
        JsonGenerator result = JsonGenerator.create();
        try {
            checkRoomExist(roomName);
            final ChessGame chessGame = findGameByRoomName(roomName);
            final StartResult startResult = chessGame.start();
            result.addAllPiece(startResult.getPieceByPosition());
            updateChessPiece(roomName, startResult.getPieceByPosition());
            updateRoomStatusTo(roomName, GameStatus.PLAYING);
        } catch (IllegalArgumentException e) {
            result.addError(e.getMessage());
        }
        return result;
    }

    public JsonGenerator move(final String roomName, String fromPosition, String toPosition) {
        JsonGenerator result = JsonGenerator.create();
        try {
            checkRoomExist(roomName);
            final ChessGame chessGame = findGameByRoomName(roomName);
            final Position from = Position.from(fromPosition);
            final Position to = Position.from(toPosition);

            final MoveResult moveResult = chessGame.move(from, to);
            result.addAllPiece(moveResult.getPieceByPosition());
            result.addKingDieResult(moveResult.isKingDie());

            updatePosition(roomName, from, to);
            updateRoom(roomName, moveResult.getGameStatus(), moveResult.getCurrentTurn());
        } catch (IllegalArgumentException e) {
            result.addError(e.getMessage());
        }
        return result;
    }

    private void updatePosition(final String roomName, final Position from, final Position to) {
        chessPieceDao.deleteByPosition(roomName, to);
        chessPieceDao.update(roomName, from, to);
    }

    public JsonGenerator endGame(final String roomName) {
        final JsonGenerator result = JsonGenerator.create();
        try {
            checkRoomExist(roomName);
            final ChessGame chessGame = findGameByRoomName(roomName);
            final EndResult endResult = chessGame.end();
            final Score score = endResult.getScore();
            result.addScore(score);
            updateRoomStatusTo(roomName, GameStatus.END);
        } catch (IllegalArgumentException e) {
            result.addError(e.getMessage());
        }
        return result;
    }

    public JsonGenerator findScore(final String roomName) {
        final JsonGenerator result = JsonGenerator.create();
        try {
            checkRoomExist(roomName);
            final ChessGame chessGame = findGameByRoomName(roomName);
            final Score score = chessGame.calculateScore();
            result.addScore(score);
        } catch (IllegalArgumentException e) {
            result.addError(e.getMessage());
        }
        return result;
    }

    public JsonGenerator result(final String roomName) {
        final JsonGenerator result = JsonGenerator.create();
        try {
            checkRoomExist(roomName);
            final ChessGame chessGame = findGameByRoomName(roomName);
            final Color winColor = chessGame.findWinColor();
            result.addWinColor(winColor);
        } catch (IllegalArgumentException e) {
            result.addError(e.getMessage());
        }
        return result;
    }

    private void checkRoomExist(final String roomName) {
        if (!roomDao.isExistName(roomName)) {
            throw new IllegalArgumentException("존재하지 않는 방 입니다.");
        }
    }

    private ChessGame findGameByRoomName(final String roomName) {
        Map<Position, ChessPiece> pieceByPosition = initAllPiece(roomName);
        Color currentTurn = initCurrentTurn(roomName);
        GameStatus gameStatus = initGameStatus(roomName);

        return new ChessGame(new ChessBoard(pieceByPosition, currentTurn), gameStatus);
    }

    private Map<Position, ChessPiece> initAllPiece(final String roomName) {
        final List<ChessPieceDto> dtos = chessPieceDao.findAllByRoomName(roomName);
        if (dtos.isEmpty()) {
            return ChessBoardFactory.createInitPieceByPosition();
        }
        return dtos.stream()
                .collect(Collectors.toMap(
                        ChessPieceDto::getPosition,
                        ChessPieceDto::getChessPiece));
    }

    private Color initCurrentTurn(final String roomName) {
        final CurrentTurnDto dto = roomDao.findCurrentTurnByName(roomName);
        if (Objects.isNull(dto)) {
            return Color.WHITE;
        }
        return dto.getCurrentTurn();
    }

    private GameStatus initGameStatus(final String roomName) {
        final RoomStatusDto dto = roomDao.findStatusByName(roomName);
        if (Objects.isNull(dto)) {
            return GameStatus.READY;
        }
        return dto.getGameStatus();
    }

    private void updateChessPiece(final String roomName, final Map<Position, ChessPiece> pieceByPosition) {
        chessPieceDao.deleteAllByRoomName(roomName);
        chessPieceDao.saveAll(roomName, pieceByPosition);
    }

    private void updateRoom(final String roomName, final GameStatus gameStatus, final Color currentTurn) {
        roomDao.update(roomName, gameStatus, currentTurn);
    }

    private void updateRoomStatusTo(final String roomName, final GameStatus gameStatus) {
        roomDao.updateStatusTo(roomName, gameStatus);
    }
}
