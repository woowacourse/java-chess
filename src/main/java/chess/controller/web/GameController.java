package chess.controller.web;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.controller.web.utils.JsonConverter;
import chess.controller.web.utils.RenderUtil;
import chess.domain.team.Team;
import chess.dto.chess.MoveRequestDto;
import chess.dto.game.GamePostRequestDto;
import chess.service.ChessService;
import chess.service.GameService;
import chess.service.PieceService;
import chess.service.UserService;
import java.util.Collections;

public class GameController {

    private final GameService gameService;
    private final UserService userService;
    private final PieceService pieceService;
    private final ChessService chessService;

    public GameController(final GameService gameService, final UserService userService,
        final PieceService pieceService) {

        this.gameService = gameService;
        this.userService = userService;
        this.pieceService = pieceService;
        this.chessService = new ChessService(pieceService, gameService, userService);
        addResponsePath();
    }

    private void addResponsePath() {
        post("/game", (req, res) -> {
            final GamePostRequestDto gamePostRequestDto =
                JsonConverter.fromJson(req.body(), GamePostRequestDto.class);
            final long whiteId = userService.findByName(gamePostRequestDto.getWhiteName()).getId();
            final long blackId = userService.findByName(gamePostRequestDto.getBlackName()).getId();
            final long id = gameService.add(whiteId, blackId);
            pieceService.initPieces(id);
            final String redirectPath = String.format("/game/%s", id);
            res.redirect(redirectPath, 301);
            return "";
        });

        get("/game/:id",
            (req, res) -> RenderUtil.render(Collections.emptyMap(), "chess.html"));

        get("/api/game/:id", (req, res) -> {
            final long gameId = Long.parseLong(req.params("id"));
            return chessService.bringChessInfo(gameId);
        }, JsonConverter::toJson);

        get("/api/game/:id/piece", (req, res) -> {
            final long gameId = Long.parseLong(req.params("id"));
            final String source = req.queryParams("source");
            final String target = req.queryParams("target");
            final Team team = Team.from(req.queryParams("team"));
            res.type("application/json");
            return Collections.singletonMap(
                "isMovable", chessService.checkMovement(gameId, source, target, team)
            );
        }, JsonConverter::toJson);

        post("/api/game/:id/piece", (req, res) -> {
            final MoveRequestDto moveRequestDto =
                JsonConverter.fromJson(req.body(), MoveRequestDto.class);
            final long gameId = Long.parseLong(req.params("id"));
            res.type("application/json");
            return chessService.move(gameId, moveRequestDto);
        }, JsonConverter::toJson);
    }
}
