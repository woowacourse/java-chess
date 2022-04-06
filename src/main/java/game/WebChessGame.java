package game;

import chess.chessboard.position.Position;
import chess.piece.Piece;
import chess.state.Start;
import chess.state.State;
import chess.view.Square;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.port;

public class WebChessGame {

    public void run() {
        post("/game_start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            State state = Start.initState("start");
            model.put("squares", showChessBoard(state.getBoard()));
            model.put("player", "흰");
            model.put("instruction", "입력하세요");
            return render(model, "game.html");
        });

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return  render(model, "start.html");
        });
    }

    private List<Square> showChessBoard(Map<Position, Piece> board) {
        List<Square> squares = new ArrayList<>();
        for(Position position : board.keySet()) {
            addPiece(position, board.get(position), squares);
        }
        return squares;
    }

    private void addPiece(Position position, Piece piece, List<Square> squares) {
        if (!piece.isBlank()) {
            squares.add(new Square(piece.getImageName(), position.getPosition()));
        }
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
