package chess;

import chess.service.ChessGameCalculatorScoreService;
import chess.service.CreateInitialBoard;
import chess.service.ChessGameMoveService;
import chess.service.ChessGameSettingService;
import chess.webController.*;

import static spark.Spark.staticFileLocation;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFileLocation("templates");

        ChessMainPageUrlController.run();

        ChessFirstStartUrlController.run(new CreateInitialBoard());

        ChessContinueStartUrlController.run(new ChessGameSettingService());

        ChessMoveUrlController.run(new ChessGameMoveService());

        ChessStatusUrlController.run(new ChessGameCalculatorScoreService());
    }
}
