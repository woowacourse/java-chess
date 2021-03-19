package chess.domain.piece;

import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Positions {

    public static final List<Position> POSITION_CACHE;

    private Positions() {
    }

    static {
        POSITION_CACHE = positions();
    }

    private static List<Position> positions(){
        return Stream.of(Row.values())
                .map(Positions::positionsInRow)
                .flatMap(List::stream)
                .collect(Collectors.toList())
                ;
    }

    private static List<Position> positionsInRow(Row row){
        return Stream.of(Column.values())
                .map(column -> Position.of(column,row))
                .collect(Collectors.toList())
                ;
    }

}
