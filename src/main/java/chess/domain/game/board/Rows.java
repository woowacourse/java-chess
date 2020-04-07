package chess.domain.game.board;

import chess.domain.piece.Piece;
import chess.domain.piece.pieces.Pieces;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.PositionFactory;
import chess.domain.position.Row;

import java.util.*;
import java.util.stream.Collectors;

public class Rows {
    private final List<Piece> pieces;

    public Rows(Column column, Pieces pieces) {
        this.pieces = Arrays.stream(Row.values())
                .map(row -> PositionFactory.of(row, column))
                .map(pieces::findByPosition)
                .collect(Collectors.toList());
    }

    public List<String> getResources() {
        List<String> resources = pieces.stream()
                .map(Piece::getResource)
                .collect(Collectors.toList());
        return Collections.unmodifiableList(resources);
    }
}
