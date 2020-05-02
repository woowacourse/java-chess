package chess.service;

import java.sql.SQLException;

public class ChessGameService {
    private static ChessGameService instance = new ChessGameService();

    private ChessBoardService chessBoardService;
    private TurnService turnService;

    private ChessGameService () {
        turnService = TurnService.getInstance();
        chessBoardService = ChessBoardService.getInstance(turnService);
    }

    public static ChessGameService getInstance() {
        if (instance == null) {
            instance = new ChessGameService();
        }
        return instance;
    }

    public void initialize() throws SQLException {
        chessBoardService.initialize();
        turnService.initialize();
    }
}
