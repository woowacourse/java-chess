package chess;

import static spark.Spark.get;

import chess.dto.PointDto;
import com.google.gson.Gson;
import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.Team;
import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.Turn;
import chess.dto.BoardDtoWeb;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
    private static Gson gson = new Gson();

    public static void main(String[] args) {
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board, new Turn(Team.WHITE));

        Spark.staticFileLocation("/public");
        get("/", (req, res) -> {
            chessGame.start();
            BoardDtoWeb boardDtoWeb = new BoardDtoWeb(board);
            Map<String, Object> model = new HashMap<>();
            model.put("board", boardDtoWeb);
            return render(model, "index.html");
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
           chessGame.move(source, destination);
           BoardDtoWeb boardDtoWeb = new BoardDtoWeb(board);
           return gson.toJson(boardDtoWeb);
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
