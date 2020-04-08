package chess.dto;

import chess.domain.game.Player;
import chess.domain.result.Result;

public class ResponseDto {
    private final ChessBoardDto chessBoardDto;
    private final Result result;
    private final Player turn;

    public ResponseDto(ChessBoardDto chessBoardDto, Result result, Player turn) {
        this.chessBoardDto = chessBoardDto;
        this.result = result;
        this.turn = turn;
    }

    public ChessBoardDto getChessBoardDto() {
        return chessBoardDto;
    }

    public Result getResult() {
        return result;
    }

    public Player getTurn() {
        return turn;
    }
}
