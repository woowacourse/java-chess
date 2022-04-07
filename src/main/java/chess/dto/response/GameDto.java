package chess.dto.response;

import chess.domain.piece.PieceColor;

public class GameDto {
    private final String gameId;
    private final CurrentTurnDto currentTurnDto;

    public GameDto(String gameId, CurrentTurnDto currentTurnDto) {
        this.gameId = gameId;
        this.currentTurnDto = currentTurnDto;
    }

    public static GameDto from(String gameId, String turn) {
        return new GameDto(gameId, CurrentTurnDto.from(turn)); // TODO: null 위험 존재
    }

    public PieceColor getCurrentTurnAsPieceColor() {
        return currentTurnDto.toPieceColor();
    }

    @Override
    public String toString() {
        return "GameDto{" +
                "gameId='" + gameId + '\'' +
                ", currentTurnDto=" + currentTurnDto +
                '}';
    }
}
