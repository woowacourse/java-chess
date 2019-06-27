package chess.service;

import chess.dao.RoundInfoDao;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.PlayerType;
import chess.service.dto.HistoryDto;
import chess.service.dto.ResultDto;
import chess.service.dto.RoundInfoDto;

import java.sql.SQLDataException;
import java.util.List;

public class RoundInfoService {

    private final HistoryService historyService;
    private final RoundInfoDao roundInfoDao;

    private RoundInfoService() {
        historyService = HistoryService.getInstance();
        roundInfoDao = RoundInfoDao.getInstance();
    }

    private static class RoundInfoServiceHolder {
        private static final RoundInfoService INSTANCE = new RoundInfoService();
    }

    public static RoundInfoService getInstance() {
        return RoundInfoService.RoundInfoServiceHolder.INSTANCE;
    }

    public List<RoundInfoDto> selectAllGame(boolean isEnd) throws SQLDataException {
        return roundInfoDao.selectAllGame(isEnd);
    }

    public RoundInfoDto selectRoundInfo(int round) throws SQLDataException {
        return roundInfoDao.selectRoundInfo(round);
    }

    public int insertRoundInfo(String whiteName, String blackName) throws SQLDataException {
        return roundInfoDao.insertRoundInfo(whiteName, blackName);
    }

    public ResultDto getScore(int round) throws SQLDataException {
        HistoryDto historyDto = historyService.selectLastHistory(round);
        Board board = BoardFactory.create(historyDto.getRows());

        ResultDto resultDto = new ResultDto();
        resultDto.setWhiteScore(board.calculateScore(PlayerType.WHITE));
        resultDto.setBlackScore(board.calculateScore(PlayerType.BLACK));
        return resultDto;
    }

    public ResultDto selectGameResult(int round) throws SQLDataException {
        return roundInfoDao.selectGameResult(round);
    }
}
