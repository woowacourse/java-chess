package chess;

import chess.domain.ChessGame;
import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.view.WebInputParser;
import chess.domain.view.WebOutputView;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/templates");

        ChessGame chessGame = new ChessGame();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/first", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                model.put("turn", WebOutputView.printTurn(chessGame.getTeam()));
                model.put("blackscore", chessGame.getStatus(Team.BLACK));
                model.put("whitescore", chessGame.getStatus(Team.WHITE));
                model.put("board", WebOutputView.printBoard(chessGame.getBoard()));
            } catch (Exception e) {
                model.put("error", e.getMessage());
                return render(model, "error.html");
            }

            return render(model, "chess.html");
        });

        post("/chess", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                Position source = WebInputParser.getSourcePosition(req.queryParams("source"));
                Position target = WebInputParser.getTargetPosition(req.queryParams("target"));

                chessGame.play(source, target);
                if (chessGame.isGameEnd()) {
                    model.put("winner", WebOutputView.printTurn(Team.switchTeam(chessGame.getTeam())));
                    return render(model, "winner.html");
                }

                model.put("turn", WebOutputView.printTurn(chessGame.getTeam()));
                model.put("blackscore", chessGame.getStatus(Team.BLACK));
                model.put("whitescore", chessGame.getStatus(Team.WHITE));
                model.put("board", WebOutputView.printBoard(chessGame.getBoard()));
            } catch (Exception e) {
                model.put("error", e.getMessage());
                return render(model, "error.html");
            }

            return render(model, "chess.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
