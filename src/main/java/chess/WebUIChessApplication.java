package chess;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class WebUIChessApplication {
    public static void main(String[] args) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

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
