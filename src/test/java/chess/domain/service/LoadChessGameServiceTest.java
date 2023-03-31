package chess.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.LoadChessGameService;
import chess.domain.game.state.ChessGame;
import chess.domain.piece.Camp;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.repository.BoardDao;
import chess.domain.repository.PieceDao;
import chess.domain.repository.entity.PieceEntity;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LoadChessGameServiceTest {
    private BoardDao memoryBoardDao;
    private PieceDao memoryPieceDao;

    @BeforeEach
    void setMemoryDao() {
        memoryBoardDao = new MemoryBoardDao();
        memoryPieceDao = new MemoryPieceDao();
    }

    @Test
    @DisplayName("게임의 존재 여부를 확인할 수 있다.")
    void isGameExistTest() {

        memoryBoardDao.saveBoard("WHITE");
        LoadChessGameService loadChessGameService = new LoadChessGameService(memoryBoardDao, memoryPieceDao);

        boolean gameExist = loadChessGameService.isGameExist();

        assertThat(gameExist).isTrue();
    }

    @Test
    @DisplayName("존재하는 게임을 불러올 수 있다.")
    void loadExistGameTest() {
        BoardDao memoryBoardDao = new MemoryBoardDao();
        PieceDao memoryPieceDao = new MemoryPieceDao();
        memoryBoardDao.saveBoard("WHITE");
        memoryPieceDao.savePieces(List.of(
                new PieceEntity("a2", "PAWN", "WHITE", 1L),
                new PieceEntity("b2", "PAWN", "WHITE", 1L),
                new PieceEntity("c2", "PAWN", "WHITE", 1L)
        ));
        LoadChessGameService loadChessGameService = new LoadChessGameService(memoryBoardDao, memoryPieceDao);

        ChessGame chessGame = loadChessGameService.loadExistGame();
        Map<Position, Piece> piecesPosition = chessGame.getPiecesPosition();
        assertThat(piecesPosition).containsEntry(
                Position.valueOf("a2"), new Pawn(Camp.WHITE)
        );

    }

    @Test
    @DisplayName("게임이 존재하지 않으면 불러올 수 없다.")
    void loadExistGameFailTest() {
        BoardDao memoryBoardDao = new MemoryBoardDao();
        PieceDao memoryPieceDao = new MemoryPieceDao();
        LoadChessGameService loadChessGameService = new LoadChessGameService(memoryBoardDao, memoryPieceDao);

        assertThatThrownBy(loadChessGameService::loadExistGame)
                .isInstanceOf(IllegalStateException.class);
    }
}

