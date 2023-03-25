package chess.domain.piece.dto;

import chess.domain.position.Position;

public class UpdatePiecePositionDto {

    private String rank;
    private String file;

    public UpdatePiecePositionDto(Position positionToUpdate) {
        this.rank = String.valueOf(positionToUpdate.getRank());
        this.file = String.valueOf(positionToUpdate.getFile());
    }

    public String getRank() {
        return rank;
    }

    public String getFile() {
        return file;
    }
}
