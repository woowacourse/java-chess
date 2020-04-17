package chess.controller;

import chess.command.Command;
import chess.controller.dto.RequestDto;
import chess.controller.dto.ResponseDto;
import chess.controller.dto.WebDto;
import chess.dao.JdbcChessDao;
import chess.service.ChessService;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;
import java.util.stream.Collectors;

import static spark.Spark.get;
import static spark.Spark.post;

public class ChessWebController {

    private ChessService chessService = new ChessService(new JdbcChessDao());

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void run() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ResponseDto responseDto = chessService.getRoomId();
            List<WebDto> roomIdDto = makeRoomIdDto(responseDto);
            model.put("roomId", roomIdDto);
            return render(model, "index.html");
        });

        post("/createChessGame", (req, res) -> {
            ResponseDto responseDto = chessService.start();
            Map<String, Object> model = new HashMap<>();
            model.put("id", responseDto.getId());
            return render(model, "create.html");
        });

        post("/loadChessGame", (req, res) -> {
            RequestDto requestDto = makeRequestDto(req);
            ResponseDto responseDto = chessService.load(requestDto);
            List<WebDto> boardDto = makeBoardDto(responseDto);
            List<WebDto> scoreDto = makeScoreDto(responseDto);
            WebDto turnDto = makeTurnDto(responseDto);
            Map<String, Object> model = new HashMap<>();
            model.put("board", boardDto);
            model.put("score", scoreDto);
            model.put("turn", turnDto);
            model.put("id", responseDto.getId());
            return render(model, "chessGame.html");
        });

        post("/restartChessGame", (req, res) -> {
            RequestDto requestDto = makeRequestDto(req);
            ResponseDto responseDto = chessService.restart(requestDto);
            List<WebDto> boardDto = makeBoardDto(responseDto);
            List<WebDto> scoreDto = makeScoreDto(responseDto);
            WebDto turnDto = makeTurnDto(responseDto);
            Map<String, Object> model = new HashMap<>();
            model.put("board", boardDto);
            model.put("score", scoreDto);
            model.put("turn", turnDto);
            model.put("id", requestDto.getId());
            return render(model, "chessGame.html");
        });


        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            RequestDto requestDto = makeRequestDto(req);
            ResponseDto responseDto = chessService.move(requestDto);
            List<WebDto> boardDto = makeBoardDto(responseDto);
            List<WebDto> scoreDto = makeScoreDto(responseDto);
            WebDto winnerDto = makeWinnerDto(responseDto);
            WebDto turnDto = makeTurnDto(responseDto);
            model.put("board", boardDto);
            model.put("score", scoreDto);
            model.put("turn", turnDto);
            model.put("winner", winnerDto);
            model.put("id", responseDto.getId());
            model.put("message", responseDto.getMessage());
            return render(model, "chessGame.html");
        });

        post("/end", (req, res) -> {
            RequestDto requestDto = makeRequestDto(req);
            ResponseDto responseDto = chessService.end(requestDto);
            List<WebDto> roomDto = makeRoomIdDto(responseDto);
            Map<String, Object> model = new HashMap<>();
            model.put("roomId", roomDto);
            return render(model, "index.html");
        });
    }

    private WebDto makeWinnerDto(final ResponseDto responseDto) {
        if (Objects.nonNull(responseDto.getWinner())) {
            return new WebDto(responseDto.getWinner().toString(), responseDto.getWinner().toString());
        }
        return null;
    }

    private RequestDto makeRequestDto(final Request req) {
        return new RequestDto(Command.of(req.queryParams("command")),
                new ArrayList<>(Arrays.asList(req.queryParams("parameter").split("_"))),
                Long.parseLong(req.queryParams("id").trim()));
    }

    private List<WebDto> makeRoomIdDto(final ResponseDto responseDto) {
        return responseDto.getRoomId()
                .stream()
                .map(id -> new WebDto(id.toString(), id))
                .collect(Collectors.toList());
    }

    private WebDto makeTurnDto(final ResponseDto responseDto) {
        return new WebDto(responseDto.getTurn().toString(), responseDto.getTurn().toString());
    }

    private List<WebDto> makeScoreDto(final ResponseDto responseDto) {
        return responseDto.getStatus().entrySet().stream().map(entry ->
                new WebDto(entry.getKey().toString(), entry.getValue().toString()))
                .collect(Collectors.toList());
    }

    private List<WebDto> makeBoardDto(final ResponseDto responseDto) {
        return responseDto.getBoard().entrySet().stream().map(entry ->
                new WebDto(entry.getKey().getName(), entry.getValue()))
                .collect(Collectors.toList());
    }
}