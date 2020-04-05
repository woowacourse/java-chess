import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.progress.Progress;
import dto.*;
import chess.game.ChessGame;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
    private static final Gson GSON = new Gson();

    public static void main(String[] args) {
        staticFiles.location("templates");
        ChessGame chessGame = new ChessGame();

        get("/main", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "start.html");
        });

        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start/board", (req, res) -> {

            BoardDTO boardDTO = new BoardDTO(chessGame.getChessBoard());
            return GSON.toJson(boardDTO);
        });

        post("/start/move", (req, res) -> {
            String[] nowLocation = parseRowAndCol(req.queryParams("now"));
            String[] destination = parseRowAndCol(req.queryParams("des"));
            LocationDTO nowDTO = new LocationDTO(nowLocation[1], nowLocation[0]);
            LocationDTO afterDTO = new LocationDTO(destination[1], destination[0]);

            Progress progress = chessGame.webDoMoveCommand(nowDTO.getLocation(), afterDTO.getLocation());
            if (Progress.CONTINUE == progress) {
                chessGame.changeTurn();
            }

            ChessGameScoresDTO chessGameScoresDTO = new ChessGameScoresDTO(chessGame.calculateScores());
            ChessMoveDTO chessMoveDTO = new ChessMoveDTO(chessGameScoresDTO, progress, chessGame.getTurn());
            return GSON.toJson(chessMoveDTO);
        });

        get("/start/winner", (req, res) -> {
            Gson gson = new Gson();
            ChessResultDTO chessResultDTO = new ChessResultDTO(chessGame.findWinner());
            return gson.toJson(chessResultDTO);
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static String[] parseRowAndCol(String rowAndCol) {
        return rowAndCol.split("_");
    }
}
