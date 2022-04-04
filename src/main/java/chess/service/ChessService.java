package chess.service;

import chess.dao.ChessPieceDao;
import chess.domain.ChessGame;
import chess.domain.Score;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Color;
import chess.domain.position.Position;
import chess.result.EndResult;
import chess.result.MoveResult;
import chess.result.StartResult;
import chess.view.PieceName;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ChessService {

    public Map<String, Object> findAllPiece(final String roomName) {
        Map<String, Object> model = new HashMap<>();
        final ChessGame chessGame = ChessGame.from(roomName);
        try {
            model = toModel(chessGame.findAllPiece());
        } catch (IllegalArgumentException e) {
            model.put("error", e.getMessage());
        }
        return model;
    }

    public Map<String, Object> startGame(final String roomName) {
        Map<String, Object> model = new HashMap<>();
        final ChessGame chessGame = ChessGame.from(roomName);
        try {
            final StartResult result = chessGame.start();
            model = toModel(result.getPieceByPosition());
        } catch (IllegalArgumentException e) {
            if (chessGame.canPlay()) {
                model = findAllPiece(roomName);
            }
            model.put("error", e.getMessage());
        }
        chessGame.updateChessPiece(roomName);
        return model;
    }

    public Map<String, Object> move(final String roomName, String fromPosition, String toPosition) {
        Map<String, Object> model = new HashMap<>();
        final ChessGame chessGame = ChessGame.from(roomName);
        try {
            final Position from = Position.from(fromPosition);
            final Position to = Position.from(toPosition);

            final MoveResult result = chessGame.move(from, to);
            model = toModel(result.getPieceByPosition());
            model.put("isKingDie", result.isKingDie());

            final ChessPieceDao chessPieceDao = new ChessPieceDao();
            chessPieceDao.deleteByPosition(roomName, to);
            chessPieceDao.update(roomName, from, to);

            chessGame.updateRoom(roomName);
        } catch (IllegalArgumentException e) {
            if (chessGame.canPlay()) {
                model = findAllPiece(roomName);
            }
            model.put("error", e.getMessage());
        }
        return model;
    }

    public Map<String, Object> endGame(final String roomName) {
        Map<String, Object> model = new HashMap<>();
        final ChessGame chessGame = ChessGame.from(roomName);
        try {
            final EndResult result = chessGame.end();
            final Score score = result.getScore();
            for (final Color color : Color.values()) {
                model.put(color.getValue(), score.findScore(color));
            }
        } catch (IllegalArgumentException e) {
            model.put("error", e.getMessage());
        }
        chessGame.updateRoom(roomName);
        return model;
    }

    public Map<String, Object> findScore(final String roomName) {
        final Map<String, Object> model = new HashMap<>();
        final ChessGame chessGame = ChessGame.from(roomName);
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
        final ChessGame chessGame = ChessGame.from(roomName);
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
        final ChessGame chessGame = ChessGame.from(roomName);
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
}
