package chess.service;

import chess.domain.ChessBoard;
import chess.domain.Turn;

import java.sql.SQLException;

public class ChessGameService {
    private static ChessGameService instance = new ChessGameService();

    private ChessBoardService chessBoardService;
    private TurnService turnService;

    private ChessGameService() {
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


    public void save() throws SQLException {
        ChessBoard chessBoard = chessBoardService.loadBoard();
        Turn turn = turnService.loadTurn();
        chessBoardService.saveBoard(chessBoard);
        turnService.saveTurn(turn);
    }
}
