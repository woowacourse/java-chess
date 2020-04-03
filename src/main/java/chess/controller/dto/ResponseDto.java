package chess.controller.dto;

import chess.domain.game.ChessBoard;
import chess.domain.status.Result;

public class ResponseDto {
    private ChessBoard chessBoard;
    private Result result;

    public ResponseDto(ChessBoard chessBoard, Result result) {
        this.chessBoard = chessBoard;
        this.result = result;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public Result getResult() {
        return result;
    }
}
