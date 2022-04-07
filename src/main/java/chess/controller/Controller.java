package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.dao.BoardDao;
import chess.domain.boardstrategy.InitBoardStrategy;
import chess.domain.game.ChessGame;
import chess.domain.piece.attribute.Team;
import chess.dto.ChessGameDto;
import chess.dto.CommandDto;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Controller {
    private static final String DRAW_MESSAGE = "무승부";
    private static final String END_MESSAGE = "게임 종료. 결과를 확인하려면 end 버튼을 클릭하세요.";
    private static final String EMPTY = "";

    public void run() {
        staticFiles.location("/static");
        final Map<String, Object> model = new HashMap<>();
        final ChessGame chessGame = new ChessGame(new InitBoardStrategy());

        get("/", (req, res) -> renderStart(model, chessGame));
        post("/start", (req, res) -> {
            chessGame.clone(initGame(model, req.queryParams("name")));
            return renderGame(model, chessGame);
        });
        post("/command", (req, res) -> {
            chessGame.clone(go(model, chessGame, req.queryParams("command")));
            saveToDB(model, chessGame);
            return renderGame(model, chessGame);
        });
        post("/end", (req, res) -> renderEnd(model, chessGame));
    }

    private ChessGame initGame(Map<String, Object> model, String name) {
        model.put("name", name);
        model.put("error", EMPTY);

        new BoardDao().create(new ChessGameDto(name, new ChessGame(new InitBoardStrategy())));
        return new BoardDao().findByName(name).getChessGame();
    }

    private ChessGame go(Map<String, Object> model, ChessGame chessGame, String input) {
        try {
            chessGame.execute(new CommandDto(input));
            model.put("error", EMPTY);
            model.put("result", getResult(chessGame));
            return chessGame;
        } catch (IllegalArgumentException e) {
            model.put("error", e.getMessage());
        }
        return chessGame;
    }

    private String getResult(ChessGame chessGame) {
        if (chessGame.isFinished()) {
            return END_MESSAGE;
        }
        return EMPTY;
    }

    private void saveToDB(Map<String, Object> model, ChessGame chessGame) {
        new BoardDao().save(new ChessGameDto((String) model.get("name"), chessGame));
    }

    private Object renderStart(Map<String, Object> model, ChessGame chessGame) {
        chessGame.reset();
        return render(model, "index.html");
    }

    private Object renderGame(Map<String, Object> model, ChessGame chessGame) {
        Map<Team, Double> scores = chessGame.getScoreOfTeams();
        model.put("whiteScore", scores.get(Team.WHITE));
        model.put("blackScore", scores.get(Team.BLACK));
        model.put("turn", chessGame.getTurn());
        model.put("pieces", new ChessGameDto(chessGame).getBoardWeb());

        return render(model, "chessGame.html");
    }

    private Object renderEnd(Map<String, Object> model, ChessGame chessGame) {
        Map<Team, Double> scores = chessGame.getScoreOfTeams();
        model.put("whiteScore", scores.get(Team.WHITE));
        model.put("blackScore", scores.get(Team.BLACK));
        model.put("winner", getWinner(chessGame));

        new BoardDao().delete((String) model.get("name"));

        return render(model, "end.html");
    }

    private String getWinner(ChessGame chessGame) {
        Team winner = chessGame.getWinner();
        if (winner == Team.NONE) {
            return DRAW_MESSAGE;
        }
        return winner.name();
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
