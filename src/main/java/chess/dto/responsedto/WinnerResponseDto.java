package chess.dto.responsedto;

import chess.domain.piece.Color;

public class WinnerResponseDto implements ResponseDto {
    private final String winner;

    public WinnerResponseDto(Color winnerColor) {
        this(winnerColor.name());
    }

    public WinnerResponseDto(String winner) {
        this.winner = winner;
    }
}
