package chess.controller.dto;

import chess.domain.game.GameStatus;
import chess.domain.game.Player;
import chess.domain.status.Result;

public class ResponseDto {
    private final ChessBoardDto chessBoardDto;
    private final Result result;
    private final Player turn;
    private final GameStatus gameStatus;
    private int roomNumber;

    public ResponseDto(ChessBoardDto chessBoardDto, Result result, Player turn, int roomNumber, GameStatus gameStatus) {
        this.chessBoardDto = chessBoardDto;
        this.result = result;
        this.turn = turn;
        this.roomNumber = roomNumber;
        this.gameStatus = gameStatus;
    }


    public ResponseDto(ChessBoardDto chessBoardDto, Result result, Player turn, GameStatus gameStatus) {
        this(chessBoardDto, result, turn, 0, gameStatus);
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

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
