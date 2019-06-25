package chess.domain.board;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Tile implements Comparable<Tile> {
    private static final Map<Pair<Column, Row>, Tile> TILE_POOL;
    private static final Pattern tilePattern = Pattern.compile("^(.)(.)$");

    static {
        TILE_POOL = Collections.unmodifiableMap(
                Column.stream()
                        .flatMap(column -> tilesOf(column, Tile::new))
                        .collect(Collectors.toMap(tile -> Pair.of(tile.column, tile.row), tile -> tile))
        );
    }

    static Stream<Tile> tilesOf(Column column, BiFunction<Column, Row, Tile> t) {
        return Row.stream()
                .map(row -> t.apply(column, row));
    }

    private final Column column;
    private final Row row;

    private Tile(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    public static Tile of(Column column, Row row) {
        return TILE_POOL.get(Pair.of(column, row));
    }

    public static Tile of(String tileText) {
        if (StringUtils.isEmpty(tileText)) {
            throw new InvalidTileException("존재하지 않는 칸입니다.");
        }

        Matcher m = tilePattern.matcher(tileText);
        if (m.find()) {
            return of(Column.of(m.group(1)), Row.of(m.group(2)));
        }
        throw new InvalidTileException("존재하지 않는 칸입니다.");
    }

    public static Stream<Tile> stream() {
        return TILE_POOL.values().stream();
    }

    public int getHeightDiff(Tile tile) {
        return row.getDiff(tile.row);
    }

    public int getWidthDiff(Tile tile) {
        return column.getDiff(tile.column);
    }

    public Column getColumn() {
        return column;
    }

    public boolean isEqualRow(Row row) {
        return this.row == row;
    }

    public Tile next(int columnDiff, int rowDiff) {
        return of(column.next(columnDiff), row.next(rowDiff));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return Objects.equals(row, tile.row) &&
                Objects.equals(column, tile.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public int compareTo(Tile o) {
        if (row.equals(o.row)) {
            return column.compareTo(o.column);
        }
        return row.compareTo(o.row);
    }

    @Override
    public String toString() {
        return column.toString() + row.toString();
    }
}
