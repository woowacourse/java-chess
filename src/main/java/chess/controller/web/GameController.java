package chess.controller.web;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.controller.web.utils.JsonConverter;
import chess.controller.web.utils.RenderUtil;
import chess.dto.board.BoardRequestDto;
import chess.dto.game.GamePostRequestDto;
import chess.service.ChessGameService;
import chess.service.GameService;
import chess.service.UserService;
import java.util.Collections;

public class GameController {

    private final ChessGameService chessGameService = new ChessGameService();
    private final GameService gameService;
    private final UserService userService;

    public GameController(final GameService gameService, final UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
        addResponsePath();
    }

    private void addResponsePath() {
        post("/game", (req, res) -> {
            final GamePostRequestDto gamePostRequestDto =
                JsonConverter.fromJson(req.body(), GamePostRequestDto.class);
            final long whiteId = userService.find(gamePostRequestDto.getWhiteName()).getId();
            final long blackId = userService.find(gamePostRequestDto.getBlackName()).getId();
            final long id = gameService.add(whiteId, blackId);
            final String redirectPath = String.format("/game/%s", id);
            res.redirect(redirectPath, 301);
            return "";
        });

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
