package chess.dto;

import java.util.List;

public class LineDto {

    private List<GamePieceDto> gamePieces;

    public LineDto(List<GamePieceDto> gamePieces) {
        this.gamePieces = gamePieces;
    }

    public List<GamePieceDto> getGamePieces() {
        return gamePieces;
    }
}
