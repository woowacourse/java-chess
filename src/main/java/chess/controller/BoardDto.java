package chess.controller;

import java.util.List;

public class BoardDto {

    private List<String> pieces;

    public BoardDto(List<String> pieces) {
        this.pieces = pieces;
    }

    public List<String> getPieces() {
        return pieces;
    }
}
