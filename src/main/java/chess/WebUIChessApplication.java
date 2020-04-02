package chess;

import domain.commend.CommendType;
import domain.commend.State;
import domain.pieces.Pieces;
import domain.pieces.PiecesFactory;
import domain.point.Point;
import java.util.function.Consumer;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import view.OutputView;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/templates");
        Pieces pieces = Pieces.of(PiecesFactory.create());
        State state = State.of(pieces);
        Map<CommendType, Consumer<String>> commends = new HashMap<>();
        init(state, commends);
        commend(commends, "start");
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
        get("/chess", (req, res) -> {
            Map<String, Object> model = cache(state);
            return render(model, "chess.html");
        });
        post("/move", (req, res) -> {
            commend(commends, req.queryParams("move"));
            res.redirect("/chess");
            return "";
        });

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
