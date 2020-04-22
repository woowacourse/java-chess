package chess.dto;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.Status;
import chess.domain.Turn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.Score;

public class GameDto {
	private final List<UnitDto> units;
	private final Turn turn;
	private final Status status;

	public GameDto(Map<Position, Piece> units, Turn turn, Status status) {
		this.units = units.values().stream()
			.map(piece -> new UnitDto(
				piece.getColumnValue(),
				piece.getRowValue(),
				piece.getTeam().name(),
				piece.getSymbol()
			)).collect(Collectors.toList());
		this.turn = turn;
		this.status = status;
	}

	public Turn getTurn() {
		return turn;
	}

	public List<UnitDto> getUnits() {
		return Collections.unmodifiableList(units);
	}
}
