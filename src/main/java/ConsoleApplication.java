import chess.controller.console.ConsoleController;
import chess.dao.MysqlChessDao;
import chess.dao.StaticMemoryChessDao;
import chess.service.ChessService;

import java.util.Objects;

import static chess.dao.ChessConnection.getConnection;

public class ConsoleApplication {
    public static void main(String[] args) {
        ChessService chessService = new ChessService(new StaticMemoryChessDao());

        if (!Objects.isNull(getConnection())) {
            chessService = new ChessService(new MysqlChessDao());
        }

        ConsoleController chessController = new ConsoleController(chessService);
        chessController.run();
    }
}
