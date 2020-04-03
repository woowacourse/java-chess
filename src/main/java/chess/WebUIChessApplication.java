package chess;

import domain.board.Board;
import domain.state.Ended;
import domain.state.State;
import domain.pieces.Pieces;
import domain.pieces.StartPieces;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import view.InputView;
import view.OutputView;

import java.util.HashMap;
import java.util.Map;

public class WebUIChessApplication {
    public static void main(String[] args) {
        Spark.port(8080);
        Spark.staticFiles.location("/statics");

        Pieces startPieces = new Pieces(new StartPieces().getInstance());
        State state = new Ended(startPieces);

        Spark.get("/chess", (req, res) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("table", BoardToTable.of(Board.of(state.getSet()).getLists()).getBoardHtml());
            return render(map, "/chess.html");
        });
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public static void main2(String[] args) {
        OutputView.printStart();
        Pieces startPieces = new Pieces(new StartPieces().getInstance());
        State state = new Ended(startPieces);
        while (true) {
            state = state.pushCommend(InputView.inputGameCommend());
            printIfPlaying(state);
            printIfStatus(state);
        }
    }

    private static void printIfPlaying(State state) {
        if (state.isPlaying()) {
            OutputView.printBoard(Board.of(state.getSet()));
        }
    }

    private static void printIfStatus(State state) {
        if (state.isReported()) {
            OutputView.printStatus(state.getPieces());
        }
    }
}
