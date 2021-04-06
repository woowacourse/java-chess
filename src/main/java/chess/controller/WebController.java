package chess.controller;

import chess.service.ChessService;
import chess.view.RenderView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.Request;
import spark.Response;

import java.sql.SQLException;

public class WebController {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final ChessService chessService;

    public WebController(ChessService chessService) {
        this.chessService = chessService;
    }

    private String makeMoveCmd(String source, String target) {
        return String.join(" ", "move", source, target);
    }

    public String moveToMainPage(Request request, Response response) throws SQLException {
        chessService.init();
        return RenderView.renderHtml(chessService.startResponse(), "chessStart.html");
    }

    public String playNewGame(Request request, Response response) throws SQLException {
        return RenderView.renderHtml(chessService.initResponse(request.params(":name")), "chessGame.html");
    }

    public String movePiece(Request request, Response response) {
        String command = makeMoveCmd(request.queryParams("source"), request.queryParams("target"));
        String historyId = request.queryParams("gameId");
        try {
            return GSON.toJson(chessService.movePiece(command, historyId));
        } catch (IllegalArgumentException | SQLException e) {
            response.status(400);
            return e.getMessage();
        }
    }

    public String continueGame(Request request, Response response) throws SQLException {
        String id = chessService.getIdByName(request.queryParams("name"));
        chessService.continueLastGame(id);
        return RenderView.renderHtml(chessService.continueResponse(id), "chessGame.html");
    }

    public String endGame(Request request, Response response) {
        chessService.end();
        return RenderView.renderHtml("chessGame.html");
    }
}
