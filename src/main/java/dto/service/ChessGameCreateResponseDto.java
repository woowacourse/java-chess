package dto.service;

import domain.game.ChessGame;

public class ChessGameCreateResponseDto {
    private final ChessGame chessGame;
    private final Long gameId;

    public ChessGameCreateResponseDto(ChessGame chessGame, Long gameId) {
        this.chessGame = chessGame;
        this.gameId = gameId;
    }


    public ChessGame getChessGame() {
        return chessGame;
    }

    public Long getGameId() {
        return gameId;
    }
}
