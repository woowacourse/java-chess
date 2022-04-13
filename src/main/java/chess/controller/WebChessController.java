package chess.controller;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.dto.GameResultDto;
import chess.dto.MoveCommandDto;
import chess.dto.PiecesDto;
import chess.service.ChessGameService;
import chess.web.util.JsonTransformer;
import chess.web.util.RenderingUtil;
import chess.web.view.ResultView;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;

public class WebChessController {

    AtomicReference<ChessGameService> chessGameService = new AtomicReference<>();

    public void run() {
        port(8082);
        staticFiles.location("/static");

        get("/", (req, res) -> {
            PiecesDto piecesDto = new PiecesDto(List.of());

            return RenderingUtil.render(piecesDto, "index.html");
        });

        get("/game", (req, res) -> {
            chessGameService.set(new ChessGameService(req.queryParams("gameId")));
            PiecesDto piecesDto = chessGameService.get()
                .createOrGet();

            return RenderingUtil.render(piecesDto, "game.html");
        });

        get("/game/progress", (req, res) -> {
            PiecesDto piecesDto = chessGameService.get()
                .getCurrentGame();

            return RenderingUtil.render(piecesDto, "game.html");
        });

        get("/game/status", (req, res) -> {
            res.type("application/json");
            JsonTransformer jsonTransformer = new JsonTransformer();

            GameResultDto gameResultDto = chessGameService.get().calculateGameResult();
            Map<String, Object> model = ResultView.of(gameResultDto).getResultView();

            return jsonTransformer.render(model);
        });

        post("/game/start", (req, res) -> {
            chessGameService.get()
                .cleanGame();

            chessGameService.get()
                .initGame();

            res.redirect("/game/progress");
            return true;
        });

        post("/game/move", (req, res) -> {
            JSONObject jObject = new JSONObject(req.body());
            String from = jObject.getString("from");
            String to = jObject.getString("to");
            MoveCommandDto movePositionCommandDto = new MoveCommandDto(from, to);

            chessGameService.get()
                .move(movePositionCommandDto);

            res.redirect("/game/progress");
            return true;
        });

        post("/game/save", (req, res) -> {
            chessGameService.get()
                .save();

            res.redirect("/game/progress");
            return true;
        });

        post("/game/end", (req, res) -> {
            chessGameService.get()
                .forceEnd();

            res.redirect("/game/progress");
            return true;
        });

        post("/game/exit", (req, res) -> {
            chessGameService.get()
                .cleanGame();

            res.redirect("/");
            return true;
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body(exception.getMessage());
        });
    }

}
