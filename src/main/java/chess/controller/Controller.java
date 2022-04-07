package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.dao.BoardDao;
import chess.domain.boardstrategy.InitBoardStrategy;
import chess.domain.game.ChessGame;
import chess.domain.game.state.Play;
import chess.domain.game.state.State;
import chess.domain.game.state.attribute.StateType;
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
    private final Map<String, Object> model = new HashMap<>();
    private final BoardDao boardDao = new BoardDao();
    private ChessGame chessGame = new ChessGame(new InitBoardStrategy());
    private State state = new Play(chessGame);

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void run() {
        staticFiles.location("/static");
        get("/", (req, res) -> getStartObject());
        post("/start", (req, res) -> {
            initGame(req.queryParams("name"));
            return renderGame();
        });
        post("/command", (req, res) -> {
            go(req.queryParams("command"));
            saveToDB();
            return renderGame();
        });
        post("/end", (req, res) -> renderEnd());
    }

    private void initGame(String name) {
        model.put("name", name);
        model.put("error", EMPTY);

        boardDao.create(new ChessGameDto(name, new ChessGame(new InitBoardStrategy())));
        chessGame = boardDao.findByName(name).getChessGame();
        System.out.println(chessGame);
        state = new Play(chessGame);
    }

    private void go(String input) {
        try {
            state = state.execute(new CommandDto(input));
            model.put("error", EMPTY);
            model.put("result", getResult());
        } catch (IllegalArgumentException e) {
            model.put("error", e.getMessage());
        }
    }

    private String getResult() {
        if (state.getType() != StateType.PLAY) {
            return END_MESSAGE;
        }
        return EMPTY;
    }

    private void saveToDB() {
        boardDao.save(new ChessGameDto((String) model.get("name"), chessGame));
    }

    private Object getStartObject() {
        chessGame.reset();
        return render(model, "index.html");
    }

    private Object renderGame() {
        Map<Team, Double> scores = chessGame.getScoreOfTeams();
        model.put("whiteScore", scores.get(Team.WHITE));
        model.put("blackScore", scores.get(Team.BLACK));
        model.put("turn", chessGame.getTurn());
        model.put("pieces", new ChessGameDto(chessGame).getBoardWeb());

        return render(model, "chessGame.html");
    }

    private Object renderEnd() {
        Map<Team, Double> scores = chessGame.getScoreOfTeams();
        model.put("whiteScore", scores.get(Team.WHITE));
        model.put("blackScore", scores.get(Team.BLACK));
        model.put("winner", getWinner());

        boardDao.delete((String) model.get("name"));

        return render(model, "end.html");
    }

    private String getWinner() {
        Team winner = chessGame.getWinner();
        if (winner == Team.NONE) {
            return DRAW_MESSAGE;
        }
        return winner.name();
    }
}
