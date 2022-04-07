package chess.game;

import chess.chessboard.position.Position;
import chess.dao.CommandDao;
import chess.piece.Piece;
import chess.state.Start;
import chess.state.State;
import chess.state.Status;
import chess.view.Square;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebChessGame {

    private State state;

    public void run() {
        CommandDao commandDao = new CommandDao();
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render( model,"start.html");
        });

        post("/start", (req, res) -> gameStart(commandDao));

        post("/game", (req, res) -> gameProceed(req, commandDao));

        post("/result", (req, res) -> result());

        post("/reload", (req, res) -> {
            return reload(commandDao);
        });
    }

    private String result() {
        final Map<String, Object> model = new HashMap<>();
        model.put("squares", showChessBoard(state.getBoard()));
        state = state.proceed("status");
        Status status = (Status) state;
        HashMap<Player, Double> results = status.calculateScore();
        model.put("whiteScore", results.get(Player.WHITE));
        model.put("blackScore", results.get(Player.BLACK));
        return render(model, "status.html");
    }

    private String playerName(final Player player) {
        return player.getName();
    }

    private State proceed(final String command, final Map<String, Object> model) {
        try {
            model.put("message", "실행한 명령어: " + command);
            return state.proceed(command);
        } catch (IllegalArgumentException exception) {
            model.put("message", exception.getMessage());
            return state;
        }
    }

    private String gameStart(final CommandDao commandDao) {
        commandDao.init();
        final Map<String, Object> model = new HashMap<>();
        state = Start.initState("start");
        model.put("squares", showChessBoard(state.getBoard()));
        model.put("player", "White");
        model.put("message", "게임을 시작합니다.");
        return render(model, "game.html");
    }

    private List<Square> showChessBoard(final Map<Position, Piece> board) {
        final List<Square> squares = new ArrayList<>();
        for(final Position position : board.keySet()) {
            addPiece(position, board.get(position), squares);
        }
        return squares;
    }

    private void addPiece(final Position position, final Piece piece, final List<Square> squares) {
        if (!piece.isBlank()) {
            squares.add(new Square(piece.getImageName(), position.getPosition()));
        }
    }

    private String gameProceed(Request req, CommandDao commandDao) {
        final Map<String, Object> model = new HashMap<>();
        model.put("player", playerName(state.getPlayer()));
        commandDao.save(req.queryParams("command"));
        model.put("commands", commandDao.findAll());
        state = proceed(req.queryParams("command"), model);
        model.put("squares", showChessBoard(state.getBoard()));
        if (state.isRunning()) {
            return render(model, "game.html");
        }
        return render(model, "finished.html");
    }

    private String reload(CommandDao commandDao) {
        final List<String> commands = commandDao.findAll();
        final Map<String, Object> model = new HashMap<>();
        state = Start.initState("start");
        model.put("commands", commandDao.findAll());
        for (String command : commands) {
            state = proceed(command, model);
        }
        model.put("player", playerName(state.getPlayer()));
        model.put("squares", showChessBoard(state.getBoard()));
        if (state.isRunning()) {
            return render(model, "game.html");
        }
        return render(model, "finished.html");
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
