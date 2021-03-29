package chess.utils;

import chess.dao.ChessGameDAO;
import chess.dao.PlayerDAO;
import chess.dao.PlayerPiecePositionDAO;
import java.sql.SQLException;

public class DBCleaner {
    private static final ChessGameDAO chessGameDAO = new ChessGameDAO();
    private static final PlayerDAO playerDAO = new PlayerDAO();
    private static final PlayerPiecePositionDAO playerPiecePositionDAO
        = new PlayerPiecePositionDAO();

    public static void removeAll() throws SQLException {
        playerPiecePositionDAO.removeAll();
        playerDAO.removeAll();
        chessGameDAO.removeAll();
    }

}
