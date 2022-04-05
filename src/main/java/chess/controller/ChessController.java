package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Score;
import chess.domain.command.MoveCommand;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;
import chess.dto.PieceDto;
import chess.dto.ScoresDto;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static spark.Spark.*;

public class ChessController {

    private static final String STATIC_FILE_LOCATION = "/static";

    private final ChessGame game;

    public ChessController() {
        game = new ChessGame(PieceFactory.createChessPieces());
    }

    public void run() {
        final Gson gson = new Gson();
        staticFileLocation(STATIC_FILE_LOCATION);

        get("/", (req, res) -> render(new HashMap<>(), "index.html"));

        get("/pieces", (req, res) -> gson.toJson(getPieces()));

        post("/move", (req, res) -> gson.toJson(movePieces(req)));

        get("/status", (req, res) -> gson.toJson(getStatus()));

        exception(Exception.class, (exception, request, response) -> {
            System.out.println(exception.getMessage());
            response.status(500);
            response.body(exception.getMessage());
        });
    }

    private ScoresDto getStatus() {
        Map<PieceColor, Score> scoresByColor = game.calculateScoreByColor();
        return ScoresDto.of(scoresByColor);
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private List<PieceDto> getPieces() {
        Map<Position, Piece> pieces = game.getPieces();
        return pieces.entrySet()
                .stream()
                .map(entry -> PieceDto.of(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private String movePieces(Request req) {
        String source = req.queryParams("source");
        String target = req.queryParams("target");
        System.out.println("move command : " + source + "->" + target);
        MoveCommand moveCommand = MoveCommand.of(source, target);
        game.proceedWith(moveCommand);
        return "Success to move pieces";
    }
}
