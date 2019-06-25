package chess;

import chess.domain.direction.core.Square;
import chess.dto.ChessMoveDTO;
import chess.service.ParserService;
import chess.util.JsonTransformer;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFileLocation("/templates");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/move", "application/json", (req, res) -> {
            Gson gson = new Gson();
            try{
                JsonObject jsonObject = gson.fromJson(req.body(), JsonObject.class);
                Square source = ParserService.getInstance().squareParsing(jsonObject.get("move").getAsJsonObject().get("source").getAsString());
                Square target = ParserService.getInstance().squareParsing(jsonObject.get("move").getAsJsonObject().get("target").getAsString());

                String board = jsonObject.get("board").getAsJsonObject().getAsString();

                System.out.println("boarda : " + board);
                return null;
            }catch (IllegalArgumentException e) {
//                return new ErrorDto(e.getMessage());
                return null;
            }
        }, new JsonTransformer());

//        get("/chessGame", (req, res) -> {
//           Gson gson = new Gson();
//           try {
//               req.body();
//               moveServic.requst()
//           } catch (Exception e) {
//               throw new IllegalArgumentException(e.getMessage());
//           }
//        });
//
//        post("/move")˚
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
