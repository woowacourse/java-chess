import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.command.Command;
import chess.progress.Progress;
import chess.result.ChessResult;
import data.BoardVO;
import chess.board.ChessBoard;
import chess.game.ChessGame;
import chess.location.Location;
import chess.piece.type.Piece;
import com.google.gson.Gson;
import data.ChessResultVO;
import data.LocationDTO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import view.OutputView;

public class WebUIChessApplication {
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
            Gson gson = new Gson();
            BoardVO boardVO = new BoardVO(chessGame.getChessBoard());
            return gson.toJson(boardVO);
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
            return progress;
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
