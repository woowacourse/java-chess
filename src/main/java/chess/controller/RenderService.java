package chess.controller;

import chess.dao.BoardDao;
import chess.domain.game.ChessGame;
import chess.domain.piece.attribute.Team;
import chess.dto.ChessGameDto;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class RenderService {
    private static final String DRAW_MESSAGE = "무승부";
    private static final String END_MESSAGE = "게임 종료. 결과를 확인하려면 end 버튼을 클릭하세요.";
    private static final String EMPTY = "";

    public Object renderStart(Map<String, Object> model) {
        return render(model, "index.html");
    }

    public Object renderGame(Map<String, Object> model, ChessGame chessGame) {
        Map<Team, Double> scores = chessGame.getScoreOfTeams();
        model.put("whiteScore", scores.get(Team.WHITE));
        model.put("blackScore", scores.get(Team.BLACK));
        model.put("turn", chessGame.getTurn());
        model.put("pieces", new ChessGameDto(chessGame).getBoardWeb());
        model.put("result", getResult(chessGame));

        return render(model, "chessGame.html");
    }

    public Object renderEnd(Map<String, Object> model, ChessGame chessGame) {
        Map<Team, Double> scores = chessGame.getScoreOfTeams();
        model.put("whiteScore", scores.get(Team.WHITE));
        model.put("blackScore", scores.get(Team.BLACK));
        model.put("winner", getWinner(chessGame));

        new BoardDao().delete((String) model.get("name"));

        return render(model, "end.html");
    }

    public void initWeb(Map<String, Object> model, String name) {
        model.put("name", name);
        model.put("error", EMPTY);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private String getResult(ChessGame chessGame) {
        if (chessGame.isFinished()) {
            return END_MESSAGE;
        }
        return EMPTY;
    }

    private String getWinner(ChessGame chessGame) {
        Team winner = chessGame.getWinner();
        if (winner == Team.NONE) {
            return DRAW_MESSAGE;
        }
        return winner.name();
    }
}
