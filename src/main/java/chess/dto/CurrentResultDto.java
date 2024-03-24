package chess.dto;

import chess.domain.piece.Color;

public record CurrentResultDto(double blackScore, double whiteScore, Color winnerColor) {
}
