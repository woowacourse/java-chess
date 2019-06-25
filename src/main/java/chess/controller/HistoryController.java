package chess.controller;

import chess.dao.HistoryDao;
import chess.dto.HistoryDto;
import chess.dto.RoundInfoDto;
import chess.service.HistoryService;
import spark.Request;
import spark.Response;

import java.sql.SQLDataException;
import java.util.List;

import static chess.WebUIChessApplication.nullable;

public class HistoryController {
    private HistoryController() {
    }

    public static List<RoundInfoDto> selectAllUnfinishedGame(Request request, Response response) throws SQLDataException {
        return HistoryService.getInstance().selectAllUnfinishedGame();
    }

    public static HistoryDto selectUnfinishedGame(Request request, Response response) throws SQLDataException {
        int round = Integer.parseInt(nullable(request.params(":round")));
        return HistoryDao.getInstance().selectLastHistory(round);
    }
}
