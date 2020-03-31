package chess.domain.game.board;

import chess.domain.piece.Piece;
import chess.domain.piece.pieces.Pieces;
import chess.domain.position.Column;
import chess.domain.position.PositionFactory;
import chess.domain.position.Row;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Rows {
    private final List<Piece> pieces;

    public Rows(Column column, Pieces pieces) {
        this.pieces = Arrays.stream(Row.values())
                .map(row -> PositionFactory.of(row, column))
                .map(pieces::findBy)
                .collect(Collectors.toList());
    }

    public List<String> getResources() {
        List<String> resources = pieces.stream()
                .map(Piece::getResource)
                .collect(Collectors.toList());
        return Collections.unmodifiableList(resources);
    }

    public List<Piece> getPieces(){
        // TODO: 2020/03/31 웹에서 사용할 수 있어서 일단 구현해 뒀음!! 포지션 정보도 필요할까 싶어... 
        return Collections.unmodifiableList(pieces);
    }
}
