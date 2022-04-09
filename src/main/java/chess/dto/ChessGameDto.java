package chess.dto;

import chess.domain.game.Color;
import chess.domain.game.Result;

public class ChessGameDto {
    private final BoardDto boardDto;
    private final Result result;
    private final Color turn;

    public ChessGameDto(BoardDto boardDto, Result result, Color turn) {
        this.boardDto = boardDto;
        this.result = result;
        this.turn = turn;
    }

    public BoardDto getBoardDto() {
        return boardDto;
    }

    public Result getResult(){
        return result;
    }

    public Color getTurn() {
        return turn;
    }
}