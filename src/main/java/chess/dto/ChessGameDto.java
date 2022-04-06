package chess.dto;

import chess.domain.ChessGame;

public class ChessGameDto {

    private final ChessGame chessGame;

    private ChessGameDto(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public static ChessGameDto from(final ChessGame chessGame) {
        return new ChessGameDto(chessGame);
    }

    public String getState() {
        return chessGame.getState().getClass().getSimpleName();
    }
}
