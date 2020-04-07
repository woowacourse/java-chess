package chess.dto;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class UnitsDto {
	private final List<UnitDto> units;

	public UnitsDto(Map<Position, Piece> units) {
		this.units = units.values().stream()
			.map(piece -> new UnitDto(
				piece.getPosition().getColumn().intValue(),
				piece.getPosition().getRow().intValue(),
				piece.getTeam().name(),
				piece.getSymbol()
			)).collect(Collectors.toList());
	}

	public List<UnitDto> getUnits() {
		return Collections.unmodifiableList(units);
	}
}
