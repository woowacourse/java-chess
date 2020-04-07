package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import controller.WebController;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
    private static Gson gson = new Gson();

    public static void main(String[] args) {
        staticFiles.location("/templates");
        WebController webController = new WebController();

        startPage();
        chessGamePage(webController);
        move(webController);
        status(webController);
        finished(webController);
    }

    private static void startPage() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }

    private static void chessGamePage(WebController webController) {
        get("/chess", (req, res) -> {
            if (webController.isFinished()) {
                res.redirect("/finished");
                return "";
            }
            return render(webController.read(), "chess.html");
        });
    }

    private static void move(WebController webController) {
        post("/move", (req, res) -> {
            try {
                webController.move(req.queryParams("from"), req.queryParams("to"));
                res.redirect("/chess");
                return "";
            } catch (Exception e) {
                return e;
            }
        });
    }

    private static void status(WebController webController) {
        post("/status", (req, res) -> {
            JsonObject object = webController.status();
            return gson.toJson(object);
        });
    }

    private static void finished(WebController webController) {
        get("/finished", (req, res) -> {
            webController.delete();
            Map<String, Object> model = new HashMap<>();
            model.put("win", webController.getWhoWinner());
            return render(model, "finished.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
