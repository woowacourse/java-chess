package chess.controller;

import chess.domain.command.Commands;
import chess.service.ChessService;
import chess.view.ModelView;
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

    public String moveToMainPage(Request request, Response response) throws SQLException {
        return RenderView.renderHtml(ModelView.startResponse(chessService.loadHistory()),
                "chessStart.html");
    }

    public String playNewGameWithNoSave(Request request, Response response) {
        return RenderView.renderHtml(ModelView.newGameResponse(chessService.initialGameInfo()), "chessGame.html");
    }

    public String playNewGameWithSave(Request request, Response response) throws SQLException {
        return RenderView.renderHtml(ModelView.newGameResponse(
                chessService.initialGameInfo(),
                chessService.addHistory(request.params(":name"))
        ), "chessGame.html");
    }

    public String movePiece(Request request, Response response) {
        String command = makeMoveCmd(request.queryParams("source"), request.queryParams("target"));
        String historyId = request.queryParams("gameId");
        try {
            chessService.move(historyId, command, new Commands(command));
            return GSON.toJson(ModelView.moveResponse(chessService.continuedGameInfo(historyId), historyId));
        } catch (IllegalArgumentException | SQLException e) {
            response.status(400);
            return e.getMessage();
        }
    }

    private String makeMoveCmd(String source, String target) {
        return String.join(" ", "move", source, target);
    }

    public String continueGame(Request request, Response response) throws SQLException {
        String id = chessService.getIdByName(request.queryParams("name"));
        return RenderView.renderHtml(ModelView.commonResponseForm(chessService.continuedGameInfo(id), id), "chessGame.html");
    }

    public String endGame(Request request, Response response) {
        return RenderView.renderHtml("chessGame.html");
    }
}
