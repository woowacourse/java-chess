package chess.domain.dto;

import java.util.Objects;

public class PieceDto {
	private String name;
	private int col;
	private int row;

	public PieceDto(String name, int col, int row) {
		this.name = name;
		this.col = col;
		this.row = row;
	}

	public String getName() {
		return name;
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PieceDto pieceDto = (PieceDto) o;
		return col == pieceDto.col &&
				row == pieceDto.row &&
				Objects.equals(name, pieceDto.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, col, row);
	}
}
