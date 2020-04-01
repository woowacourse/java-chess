package chess.dto;

public class ChessGameDto {
	private BoardDto boardDto;
	private TurnDto turnDto;
	private StatusDto statusDto;
	private boolean isFinished;

	public ChessGameDto(BoardDto boardDto, TurnDto turnDto, StatusDto statusDto, boolean isFinished) {
		this.boardDto = boardDto;
		this.turnDto = turnDto;
		this.statusDto = statusDto;
		this.isFinished = isFinished;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean finished) {
		isFinished = finished;
	}

	public BoardDto getBoardDto() {
		return boardDto;
	}

	public void setBoardDto(BoardDto boardDto) {
		this.boardDto = boardDto;
	}

	public TurnDto getTurnDto() {
		return turnDto;
	}

	public void setTurnDto(TurnDto turnDto) {
		this.turnDto = turnDto;
	}

	public StatusDto getStatusDto() {
		return statusDto;
	}

	public void setStatusDto(StatusDto statusDto) {
		this.statusDto = statusDto;
	}
}
