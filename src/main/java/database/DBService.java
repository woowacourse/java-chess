package database;

import java.sql.Connection;

public class DBService {

    private final ChessBoardDao chessBoardDao;
    private final ChessGameDao chessGameDao;

    public DBService(Connection connection) {
        this.chessBoardDao = new ChessBoardDao(connection);
        this.chessGameDao = new ChessGameDao(connection);
    }
}
