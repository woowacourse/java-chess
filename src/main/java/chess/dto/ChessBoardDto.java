package chess.dto;

public class ChessBoardDto {
    private BoardDto boardDTO;
    private TurnDto turnDTO;
    private ResultCounterDto resultCounterDTO;

    public BoardDto getBoardDTO() {
        return boardDTO;
    }

    public void setBoardDTO(BoardDto boardDTO) {
        this.boardDTO = boardDTO;
    }

    public TurnDto getTurnDTO() {
        return turnDTO;
    }

    public void setTurnDTO(TurnDto turnDTO) {
        this.turnDTO = turnDTO;
    }

    public ResultCounterDto getResultCounterDTO() {
        return resultCounterDTO;
    }

    public void setResultCounterDTO(ResultCounterDto resultCounterDTO) {
        this.resultCounterDTO = resultCounterDTO;
    }
}
