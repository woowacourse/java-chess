package chess.controller;

import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;
import chess.dto.PieceDto;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

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
}
