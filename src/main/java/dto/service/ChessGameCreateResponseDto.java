package dto.service;

import domain.game.ChessGame;

public class ChessGameCreateResponseDto {
    private final ChessGame chessGame;
    private final Long roomId;

    public ChessGameCreateResponseDto(ChessGame chessGame, Long gameId) {
        this.chessGame = chessGame;
        this.roomId = gameId;
    }


    public ChessGame getChessGame() {
        return chessGame;
    }

    public Long getRoomId() {
        return roomId;
    }
}
