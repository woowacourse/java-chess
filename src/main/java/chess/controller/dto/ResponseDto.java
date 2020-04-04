package chess.controller.dto;

import chess.domain.game.ChessBoard;
import chess.domain.game.Player;
import chess.domain.status.Result;

public class ResponseDto {
    private final ChessBoardDto chessBoardDto;
    private final Result result;
    private final Player turn;

    public ResponseDto(ChessBoard chessBoard, Result result, Player turn) {
        this.chessBoardDto = new ChessBoardDto(chessBoard.getChessBoard());
        this.result = result;
        this.turn = turn;
    }

    public ChessBoardDto getChessBoardDto() {
        return chessBoardDto;
    }

    public Result getResult() {
        return result;
    }
}
