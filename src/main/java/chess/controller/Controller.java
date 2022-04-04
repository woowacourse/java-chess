package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.domain.boardstrategy.InitBoardStrategy;
import chess.domain.game.ChessGame;
import chess.domain.game.state.Play;
import chess.domain.game.state.State;
import chess.domain.game.state.attribute.StateType;
import chess.domain.piece.attribute.Team;
import chess.dto.BoardDto;
import chess.dto.CommandDto;
import chess.dto.PieceDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Controller {
    private final Map<String, Object> model = new HashMap<>();
    private ChessGame chessGame = new ChessGame(new InitBoardStrategy());
    private State state = new Play(chessGame);

    public void run() {
        staticFiles.location("/static");
        get("/", (req, res) -> getStartObject());
        post("/command", (req, res) -> {
            go(req.queryParams("command"));
            return getObject();
        });
        post("/end", (req, res) -> getEndObject());
    }

    private void go(String input) {
        try {
            state = state.execute(new CommandDto(input));
            model.put("error", "현재 에러 없음");
            model.put("result", "");
            if (state.getType() != StateType.PLAY) {
                model.put("result", "게임 종료. 결과를 확인하려면 end 버튼을 클릭하세요.");
            }
        } catch (IllegalArgumentException e) {
            model.put("error", e.getMessage());
        }
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

    private void updateTurn() {
        model.put("turn", chessGame.getTurn());
    }

    private void updatePieces() {
        List<PieceDto> pieces = new BoardDto(chessGame.getBoard()).getBoardWeb();
        model.put("pieces", pieces);
    }

    private void updateScore() {
        Map<Team, Double> scores = chessGame.getScoreOfTeams();
        model.put("whiteScore", scores.get(Team.WHITE));
        model.put("blackScore", scores.get(Team.BLACK));
    }

    private Object getEndObject() {
        updateScore();
        Team winner = chessGame.getWinner();
        model.put("winner", winner);
        return render(model, "end.html");
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
