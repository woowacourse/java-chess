package chess.controller.web;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.controller.web.utils.JsonConverter;
import chess.controller.web.utils.RenderUtil;
import chess.dto.board.BoardRequestDto;
import chess.service.ChessGameService;
import java.util.Collections;

public class GameController {

    private final ChessGameService chessGameService = new ChessGameService();

    public GameController() {
        addResponsePath();
    }

    private void addResponsePath() {
        get("/game/:id", (req, res) -> {
            chessGameService.initializeGame();
            return RenderUtil.render(Collections.emptyMap(), "chess.html");
        });

        get("api/game/:id/piece", (req, res) -> {
            final String source = req.queryParams("source");
            final String target = req.queryParams("target");
            res.type("application/json");
            return Collections.singletonMap(
                "isMovable", chessGameService.checkMovement(source, target)
            );
        }, JsonConverter::toJson);

        post("api/game/:id/piece", (req, res) -> {
            final BoardRequestDto boardRequestDto =
                JsonConverter.fromJson(req.body(), BoardRequestDto.class);
            res.type("application/json");
            return chessGameService.move(boardRequestDto);
        }, JsonConverter::toJson);
    }
}
