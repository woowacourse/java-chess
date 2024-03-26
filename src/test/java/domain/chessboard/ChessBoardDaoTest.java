package domain.chessboard;

import connection.ChessConnectionGenerator;
import domain.Team;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.square.File;
import domain.square.Rank;
import domain.square.Square;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repository.ChessBoardDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ChessBoardDaoTest {
    final Connection connection = ChessConnectionGenerator.getTestConnection();
    final ChessBoardDao chessBoardDao = new ChessBoardDao(connection);
    final int gameId = 41;

    @BeforeEach
    void before() {
        try {
            if (connection != null) {
                connection.setAutoCommit(false);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void after() {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("기물과 위치를 찾는다.")
    @Test
    void find() {
        // given

        final Square square = new Square(File.A, Rank.TWO);
        final Piece piece = new Pawn(Team.BLACK);

        // when
        chessBoardDao.addSquarePiece(square, piece, gameId);

        // then
        final Piece findPiece = chessBoardDao.findBySquare(square, gameId).get();
        assertThat(findPiece).isEqualTo(piece);
    }

    @DisplayName("기물의 위치를 업데이트한다.")
    @Test
    void update() {
        // given

        final Square square = new Square(File.A, Rank.TWO);
        final Piece piece = new Pawn(Team.BLACK);
        chessBoardDao.addSquarePiece(square, piece, gameId);

        final Piece newPiece = new Queen(Team.WHITE);

        // when
        chessBoardDao.update(square, piece, gameId);

        // then
        final Piece findPiece = chessBoardDao.findBySquare(square, gameId).get();
        assertThat(findPiece).isEqualTo(piece);
    }

    @DisplayName("해당 위치의 기물을 삭제한다.")
    @Test
    void delete() {
        // given

        final Square square = new Square(File.A, Rank.TWO);
        final Piece piece = new Pawn(Team.BLACK);
        chessBoardDao.addSquarePiece(square, piece, gameId);

        // when
        chessBoardDao.deleteBySquare(square, gameId);

        // then
        final Optional<Piece> findPiece = chessBoardDao.findBySquare(square, gameId);
        assertThat(findPiece).isEmpty();
    }

    @DisplayName("전체 위치 별 기물을 찾는다.")
    @Test
    void findAll() {
        // given

        final ChessBoard chessBoard = ChessBoard.create();
        final Map<Square, Piece> pieceSquares = chessBoard.getPieceSquares();
        chessBoardDao.addAll(pieceSquares, gameId);

        // when
        final Map<Square, Piece> results = chessBoardDao.findAll(gameId);

        // then
        Assertions.assertThat(results.entrySet()).isEqualTo(pieceSquares.entrySet());
    }
}
