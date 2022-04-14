package chess.dto;

import chess.domain.piece.PieceColor;

import static chess.dto.GameStatusDto.FINISHED;
import static chess.dto.GameStatusDto.PLAYING;

public class GameDto {

    private final String turn;
    private final String status;

    public GameDto(String turn, String status) {
        this.turn = turn;
        this.status = status;
    }

    public static GameDto from(PieceColor turnColor, boolean isPlaying) {
        if (isPlaying) {
            return new GameDto(turnColor.getName(), PLAYING.getName());
        }
        return new GameDto(turnColor.getName(), FINISHED.getName());
    }

    public String getTurn() {
        return turn;
    }

    public String getStatus() {
        return status;
    }
}
