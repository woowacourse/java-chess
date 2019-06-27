package chess;

import chess.domain.*;
import chess.dto.GameDto;
import chess.dto.NavigatorDto;
import chess.dto.PieceDto;
import chess.dto.UserDto;
import chess.service.GameService;
import chess.service.PieceService;
import chess.service.UserService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebUIChessController {
    public static Map<String, Object> getNotEndGames() {
        Map<String, Object> model = new HashMap<>();
        model.put("notEndGames", GameService.getNotEndGames());
        return model;
    }

    public static String generateGameAndUsers(Request req, Response res) {
        int gameId = GameService.addGame();
        UserService.addUsers(req, gameId);
        res.redirect("/" + gameId);
        return "";
    }

    public static Map<String, Object> getGameElements(Request req) {
        int gameId = Integer.parseInt(req.params("gameId"));

        List<Row> rows = Row.getRows();
        List<Column> columns = Column.getColumns();
        Collections.reverse(rows);

        GameDto gameDto = GameService.findById(gameId);
        List<UserDto> userDtos = UserService.findByGameId(gameId);

        Board board = new Board(gameDto.getTurn());
        req.session().attribute("board", board);

        List<PieceDto> pieceDtos = PieceService.findByGameId(gameId);
        board = PieceService.setBoard(board, pieceDtos, gameId);

        if (gameDto.isEnd() == true) {
            ResultCalculator resultCalculator = new ResultCalculator(board);
            Result result = new Result(resultCalculator.calculateResult());
            throw new RuntimeException(String.format("[최종 스코어] 백 : 흑 = %.1f : %.1f 게임이 종료되었습니다.",
                    result.getWhiteResult(), result.getBlackResult()));
        }

        Gson gson = new Gson();
        Map<String, Object> model = new HashMap<>();
        model.put("gameId", gameId);
        model.put("rows", rows);
        model.put("columns", columns);
        model.put("whiteUser", userDtos.get(0));
        model.put("blackUser", userDtos.get(1));
        model.put("whiteTurn", gameDto.getTurn() == Aliance.WHITE);
        model.put("blackTurn", gameDto.getTurn() == Aliance.BLACK);
        model.put("pieces", gson.toJson(board.getPieces()));
        return model;
    }

    public static String movePiece(Request req, Response res) {
        int gameId = Integer.parseInt(req.params("gameId"));
        Board board = req.session().attribute("board");

        String start = req.queryParams("start");
        String end = req.queryParams("end");

        board.movePiece(start, end);
        Aliance nextTurn = board.switchTurn();

        PieceService.updatePiece(new NavigatorDto(gameId, start, end));

        boolean isEnd = !board.isKingAlive(nextTurn);
        GameService.updateGame(new GameDto(gameId, isEnd, nextTurn.getTeamId()));

        res.redirect("/" + gameId);
        return "";
    }
}
