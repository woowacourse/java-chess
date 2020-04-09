package chess.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.dto.PieceDto;
import chess.dto.ScoreDto;

public class WebOutputRenderer {
	public static List<PieceDto> toPiecesDto(Board board) {
		return board.getPieces().entrySet()
			.stream()
			.map(x -> PieceDto.of(x.getKey(), x.getValue()))
			.collect(Collectors.toList());
	}

	public static List<ScoreDto> scoreToModel(Map<Color, Double> scores) {
		return scores.entrySet()
			.stream()
			.map(x -> new ScoreDto(x.getKey(), x.getValue()))
			.collect(Collectors.toList());
	}
}
