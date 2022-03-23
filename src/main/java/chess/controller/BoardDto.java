package chess.controller;

import java.util.ArrayList;
import java.util.List;

public final class BoardDto {

    private final List<String> pieces;

    public BoardDto(final List<String> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    public List<String> getPieces() {
        return new ArrayList<>(pieces);
    }
}
