package chess;

import chess.dao.ChessGameDAO;
import chess.dao.PieceDAO;
import chess.domain.piece.Position;
import chess.service.ChessGameService;
import chess.view.dto.ChessGameStatusDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void main(String[] args) {
        ChessGameService chessGameService = new ChessGameService(new ChessGameDAO(), new PieceDAO());

        port(8080);
        staticFileLocation("/static");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ChessGameStatusDto latestGameStatus = chessGameService.findLatestChessGameStatus();
            model.put("status", latestGameStatus);
            return render(model, "index.html");
        });

        get("/pieces", (request, response) -> {
            Position source = Position.parseChessPosition(request.queryParams("source"));
            Position target = Position.parseChessPosition(request.queryParams("target"));
            return chessGameService.moveChessPiece(source, target);
        }, MAPPER::writeValueAsString);

        get("/chessgames", ((request, response) -> chessGameService.findLatestPlayingGame()),
                MAPPER::writeValueAsString);

        post("/chessgames", (request, response) -> chessGameService.createNewChessGame(),
                MAPPER::writeValueAsString);

        delete("/chessgames", (request, response) -> chessGameService.endGame(),
                MAPPER::writeValueAsString);

        get("/scores", (request, response) -> chessGameService.calculateScores(),
                MAPPER::writeValueAsString);
    }

    private static String render(final Map<String, Object> model, final String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}
