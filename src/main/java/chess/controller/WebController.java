package chess.controller;

import chess.domain.ChessGame;
import chess.domain.command.Commands;
import chess.domain.utils.BoardInitializer;
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
    private final ChessGame chessGame;

    public WebController(ChessService chessService, ChessGame chessGame) {
        this.chessService = chessService;
        this.chessGame = chessGame;
    }

    public String moveToMainPage(Request request, Response response) throws SQLException {
        chessGame.initBoard(BoardInitializer.init());
        return RenderView.renderHtml(ModelView.startResponse(chessService.loadHistory()),
                "chessStart.html");
    }

    public String playNewGameWithNoSave(Request request, Response response) {
        return RenderView.renderHtml(ModelView.newGameResponse(chessGame.gameInfo()), "chessGame.html");
    }

    public String playNewGameWithSave(Request request, Response response) throws SQLException {
        return RenderView.renderHtml(ModelView.newGameResponse(
                chessGame.gameInfo(),
                chessService.addHistory(request.params(":name"))
        ), "chessGame.html");
    }

    public String movePiece(Request request, Response response) {
        String command = makeMoveCmd(request.queryParams("source"), request.queryParams("target"));
        String historyId = request.queryParams("gameId");
        try {
            chessGame.moveAs(new Commands(command));
            chessService.updateMoveInfo(command, historyId, chessGame.isEnd());
            return GSON.toJson(ModelView.moveResponse(chessGame.gameInfo(), historyId));
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
        chessGame.makeBoardStateOf(chessService.lastState(id));
        return RenderView.renderHtml(ModelView.commonResponseForm(chessGame.gameInfo(), id), "chessGame.html");
    }

    public String endGame(Request request, Response response) {
        chessGame.endGame();
        return RenderView.renderHtml("chessGame.html");
    }
}
