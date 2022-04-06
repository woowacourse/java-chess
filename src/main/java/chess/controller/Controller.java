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
import chess.dto.PieceDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Controller {
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
            return getObject();
        });
        post("/command", (req, res) -> {
            go(req.queryParams("command"));
            saveToDB();
            return getObject();
        });
        post("/end", (req, res) -> getEndObject());
    }

    private void initGame(String name) {
        model.put("name", name);
        initError();
        boardDao.create(new ChessGameDto(name, new ChessGame(new InitBoardStrategy())));
        chessGame = boardDao.findByName(name).getChessGame();
        System.out.println(chessGame);
        state = new Play(chessGame);
    }

    private void go(String input) {
        try {
            state = state.execute(new CommandDto(input));
            initError();
            setResult();
        } catch (IllegalArgumentException e) {
            model.put("error", e.getMessage());
        }
    }

    private void setResult() {
        model.put("result", "");
        if (state.getType() != StateType.PLAY) {
            model.put("result", "게임 종료. 결과를 확인하려면 end 버튼을 클릭하세요.");
        }
    }

    private void saveToDB() {
        boardDao.save(new ChessGameDto((String) model.get("name"), chessGame));
    }

    private void initError() {
        model.put("error", "");
    }

    private Object getStartObject() {
        chessGame.reset();
        return render(model, "index.html");
    }

    private Object getObject() {
        updateTurn();
        updatePieces();
        updateScore();
        return render(model, "chessGame.html");
    }

    private Object getEndObject() {
        updateScore();
        updateWinner();
        boardDao.delete((String) model.get("name"));
        return render(model, "end.html");
    }

    private void updateTurn() {
        model.put("turn", chessGame.getTurn());
    }

    private void updatePieces() {
        List<PieceDto> pieces = new ChessGameDto(chessGame).getBoardWeb();
        model.put("pieces", pieces);
    }

    private void updateScore() {
        Map<Team, Double> scores = chessGame.getScoreOfTeams();
        model.put("whiteScore", scores.get(Team.WHITE));
        model.put("blackScore", scores.get(Team.BLACK));
    }

    private void updateWinner() {
        Team winner = chessGame.getWinner();
        if (winner == Team.NONE) {
            model.put("winner", "무승부");
            return;
        }
        model.put("winner", winner);
    }
}
