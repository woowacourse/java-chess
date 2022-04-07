package chess.dto.response;

import chess.domain.piece.PieceColor;

public class ChessGameDto {
    private final String gameId;
    private final TurnDto turnDto;

    public ChessGameDto(String gameId, TurnDto turnDto) {
        this.gameId = gameId;
        this.turnDto = turnDto;
    }

    public static ChessGameDto from(String gameId, String turn) {
        return new ChessGameDto(gameId, TurnDto.from(turn)); // TODO: null 위험 존재
    }

    public PieceColor getCurrentTurnAsPieceColor() {
        return turnDto.toPieceColor();
    }

    @Override
    public String toString() {
        return "GameDto{" +
                "gameId='" + gameId + '\'' +
                ", currentTurnDto=" + turnDto +
                '}';
    }
}
