package chess.controller;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.domain.ChessResult;
import chess.domain.JsonTransformer;
import chess.domain.dto.request.MoveRequest;
import chess.service.ChessService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public final class WebUIChessController {

    public void runChess() {
        final Gson gson = new Gson();
        final JsonTransformer jsonTransformer = new JsonTransformer();
        staticFiles.location("/public");
        port(8080);

        final ChessService chessService = new ChessService();
        get("/", (req, res) -> {
            final Map<String, Object> model = new HashMap<>();
            chessService.chessBoardInit();
            return render(model, "index.html");
        });

        get("/api/start", (req, res) -> chessService.start(), jsonTransformer);

        post("/api/move", (req, res) -> {
            final String requests = req.body();
            final MoveRequest moveRequest = gson.fromJson(requests, MoveRequest.class);
            return chessService.move(moveRequest);
        }, jsonTransformer);

        get("/api/end", (req, res) -> chessService.end(), jsonTransformer);

        get("/result", (req, res) -> {
            final Map<String, Object> model = new HashMap<>();
            final ChessResult chessResult = chessService.chessResult();
            model.put("opposite", chessResult.winner().oppositeTeamName());
            model.put("winner", chessResult.winner().teamName());
            chessService.restart();
            return render(model, "result.html");
        });
    }

    private String render(final Map<String, Object> model, final String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}

