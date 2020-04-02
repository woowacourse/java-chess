import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import data.BoardVO;
import chess.board.ChessBoard;
import chess.game.ChessGame;
import chess.location.Location;
import chess.piece.type.Piece;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("templates");

        ChessGame chessGame = new ChessGame();
        ChessBoard chessBoard = chessGame.getChessBoard();
        Map<Location, Piece> board = chessBoard.getBoard();

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
            BoardVO boardVO = new BoardVO(chessBoard);
            return gson.toJson(boardVO);
        });


    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
