package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.domain.board.Board;
import chess.domain.board.Score;
import chess.domain.board.generator.BoardGenerator;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.dto.PieceDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessController {
    public void run(BoardGenerator boardGenerator) {
        ChessGame chessGame = new ChessGame();

        get("/", (request, response) -> render(new HashMap<>(), "index.html"));
        get("/board", (request, response) -> renderBoard(chessGame));
        post("/new", (request, response) -> createNewBoard(boardGenerator, chessGame, response));
        get("/move", (request, response) -> move(chessGame, request));
        get("/status", (request, response) -> renderStatus(chessGame));
        get("/end", (request, response) -> end(chessGame, response));
    }

    private String renderBoard(ChessGame chessGame) {
        Map<String, Object> model = generateBoardModel(chessGame);
        return render(model, "board.html");
    }

    private Object createNewBoard(BoardGenerator boardGenerator, ChessGame chessGame, Response response) {
        chessGame.start(boardGenerator);
        response.redirect("/board");
        return null;
    }

    private String move(ChessGame chessGame, Request request) {
        List<String> inputs = new ArrayList<>();
        inputs.add("move");
        inputs.add(request.queryParams("from"));
        inputs.add(request.queryParams("to"));

        Map<String, Object> model = new HashMap<>();
        if (!chessGame.move(inputs)) {
            model.put("message", "잘못된 이동입니다.");
        }
        model.putAll(generateBoardModel(chessGame));

        return render(model, "board.html");
    }

    private String renderStatus(ChessGame chessGame) {
        Score score = chessGame.status();

        Map<String, Object> model = generateBoardModel(chessGame);
        model.put("message", drawScoreSentence(score));

        return render(model, "board.html");
    }

    private String drawScoreSentence(Score score) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry<Team, Double> entry : score.getValue().entrySet()) {
            stringBuilder.append(entry.getKey().name())
                    .append(": ")
                    .append(entry.getValue())
                    .append(" / ");
        }
        stringBuilder.append(drawWinner(score.findWinTeam()));

        return stringBuilder.toString();
    }

    private String drawWinner(Team team) {
        if (team == null) {
            return "무승부";
        }
        return String.format("승리 팀: %s", team);
    }

    private Object end(ChessGame chessGame, Response response) {
        chessGame.end();
        response.redirect("/");
        return null;
    }

    private Map<String, Object> generateBoardModel(ChessGame chessGame) {
        Board board = chessGame.getBoard();

        Map<String, Object> model = new HashMap<>();
        for (Entry<Position, Piece> entry : board.getValue().entrySet()) {
            model.put(entry.getKey().getName()
                    , new PieceDto(entry.getValue()));
        }
        model.put("turn", chessGame.getTurn());
        return model;
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
