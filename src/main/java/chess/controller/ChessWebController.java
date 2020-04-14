package chess.controller;

import chess.controller.dto.ChessWebIndexDto;
import chess.controller.dto.ChessWebResponseDto;
import chess.controller.dto.ResponseDto;
import chess.domain.position.Position;
import chess.service.ChessService;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.LongConsumer;
import java.util.stream.Collectors;

import static spark.Spark.get;
import static spark.Spark.post;

public class ChessWebController {

    private ChessService chessService = new ChessService();

    public void run() {
        get("/", (req, res) -> {
            ChessWebIndexDto chessWebIndexDto = ChessWebIndexDto.of(chessService.getRoomId());
            return render(chessWebIndexDto, "index.html");
        });

        post("/createChessGame", (req, res) -> {
            Long id = chessService.createGame();
            Map<String, Object> model = new HashMap<>();
            model.put("id", id);
            return render(model, "create.html");
        });

        post("/restart", (req, res) -> fetchGame(req, chessService::restart));

        post("/load", (req, res) -> fetchGame(req, chessService::load));

        post("/move", (req, res) -> {
            String message = null;
            String number = req.queryParams("id").trim();
            Long id = Long.valueOf(number);
            List<String> parameters = new ArrayList<>(Arrays.asList(req.queryParams("parameter").split("_")));
            try {
                chessService.move(id, parameters);
            } catch (IllegalArgumentException | UnsupportedOperationException e) {
                message = e.getMessage();
            }
            ResponseDto responseDto = chessService.getResponseDto(id);
            ChessWebResponseDto chessWebResponseDto = ChessWebResponseDto.of(id, responseDto, message);
            return render(chessWebResponseDto, "chessGame.html");
        });

        post("/save", (req, res) -> endGame(req, chessService::save));

        post("/end", (req, res) -> endGame(req, chessService::remove));

        get("/movable/:id/:position", (req, res) -> {
            Long id = Long.valueOf(req.params(":id"));
            Position position = Position.of(req.params(":position"));
            List<Position> positions = chessService.getMovablePositions(id, position);
            return positions.stream().map(Position::getName).collect(Collectors.joining(","));
        });
    }

    private String fetchGame(Request req, LongConsumer function) {
        String number = req.queryParams("id").trim();
        Long id = Long.valueOf(number);
        function.accept(id);
        ChessWebResponseDto chessWebResponseDto = getGameResponseDto(id);
        return render(chessWebResponseDto, "chessGame.html");
    }

    private String endGame(Request req, LongConsumer function) {
        String number = req.queryParams("id").trim();
        Long id = Long.valueOf(number);
        function.accept(id);
        ChessWebIndexDto chessWebIndexDto = ChessWebIndexDto.of(chessService.getRoomId());
        return render(chessWebIndexDto, "index.html");
    }

    private ChessWebResponseDto getGameResponseDto(Long id) {
        ResponseDto responseDto = chessService.getResponseDto(id);
        return ChessWebResponseDto.of(id, responseDto);
    }

    private static String render(Object object, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(object, templatePath));
    }
}
