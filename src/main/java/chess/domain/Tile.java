package chess.domain;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class Tile implements Comparable<Tile> {
    private static final Map<Pair<Column, Row>, Tile> TILE_POOL;
    private static final Pattern tilePattern = Pattern.compile("^(.)(.)$");

    static {
        TILE_POOL = new HashMap();
        Row.stream()
                .forEach(row ->
                        Column.stream()
                                .forEach(column ->
                                        TILE_POOL.put(Pair.of(column, row), new Tile(column, row))));
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
        return "Tile{" +
                "column=" + column +
                ", row=" + row +
                '}';
    }
}
