package chess.service;

import chess.dao.chess.MockChessGameDao;
import chess.dao.chess.MockPieceDao;
import chess.dao.chess.PieceEntityHelper;
import chess.domain.board.ChessBoard;
import chess.domain.chess.CampType;
import chess.domain.chess.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.move.Position;
import chess.entity.ChessGameEntity;
import chess.entity.PieceEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ChessGameServiceTestHandler {

    @Test
    @DisplayName("사용자의 아이디에 해당하는 체스 게임이 존재하지 않으면 새 게임을 반환한다.")
    void getOrCreateChessGame_empty() {
        // given
        final MockChessGameDao chessGameDao = new MockChessGameDao();
        final ChessGameService chessGameService = new ChessGameService(
                chessGameDao, new ChessBoardService(new MockPieceDao()));
        final Map<Position, Piece> expected = new ChessGame(CampType.WHITE).getChessBoard();

        // when
        final Map<Position, Piece> actual = chessGameService.getOrCreateChessGame(2L)
                .getChessBoard();

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("사용자의 아이디에 해당하는 체스 게임이 존재하면 해당 게임을 반환한다.")
    void getOrCreateChessGame() {
        // given
        final long userId = 1L;
        final ChessGameService chessGameService = getChessGameService(userId);
        final ChessBoard mockProgressBoard = ChessBoardHelper.createMockProgressBoard();
        final Map<Position, Piece> expected = new ChessGame(mockProgressBoard, CampType.WHITE)
                .getChessBoard();

        // when
        final Map<Position, Piece> actual = chessGameService.getOrCreateChessGame(userId)
                .getChessBoard();

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("사용자의 아이디를 기준으로 체스 게임을 조회한다")
    void getChessGameId() {
        // given
        final long userId = 1L;
        final ChessGameService chessGameService = getChessGameService(userId);

        // when
        long chessGameId = chessGameService.getChessGameId(userId);

        // then
        assertThat(chessGameId)
                .isEqualTo(1L);
    }

    @Test
    @DisplayName("게임을 플레이하면, 시작 위치에 존재하는 체스말을 제거한다")
    void play_deletePieces() {
        // given
        final long userId = 1L;
        final ChessGameService chessGameService = getChessGameService(userId);

        // when
        chessGameService.play(1L, new Position(1, 0), new Position(2, 0));

        // then
        final Map<Position, Piece> expected = new HashMap<>(chessGameService.getOrCreateChessGame(userId).getChessBoard());
        expected.remove(new Position(1, 0));
        final Map<Position, Piece> actual = chessGameService.getOrCreateChessGame(1L).getChessBoard();

        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("게임을 플레이하면, 체스말 정보를 바탕으로 체스말을 저장한다")
    void play_savePieces() {
        // given
        final long userId = 1L;
        final ChessGameService chessGameService = getChessGameService(userId);

        // when
        chessGameService.play(1L, new Position(1, 1), new Position(2, 1));

        // then
        final long chessGameId = chessGameService.getChessGameId(1L);
        assertThat(chessGameId)
                .isEqualTo(1L);
    }

    @Test
    @DisplayName("게임을 플레이하면, 체스 게임 아이디에 해당하는 진영 정보를 업데이트한다.")
    void play_updateCurrentCamp() {
        // given
        final long userId = 1L;
        final ChessGameService chessGameService = getChessGameService(userId);
        final CampType changedCamp = CampType.BLACK;

        // when
        chessGameService.play(1L, new Position(1, 1), new Position(2, 1));

        // then
        final ChessGame chessGame = chessGameService.getOrCreateChessGame(1L);
        final CampType campType = chessGame.getCurrentCamp();
        assertThat(campType)
                .isEqualTo(changedCamp);
    }

    @Test
    @DisplayName("사용자의 아이디에 해당하는 체스 기물과 체스판 정보를 제거한다")
    void clear() {
        // given
        final long userId = 1L;
        final ChessGameService chessGameService = getChessGameService(userId);

        // when
        chessGameService.clear(userId);

        // then
        final Map<Position, Piece> actual = chessGameService.getOrCreateChessGame(userId).getChessBoard();
        final Map<Position, Piece> expected = new ChessGame(CampType.WHITE).getChessBoard();
        assertThat(actual)
                .isEqualTo(expected);
    }

    private ChessGameService getChessGameService(final long userId) {
        final MockChessGameDao chessGameDao = new MockChessGameDao();
        final long chessGameId = chessGameDao.insert(new ChessGameEntity(1L, "WHITE", userId));
        final MockPieceDao pieceDao = getMockPieceDao(chessGameId);
        return new ChessGameService(chessGameDao, new ChessBoardService(pieceDao));
    }

    private MockPieceDao getMockPieceDao(final long chessGameId) {
        final List<PieceEntity> pieceEntities = PieceEntityHelper.createPieceEntities(chessGameId);
        final MockPieceDao pieceDao = new MockPieceDao();
        pieceEntities.forEach(pieceDao::insert);
        return pieceDao;
    }
}
