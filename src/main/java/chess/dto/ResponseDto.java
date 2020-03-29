package chess.dto;

import java.util.Map;

import chess.domain.Turn;
import chess.domain.position.Position;

public class ResponseDto {
	private final Map<Position, String> boardDto;
	private Turn turn = null;

	public ResponseDto(Map<Position, String> boardDto) {
		this.boardDto = boardDto;
	}

	public ResponseDto(Map<Position, String> boardDto, Turn turn) {
		this.boardDto = boardDto;
		this.turn = turn;
	}

	public Map<Position, String> getBoardDto() {
		return boardDto;
	}

	public Turn getTurn() {
		return turn;
	}
}
