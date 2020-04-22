package chess.dao;

public class BoardMapper {
	private final String piece_name;
	private final String piece_team;
	private final String piece_position;

	public BoardMapper(String piece_name, String piece_team, String piece_position) {
		this.piece_name = piece_name;
		this.piece_team = piece_team;
		this.piece_position = piece_position;
	}

	public String pieceName() {
		return piece_name;
	}

	public String pieceTeam() {
		return piece_team;
	}

	public String piecePosition() {
		return piece_position;
	}
}
