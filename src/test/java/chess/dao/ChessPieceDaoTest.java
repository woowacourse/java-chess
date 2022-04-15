package chess.dao;

import static org.assertj.core.api.Assertions.assertThatNoException;

import chess.domain.ChessBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessPieceDaoTest {

    ChessPieceDao chessPieceDao = new ChessPieceDao();

    @Test
    @DisplayName("DB에 저장된 체스 피스를 전체를 저장한다.")
    void save() {
        ChessBoard chessBoard = ChessBoard.create();
        ChessGameDao chessGameDao = new ChessGameDao();
        int chessGameId = chessGameDao.findId();

        assertThatNoException().isThrownBy(() -> chessPieceDao.saveAll(chessGameId, chessBoard.getBoard()));
    }

    @Test
    @DisplayName("DB에 저장된 체스 피스를 모두 조회한다.")
    void findAll() {
        assertThatNoException().isThrownBy(() -> chessPieceDao.findAll());
    }

    @Test
    @DisplayName("DB에 저장된 체스 피스의 위치를 업데이트한다.")
    void updateChessBoard() {
        assertThatNoException().isThrownBy(() -> chessPieceDao.update(2, 'a', 4, 'a'));
    }

    @Test
    @DisplayName("체스 보드의 모든 체스 피스를 DB에서 제거한다.")
    void deleteAll() {
        assertThatNoException().isThrownBy(chessPieceDao::deleteAll);
    }

    @Test
    @DisplayName("특정 위치의 체스 피스를 DB에서 제거한다.")
    void delete() {
        assertThatNoException().isThrownBy(() -> chessPieceDao.deleteByPosition(2, 'a'));
    }
}
