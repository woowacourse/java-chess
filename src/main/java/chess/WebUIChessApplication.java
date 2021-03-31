package chess;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.dto.ErrorDto;
import chess.dto.ScoreDto;
import chess.dto.WebBoardDto;
import chess.dto.WinnerDto;
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

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "init.html");
        });

        post("/chess", (req, res) -> {
            WebBoardDto webBoardDto = new WebBoardDto(chessGame.board());
            Map<String, Object> model = webBoardDto.getBoardDto();
            model.put("whiteScore", new ScoreDto(chessGame.score(Team.WHITE)));
            model.put("blackScore", new ScoreDto(chessGame.score(Team.BLACK)));
            return render(model, "main.html");
        });

        get("/chess/move", (req, res) -> {
            WebBoardDto webBoardDto = new WebBoardDto(chessGame.board());
            try {
                chessGame.move(Arrays.asList("move", req.queryParams("source"), req.queryParams("target")));
                if (!chessGame.isRunning()) {
                    WebBoardDto boardDto = new WebBoardDto(chessGame.board());
                    Map<String, Object> model = boardDto.getBoardDto();
                    model.put("winner", new WinnerDto(chessGame.winner()).getWinnerMsg());
                    return render(model, "main.html");
                }

                WebBoardDto movedBoardDto = new WebBoardDto(chessGame.board());
                Map<String, Object> model = movedBoardDto.getBoardDto();
                model.put("whiteScore", new ScoreDto(chessGame.score(Team.WHITE)));
                model.put("blackScore", new ScoreDto(chessGame.score(Team.BLACK)));
                return render(model, "main.html");
            } catch (IllegalArgumentException e) {
                Map<String, Object> model = webBoardDto.getBoardDto();
                ErrorDto errorDto = new ErrorDto(e.getMessage());
                model.put("error", errorDto.getMsg());
                return render(model, "main.html");
            }
        });

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "init.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
