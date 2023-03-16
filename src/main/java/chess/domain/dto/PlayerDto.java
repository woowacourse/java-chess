package chess.domain.dto;

import chess.domain.Pieces;

public class PlayerDto {

    private final Pieces pieces;
    private final String color;

    public PlayerDto(Pieces pieces, String color) {
        this.pieces = pieces;
        this.color = color;
    }
}
