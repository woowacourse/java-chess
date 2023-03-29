package controller;

import java.util.List;

public final class ChessBoardDto {

    private final List<String> rows;

    public ChessBoardDto(final List<String> rows) {
        this.rows = rows;
    }

    public List<String> getRows() {
        return rows;
    }
}
