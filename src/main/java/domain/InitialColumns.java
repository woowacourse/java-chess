package domain;

import java.util.ArrayList;
import java.util.List;

public class InitialColumns {
    private final List<Column> columns;

    public InitialColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<Column> getColumns() {
        return new ArrayList<>(columns);
    }
}
