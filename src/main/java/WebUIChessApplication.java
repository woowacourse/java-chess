import chess.controller.web.WebController;
import chess.dao.MysqlChessDao;
import chess.dao.StaticMemoryChessDao;
import chess.service.ChessService;

import java.util.Objects;

import static chess.dao.ChessConnection.getConnection;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class WebUIChessApplication {
    public static void main(String[] args) {
        port(8080);
        staticFileLocation("static");

        ChessService chessService = new ChessService(new StaticMemoryChessDao());

        if (!Objects.isNull(getConnection())) {
            chessService = new ChessService(new MysqlChessDao());
        }

        WebController webController = new WebController(chessService);
        webController.start();
    }

}
