package chess.dto;

public class ChessBoardDTO {
    private BoardDTO boardDTO;
    private TurnDTO turnDTO;
    private ResultCounterDTO resultCounterDTO;

    public BoardDTO getBoardDTO() {
        return boardDTO;
    }

    public void setBoardDTO(BoardDTO boardDTO) {
        this.boardDTO = boardDTO;
    }

    public TurnDTO getTurnDTO() {
        return turnDTO;
    }

    public void setTurnDTO(TurnDTO turnDTO) {
        this.turnDTO = turnDTO;
    }

    public ResultCounterDTO getResultCounterDTO() {
        return resultCounterDTO;
    }

    public void setResultCounterDTO(ResultCounterDTO resultCounterDTO) {
        this.resultCounterDTO = resultCounterDTO;
    }
}
