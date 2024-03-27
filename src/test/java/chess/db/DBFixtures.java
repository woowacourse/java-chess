package chess.db;

import java.sql.Connection;
import java.util.function.Supplier;

public class DBFixtures {

    public static DBConnector createDBConnector() {
        return new DBConnector("chess_test");
    }

    public static Supplier<Connection> createConnectorSupplier() {
        return () -> createDBConnector().getConnection();
    }

    public static PiecesDao createPiecesDao() {
        return new PiecesDao(createConnectorSupplier());
    }

    public static TurnsDao createTurnsDao() {
        return new TurnsDao(createConnectorSupplier());
    }

    public static ChessGameDBService createChessGameDBService() {
        return new ChessGameDBService(createConnectorSupplier());
    }
}
