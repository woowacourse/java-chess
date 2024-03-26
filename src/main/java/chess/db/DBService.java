package chess.db;

import java.sql.Connection;
import java.util.function.Supplier;

public class DBService {
    private final PiecesDao piecesDao;
    private final TurnsDao turnsDao;

    public DBService(Supplier<Connection> connector) {
        this.piecesDao = new PiecesDao(connector);
        this.turnsDao = new TurnsDao(connector);
    }

    public boolean hasPreviousData() {
        return piecesDao.count() != 0;
    }
}
