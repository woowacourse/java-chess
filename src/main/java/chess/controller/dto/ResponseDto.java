package chess.controller.dto;

import chess.domain.game.Player;
import chess.domain.status.Result;

public class ResponseDto {
    private final ChessBoardDto chessBoardDto;
    private final Result result;
    private final Player turn;
    private int roomNumber;

    public ResponseDto(ChessBoardDto chessBoardDto, Result result, Player turn, int roomNumber) {
        this.chessBoardDto = chessBoardDto;
        this.result = result;
        this.turn = turn;
        this.roomNumber = roomNumber;
    }

    public ResponseDto(ChessBoardDto chessBoardDto, Result result, Player turn) {
        this(chessBoardDto, result, turn, 0);
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

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
