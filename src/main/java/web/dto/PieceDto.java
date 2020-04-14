package web.dto;

public class PieceDto {
	private final String symbol;
	private final String team;
	private String Position;

	public PieceDto(String symbol, String team, String position) {
		this.symbol = symbol;
		this.team = team;
		this.Position = position;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getTeam() {
		return team;
	}

	public String getPosition() {
		return Position;
	}
}
