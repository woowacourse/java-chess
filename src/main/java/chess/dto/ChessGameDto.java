package chess.dto;

import chess.domain.game.ChessGame;

public class ChessGameDto {

    private final ChessGame chessGame;

    public ChessGameDto(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public ChessGame getChessGame() {
        return chessGame;
    }

}
