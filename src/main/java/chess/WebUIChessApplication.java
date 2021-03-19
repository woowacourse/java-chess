package chess;

import chess.controller.ChessController;
import chess.domain.ChessGame;
import chess.domain.command.*;

import java.util.Arrays;

public class WebUIChessApplication {
    public static void main(String[] args) {
//        get("/", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            return render(model, "index.html");
//        });
        ChessGame chessGame = new ChessGame();
        Commands commands = new Commands(
                Arrays.asList(
                        new EndOnCommand(chessGame),
                        new StartOnCommand(chessGame),
                        new MoveOnCommand(chessGame),
                        new StatusOnCommand(chessGame)
                )
        );
        new ChessController().run(chessGame, commands);
    }
//
//    private static String render(Map<String, Object> model, String templatePath) {
//        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
//    }
}
