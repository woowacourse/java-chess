package chess;

import chess.dao.ChessGameDAO;
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
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static final ChessGameDAO CHESS_GAME_DAO = new ChessGameDAO();

    public static void main(String[] args) {
        staticFiles.location("/templates");

        get("/", (req, res) -> {
            ChessGame chessGame = new ChessGame(new Board());
            chessGame.start();
            Map<String, Object> model = new HashMap<>();
            return render(model, "init.html");
        });

        get("/chess/new", (req, res) -> {
            ChessGame chessGame = new ChessGame(new Board());
            chessGame.start();
            CHESS_GAME_DAO.updateChessGameState();
            WebBoardDto webBoardDto = new WebBoardDto(chessGame.board());
            Map<String, Object> model = webBoardDto.getBoardDto();
            model.put("whiteScore", new ScoreDto(chessGame.score(Team.WHITE)));
            model.put("blackScore", new ScoreDto(chessGame.score(Team.BLACK)));
            return render(model, "main.html");
        });

        get("/chess/load", (req, res) -> {
            ChessGame chessGame = new ChessGame(new Board());
            chessGame.start();
            List<String> moves = CHESS_GAME_DAO.getRunningGameMove();
            moves.forEach(move -> chessGame.move(Arrays.asList(move.split(" "))));
            WebBoardDto webBoardDto = new WebBoardDto(chessGame.board());
            Map<String, Object> model = webBoardDto.getBoardDto();
            model.put("whiteScore", new ScoreDto(chessGame.score(Team.WHITE)));
            model.put("blackScore", new ScoreDto(chessGame.score(Team.BLACK)));
            return render(model, "main.html");
        });

        get("/chess/move", (req, res) -> {
            ChessGame chessGame = new ChessGame(new Board());
            chessGame.start();
            List<String> moves = CHESS_GAME_DAO.getRunningGameMove();
            moves.forEach(move -> chessGame.move(Arrays.asList(move.split(" "))));
            WebBoardDto webBoardDto = new WebBoardDto(chessGame.board());
            try {
                List<String> arguments = Arrays.asList("move", req.queryParams("source"), req.queryParams("target"));
                chessGame.move(arguments);
                CHESS_GAME_DAO.addMoveCommand(String.join(" ", arguments));
                if (!chessGame.isRunning()) {
                    WebBoardDto boardDto = new WebBoardDto(chessGame.board());
                    Map<String, Object> model = boardDto.getBoardDto();
                    model.put("winner", new WinnerDto(chessGame.winner()).getWinnerMsg());
                    CHESS_GAME_DAO.updateChessGameState();
                    return render(model, "main.html");
                }
                webBoardDto = new WebBoardDto(chessGame.board());
                Map<String, Object> model = webBoardDto.getBoardDto();
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
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
