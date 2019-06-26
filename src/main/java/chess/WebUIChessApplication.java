package chess;

import chess.domain.Aliance;
import chess.domain.Board;
import chess.domain.Column;
import chess.domain.Row;
import com.google.gson.Gson;
import dao.GameDao;
import dao.GameDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import dto.GameDto;
import dto.UserDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/");

        get("/", (req, res) -> {
            GameDao gameDao = GameDaoImpl.getInstance();
            List<GameDto> notEndGameDtos = gameDao.findNotEndGames();

            Map<String, Object> model = new HashMap<>();
            model.put("notEndGames", notEndGameDtos);

            return render(model, "intro.html");
        });

        post("/", (req, res) -> {
            GameDao gameDao = GameDaoImpl.getInstance();
            int gameId = gameDao.addGame();

            UserDao userDao = UserDaoImpl.getInstance();
            UserDto whiteUserDto = new UserDto(gameId, req.queryParams("white_user"), 2);
            UserDto blackUserDto = new UserDto(gameId, req.queryParams("black_user"), 1);
            userDao.addUser(whiteUserDto);
            userDao.addUser(blackUserDto);

            res.redirect("/" + gameId);
            return "";
        });

        get("/:gameId", (req, res) -> {
            List<Row> rows = Row.getRows();
            List<Column> columns = Column.getColumns();
            Collections.reverse(rows);

            GameDao gameDao = GameDaoImpl.getInstance();
            GameDto gameDto = gameDao.findById(Integer.parseInt(req.params("gameId")));
            UserDao userDao = UserDaoImpl.getInstance();
            List<UserDto> userDtos = userDao.findByGameId(Integer.parseInt(req.params("gameId")));

            Board board = new Board();
            Gson gson = new Gson();
            Map<String, Object> model = new HashMap<>();
            model.put("rows", rows);
            model.put("columns", columns);
            model.put("whiteUser", userDtos.get(0));
            model.put("blackUser", userDtos.get(1));
            model.put("whiteTurn", gameDto.getTurn() == Aliance.WHITE);
            model.put("blackTurn", gameDto.getTurn() == Aliance.BLACK);
            model.put("pieces", gson.toJson(board.getPieces()));

            return render(model, "chess.html");
        });

        exception(Exception.class, (exception, req, res) -> {
            res.body(String.format("<script>alert('%s'); history.back();</script>", exception.getMessage(), req.pathInfo()));
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
