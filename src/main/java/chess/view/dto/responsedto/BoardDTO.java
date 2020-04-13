package chess.view.dto.responsedto;

import java.util.Objects;

public class BoardDTO {
	private final String position;
	private final String symbol;

	public BoardDTO(String position, String symbol) {
		this.position = position;
		this.symbol = symbol;
	}

	public String getPosition() {
		return position;
	}

	public String getSymbol() {
		return symbol;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BoardDTO boardDTO = (BoardDTO)o;
		return Objects.equals(position, boardDTO.position) &&
			Objects.equals(symbol, boardDTO.symbol);
	}

	@Override
	public int hashCode() {
		return Objects.hash(position, symbol);
	}

	@Override
	public String toString() {
		return "BoardDTO{" +
			"position='" + position + '\'' +
			", symbol='" + symbol + '\'' +
			'}';
	}
}
