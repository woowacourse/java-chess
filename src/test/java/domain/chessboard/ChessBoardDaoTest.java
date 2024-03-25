package domain.chessboard;

import domain.Team;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.square.File;
import domain.square.Rank;
import domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class ChessBoardDaoTest {
    final ChessBoardDao chessBoardDao = ChessBoardDao.test();

    @DisplayName("DB에 연결한다.")
    @Test
    void connection() {
        try (final Connection connection = chessBoardDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("CRUD")
    @TestFactory
    Collection<DynamicTest> move() {
        return List.of(
                dynamicTest("기물과 위치를 저장한다.", () -> {
                    // given
                    final Square square = new Square(File.A, Rank.TWO);
                    final Piece piece = new Pawn(Team.BLACK);

                    // when
                    chessBoardDao.addSquarePiece(square, piece);

                    // then
                    final Piece findPiece = chessBoardDao.findBySquare(square);
                    assertThat(findPiece).isEqualTo(piece);
                }),
                dynamicTest("기물과 위치를 업데이트한다.", () -> {
                    // given
                    final Square square = new Square(File.A, Rank.TWO);
                    final Piece piece = new Queen(Team.WHITE);

                    // when
                    chessBoardDao.update(square, piece);

                    // then
                    final Piece findPiece = chessBoardDao.findBySquare(square);
                    assertThat(findPiece).isEqualTo(piece);
                }),
                dynamicTest("위치를 삭제한다.", () -> {
                    // given
                    final Square square = new Square(File.A, Rank.TWO);

                    // when
                    chessBoardDao.deleteBySquare(square);

                    // then
                    final Piece findPiece = chessBoardDao.findBySquare(square);
                    assertThat(findPiece).isNull();
                })
        );
    }
}
