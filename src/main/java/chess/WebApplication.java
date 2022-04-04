package chess;

import chess.controller.Command;
import chess.domain.ChessBoardPosition;
import chess.domain.ChessGame;
import chess.webview.WebOutputView;
import java.util.List;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebApplication {
    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private static final Map<Character, Integer> convertColumn = new HashMap<>();
    private static final int COLUMN_INDEX = 0;
    private static final int ROW_INDEX = 1;
    private static final String IN_GAME_COMMAND_DELIMITER = " ";

    static {
        convertColumn.put('a', 1);
        convertColumn.put('b', 2);
        convertColumn.put('c', 3);
        convertColumn.put('d', 4);
        convertColumn.put('e', 5);
        convertColumn.put('f', 6);
        convertColumn.put('g', 7);
        convertColumn.put('h', 8);
    }

    public static void main(String[] args) {
        port(8082);
        staticFiles.location("/public");
        ChessGame chessGame = ChessGame.create();

        get("/welcome", (req, res) -> {
            Command command = Command.of(req.queryParams("command"));
            if (Command.START.equals(command)) {
                chessGame.initialze();
                res.redirect("/board");
                return null;
            }
            if (Command.END.equals(command)) {
                stop();
            }
            return null;
        });

        get("/board", (req, res) -> {
            return render(WebOutputView.makeBoardModel(chessGame.getChessBoardInformation()), "board.html");
        });

        post("/inGameCommand", (req, res) -> {
            List<String> inputs = divideInGameCommandInput(req.queryParams("command"));
            Command command = Command.of(inputs.get(COMMAND_INDEX));
            if (Command.MOVE.equals(command)) {
                ChessBoardPosition source = coordinateToChessBoardPosition(inputs.get(SOURCE_INDEX));
                ChessBoardPosition target = coordinateToChessBoardPosition(inputs.get(TARGET_INDEX));
                chessGame.move(source, target);
                res.redirect("/board");
                return null;
            }
            if (Command.STATUS.equals(command)) {
                res.redirect("/status");
            }
            return null;
        });
    }

    private static List<String> divideInGameCommandInput(String command) {
        return List.of(command.split(IN_GAME_COMMAND_DELIMITER));
    }

    private static ChessBoardPosition coordinateToChessBoardPosition(String coordinate) {
        System.out.println(coordinate);
        return ChessBoardPosition.of(extractColumn(coordinate), extractRow(coordinate));
    }

    private static int extractColumn(String input) {
        return convertColumn.get(input.charAt(COLUMN_INDEX));
    }

    private static int extractRow(String input) {
        return Character.getNumericValue(input.charAt(ROW_INDEX));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
