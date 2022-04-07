package chess.dto.response;

import chess.domain.piece.PieceColor;

public class GameDto {
    private final String gameId;
    private final TurnDto turnDto;

    public GameDto(String gameId, TurnDto turnDto) {
        this.gameId = gameId;
        this.turnDto = turnDto;
    }

    public static GameDto from(String gameId, String turn) {
        return new GameDto(gameId, TurnDto.from(turn)); // TODO: null 위험 존재
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
