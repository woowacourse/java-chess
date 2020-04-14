package chess;

import chess.db.dao.BlackPieceDAO;
import chess.db.dao.ChessBoardStateDAO;
import chess.db.dao.WhitePieceDAO;
import chess.service.ChessGameCalculatorScore;
import chess.service.createInitialBoard;
import chess.service.ChessGameMove;
import chess.service.ChessGameSetting;
import chess.webController.*;

import static spark.Spark.staticFileLocation;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFileLocation("templates");

        ChessMainPageUrlController.run();

        ChessFirstStartUrlController.run(new createInitialBoard());

        ChessContinueStartUrlController.run(new ChessGameSetting());

        ChessMoveUrlController.run(new ChessGameMove());

        ChessStatusUrlController.run(new ChessGameCalculatorScore());
    }
}
