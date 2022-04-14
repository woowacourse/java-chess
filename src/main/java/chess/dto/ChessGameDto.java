package chess.dto;

import chess.domain.game.Color;

public class ChessGameDto {
    private final BoardDto boardDto;
    private final ResultDto result;
    private final Color turn;

    public ChessGameDto(BoardDto boardDto, ResultDto result, Color turn) {
        this.boardDto = boardDto;
        this.result = result;
        this.turn = turn;
    }

    public BoardDto getBoardDto() {
        return boardDto;
    }

    public Color getTurn() {
        return turn;
    }
}
