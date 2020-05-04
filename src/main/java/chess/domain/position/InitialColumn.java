package chess.domain.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum InitialColumn {
    PAWN(new ArrayList<>()),
    ROOK(Arrays.asList(new Column(1), new Column(8))),
    KNIGHT(Arrays.asList(new Column(2), new Column(7))),
    BISHOP(Arrays.asList(new Column(3), new Column(6))),
    QUEEN(Collections.singletonList(new Column(4))),
    KING(Collections.singletonList(new Column(5)));

    private final List<Column> initialColumns;

    InitialColumn(List<Column> initialColumns) {
        this.initialColumns = initialColumns;
    }

    public static InitialColumn valueOf(int column) {
        return Arrays.stream(values())
                .filter(initialColumn -> initialColumn.contains(column))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%d에 해당하는 체스 말을 찾을 수 없습니다.", column)));

    }

    private boolean contains(int column) {
        return initialColumns.contains(new Column(column));
    }

    @Override
    public String toString() {
        return String.join(", ", initialColumns.stream()
                .map(Column::toString)
                .collect(Collectors.toList()));
    }
}
