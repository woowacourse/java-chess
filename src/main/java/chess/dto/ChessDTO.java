package chess.dto;

public class ChessDTO {
	private int id;
	private String rows;

	public ChessDTO(String rows) {
		this.rows = rows;
	}

	public String getRows() {
		return rows;
	}

}
