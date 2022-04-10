package chess.dto;

import chess.domain.piece.Color;
import chess.domain.state.Result;
import chess.domain.state.Status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ChessGameDto {

    final Map<String, PieceDto> positionsAndPieces = new HashMap<>();
    private final Map<Color, Double> whiteScore;
    private final Map<Color, Double> blackScore;
    private final Result result;

    public ChessGameDto(final Map<String, List<String>> board, final Status status) {
        for (final Map.Entry<String, List<String>> entry : board.entrySet()) {
            positionsAndPieces.put(entry.getKey(), new PieceDto(entry.getValue()));
        }
        whiteScore = status.getWhiteScore();
        blackScore = status.getBlackScore();
        result = status.getResult();
    }
}
