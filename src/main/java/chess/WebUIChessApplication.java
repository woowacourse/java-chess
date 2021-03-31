package chess;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.dto.ErrorDto;
import chess.dto.WebBoardDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/templates");
        ChessGame chessGame = new ChessGame(new Board());
        chessGame.start();

        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "init.html");
        });

        post("/main", (req, res) -> {
            WebBoardDto webBoardDto = new WebBoardDto(chessGame.board());
            Map<String, Object> model = webBoardDto.getBoardDto();
            model.put("whiteScore", Double.toString(chessGame.score(Team.WHITE)));
            model.put("blackScore", Double.toString(chessGame.score(Team.BLACK)));
            return render(model, "main.html");
        });

        get("/chess/move", (req, res) -> {
            WebBoardDto webBoardDto = new WebBoardDto(chessGame.board());
            try {
                chessGame.move(Arrays.asList("move", req.queryParams("source"), req.queryParams("target")));
                WebBoardDto movedBoardDto = new WebBoardDto(chessGame.board());
                Map<String, Object> model = movedBoardDto.getBoardDto();
                model.put("whiteScore", Double.toString(chessGame.score(Team.WHITE)));
                model.put("blackScore", Double.toString(chessGame.score(Team.BLACK)));
                return render(model, "main.html");
            } catch(IllegalArgumentException e) {
                Map<String, Object> model = webBoardDto.getBoardDto();
                ErrorDto errorDto = new ErrorDto(e.getMessage());
                model.put("error", errorDto.getMsg());
                return render(model, "main.html");
            }
        });

        if (!chessGame.isRunning()) {
            get("/chess/move", (req, res) -> {
                WebBoardDto boardDto = new WebBoardDto(chessGame.board());
                Map<String, Object> model = boardDto.getBoardDto();
                model.put("winner", chessGame.winner());
                return render(model, "main.html");
            });
        }

        post("/restart", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "init.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
