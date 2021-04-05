package chess;

import static chess.domain.piece.Color.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import chess.dao.ChessDao;
import chess.dao.SQLConnection;
import chess.domain.ChessGame;
import chess.dto.RequestDto;
import chess.dto.UserDto;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.dto.BoardDto;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessService {
    private static final ChessDao chessDAO = new ChessDao(new SQLConnection());
    private static final Gson GSON = new Gson();

    private static ChessGame chessGame;
    private static UserDto userDto;

    public static String makeChessBoard(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        model.put("user", userDto);
        return render(model, "chess.html");
    }

    static String restartChess(Request req, Response res) throws SQLException {
        chessGame = new ChessGame();
        chessDAO.deleteBoard(chessDAO.findUserIdByUser(userDto));
        return render(new HashMap<>(), "/chess");
    }

    public static String matchBoardImageSouce(Request request, Response response) throws SQLException {
        String userId = chessDAO.findUserIdByUser(userDto);
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
        chessDAO.addBoard(chessDAO.findUserIdByUser(userDto), req.body(), chessGame.nextTurn());
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
        UserDto userDto = new UserDto(req.queryParams("name"), req.queryParams("password"));
        chessDAO.addUser(userDto);
        return render(new HashMap<>(), "start.html");
    }

    public static String login(Request req, Response res) throws SQLException {
        userDto = new UserDto(req.queryParams("name"), req.queryParams("password"));
        Map<String, Object> model = new HashMap<>();
        if (chessDAO.findByUserNameAndPwd(userDto.getName(), userDto.getPwd()) == null) {
            return render(model, "start.html");
        }
        model.put("user", userDto);
        return render(model, "chess.html");
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
