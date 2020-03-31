package chess.dto;

import java.util.Collections;
import java.util.Map;

import chess.domain.Team;
import chess.domain.Turn;
import chess.domain.position.Position;

public class ResponseDto {
	private final Map<Position, String> boardDto;
	private Map<Team, Double> scoreDto;
	private Turn turn = null;

	public ResponseDto(Map<Position, String> boardDto) {
		this.boardDto = boardDto;
	}

	public ResponseDto(Map<Position, String> boardDto, Turn turn) {
		this.boardDto = boardDto;
		this.turn = turn;
	}

	public ResponseDto(Map<Position, String> boardDto, Map<Team, Double> scoreDto) {
		this.boardDto = boardDto;
		this.scoreDto = scoreDto;
	}

	public Map<Position, String> getBoardDto() {
		return boardDto;
	}

	public Turn getTurn() {
		return turn;
	}

	public Map<Team, Double> getScoreDto() {
		return Collections.unmodifiableMap(scoreDto);
	}
}
