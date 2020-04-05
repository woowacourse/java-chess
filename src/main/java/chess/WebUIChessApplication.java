package chess;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import domain.commend.CommendType;
import domain.commend.State;
import domain.pieces.Pieces;
import domain.pieces.PiecesFactory;
import domain.point.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import view.OutputView;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/templates");
        Pieces pieces = Pieces.of(PiecesFactory.create());
        State state = State.of(pieces);
        Map<CommendType, Consumer<String>> commends = new HashMap<>();
        init(state, commends);
        commend(commends, "start");
        Gson gson = new Gson();
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
        get("/chess", (req, res) -> {
            Map<String, Object> model = cache(state);
            return render(model, "chess.html");
        });
        get("/move", (req, res) -> {
            try {
                commend(commends, req.queryParams("move"));
                JsonObject object = getJsonObject(state);
                return gson.toJson(object);
            } catch (Exception e) {
               return e;
            }
        });
        get("/status", (req, res) -> {
            JsonObject object = new JsonObject();
            object.addProperty("team", state.getPresentTurn().toString());
            object.addProperty("status", state.status());
            return gson.toJson(object);
        });
        get("/finished", (req, res) -> {
            Map<String, Object> model = cache(state);
            return render(model, "finished.html");
        });
        get("/info", (req, res) -> {
            JsonObject object = getJsonObject(state);
            return gson.toJson(object);
        });
        get("/isFinished", (req, res) -> state.isFinished());

    }

    private static JsonObject getJsonObject(State state) {
        JsonObject object = new JsonObject();
        for (Point point : state.getPieces().getPieces().keySet()) {
            if (state.getPieces().getPiece(point).toString().equals(".")) {
                object.addProperty(point.toString(), "");
            } else {
                object.addProperty(point.toString(), state.getPieces().getPiece(point).toString());
            }
        }
        return object;
    }

    private static void init(State state, Map<CommendType, Consumer<String>> commends) {
        commends.put(CommendType.START, (input -> state.start()));
        commends.put(CommendType.END, (input -> state.end()));
        commends.put(CommendType.MOVE, (input -> state.move(input)));
        commends.put(CommendType.STATUS, (input -> OutputView.printScore(state.status())));
    }

    private static void commend(Map<CommendType, Consumer<String>> commends, String input) {
        commends.get(CommendType.find(input)).accept(input);
    }

    private static Map<String, Object> cache(State state) {
        Map<String, Object> model = new HashMap<>();
        for (Point point : state.getPieces().getPieces().keySet()) {
            if (state.getPieces().getPiece(point).toString().equals(".")) {
                model.put(point.toString(), "");
            } else {
                model.put(point.toString(), state.getPieces().getPiece(point).toString());
            }
        }
        return model;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
