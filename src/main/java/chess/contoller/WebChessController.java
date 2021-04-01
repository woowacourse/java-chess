package chess.contoller;

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

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

public class WebChessController {
    private static final ChessGameDAO CHESS_GAME_DAO = new ChessGameDAO();

    public void init() {
        staticFiles.location("/templates");

        initWebChessGame();

        newWebChessGame();

        loadWebChessGame();

        playWebChessGame();
    }

    private void initWebChessGame() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "init.html");
        });
    }

    private void newWebChessGame() {
        get("/chess/new", (req, res) -> {
            ChessGame chessGame = startChessGame();
            CHESS_GAME_DAO.updateChessGameState();
            Map<String, Object> model = makeBoardDto(chessGame);
            return render(model, "main.html");
        });
    }

    private void loadWebChessGame() {
        get("/chess/load", (req, res) -> {
            ChessGame chessGame = startAndLoadChessGame();
            Map<String, Object> model = makeBoardDto(chessGame);
            return render(model, "main.html");
        });
    }

    private void playWebChessGame() {
        get("/chess/move", (req, res) -> {
            ChessGame chessGame = startAndLoadChessGame();
            try {
                List<String> arguments = Arrays.asList("move", req.queryParams("source"), req.queryParams("target"));
                chessGame.move(arguments);
                CHESS_GAME_DAO.addMoveCommand(String.join(" ", arguments));
                if (!chessGame.isRunning()) {
                    return alertWinner(chessGame);
                }
                Map<String, Object> model = makeBoardDto(chessGame);
                return render(model, "main.html");
            } catch (IllegalArgumentException e) {
                return alertError(chessGame, e);
            }
        });
    }

    private String alertWinner(ChessGame chessGame) throws SQLException {
        Map<String, Object> model = makeBoardDto(chessGame);
        model.put("winner", new WinnerDto(chessGame.winner()).getWinnerMsg());
        CHESS_GAME_DAO.updateChessGameState();
        return render(model, "main.html");
    }

    private String alertError(ChessGame chessGame, IllegalArgumentException e) {
        Map<String, Object> model = makeBoardDto(chessGame);
        ErrorDto errorDto = new ErrorDto(e.getMessage());
        model.put("error", errorDto.getMsg());
        return render(model, "main.html");
    }

    private Map<String, Object> makeBoardDto(ChessGame chessGame) {
        WebBoardDto webBoardDto = new WebBoardDto(chessGame.board());
        Map<String, Object> model = webBoardDto.getBoardDto();
        putScoreDtoToModel(chessGame, model);
        return model;
    }

    private void putScoreDtoToModel(ChessGame chessGame, Map<String, Object> model) {
        model.put("whiteScore", new ScoreDto(chessGame.score(Team.WHITE)));
        model.put("blackScore", new ScoreDto(chessGame.score(Team.BLACK)));
    }

    private ChessGame startAndLoadChessGame() throws SQLException {
        ChessGame chessGame = startChessGame();
        List<String> moves = CHESS_GAME_DAO.getRunningGameMove();
        for (String move : moves) {
            chessGame.move(Arrays.asList(move.split(" ")));
        }
        return chessGame;
    }

    private ChessGame startChessGame() {
        ChessGame chessGame = new ChessGame(new Board());
        chessGame.start();
        return chessGame;
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
