package chess.utils;

import chess.dao.game.ChessGameDAO;
import chess.dao.game.ChessGameRepository;
import chess.dao.player.PlayerDAO;
import chess.dao.player.PlayerRepository;
import chess.dao.playerpieceposition.PlayerPiecePositionDAO;
import chess.dao.playerpieceposition.PlayerPiecePositionRepository;
import java.sql.SQLException;

public class DBCleaner {
    private static final ChessGameRepository CHESS_GAME_REPOSITORY = new ChessGameDAO();
    private static final PlayerRepository PLAYER_REPOSITORY = new PlayerDAO();
    private static final PlayerPiecePositionRepository PLAYER_PIECE_POSITION_REPOSITORY
        = new PlayerPiecePositionDAO();

    public static void removeAll() throws SQLException {
        PLAYER_PIECE_POSITION_REPOSITORY.removeAll();
        PLAYER_REPOSITORY.removeAll();
        CHESS_GAME_REPOSITORY.removeAll();
    }

}
