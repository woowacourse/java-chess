package chess.service;

import chess.dao.boardpieces.InMemoryBoardPiecesDao;
import chess.dao.boardstatuses.InMemoryBoardStatusesDao;
import chess.domain.Camp;
import chess.domain.PieceInitializer;
import chess.dto.ChessBoardStatus;

public class ChessBoardFixture {

    public static final int FIXTURE_BOARD_ID = 1;

    public static ChessBoardService createServiceOfEmpty() {
        return new ChessBoardService(new InMemoryBoardPiecesDao(), new InMemoryBoardStatusesDao());
    }

    public static ChessBoardService createServiceOfBoard(Camp existingBoardTurn) {
        InMemoryBoardPiecesDao boardPiecesDao = new InMemoryBoardPiecesDao();
        boardPiecesDao.insertOrUpdate(FIXTURE_BOARD_ID, PieceInitializer.createPiecesWithPosition());

        InMemoryBoardStatusesDao boardStatusesDao = new InMemoryBoardStatusesDao();
        boardStatusesDao.insertOrUpdate(FIXTURE_BOARD_ID, new ChessBoardStatus(existingBoardTurn, false));

        return new ChessBoardService(boardPiecesDao, boardStatusesDao);
    }

    public static ChessBoardService createServiceOfOverBoard() {
        InMemoryBoardPiecesDao boardPiecesDao = new InMemoryBoardPiecesDao();
        boardPiecesDao.insertOrUpdate(FIXTURE_BOARD_ID, PieceInitializer.createPiecesWithPosition());

        InMemoryBoardStatusesDao boardStatusesDao = new InMemoryBoardStatusesDao();
        boardStatusesDao.insertOrUpdate(FIXTURE_BOARD_ID, new ChessBoardStatus(Camp.WHITE, true));

        return new ChessBoardService(boardPiecesDao, boardStatusesDao);
    }

}
