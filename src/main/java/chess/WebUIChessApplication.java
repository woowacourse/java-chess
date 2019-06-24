package chess;

import chess.model.Play;
import chess.model.board.Board;
import chess.view.JsonOutput;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static final int SERVICE_PORT = 8080;
    private static final String STATIC_FILE_LOCATION = "/";

    private Play play;
    private boolean isPlaying;

    public static void main(final String[] args) {
        port(SERVICE_PORT);
        staticFileLocation(STATIC_FILE_LOCATION);
        init();

        final WebUIChessApplication app = new WebUIChessApplication();
        app.play = new Play(Board.makeInitialBoard());

        get("/api/board", (req, res) -> {
            final String boardJson = JsonOutput.board(app.play.getAllPositions());
            return JsonOutput.responseOk("board", boardJson);
        });
    }
}
