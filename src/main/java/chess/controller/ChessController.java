package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

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
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessController {
    public void run(BoardGenerator boardGenerator) {
        staticFileLocation("/static");

        ChessGame chessGame = new ChessGame();

        // 초기 페이지
        get("/", (request, response) -> render(new HashMap<>(), "index.html"));

        // 보드 출력
        get("/board", (request, response) -> {
            Map<String, Object> model = generateBoardModel(chessGame);
            return render(model, "board.html");
        });

        // 새로운 보드 생성
        post("/new", (request, response) -> {
            chessGame.start(boardGenerator);
            response.redirect("/board");
            return null;
        });

        // 기물 이동
        get("/move", (request, response) -> {
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
        });

        // 점수 보기
        get("/status", (request, response) -> {
            Score score = chessGame.status();

            Map<String, Object> model = generateBoardModel(chessGame);
            model.put("message", drawScoreSentence(score));

            return render(model, "board.html");
        });

        // 게임 종료
        get("/end", (request, response) -> {
            chessGame.end();
            response.redirect("/");
            return null;
        });
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
}
