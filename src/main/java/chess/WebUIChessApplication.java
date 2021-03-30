package chess;

import chess.repository.ChessRepository;
import chess.repository.ConnectionManager;
import chess.service.ChessService;
import chess.web.ChessController;
import chess.web.ExceptionHandler;
import chess.web.MoveController;

import java.sql.Connection;

import static spark.Spark.*;

public class WebUIChessApplication {

    public static void main(String[] args) {
        setConfiguration();
        ChessController chessController = initializeChessController();
        get("/", MoveController::moveToMainPage);
        get("/chessboard/result", MoveController::moveToResultPage);
        get("/chessboard/update", chessController::updateChessBoard);
        post("/chessboard/move", chessController::move);
        get("/chessboard/result/show", chessController::showResult);
        get("/chessboard/restart", chessController::restart);
        get("/chessboard", chessController::updateChessBoard);
        post("/chessboard/move", chessController::move);
        exception(RuntimeException.class, ExceptionHandler::bindException);
    }

    private static void setConfiguration() {
        port(8080);
        staticFiles.location("/static");
    }

    private static ChessController initializeChessController() {
        Connection connection = ConnectionManager.makeConnection();
        ChessRepository chessRepository = new ChessRepository(connection);
        ChessService chessService = new ChessService(chessRepository);
        return new ChessController(chessService);
    }
}
