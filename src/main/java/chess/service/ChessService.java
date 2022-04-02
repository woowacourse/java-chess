package chess.service;

import chess.domain.ChessGame;
import chess.domain.Score;
import chess.domain.chessboard.ChessBoardFactory;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Color;
import chess.domain.position.Position;
import chess.result.EndResult;
import chess.result.MoveResult;
import chess.result.StartResult;
import chess.view.PieceName;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessService {

    private final ChessGame chessGame;

    public ChessService() {
        this.chessGame = new ChessGame(ChessBoardFactory.createChessBoard());
    }

    public Map<String, Object> findAllPiece() {
        Map<String, Object> model = new HashMap<>();
        try {
            model = toModel(chessGame.findAllPiece());
        } catch (IllegalArgumentException e) {
            model.put("error", e.getMessage());
        }
        return model;
    }

    public Map<String, Object> startGame() {
        Map<String, Object> model = new HashMap<>();
        try {
            final StartResult result = chessGame.start();
            model = toModel(result.getPieceByPosition());
        } catch (IllegalArgumentException e) {
            if (chessGame.canPlay()) {
                model = findAllPiece();
            }
            model.put("error", e.getMessage());
        }
        return model;
    }

    public Map<String, Object> move(String fromPosition, String toPosition) {
        Map<String, Object> model = new HashMap<>();
        try {
            final MoveResult result = chessGame.move(
                    Position.from(fromPosition),
                    Position.from(toPosition)
            );
            model = toModel(result.getPieceByPosition());
            model.put("isKingDie", result.isKingDie());
        } catch (IllegalArgumentException e) {
            if (chessGame.canPlay()) {
                model = findAllPiece();
            }
            model.put("error", e.getMessage());
        }
        return model;
    }

    public Map<String, Object> endGame() {
        Map<String, Object> model = new HashMap<>();
        try {
            final EndResult result = chessGame.end();
            final Score score = result.getScore();
            for (final Color color : Color.values()) {
                model.put(color.getValue(), score.findScore(color));
            }
        } catch (IllegalArgumentException e) {
            model.put("error", e.getMessage());
        }
        return model;
    }

    public Map<String, Object> findScore() {
        final Map<String, Object> model = new HashMap<>();
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

    public Map<String, Object> findCurrentTurn() {
        final Map<String, Object> model = new HashMap<>();
        try {
            final Color currentTurn = chessGame.findCurrentTurn();
            model.put("current_turn", currentTurn.getValue());
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
