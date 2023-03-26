package chess.domain.dto;

import chess.domain.game.ChessGame;

public class ChessGameDto {

    private final String id;
    private final ChessGame chessGame;

    public ChessGameDto(final String id, final ChessGame chessGame) {
        this.id = id;
        this.chessGame = chessGame;
    }

    public String getId() {
        return id;
    }

    public ChessGame getChessGame() {
        return chessGame;
    }
}
