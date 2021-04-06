package chess.domain.chess;

import java.sql.SQLException;
import java.util.Optional;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.piece.PieceDAO;

public class ChessDAOTest {

    private final ChessDAO chessDAO = new ChessDAO();
    private final PieceDAO pieceDAO = new PieceDAO();
    private Long chessId;

    @BeforeEach
    void setUp() throws SQLException {
        Optional<Long> optionalPreviousChessId = chessDAO.findChessId();
        if (optionalPreviousChessId.isPresent()) {
            final Long previousChessId = optionalPreviousChessId.get();
            pieceDAO.delete(previousChessId);
            chessDAO.delete(previousChessId);
        }

        chessDAO.insert();
        chessId = chessDAO.findChessId().get();
    }


    @DisplayName("체스의 아이디 가져오기 테스트")
    @Test
    void findChessId() throws SQLException {

        // when
        ThrowableAssert.ThrowingCallable callable =  () -> chessDAO.findChessId().get();

        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }

    @DisplayName("체스 아이디로 체스의 현재 턴 가져오기 테스트")
    @Test
    void findTurnById() throws SQLException {

        // when
        final String turn = chessDAO.findTurnById(chessId).get();

        // then
        assertThat(turn).isEqualTo("WHITE");
    }

    @DisplayName("초기 체스판 삽입 테스트")
    @Test
    void insert() throws SQLException {

        // then
        final boolean inserted = chessDAO.findTurnById(chessId).isPresent();
        assertThat(inserted).isTrue();
    }

    @DisplayName("턴 변경 테스트")
    @Test
    void updateTurn() throws SQLException {

        // when
        chessDAO.updateTurn(chessId);

        // then
        final String turn = chessDAO.findTurnById(chessId).get();
        assertThat(turn).isEqualTo("BLACK");
    }

    @DisplayName("체스 아이디에 해당 하는 체스 삭제 테스트")
    @Test
    void delete() throws SQLException {

        // when
        chessDAO.delete(chessId);

        // then
        final boolean isChessRemaining = chessDAO.findTurnById(chessId).isPresent();
        assertThat(isChessRemaining).isFalse();
    }
}
