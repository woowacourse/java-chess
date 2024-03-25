package domain.chessboard;

import domain.Team;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.square.File;
import domain.square.Rank;
import domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class ChessBoardDaoTest {
    final ChessBoardDao chessBoardDao = new ChessBoardDao();

    @DisplayName("DB에 연결한다.")
    @Test
    void connection() {
        try (final Connection connection = chessBoardDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("기물과 위치를 저장한다.")
    @Test
    void create() {
        // given
        final Square square = new Square(File.A, Rank.TWO);
        final Pawn piece = new Pawn(Team.BLACK);

        // when
        chessBoardDao.addSquarePiece(square, piece);

        // then
        final Piece findPiece = chessBoardDao.findBySquare(square);
        assertThat(findPiece).isEqualTo(piece);
    }

    @DisplayName("위치를 삭제한다.")
    @Test
    void deleteBySquare() {
        // given
        final Square square = new Square(File.A, Rank.TWO);

        // when
        chessBoardDao.deleteBySquare(square);

        // then
        final Piece findPiece = chessBoardDao.findBySquare(square);
        assertThat(findPiece).isNull();
    }


}
