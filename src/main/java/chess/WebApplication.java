package chess;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.dto.ApiResult;
import chess.dto.MoveCommand;
import chess.service.ChessWebService;
import chess.utils.RequestToCommand;
import com.google.gson.Gson;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {
        port(8089);
        staticFileLocation("/static");

        ChessWebService service = new ChessWebService();
        Gson gson = new Gson();

        get("/", (req, res) -> {
            Map<String, Object> model = service.getBoard().toMap();
            return render(model, "index.html");
        });

        get("/initialize", (req, res) -> {
            service.initializeState();
            res.redirect("/");
            return null;
        });

        get("/status", (req, res) -> {
            ApiResult statusResult = service.getStatus();
            return gson.toJson(statusResult);
        });

        post("/move", (req, res) -> {
            final MoveCommand command = RequestToCommand.toMoveCommand(req.body());
            ApiResult apiResult = service.movePiece(command);

            return gson.toJson(apiResult);
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
