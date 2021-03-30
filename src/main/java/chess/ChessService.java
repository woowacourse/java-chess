package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import chess.domain.ChessGame;
import chess.domain.RequestDto;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessService {
    private static final Gson GSON = new Gson();
    private ChessGame chessGame;

    public ChessService() {
        this.chessGame = new ChessGame();
        get("/chess", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/board", (req, res) -> GSON.toJson(chessGame.getBoard().get(Point.of(req.body())).getImage()));

        post("/move", (req, res) -> {
            RequestDto requestDto = GSON.fromJson(req.body(), RequestDto.class);
            return move(requestDto);
        });

        post("/color", (req, res) -> {
            if(chessGame.getBoard().get(Point.of(req.body())).isSameTeam(Color.BLACK)){
                return "흑";
            } else {
                return "백";
            }
        });

        post("/turn", (req, res) -> {
            if(chessGame.nextTurn().isSameAs(Color.BLACK)){
                return "백";
            } else {
                return "흑";
            }
        });

        post("/score", (req, res) -> score(req.body()));

        get("/rerun", (req, res) -> {
            final Map<String, Object> model = new HashMap<>();
            this.chessGame = new ChessGame();
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public int move(RequestDto requestDto) {
        String source = requestDto.getSource();
        String target = requestDto.getTarget();
        try {
            chessGame.playTurn(Point.of(source), Point.of(target));
            if(chessGame.isEnd()) {
                return 333;
            }
            return 200;
        } catch (UnsupportedOperationException | IllegalArgumentException e) {
            return 401;
        }
    }

    public double score(String color) {
        return chessGame.calculateScore(Color.valueOf(color)).getScore();
    }
}
