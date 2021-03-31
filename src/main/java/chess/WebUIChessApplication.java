package chess;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.domain.ChessBoard;
import chess.domain.dto.MovementDto;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
    private static final ChessBoard chessBoard = new ChessBoard();

    public static void main(String[] args) {
        staticFileLocation("/static");
        Gson gson = new Gson();

        post("/chessboard", (req, res) ->{
            chessBoard.move(gson.fromJson(req.body(), MovementDto.class));
           return chessBoard.getChessBoardDto();
        }, gson::toJson);

        get("/chessboard", (req, res) -> {
            res.type("application/json");
            return chessBoard.getChessBoardDto();
        }, gson::toJson);

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}
