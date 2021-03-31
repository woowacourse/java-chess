package chess;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import chess.domain.ChessGame;
import chess.domain.RequestDto;
import chess.domain.User;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessService {
    private static final Gson GSON = new Gson();
    private final ChessDAO chessDAO = new ChessDAO();
    private ChessGame chessGame;
    private User user;

    public ChessService() {
        this.chessGame = new ChessGame();
        get("/chess", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "chess.html");
        });

        post("/board", (req, res) -> GSON.toJson(chessGame.getBoard().get(Point.of(req.body())).getImage()));

        post("/piecename", (req, res) -> GSON.toJson(chessGame.getBoard().get(Point.of(req.body())).getName()));

        post("/move", (req, res) -> {
            RequestDto requestDto = GSON.fromJson(req.body(), RequestDto.class);
            return move(requestDto);
        });

        post("/color", (req, res) -> {
            if (chessGame.getBoard().get(Point.of(req.body())).isSameTeam(Color.BLACK)) {
                return "흑";
            } else {
                return "백";
            }
        });

        post("/turn", (req, res) -> {
            if (chessGame.nextTurn().isSameAs(Color.BLACK)) {
                return "WHITE";
            } else {
                return "BLACK";
            }
        });

        post("/score", (req, res) -> score(req.body()));

        get("/rerun", (req, res) -> {
            final Map<String, Object> model = new HashMap<>();
            this.chessGame = new ChessGame();
            return render(model, "chess.html");
        });

        get("/", (req, res) -> {
            final Map<String, Object> model = new HashMap<>();
            return render(model, "start.html");
        });

        post("/user", (req, res) -> {
            user = new User(req.queryParams("name"), req.queryParams("password"));
            Map<String, Object> model = new HashMap<>();
            if (chessDAO.findByUserNameAndPwd(user.getId(), user.getPwd()) == null) {
                return render(model, "start.html");
            }
            model.put("user", user);
            return render(model, "chess.html");
        });

        get("/adduser", (req, res) -> {
            final Map<String, Object> model = new HashMap<>();
            return render(model, "form.html");
        });

        post("/signup", (req, res) -> {
            final Map<String, Object> model = new HashMap<>();
            user = new User(req.queryParams("name"), req.queryParams("password"));
            chessDAO.addUser(user);
            return render(model, "start.html");
        });

        post("/addboard", (req, res) -> {
            final Map<String, Object> model = new HashMap<>();
            chessDAO.addBoard(chessDAO.findUserIdByUser(user), req.body(), chessGame.nextTurn());
            return render(model, "start.html");
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
