package chess;

import static spark.Spark.get;
import static spark.Spark.put;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.Team;
import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.Turn;
import chess.dto.BoardDtoWeb;
import chess.dto.GameStatusDto;
import chess.dto.PointDto;
import chess.dto.StandardResponse;
import chess.dto.StatusResponse;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import spark.ModelAndView;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    private static final Gson gson = new Gson();

    public static  void main(String[] args) {
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board, new Turn(Team.WHITE));

        Spark.staticFileLocation("/public");
        get("/", (req, res) -> {
            BoardDtoWeb boardDtoWeb = new BoardDtoWeb(board);
            Map<String, Object> model = new HashMap<>();
            model.put("board", boardDtoWeb);
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
           chessGame.start();
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
