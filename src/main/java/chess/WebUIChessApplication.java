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
        Board board = boardFromDb(gameDao);
        ChessGame chessGame = chessGameFromDb(board, gameDao);

        Spark.staticFileLocation("/public");

        get("/", (req, res) -> {
            BoardDtoWeb boardDtoWeb = new BoardDtoWeb(board);
            Map<String, Object> model = new HashMap<>();
            model.put("board", boardDtoWeb);
            return render(model);
        });

        put("/start", (req, res) -> {
            chessGame.start();
            gameDao
                .addSerializedBoardAndStatus(new BoardDtoWeb(board), new GameStatusDto(chessGame));
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

        put("/move", "application/json", (req, res) -> {
            Map<String, String> body = new HashMap<>();
            body = (Map<String, String>) gson.fromJson(req.body(), body.getClass());
            Point source = Point.of(body.get("source"));
            Point destination = Point.of(body.get("destination"));
            Map<String, Object> model = new HashMap<>();
            chessGame.move(source, destination);
            gameDao
                .addSerializedBoardAndStatus(new BoardDtoWeb(board), new GameStatusDto(chessGame));
            model.put("board", new BoardDtoWeb(board));
            return gson.toJson(model);
        });

        get("/getGameStatus", "application/json",
            (req, res) -> gson.toJson(new GameStatusDto(chessGame)));

        put("/exit", (req, res) -> {
            chessGame.end();
            return gson.toJson("success");
        });
    }

    private static Board boardFromDb(GameDao gameDao)
        throws SQLException, IOException, ClassNotFoundException {
        return gameDao.latestBoard().toEntity();
    }

    private static ChessGame chessGameFromDb(Board board, GameDao gameDao)
        throws SQLException, IOException, ClassNotFoundException {
        GameStatusDto gameStatusDto = gameDao.latestGameStatus();
        Turn turn = gameStatusDto.toTurnEntity();
        GameState gameState = gameStatusDto.toGameStateEntity(board);
        return new ChessGame(turn, new ScoreBoard(board), gameState);
    }

    private static String render(Map<String, Object> model) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, "index.html"));
    }
}
