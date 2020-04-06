package chess;

import chess.controller.ChessController;
import chess.controller.dto.*;
import chess.domain.player.Team;
import chess.domain.position.Position;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");
        ChessController chessController = new ChessController();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/chessGame", (req, res) -> {
            ResponseDto responseDto = chessController.getResponseDto();
            List<WebDto> boardDto = getBoardDto(responseDto.getBoard());
            List<WebDto> scoreDto = getScoreDto(responseDto.getScores());
            Map<String, Object> model = new HashMap<>();
            model.put("board", boardDto);
            model.put("score", scoreDto);
            return render(model, "chessGame.html");
        });

        post("/chessGame", (req, res) -> {
            RequestDto requestDto = getRequestDtoFrom(req);
            ResponseDto responseDto = chessController.run(requestDto);
            List<WebDto> boardDto = getBoardDto(responseDto.getBoard());
            List<WebDto> scoreDto = getScoreDto(responseDto.getScores());
            Map<String, Object> model = new HashMap<>();
            model.put("board", boardDto);
            model.put("score", scoreDto);
            model.put("message", responseDto.getMessage());
            return render(model, "chessGame.html");
        });

    }

    private static RequestDto getRequestDtoFrom(final Request req) {
        Command command = Command.of(req.queryParams("command"));
        List<String> parameters = Arrays.asList(req.queryParams("parameter").split("_"));
        return new RequestDto(command, parameters);
    }

    private static List<WebDto> getBoardDto(Map<Position, PieceDto> board) {
        return board.entrySet()
                .stream()
                .map(entry -> {
                    Position position = entry.getKey();
                    PieceDto pieceDto = entry.getValue();
                    String key = position.getFile().toString() + position.getRank().getRank();
                    String value = pieceDto.getTeam() + pieceDto.getPieceType();
                    return new WebDto(key, value);
                })
                .collect(Collectors.toList());
    }

    private static List<WebDto> getScoreDto(final Map<Team, Double> scores) {
        return scores.entrySet()
                .stream()
                .map(entry -> {
                    String key = entry.getKey().toString();
                    String value = String.valueOf(entry.getValue());
                    return new WebDto(key, value);
                })
                .collect(Collectors.toList());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
