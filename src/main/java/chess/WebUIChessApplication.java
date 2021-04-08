package chess;

import chess.dto.PositionDTO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) throws SQLException {
        port(8084);
        JsonTransformer jsonTransformer = new JsonTransformer();

        ChessProgram chessProgram = new ChessProgram();
        staticFiles.location("/public");
        chessProgram.initChessGame();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
        post("/restart", (req, res) -> {
            chessProgram.initChessGame();
            return "보드 초기화 성공!";
        }, jsonTransformer);
        post("/move", (req, res) -> {
            PositionDTO positionDTO = jsonTransformer.getGson().fromJson(req.body(), PositionDTO.class);
            return chessProgram.move(positionDTO);
        }, jsonTransformer);
        post("/currentBoard", (req, res) -> {
            return chessProgram.getCurrentBoard();
        }, jsonTransformer);
        post("/currentTurn", (req, res) -> {
            return chessProgram.turnName();
        }, jsonTransformer);

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}