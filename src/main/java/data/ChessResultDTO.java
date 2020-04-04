package data;

import chess.result.ChessResult;

public class ChessResultDTO {
    private final String result;
    private final String name;

    public ChessResultDTO(ChessResult chessResult) {
        this.result = "승리";
        this.name = chessResult.getName();
    }
}
