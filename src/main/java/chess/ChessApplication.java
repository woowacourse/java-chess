package chess;

import chess.controller.ChessHandler;
import chess.database.JdbcTemplate;
import chess.database.properties.ChessProperties;
import chess.service.ServiceManager;

public final class ChessApplication {
    public static void main(String[] args) {
        final ChessProperties chessProperties = new ChessProperties();
        final ServiceManager serviceManager = new ServiceManager(new JdbcTemplate(chessProperties));
        final ChessHandler chessHandler = new ChessHandler(serviceManager);
        chessHandler.run();
    }
}
