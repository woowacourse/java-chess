package chess.dto;

public class StatusDto {
	private ScoreDto white;
	private ScoreDto black;

	public StatusDto(ScoreDto white, ScoreDto black) {
		this.white = white;
		this.black = black;
	}

	public ScoreDto getWhite() {
		return white;
	}

	public void setWhite(ScoreDto white) {
		this.white = white;
	}

	public ScoreDto getBlack() {
		return black;
	}

	public void setBlack(ScoreDto black) {
		this.black = black;
	}
}
