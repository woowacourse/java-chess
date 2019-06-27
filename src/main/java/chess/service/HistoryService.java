package chess.service;

import chess.dao.HistoryDao;
import chess.dao.RoundInfoDao;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Point;
import chess.service.dto.HistoryDto;

public class HistoryService {

    private final HistoryDao historyDao;

    private HistoryService() {
        historyDao = HistoryDao.getInstance();
    }

    private static class HistoryServiceHolder {
        private static final HistoryService INSTANCE = new HistoryService();
    }

    public static HistoryService getInstance() {
        return HistoryServiceHolder.INSTANCE;
    }

    public HistoryDto selectLastHistory(int round) {
        return historyDao.selectLastHistory(round);
    }

    public HistoryDto movePiece(int round, Point prev, Point next) {
        HistoryDto historyDto = selectLastHistory(round);
        Board board = BoardFactory.create(historyDto.getRows());

        if (!board.move(prev, next)) {
            historyDto.setCanMove(false);
            return historyDto;
        }

        historyDto.setCanMove(true);
        historyDto.setRows(board.mappingBoardToString());
        historyDto.setTurn(historyDto.getTurn() + 1);

        historyDao.insertHistory(historyDto);

        if (board.isKingDead()) {
            historyDto.setKingDead(true);
            RoundInfoDao.getInstance().updateGameOver(round);
        }
        return historyDto;
    }

    public HistoryDto insertFirstHistory(int round) {
        HistoryDto historyDto = new HistoryDto();
        historyDto.setRound(round);
        historyDto.setRows(BoardFactory.getBasicArrange());
        historyDto.setTurn(0);
        historyDao.insertHistory(historyDto);
        return historyDto;
    }
}
