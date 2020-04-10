package chess.dto;

import chess.domain.piece.Piece;

public class PieceDto {
    String position;
    String name;

    public PieceDto(Piece piece) {
        this.position = piece.getPosition().toString();
        this.name = piece.getPieceName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
