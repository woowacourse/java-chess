package chess.controller;

import chess.controller.dto.PieceDto;
import chess.controller.dto.ResponseDto;
import chess.controller.dto.WebDto;
import chess.domain.player.Team;
import chess.domain.position.Position;
import chess.service.ChessService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static spark.Spark.get;
import static spark.Spark.post;

public class ChessWebController {

    private ChessService chessService = new ChessService();

    public void run() {
        get("/", (req, res) -> {
            List<WebDto> roomDto = getRoomDto(chessService.getRoomId());
            Map<String, Object> model = new HashMap<>();
            model.put("roomId", roomDto);
            return render(model, "index.html");
        });

        post("/createChessGame", (req, res) -> {
            Long id = chessService.createGame();
            Map<String, Object> model = new HashMap<>();
            model.put("id", id);
            return render(model, "create.html");
        });

        post("/restart", (req, res) -> {
            String number = req.queryParams("id").trim();
            Long id = Long.valueOf(number);
            chessService.restart(id);
            ResponseDto responseDto = chessService.getResponseDto(id);
            List<WebDto> boardDto = getBoardDto(responseDto.getBoard());
            List<WebDto> scoreDto = getScoreDto(responseDto.getScores());
            WebDto turnDto = getTurnDto(responseDto.getTurn());
            Map<String, Object> model = new HashMap<>();
            model.put("board", boardDto);
            model.put("score", scoreDto);
            model.put("turn", turnDto);
            model.put("id", id);
            return render(model, "chessGame.html");
        });

        post("/load", (req, res) -> {
            String number = req.queryParams("id").trim();
            Long id = Long.valueOf(number);
            chessService.load(id);
            ResponseDto responseDto = chessService.getResponseDto(id);
            List<WebDto> boardDto = getBoardDto(responseDto.getBoard());
            List<WebDto> scoreDto = getScoreDto(responseDto.getScores());
            WebDto turnDto = getTurnDto(responseDto.getTurn());
            Map<String, Object> model = new HashMap<>();
            model.put("board", boardDto);
            model.put("score", scoreDto);
            model.put("turn", turnDto);
            model.put("id", id);
            return render(model, "chessGame.html");
        });

        post("/move", (req, res) -> {
            String message = null;
            Map<String, Object> model = new HashMap<>();
            String number = req.queryParams("id").trim();
            Long id = Long.valueOf(number);
            List<String> parameters = new ArrayList<>(Arrays.asList(req.queryParams("parameter").split("_")));
            try {
                chessService.move(id, parameters);
            } catch (IllegalArgumentException | UnsupportedOperationException e) {
                model.put("error-message", e.getMessage());
            }
            ResponseDto responseDto = chessService.getResponseDto(id);
            List<WebDto> boardDto = getBoardDto(responseDto.getBoard());
            List<WebDto> scoreDto = getScoreDto(responseDto.getScores());
            WebDto winnerDto = getTurnDto(responseDto.getWinner());
            WebDto turnDto = getTurnDto(responseDto.getTurn());
            model.put("board", boardDto);
            model.put("score", scoreDto);
            model.put("turn", turnDto);
            model.put("message", responseDto.getMessage());
            model.put("winner", winnerDto);
            model.put("id", id);
            return render(model, "chessGame.html");
        });

        post("/save", (req, res) -> {
            String number = req.queryParams("id").trim();
            Long id = Long.valueOf(number);
            chessService.save(id);
            List<WebDto> roomDto = getRoomDto(chessService.getRoomId());
            Map<String, Object> model = new HashMap<>();
            model.put("roomId", roomDto);
            return render(model, "index.html");
        });

        post("/end", (req, res) -> {
            String number = req.queryParams("id").trim();
            Long id = Long.valueOf(number);
            chessService.remove(id);
            List<WebDto> roomDto = getRoomDto(chessService.getRoomId());
            Map<String, Object> model = new HashMap<>();
            model.put("roomId", roomDto);
            return render(model, "index.html");
        });

        get("/movable/:id/:position", (req, res) -> {
            Long id = Long.valueOf(req.params(":id"));
            Position position = Position.of(req.params(":position"));
            List<Position> positions = chessService.getMovablePositions(id, position);
            return positions.stream().map(Position::getName).collect(Collectors.joining(","));
        });

    }

    private List<WebDto> getRoomDto(List<Long> roomIds) {
        return roomIds.stream()
                .map(room -> {
                    String key = String.valueOf(room);
                    String value = String.valueOf(room);
                    return new WebDto(key, value);
                })
                .collect(Collectors.toList());
    }

    private static WebDto getTurnDto(final Team turn) {
        return new WebDto(turn.toString(), turn.toString());
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
