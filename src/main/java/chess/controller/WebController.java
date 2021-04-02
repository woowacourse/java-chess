package chess.controller;

import chess.service.ChessService;
import chess.view.RenderView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebController {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final ChessService chessService;
//    private CommandDatabase commandDatabase;


    public WebController(ChessService chessService) {
        this.chessService = chessService;
    }

    public void play() {
        get("/play", (req, res) -> {
            chessService.init();
            return RenderView.renderHtml(chessService.startResponse(), "chessStart.html");
        });

        get("/play/:name/new", (req, res) ->
                RenderView.renderHtml(chessService.initResponse(req.params(":name")), "chessGame.html"));

        post("/play/move", (req, res) -> {
            String command = makeMoveCmd(req.queryParams("source"), req.queryParams("target"));
            String historyId = req.queryParams("gameId");
            try {
                chessService.move(command);
                chessService.flushCommands(command, historyId);
                return GSON.toJson(chessService.moveResponse(historyId));
            } catch (IllegalArgumentException e) {
                res.status(400);
                return e.getMessage();
            }
        });

        get("/play/end", (req, res) -> {
            chessService.end();
            return RenderView.renderHtml("chessGame.html");
        });

        get("/play/continue", (req, res) -> {
            String id = chessService.getIdByName(req.queryParams("name"));
            chessService.continueLastGame(id);
            return RenderView.renderHtml(chessService.continueResponse(id), "chessGame.html");
        });
    }

    private String makeMoveCmd(String source, String target) {
        return String.join(" ", "move", source, target);
    }

}
