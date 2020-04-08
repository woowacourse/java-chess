package chess.controller;

import chess.controller.dto.ResponseDto;
import chess.controller.dto.WebDto;
import chess.service.ChessService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static spark.Spark.get;

public class ChessWebController {

    private ChessService chessService = new ChessService();

    public void run() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ResponseDto responseDto = chessService.getRoomId();
            List<Long> roomId = responseDto.getRoomId();
            List<WebDto> roomIdDto = roomId.stream().map(id -> new WebDto(id.toString(), id)).collect(Collectors.toList());
            model.put("roomId", roomIdDto);
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
//
//        post("/restartChessGame", (req, res) -> {
//            String number = req.queryParams("id").trim();
//            Long id = Long.valueOf(number);
//            chessService.restart(id);
//            ResponseDto responseDto = chessService.getResponseDto(id);
//            List<WebDto> boardDto = getBoardDto(responseDto.getBoard());
//            List<WebDto> scoreDto = getScoreDto(responseDto.getScores());
//            WebDto turnDto = getTurnDto(responseDto.getTurn());
//            Map<String, Object> model = new HashMap<>();
//            model.put("board", boardDto);
//            model.put("score", scoreDto);
//            model.put("turn", turnDto);
//            model.put("id", id);
//            return render(model, "chessGame.html");
//        });
//
//        post("/loadChessGame", (req, res) -> {
//            String number = req.queryParams("id").trim();
//            Long id = Long.valueOf(number);
//            chessService.load(id);
//            ResponseDto responseDto = chessService.getResponseDto(id);
//            List<WebDto> boardDto = getBoardDto(responseDto.getBoard());
//            List<WebDto> scoreDto = getScoreDto(responseDto.getScores());
//            WebDto turnDto = getTurnDto(responseDto.getTurn());
//            Map<String, Object> model = new HashMap<>();
//            model.put("board", boardDto);
//            model.put("score", scoreDto);
//            model.put("turn", turnDto);
//            model.put("id", id);
//            return render(model, "chessGame.html");
//        });
//
//        post("/move", (req, res) -> {
//            String message = null;
//            Map<String, Object> model = new HashMap<>();
//            String number = req.queryParams("id").trim();
//            Long id = Long.valueOf(number);
//            List<String> parameters = new ArrayList<>(Arrays.asList(req.queryParams("parameter").split("_")));
//            try {
//                chessService.move(id, parameters);
//            } catch (IllegalArgumentException | UnsupportedOperationException e) {
//                model.put("error-message", e.getMessage());
//            }
//            ResponseDto responseDto = chessService.getResponseDto(id);
//            List<WebDto> boardDto = getBoardDto(responseDto.getBoard());
//            List<WebDto> scoreDto = getScoreDto(responseDto.getScores());
//            WebDto winnerDto = getTurnDto(responseDto.getWinner());
//            WebDto turnDto = getTurnDto(responseDto.getTurn());
//            model.put("board", boardDto);
//            model.put("score", scoreDto);
//            model.put("turn", turnDto);
//            model.put("message", responseDto.getMessage());
//            model.put("winner", winnerDto);
//            model.put("id", id);
//            return render(model, "chessGame.html");
//        });
//
//        post("/end", (req, res) -> {
//            String number = req.queryParams("id").trim();
//            Long id = Long.valueOf(number);
//            List<String> parameters = new ArrayList<>(Arrays.asList(req.queryParams("parameter").split("_")));
//            chessService.end(id, parameters);
//            List<WebDto> roomDto = getRoomDto(chessService.getRoomId());
//            Map<String, Object> model = new HashMap<>();
//            model.put("roomId", roomDto);
//            return render(model, "index.html");
//        });
//
//        get("/", (req, res) -> {
//            List<WebDto> roomDto = getRoomDto(chessService.getRoomId());
//            Map<String, Object> model = new HashMap<>();
//            model.put("roomId", roomDto);
//            return render(model, "index.html");
//        });
//    private List<WebDto> getRoomDto(List<Long> roomIds) {
//        if (Objects.isNull(roomIds)) {
//            return null;
//        }
//        return roomIds.stream()
//                .map(room -> {
//                    String key = String.valueOf(room);
//                    String value = String.valueOf(room);
//                    return new WebDto(key, value);
//                })
//                .collect(Collectors.toList());

//    }
//    private static WebDto getTurnDto(final Player turn) {
//        if (Objects.isNull(turn)) {
//            return null;
//        }
//        return new WebDto(turn.toString(), turn.toString());
//    }
//
//    private static List<WebDto> getBoardDto(Map<Position, PieceDto> board) {
//        return board.entrySet()
//                .stream()
//                .map(entry -> {
//                    Position position = entry.getKey();
//                    PieceDto pieceDto = entry.getValue();
//                    String key = position.getFile().toString() + position.getRank().getRank();
//                    String value = pieceDto.getTeam() + pieceDto.getPieceType();
//                    return new WebDto(key, value);
//                })
//                .collect(Collectors.toList());
//    }
//
//    private static List<WebDto> getScoreDto(final Map<Player, Double> scores) {
//        return scores.entrySet()
//                .stream()
//                .map(entry -> {
//                    String key = entry.getKey().toString();
//                    String value = String.valueOf(entry.getValue());
//                    return new WebDto(key, value);
//                })
//                .collect(Collectors.toList());
//    }
//
}