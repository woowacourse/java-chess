package chess;

import static spark.Spark.get;
import static spark.Spark.post;

import domain.Status;
import domain.chessgame.ChessBoard;
import domain.chessgame.ChessGame;
import domain.piece.Piece;
import domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import utils.ChessBoardGenerator;

public class WebApplication {

    public static void main(String[] args) {
        ChessBoard chessBoard = ChessBoardGenerator.generate();
        ChessGame chessGame = new ChessGame(chessBoard);

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
        get("/play", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Map<Position, Piece> board = chessBoard.getBoard();
            for (Position position : board.keySet()) {
                model.put(position.getPosition(), board.get(position).symbolByPlayer());
            }
            return render(model, "index.html");
        });
        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Map<Position, Piece> board = chessBoard.getBoard();
            for (Position position : board.keySet()) {
                model.put(position.getPosition(), board.get(position).symbolByPlayer());
            }
            return render(model, "index.html");
        });
        get("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
        get("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Status status = chessGame.status();
            model.put("winner", status.winner());
            model.put("whiteScore", status.getWhiteScore());
            model.put("blackScore", status.getBlackScore());
            return render(model, "index.html");
        });
        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String[] source = req.queryParams("source").split("");
            String[] target = req.queryParams("target").split("");
            Position sourcePosition = Position.of(source[0], source[1]);
            Position targetPosition = Position.of(target[0], target[1]);
            System.out.println(sourcePosition);
            System.out.println(targetPosition);
            chessGame.move(sourcePosition, targetPosition);
            if (chessGame.isFinished()) {
                res.redirect("/end");
                return null;
            }
            Map<Position, Piece> board = chessBoard.getBoard();
            for (Position position : board.keySet()) {
                model.put(position.getPosition(), board.get(position).symbolByPlayer());
            }
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
