package chess.dto;

public class PieceResponseDTO {
	String position;
	String imgUrl;

	public PieceResponseDTO(String position, String imgUrl) {
		this.position = position;
		this.imgUrl = imgUrl;
	}

	public String getPosition() {
		return position;
	}

	public String getImgUrl() {
		return imgUrl;
	}
}
