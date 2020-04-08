package dto;

import chess.result.ChessResult;

public class ChessResultDto {
    private static final String WIN_VALUE = "승리";

    private final String result;
    private final String name;

    public ChessResultDto(ChessResult chessResult) {
        this.result = WIN_VALUE;
        this.name = chessResult.getName();
    }
}
