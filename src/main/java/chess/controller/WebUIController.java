package chess.controller;

import static spark.Spark.get;
import static spark.Spark.internalServerError;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.score.Score;
import chess.dto.ChessGameDTO;
import chess.service.ChessGameService;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIController {

    private static ChessGameService chessGameService = new ChessGameService();

    public static void run() {
        try {
            runWithoutException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void runWithoutException() {
        staticFiles.location("/static");

        get("/", (request, response) -> render(chessGameService.loadRecord()));

        post("/", WebUIController::moveAndRender);

        internalServerError(renderErrorPage());
    }

    private static String moveAndRender(Request request, Response response) throws SQLException {
        ChessGameDTO chessGameDTO = chessGameService.move(
            request.queryParams("from-position"), request.queryParams("to-position"));

        return render(chessGameDTO);
    }

    private static String render(ChessGameDTO chessGameDTO) {
        if (chessGameDTO.isGameFinished()) {
            return renderFinishedStatus(chessGameDTO.getBoard(), chessGameDTO.getScore());
        }
        return renderRunningStatus(chessGameDTO.getBoard());
    }

    private static String renderRunningStatus(Map<Position, Piece> board) {
        Map<String, Object> model = makeBoardModel(board);
        return render(model, "chess.html");
    }

    private static String renderFinishedStatus(Map<Position, Piece> board, Score score) {
        Map<String, Object> model = makeBoardModel(board);

        model.put("winner", score.getWinner());

        return render(model, "concluded.html");
    }

    private static Map<String, Object> makeBoardModel(Map<Position, Piece> board) {
        Map<String, Object> model = new HashMap<>();

        for (Entry<Position, Piece> entry : board.entrySet()) {
            model.put(entry.getKey().toString(), entry.getValue());
        }
        model.put("empty-image", "image/peace/empty.png");

        return model;
    }

    private static String renderErrorPage() {
        return render(new HashMap<>(), "error.html");
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
