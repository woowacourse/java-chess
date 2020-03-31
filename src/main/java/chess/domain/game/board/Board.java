package chess.domain.game.board;

import chess.domain.piece.pieces.Pieces;
import chess.domain.position.Column;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private final List<Rows> rows;

    public Board(Pieces pieces) {
        rows = Arrays.stream(Column.values())
                .map(column -> new Rows(column, pieces))
                .collect(Collectors.toList());
    }

    public List<Rows> getRows() {
        return Collections.unmodifiableList(rows);
    }
}