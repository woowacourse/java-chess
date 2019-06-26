package chess.service;

import chess.dao.HistoryDao;
import chess.dao.RoundInfoDao;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Point;
import chess.dto.HistoryDto;

import java.sql.SQLDataException;

public class HistoryService {
    private HistoryService() {
    }

    private static class HistoryServiceHolder {
        private static final HistoryService INSTANCE = new HistoryService();
    }
    public static HistoryService getInstance() {
        return HistoryServiceHolder.INSTANCE;
    }

    public HistoryDto selectLastHistory(int round) throws SQLDataException {
        return HistoryDao.getInstance().selectLastHistory(round);
    }

    public HistoryDto movePiece(int round, Point prev, Point next) throws SQLDataException {
        HistoryDto historyDto = selectLastHistory(round);
        Board board = BoardFactory.create(historyDto.getRows());

        if (!board.move(prev, next)) {
            historyDto.setCanMove(false);
            return historyDto;
        }

        historyDto.setCanMove(true);
        historyDto.setRows(board.mappingBoardToString());
        historyDto.setTurn(historyDto.getTurn() + 1);

        int result = HistoryDao.getInstance().insertHistory(historyDto);

        if (board.isKingDead()) {
            historyDto.setKingDead(true);
            int updateResult = RoundInfoDao.getInstance().updateGameOver(round);
            // TODO: 2019-06-25 Redirect result page?
        }
        return historyDto;
    }

    public HistoryDto insertFirstHistory(int round) throws SQLDataException {
        HistoryDto historyDto = new HistoryDto();
        historyDto.setRound(round);
        historyDto.setRows(BoardFactory.getBasicArrange());
        historyDto.setTurn(0);
        int historyResult = HistoryDao.getInstance().insertHistory(historyDto);
        return historyDto;
    }
}
