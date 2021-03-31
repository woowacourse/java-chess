package dto.response;

import java.util.List;

public class ChessGameResponseDto {

    private final boolean isPlaying;
    private final List<PieceResponseDto> pieces;

    public ChessGameResponseDto(boolean isPlaying, List<PieceResponseDto> pieces) {
        this.isPlaying = isPlaying;
        this.pieces = pieces;
    }

}
