import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.progress.Progress;
import data.*;
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

            BoardVO boardVO = new BoardVO(chessGame.getChessBoard());
            return GSON.toJson(boardVO);
        });

        post("/start/move", (req, res) -> {

            String now = req.queryParams("now");
            String after = req.queryParams("des");
            String[] strings = parseRowAndCol(now);
            String[] afterStrings = parseRowAndCol(after);
            LocationDTO nowDTO = new LocationDTO(strings[1], strings[0]);
            LocationDTO afterDTO = new LocationDTO(afterStrings[1], afterStrings[0]);
            // END 혹은 CONTINUE 혹은 ERROR 출력
            Progress progress = chessGame.webDoMoveCommand(nowDTO.getLocation(), afterDTO.getLocation());
            if (Progress.CONTINUE == progress) {
                chessGame.changeTurn();
            }
            // Score 확
            ChessGameScoresVO chessGameScoresVO = new ChessGameScoresVO(chessGame.calculateScores());
            System.out.println("blackscore = " + chessGameScoresVO.getBlackScore().getValue());
            System.out.println("whitescore = " + chessGameScoresVO.getWhiteScore().getValue());
            ChessMoveVO chessMoveVO = new ChessMoveVO(chessGameScoresVO, progress);
            return GSON.toJson(chessMoveVO);
        });

        get("/start/winner", (req, res) -> {
            Gson gson = new Gson();
            ChessResultVO chessResultVO = new ChessResultVO(chessGame.findWinner());
            return gson.toJson(chessResultVO);
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static String[] parseRowAndCol(String rowAndCol) {
        return rowAndCol.split("_");
    }
}
