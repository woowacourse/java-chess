package chess.service;

import chess.domain.piece.Color;
import java.util.Map;

public interface ChessService {
    Map<String, String> getBoardByGameId(String gameId);

    boolean move(String gameId, String from, String to);

    boolean isFinished(String gameId);

    Map<Color, Double> getScore(String gameId);
}
