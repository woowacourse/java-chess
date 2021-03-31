package chess;

import static spark.Spark.get;
import static spark.Spark.put;

import chess.dao.GameDao;
import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.ScoreBoard;
import chess.domain.chessgame.Turn;
import chess.domain.gamestate.GameState;
import chess.dto.BoardWebDto;
import chess.dto.GameStatusDto;
import chess.dto.PointDto;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    private static final Gson GSON = new Gson();

    public static void main(String[] args) throws SQLException {
        GameDao gameDao = new GameDao();
        Board board = boardFromDb(gameDao);
        ChessGame chessGame = chessGameFromDb(board, gameDao);

        Spark.staticFileLocation("/public");

        get("/", (req, res) -> {
            BoardWebDto boardWebDto = new BoardWebDto(board);
            Map<String, Object> model = new HashMap<>();
            model.put("board", boardWebDto);
            return render(model);
        });

        put("/start", (req, res) -> {
            chessGame.start();
            gameDao
                .insertBoardAndStatusDto(new BoardWebDto(board), new GameStatusDto(chessGame));
            return GSON.toJson(new BoardWebDto(board));
        });

        get("/movablePoints/:point", "application/json", (req, res) -> {
            Point currentPoint = Point.of(req.params("point"));
            List<Point> movablePoints = chessGame.movablePoints(currentPoint);
            List<PointDto> pointDtos = movablePoints.stream()
                .map(PointDto::new)
                .collect(Collectors.toList());
            return GSON.toJson(pointDtos);
        });

        put("/move", "application/json", (req, res) -> {
            Map<String, String> body = new HashMap<>();
            body = (Map<String, String>) GSON.fromJson(req.body(), body.getClass());
            Point source = Point.of(body.get("source"));
            Point destination = Point.of(body.get("destination"));
            chessGame.move(source, destination);
            gameDao
                .insertBoardAndStatusDto(new BoardWebDto(board), new GameStatusDto(chessGame));
            return GSON.toJson(new BoardWebDto(board));
        });

        get("/getGameStatus", "application/json",
            (req, res) -> GSON.toJson(new GameStatusDto(chessGame)));

        put("/exit", (req, res) -> {
            chessGame.end();
            return GSON.toJson("success");
        });
    }

    private static Board boardFromDb(GameDao gameDao) throws SQLException {
        return gameDao.latestBoard().toEntity();
    }

    private static ChessGame chessGameFromDb(Board board, GameDao gameDao) throws SQLException {
        GameStatusDto gameStatusDto = gameDao.latestGameStatus();
        Turn turn = gameStatusDto.toTurnEntity();
        GameState gameState = gameStatusDto.toGameStateEntity(board);
        return new ChessGame(turn, new ScoreBoard(board), gameState);
    }

    private static String render(Map<String, Object> model) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, "index.html"));
    }
}
