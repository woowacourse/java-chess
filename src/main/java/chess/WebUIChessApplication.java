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

    private Play play;

    public static void main(final String[] args) {
        port(SERVICE_PORT);
        staticFileLocation(STATIC_FILE_LOCATION);
        init();

        final WebUIChessApplication app = new WebUIChessApplication();
        app.play = new Play(Board.makeInitialBoard());

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
            app.play = new Play(Board.makeInitialBoard());
            final String boardJson = JsonOutput.board(app.play.getAllPositions());
            return JsonOutput.responseOk("board", boardJson);
        });

        post("/api/pieceMove", (request, response) -> {
            response.type(CONTENT_JSON);
            final Square source = JsonInput.getSquareFromJson(request.body(), "source");
            final Square target = JsonInput.getSquareFromJson(request.body(), "target");
            try {
                app.play.movePieceAndTurnSide(source, target);
            } catch (IllegalArgumentException e) {
                return JsonOutput.responseFailed(e.getMessage());
            }
            if (app.play.isKingDead(Side.WHITE) || app.play.isKingDead(Side.BLACK)) {
                return JsonOutput.responseOk("gameover", "{}");
            }
            return JsonOutput.responseOk();
        });
    }
}
