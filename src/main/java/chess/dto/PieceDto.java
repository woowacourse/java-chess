package chess.dto;

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
}
