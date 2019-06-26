package chess.service;

import chess.dao.RoundInfoDao;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.PlayerType;
import chess.dto.HistoryDto;
import chess.dto.ResultDto;
import chess.dto.RoundInfoDto;

import java.sql.SQLDataException;
import java.util.List;

public class RoundInfoService {
    private RoundInfoService() {
    }

    private static class RoundInfoServiceHolder {
        private static final RoundInfoService INSTANCE = new RoundInfoService();
    }

    public static RoundInfoService getInstance() {
        return RoundInfoService.RoundInfoServiceHolder.INSTANCE;
    }

    public List<RoundInfoDto> selectAllGame(boolean isEnd) throws SQLDataException {
        return RoundInfoDao.getInstance().selectAllGame(isEnd);
    }

    public RoundInfoDto selectRoundInfo(int round) throws SQLDataException {
        return RoundInfoDao.getInstance().selectRoundInfo(round);
    }

    public int insertRoundInfo(String whiteName, String blackName) throws SQLDataException {
        return RoundInfoDao.getInstance().insertRoundInfo(whiteName, blackName);
    }

    public ResultDto getScore(int round) throws SQLDataException {
        HistoryDto historyDto = HistoryService.getInstance().selectLastHistory(round);
        Board board = BoardFactory.create(historyDto.getRows());

        ResultDto resultDto = new ResultDto();
        resultDto.setWhiteScore(board.calculateScore(PlayerType.WHITE));
        resultDto.setBlackScore(board.calculateScore(PlayerType.BLACK));
        return resultDto;
    }

    public ResultDto selectGameResult(int round) throws SQLDataException {
        return RoundInfoDao.getInstance().selectGameResult(round);
    }
}
