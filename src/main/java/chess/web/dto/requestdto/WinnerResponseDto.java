package chess.web.dto.requestdto;

import chess.domain.piece.Color;
import chess.web.dto.responsedto.ResponseDto;

public class WinnerResponseDto implements ResponseDto {
    private final String winner;

    public WinnerResponseDto(Color winnerColor) {
        this(winnerColor.name());
    }

    public WinnerResponseDto(String winner) {
        this.winner = winner;
    }
}
