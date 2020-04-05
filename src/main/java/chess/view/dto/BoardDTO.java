package chess.view.dto;

import java.util.Objects;

public class BoardDTO {
	String position;
	String symbol;
	String team;

	public BoardDTO(String position, String symbol, String team) {
		this.position = position;
		this.symbol = symbol;
		this.team = team;
	}

	public String getPosition() {
		return position;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getTeam() {
		return team;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BoardDTO boardDTO = (BoardDTO)o;
		return Objects.equals(position, boardDTO.position) &&
			Objects.equals(symbol, boardDTO.symbol) &&
			Objects.equals(team, boardDTO.team);
	}

	@Override
	public int hashCode() {
		return Objects.hash(position, symbol, team);
	}

	@Override
	public String toString() {
		return "BoardDTO{" +
			"position='" + position + '\'' +
			", symbol='" + symbol + '\'' +
			", team='" + team + '\'' +
			'}';
	}
}
