package chess;

import chess.controller.ChessWebController;
import chess.controller.dto.Command;
import chess.controller.dto.PieceDto;
import chess.controller.dto.RequestDto;
import chess.controller.dto.WebDto;
import chess.domain.player.Team;
import chess.domain.position.Position;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;
import java.util.stream.Collectors;

import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");
//        ChessController chessController = new ChessController();
//
//        get("/", (req, res) -> {
//            ResponseDto responseDto = chessController.run();
//            List<WebDto> roomDto = getRoomDto(responseDto.getRoomId());
//            Map<String, Object> model = new HashMap<>();
//            model.put("roomId", roomDto);
//            return render(model, "index.html");
//        });
//
//        post("/", (req, res) -> {
//            String number = req.queryParams("id");
//            Long id = Long.valueOf(number);
//            Map<String, Object> model = new HashMap<>();
//            RequestDto requestDto = getRequestDtoFrom(req);
//            ResponseDto responseDto = chessController.run(id, requestDto);
//
//            List<WebDto> roomDto = getRoomDto(responseDto.getRoomId());
//            model.put("roomId", roomDto);
//            return render(model, "index.html");
//        });
//
//        get("/chessGame", (req, res) -> {
//            String number = req.queryParams("id");
//            Long id = Long.valueOf(number);
//            ResponseDto responseDto = chessController.getResponseDto(id);
//            List<WebDto> boardDto = getBoardDto(responseDto.getBoard());
//            List<WebDto> scoreDto = getScoreDto(responseDto.getScores());
//            Map<String, Object> model = new HashMap<>();
//            model.put("board", boardDto);
//            model.put("score", scoreDto);
//            return render(model, "chessGame.html");
//        });
//
//        post("/chessGame", (req, res) -> {
//            String number = req.queryParams("id");
//            Long id = Long.valueOf(number.trim());
//            RequestDto requestDto = getRequestDtoFrom(req);
//            ResponseDto responseDto = chessController.run(id, requestDto);
//            List<WebDto> boardDto = getBoardDto(responseDto.getBoard());
//            List<WebDto> scoreDto = getScoreDto(responseDto.getScores());
//            WebDto winnerDto = getTurnDto(responseDto.getWinner());
//            WebDto turnDto = getTurnDto(responseDto.getTurn());
//            Map<String, Object> model = new HashMap<>();
//            model.put("board", boardDto);
//            model.put("score", scoreDto);
//            model.put("turn", turnDto);
//            model.put("message", responseDto.getMessage());
//            model.put("winner", winnerDto);
//            model.put("id", id);
//            return render(model, "chessGame.html");
//        });
//
//        post("/createChessGame", (req, res) -> {
//            Long id = chessController.createChessGame();
//            Map<String, Object> model = new HashMap<>();
//            model.put("id", id);
//            return render(model, "create.html");
//        });
        ChessWebController chessWebController = new ChessWebController();
        chessWebController.run();

    }

    private static WebDto getTurnDto(final Team turn) {
        if (Objects.isNull(turn)) {
            return null;
        }
        return new WebDto(turn.toString(), turn.toString());
    }

    private static List<WebDto> getRoomDto(final List<Long> roomId) {
        if (Objects.isNull(roomId)) {
            return null;
        }
        return roomId.stream()
                .map(room -> {
                    String key = String.valueOf(room);
                    String value = String.valueOf(room);
                    return new WebDto(key, value);
                })
                .collect(Collectors.toList());
    }

    private static RequestDto getRequestDtoFrom(final Request req) {
        Command command = Command.of(req.queryParams("command"));
        List<String> parameters = new ArrayList<>(Arrays.asList(req.queryParams("parameter").split("_")));
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
