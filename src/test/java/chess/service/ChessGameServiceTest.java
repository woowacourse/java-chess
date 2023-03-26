package chess.service;

import chess.dao.chess.MockChessGameDao;
import chess.dao.chess.MockPieceDao;
import chess.dao.chess.PieceEntityHelper;
import chess.domain.board.ChessBoard;
import chess.domain.chess.CampType;
import chess.domain.chess.ChessGame;
import chess.entity.ChessGameEntity;
import chess.entity.PieceEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ChessGameServiceTest {

    @Test
    @DisplayName("사용자의 아이디에 해당하는 체스 게임이 존재하지 않으면 새 게임을 반환한다.")
    void getChessGame_empty() {
        // given
        final ChessGameService chessGameService = new ChessGameService(
                new MockChessGameDao(), new ChessBoardService(new MockPieceDao()));
        final ChessGame expected = new ChessGame(ChessBoardHelper.createMockBoard(), null);

        // when
        final ChessGame actual = chessGameService.getChessGame(2L);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("사용자의 아이디에 해당하는 체스 게임이 존재하면 해당 게임을 반환한다.")
    void getChessGame() {
        // given
        final Long userId = 1L;
        final Long chessGameId = 1L;
        final List<PieceEntity> pieceEntities = PieceEntityHelper.createPieceEntities(chessGameId);
        final MockPieceDao pieceDao = new MockPieceDao();
        pieceEntities.forEach(pieceDao::save);

        final MockChessGameDao chessGameDao = new MockChessGameDao();
        chessGameDao.save(new ChessGameEntity("WHITE", userId));

        final ChessGameService chessGameService = new ChessGameService(chessGameDao, new ChessBoardService(pieceDao));
        final ChessBoard mockProgressBoard = ChessBoardHelper.createMockProgressBoard();
        final ChessGame expected = new ChessGame(mockProgressBoard, CampType.WHITE);

        // when
        final ChessGame actual = chessGameService.getChessGame(userId);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }
}
