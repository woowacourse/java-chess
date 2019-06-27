package chess.dto;

public class ChessBoardDto {
    private BoardDto boardDto;
    private TurnDto turnDto;
    private ResultCounterDto resultCounterDto;

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

    public ResultCounterDto getResultCounterDto() {
        return resultCounterDto;
    }

    public void setResultCounterDto(ResultCounterDto resultCounterDto) {
        this.resultCounterDto = resultCounterDto;
    }
}
