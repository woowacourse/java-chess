package chess.controller.dto;

import chess.domain.game.ChessBoard;
import chess.domain.status.Result;

public class ResponseDto {
    private final ChessBoardDto chessBoardDto;
    private final Result result;

    public ResponseDto(ChessBoard chessBoard, Result result) {
        this.chessBoardDto = new ChessBoardDto(chessBoard.getChessBoard());
        this.result = result;
    }

    public ChessBoardDto getChessBoardDto() {
        return chessBoardDto;
    }

    public Result getResult() {
        return result;
    }
}
