package chess.db;

public class DBFixtures {

    public static ChessGameDBConnector createChessGameDBConnector() {
        return new ChessGameDBConnector("chess_test");
    }

    public static PiecesDao createPiecesDao() {
        return new PiecesDao(createChessGameDBConnector());
    }

    public static TurnsDao createTurnsDao() {
        return new TurnsDao(createChessGameDBConnector());
    }

    public static ChessGameDBService createChessGameDBService() {
        return new ChessGameDBService(createChessGameDBConnector());
    }
}
