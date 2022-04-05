package chess;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {
        port(8082);
        staticFileLocation("/static");

        ChessGame chessGame = new ChessGame();
        chessGame.start();

        get("/", (req, res) -> {
            Map<Position, Piece> board = chessGame.getBoard();
            Map<String, Object> model = new HashMap<>();
            for (Position position : board.keySet()) {
                Piece piece = board.get(position);
                model.put(position.toString(), piece);
            }

            model.put("turn", chessGame.getTurn());

            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            final Request request = Request.of(req.body());
            String command = request.command();

            String source = command.substring(0, 2);
            String target = command.substring(2, 4);

            chessGame.move(Position.toPosition(source), Position.toPosition(target));
            res.redirect("/");
            return null;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
