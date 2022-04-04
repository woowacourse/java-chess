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
import chess.dto.RoomDto;
import chess.result.EndResult;
import chess.result.MoveResult;
import chess.result.StartResult;
import chess.view.PieceName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ChessService {

    public Map<String, Object> findAllPiece(final String roomName) {
        Map<String, Object> model = new HashMap<>();
        final ChessGame chessGame = findGameByRoomName(roomName);
        try {
            model = toModel(chessGame.findAllPiece());
        } catch (IllegalArgumentException e) {
            model.put("error", e.getMessage());
        }
        return model;
    }

    public Map<String, Object> startGame(final String roomName) {
        Map<String, Object> model = new HashMap<>();
        final ChessGame chessGame = findGameByRoomName(roomName);
        try {
            final StartResult result = chessGame.start();
            model = toModel(result.getPieceByPosition());
            updateChessPiece(roomName, result.getPieceByPosition());
            updateRoomStatusTo(roomName, GameStatus.PLAYING);
        } catch (IllegalArgumentException e) {
            if (chessGame.canPlay()) {
                model = findAllPiece(roomName);
            }
            model.put("error", e.getMessage());
        }
        return model;
    }

    public Map<String, Object> move(final String roomName, String fromPosition, String toPosition) {
        Map<String, Object> model = new HashMap<>();
        final ChessGame chessGame = findGameByRoomName(roomName);
        try {
            final Position from = Position.from(fromPosition);
            final Position to = Position.from(toPosition);

            final MoveResult result = chessGame.move(from, to);
            model = toModel(result.getPieceByPosition());
            model.put("isKingDie", result.isKingDie());

            updatePosition(roomName, from, to);
            updateRoom(roomName, result.getGameStatus(), result.getCurrentTurn());
        } catch (IllegalArgumentException e) {
            if (chessGame.canPlay()) {
                model = findAllPiece(roomName);
            }
            model.put("error", e.getMessage());
        }
        return model;
    }

    private void updatePosition(final String roomName, final Position from, final Position to) {
        final ChessPieceDao chessPieceDao = new ChessPieceDao();
        chessPieceDao.deleteByPosition(roomName, to);
        chessPieceDao.update(roomName, from, to);
    }

    public Map<String, Object> endGame(final String roomName) {
        Map<String, Object> model = new HashMap<>();
        final ChessGame chessGame = findGameByRoomName(roomName);
        try {
            final EndResult result = chessGame.end();
            final Score score = result.getScore();
            for (final Color color : Color.values()) {
                model.put(color.getValue(), score.findScore(color));
            }
            updateRoomStatusTo(roomName, GameStatus.END);
        } catch (IllegalArgumentException e) {
            model.put("error", e.getMessage());
        }
        return model;
    }

    public Map<String, Object> findScore(final String roomName) {
        final Map<String, Object> model = new HashMap<>();
        final ChessGame chessGame = findGameByRoomName(roomName);
        try {
            final Score score = chessGame.calculateScore();
            for (final Color color : Color.values()) {
                model.put(color.getValue(), score.findScore(color));
            }
        } catch (IllegalArgumentException e) {
            model.put("error", e.getMessage());
        }
        return model;
    }

    public Map<String, Object> findCurrentTurn(final String roomName) {
        final Map<String, Object> model = new HashMap<>();
        final ChessGame chessGame = findGameByRoomName(roomName);
        try {
            final Color currentTurn = chessGame.findCurrentTurn();
            model.put("current_turn", currentTurn.getValue());
        } catch (IllegalArgumentException e) {
            model.put("error", e.getMessage());
        }
        return model;
    }

    public Map<String, Object> result(final String roomName) {
        final Map<String, Object> model = new HashMap<>();
        final ChessGame chessGame = findGameByRoomName(roomName);
        try {
            final Color winColor = chessGame.findWinColor();
            if (Objects.isNull(winColor)) {
                model.put("win_color", "draw");
                return model;
            }
            model.put("win_color", winColor.getValue());
        } catch (IllegalArgumentException e) {
            model.put("error", e.getMessage());
        }
        return model;
    }

    private Map<String, Object> toModel(final Map<Position, ChessPiece> pieceByPosition) {
        return pieceByPosition.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getValue(),
                        entry -> PieceName.findWebImagePath(entry.getValue())));
    }

    private ChessGame findGameByRoomName(final String roomName) {
        Map<Position, ChessPiece> pieceByPosition = initAllPiece(roomName);
        Color currentTurn = initCurrentTur(roomName);
        GameStatus gameStatus = initGameStatus(roomName);

        return new ChessGame(new ChessBoard(pieceByPosition, currentTurn), gameStatus);
    }

    private Map<Position, ChessPiece> initAllPiece(final String roomName) {
        final ChessPieceDao chessPieceDao = new ChessPieceDao();
        final List<ChessPieceDto> dtos = chessPieceDao.findAllByRoomName(roomName);
        if (dtos.isEmpty()) {
            return ChessBoardFactory.createInitPieceByPosition();
        }
        return dtos.stream()
                .collect(Collectors.toMap(
                        ChessPieceDto::getPosition,
                        ChessPieceDto::getChessPiece));
    }

    private Color initCurrentTur(final String roomName) {
        final RoomDao roomDao = new RoomDao();
        final RoomDto dto = roomDao.findByName(roomName);
        if (Objects.isNull(dto)) {
            return Color.WHITE;
        }
        return dto.getCurrentTurn();
    }

    private GameStatus initGameStatus(final String roomName) {
        final RoomDao roomDao = new RoomDao();
        final RoomDto dto = roomDao.findByName(roomName);
        if (Objects.isNull(dto)) {
            return GameStatus.READY;
        }
        return dto.getGameStatus();
    }

    private void updateChessPiece(final String roomName, final Map<Position, ChessPiece> pieceByPosition) {
        final ChessPieceDao chessPieceDao = new ChessPieceDao();
        chessPieceDao.deleteAllByRoomName(roomName);
        chessPieceDao.saveAll(roomName, pieceByPosition);
    }

    private void updateRoom(final String roomName, final GameStatus gameStatus, final Color currentTurn) {
        final RoomDao roomDao = new RoomDao();
        roomDao.update(roomName, gameStatus, currentTurn);
    }

    private void updateRoomStatusTo(final String roomName, final GameStatus gameStatus) {
        final RoomDao roomDao = new RoomDao();
        roomDao.updateStatusTo(roomName, gameStatus);
    }
}
