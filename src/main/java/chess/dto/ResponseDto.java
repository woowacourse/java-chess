package chess.dto;

import java.util.Map;

import chess.domain.Team;
import chess.domain.position.Position;

public class ResponseDto {
	Map<Position, String> boardDto;

	public ResponseDto(Map<Position, String> boardDto) {
		this.boardDto = boardDto;
	}

	public Map<Position, String> getBoardDto() {
		return boardDto;
	}
}
