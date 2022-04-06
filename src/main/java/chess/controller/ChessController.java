package chess.controller;

import chess.dao.GameDaoImpl;
import chess.dao.PieceDaoImpl;
import chess.domain.ChessGame;
import chess.domain.command.MoveCommand;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;
import chess.dto.PieceDto;
import chess.serviece.ChessService;
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
    private static final String VIEW_NAME = "index.html";

    private final ChessService chessService;

    public ChessController() {
        this.chessService = new ChessService(new PieceDaoImpl(), new GameDaoImpl());
    }

    public void run() {
        final Gson gson = new Gson();
        staticFileLocation(STATIC_FILE_LOCATION);

        get("/", (req, res) -> render(new HashMap<>()));

        get("/start", (req, res) -> gson.toJson(chessService.initializeGame()));

        get("/chess", (req, res) -> gson.toJson(chessService.getChess()));

        post("/move", (req, res) -> gson.toJson(chessService.movePiece(parseToMoveCommand(req))));

        get("/score", (req, res) -> gson.toJson(chessService.getScore()));

        post("/end", (req, res) -> gson.toJson(chessService.finishGame()));

        exception(Exception.class, (exception, request, response) -> {
            System.out.println(exception.getMessage());
            response.status(500);
            response.body(exception.getMessage());
        });
    }

    private String render(Map<String, Object> model) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, VIEW_NAME));
    }

    private MoveCommand parseToMoveCommand(Request request) {
        String source = request.queryParams("source");
        String target = request.queryParams("target");
        return MoveCommand.of(source, target);
    }
}
