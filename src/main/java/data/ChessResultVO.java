package data;

import chess.result.ChessResult;

public class ChessResultVO {
    private final String result;
    private final String name;

    public ChessResultVO(ChessResult chessResult) {
        this.result = "승리";
        this.name = chessResult.getName();
    }

    public String getName() {
        return name;
    }
}
