package chess;

import static spark.Spark.get;

import chess.dao.GameDao;
import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.ScoreBoard;
import chess.dto.BoardDtoWeb;
import chess.dto.GameStatusDto;
import chess.dto.PointDto;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    private static final Gson gson = new Gson();

    public static void main(String[] args)
        throws IOException, SQLException, ClassNotFoundException {
        GameDao gameDao = new GameDao();
        GameStatusDto gameStatusDto = gameDao.latestGameStatus();
        Board board = gameDao.latestBoard().toEntity();
        ChessGame chessGame = new ChessGame(gameStatusDto.toTurnEntity(), new ScoreBoard(board),
            gameStatusDto.toGameStateEntity(board));

        Spark.staticFileLocation("/public");
        get("/", (req, res) -> {
            BoardDtoWeb boardDtoWeb = new BoardDtoWeb(board);
            Map<String, Object> model = new HashMap<>();
            model.put("board", boardDtoWeb);
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            chessGame.start();
            gameDao
                .addSerializedBoardAndStatus(new BoardDtoWeb(board), new GameStatusDto(chessGame));
            return gson.toJson(new BoardDtoWeb(board));
        });

        get("/board", "application/json", (req, res) -> {
            return gson.toJson(new BoardDtoWeb(board));
        });

        get("/movablePoints/:point", "application/json", (req, res) -> {
            Point currentPoint = Point.of(req.params("point"));
            List<Point> movablePoints = chessGame.movablePoints(currentPoint);
            List<PointDto> pointDtos = movablePoints.stream()
                .map(PointDto::new)
                .collect(Collectors.toList());
            return gson.toJson(pointDtos);
        });

        get("/move", "application/json", (req, res) -> {
            Point source = Point.of(req.queryParams("source"));
            Point destination = Point.of(req.queryParams("destination"));
            Map<String, Object> model = new HashMap<>();
            chessGame.move(source, destination);
            gameDao
                .addSerializedBoardAndStatus(new BoardDtoWeb(board), new GameStatusDto(chessGame));
            model.put("board", new BoardDtoWeb(board));
            return gson.toJson(model);
        });

        get("/getGameStatus", "application/json", (req, res) -> {
            return gson.toJson(new GameStatusDto(chessGame));
        });

        get("/currentTurn", "application/json", (req, res) -> {
            return gson.toJson(chessGame.currentTurn());
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
