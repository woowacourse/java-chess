package chess;

import chess.model.Play;
import chess.model.Side;
import chess.model.board.Board;
import chess.model.board.Square;
import chess.view.JsonInput;
import chess.view.JsonOutput;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static final int SERVICE_PORT = 8080;
    private static final String STATIC_FILE_LOCATION = "/";
    private static final String CONTENT_JSON = "application/json";
    private static final String EMPTY_JSON = "{}";
    private static WebUIChessApplication app;
    private static BoardDAO boardDAO;

    private Play play;

    public static void main(final String[] args) {
        port(SERVICE_PORT);
        staticFileLocation(STATIC_FILE_LOCATION);
        init();

        app = new WebUIChessApplication();
        final DBconnection dbConnection = new DBconnection("localhost", "chess", "user", "1234");

        try {
            boardDAO = new BoardDAO(dbConnection.getConnection());
            app.play = new Play(Board.of(boardDAO.initBoardPositions()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        get("/api/board", (request, response) -> {
            response.type(CONTENT_JSON);
            final String boardJson = JsonOutput.board(app.play.getAllPositions());
            return JsonOutput.responseOk("board", boardJson);
        });

        get("/api/score", (request, response) -> {
            response.type(CONTENT_JSON);
            final String scoreJson = JsonOutput.scoreAll(app.play);
            return JsonOutput.responseOk("score", scoreJson);
        });

        post("/api/init", (request, response) -> {
            response.type(CONTENT_JSON);
            boardDAO.resetBoard();
            app.play = new Play(Board.of(boardDAO.initBoardPositions()));
            final String boardJson = JsonOutput.board(app.play.getAllPositions());
            return JsonOutput.responseOk("board", boardJson);
        });

        post("/api/pieceMove", (request, response) -> {
            response.type(CONTENT_JSON);
            final Square source = JsonInput.getSquareFromJson(request.body(), "source");
            final Square target = JsonInput.getSquareFromJson(request.body(), "target");
            try {
                app.play.movePieceAndTurnSide(source, target);
                boardDAO.movePiece(source, target);
            } catch (IllegalArgumentException e) {
                return JsonOutput.responseFailed(e.getMessage());
            }
            if (app.play.isKingDead(Side.WHITE) || app.play.isKingDead(Side.BLACK)) {
                return JsonOutput.responseOk("gameover", EMPTY_JSON);
            }
            return JsonOutput.responseOk();
        });
    }
}
