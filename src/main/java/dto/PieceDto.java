package dto;

import java.util.Objects;

public class PieceDto {
	final int id;
	final String pieceType;
	final String team;
	final String coordinate;
	final int roomId;

	public PieceDto(final int id, final String pieceType, final String team,
					final String coordinate, final int roomId) {
		this.id = id;
		this.pieceType = pieceType;
		this.team = team;
		this.coordinate = coordinate;
		this.roomId = roomId;
	}

	public int getId() {
		return id;
	}

	public String getPieceType() {
		return pieceType;
	}

	public String getTeam() {
		return team;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public int getRoomId() {
		return roomId;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final PieceDto pieceDto = (PieceDto) o;
		return id == pieceDto.id &&
				roomId == pieceDto.roomId &&
				Objects.equals(pieceType, pieceDto.pieceType) &&
				Objects.equals(team, pieceDto.team) &&
				Objects.equals(coordinate, pieceDto.coordinate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, pieceType, team, coordinate, roomId);
	}

	@Override
	public String toString() {
		return "Piece{" +
				"id=" + id +
				", pieceType='" + pieceType + '\'' +
				", team='" + team + '\'' +
				", coordinate='" + coordinate + '\'' +
				", roomId=" + roomId +
				'}';
	}
}
