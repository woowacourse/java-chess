package chess.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.dao.boardpieces.InMemoryBoardPiecesDao;
import chess.dao.boardstatuses.InMemoryBoardStatusesDao;
import chess.domain.Camp;
import chess.domain.ChessBoard;
import chess.domain.PieceInitializer;
import chess.dto.ChessBoardStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardServiceTest {

    private ChessBoardService chessBoardService;

    @BeforeEach
    void setUp() {
        chessBoardService = new ChessBoardService(new InMemoryBoardPiecesDao(), new InMemoryBoardStatusesDao());
    }

    @DisplayName("없는 방 번호일 경우 새 보드, 기존 방 번호일 경우 해당 보드 정보를 불러온다.")
    @Test
    void 체스보드_로드() {
        Camp existingBoardTurn = Camp.BLACK;
        chessBoardService = createServiceOfBoard(existingBoardTurn);

        ChessBoard existing = chessBoardService.loadChessBoard(1);
        ChessBoard created = chessBoardService.loadChessBoard(2);

        assertThat(existing.status().getCurrentTurn())
                .isEqualTo(existingBoardTurn);
        assertThat(created.status().getCurrentTurn())
                .isEqualTo(Camp.WHITE);
    }

    private ChessBoardService createServiceOfBoard(Camp existingBoardTurn) {
        InMemoryBoardPiecesDao boardPiecesDao = new InMemoryBoardPiecesDao();
        boardPiecesDao.insertOrUpdate(1, PieceInitializer.createPiecesWithPosition());

        InMemoryBoardStatusesDao boardStatusesDao = new InMemoryBoardStatusesDao();
        boardStatusesDao.insertOrUpdate(1, new ChessBoardStatus(existingBoardTurn, false));

        return new ChessBoardService(boardPiecesDao, boardStatusesDao);
    }

    @DisplayName("주어진 보드 정보를 저장한다.")
    @Test
    void 체스보드_저장() {
        final ChessBoard chessBoard = new ChessBoard(1, PieceInitializer.createPiecesWithPosition(),
                new ChessBoardStatus(Camp.BLACK, false));

        assertDoesNotThrow(() -> chessBoardService.saveChessBoard(chessBoard));
    }

    @DisplayName("모든 실행 가능한 보드 아이디를 조회한다.")
    @Test
    void 실행가능_체스보드_아이디_조회() {
        chessBoardService = createServiceOfOverBoard();

        assertThat(chessBoardService.findAllNotOverBoardIds())
                .isEmpty();
    }

    private ChessBoardService createServiceOfOverBoard() {
        InMemoryBoardPiecesDao boardPiecesDao = new InMemoryBoardPiecesDao();
        boardPiecesDao.insertOrUpdate(1, PieceInitializer.createPiecesWithPosition());

        InMemoryBoardStatusesDao boardStatusesDao = new InMemoryBoardStatusesDao();
        boardStatusesDao.insertOrUpdate(1, new ChessBoardStatus(Camp.WHITE, true));

        return new ChessBoardService(boardPiecesDao, boardStatusesDao);
    }

}
