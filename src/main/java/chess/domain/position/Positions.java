package chess.domain.position;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Positions {

    // todo : 현재 이 클래스는 안 쓰고 있음 (모두 private 처리 해둠)

    private Positions() {
    }

    private static final List<Position> POSITION_CACHE;

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
                .map(column -> Position.ofColumnAndRow(column,row))
                .collect(Collectors.toList())
                ;
    }

}
