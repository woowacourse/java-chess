package chess.dto;

import chess.domain.piece.PieceColor;

public class GameDto {

    private final String turn;
    private final String status;

    public GameDto(String turn, String status) {
        this.turn = turn;
        this.status = status;
    }

    public static GameDto from(PieceColor turnColor, boolean isPlaying) {
        if (isPlaying) {
            return new GameDto(turnColor.getName(), "playing");
        }
        return new GameDto(turnColor.getName(), "finished");
    }

    public String getTurn() {
        return turn;
    }

    public String getStatus() {
        return status;
    }
}
