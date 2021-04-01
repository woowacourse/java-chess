package chess;

import static chess.domain.piece.Color.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import chess.domain.ChessGame;
import chess.domain.RequestDto;
import chess.domain.User;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessService {
    private static final ChessDAO chessDAO = new ChessDAO();
    private static final Gson GSON = new Gson();

    private static ChessGame chessGame;
    private static User user;

    public static String makeChessBoard(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        return render(model, "chess.html");
    }

    static String restartChess(Request req, Response res) throws SQLException {
        chessGame = new ChessGame();
        chessDAO.deleteBoard(chessDAO.findUserIdByUser(user));
        return render(new HashMap<>(), "/chess");
    }

    public static String matchBoardImageSouce(Request request, Response response) throws SQLException {
        String userId = chessDAO.findUserIdByUser(user);
        BoardDto boardDto = chessDAO.findBoard(userId);
        chessGame = makeChessGame(boardDto);
        return GSON.toJson(chessGame.getBoard().get(Point.of(request.body())).getImage());
    }

    public static String matchPieceName(Request request, Response response) {
        return GSON.toJson(chessGame.getBoard().get(Point.of(request.body())).getName());
    }

    private static ChessGame makeChessGame(BoardDto boardDto) {
        if (boardDto == null) {
            return new ChessGame();
        }
        return new ChessGame(boardDto.getBoard());
    }

    public static int moveRequest(Request request, Response response) {
        RequestDto requestDto = GSON.fromJson(request.body(), RequestDto.class);
        return move(requestDto);
    }

    public static String addBoard(Request req, Response res) throws SQLException {
        chessDAO.addBoard(chessDAO.findUserIdByUser(user), req.body(), chessGame.nextTurn());
        return render(new HashMap<>(), "start.html");
    }

    public static String makeNextColor(Request req, Response res) {
        if (chessGame.nextTurn().isSameAs(BLACK)) {
            return WHITE.name();
        }
        return BLACK.name();
    }

    public static String makeCurrentColor(Request req, Response res) {
        if (chessGame.getBoard().get(Point.of(req.body())).isSameTeam(BLACK)) {
            return BLACK.name();
        }
        return WHITE.name();
    }

    public static int move(RequestDto requestDto) {
        String source = requestDto.getSource();
        String target = requestDto.getTarget();
        try {
            chessGame.playTurn(Point.of(source), Point.of(target));
            if (chessGame.isEnd()) {
                return 333;
            }
            return 200;
        } catch (UnsupportedOperationException | IllegalArgumentException e) {
            return 401;
        }
    }

    public static double score(Request request, Response response) {
        return chessGame.calculateScore(Color.valueOf(request.body())).getScore();
    }

    public static String signUp(Request req, Response res) throws SQLException {
        User user = new User(req.queryParams("name"), req.queryParams("password"));
        chessDAO.addUser(user);
        return render(new HashMap<>(), "start.html");
    }

    public static String login(Request req, Response res) throws SQLException {
        user = new User(req.queryParams("name"), req.queryParams("password"));
        Map<String, Object> model = new HashMap<>();
        if (chessDAO.findByUserNameAndPwd(user.getId(), user.getPwd()) == null) {
            return render(model, "start.html");
        }
        model.put("user", user);
        return render(model, "chess.html");
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
