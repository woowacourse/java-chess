package chess.dao;

import chess.database.DatabaseConnector;
import chess.domain.ChessGame;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import chess.dto.ChessGameDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class MySqlChessGameDaoTest {

    private final MySqlChessGameDao mySqlChessGameDao = new MySqlChessGameDao(new DatabaseConnector());

    @Test
    @DisplayName("id로 검색 테스트")
    void find_by_id_test() {
        // a1 white king
        Map<Square, Piece> board = mySqlChessGameDao.findById(1).getBoard().getBoard();
        assertThat(board.get(Square.of(File.A, Rank.ONE))).isInstanceOf(King.class);
        assertThat(board.get(Square.of(File.A, Rank.ONE)).isWhite()).isTrue();
    }

    @Test
    @DisplayName("저장 테스트")
    void save_test() {
        ChessGame chessGame = new ChessGame();
        // id = 21
        assertThatCode(() -> mySqlChessGameDao.save(ChessGameDto.of(chessGame)))
                .doesNotThrowAnyException();
    }

    @AfterAll
    @DisplayName("create한 것 삭제하기")
    static void delete_created_game() {
        // id = 23
        delete(23);
    }

    private static void delete(final int chessGameId) {
        try (final Connection connection = new DatabaseConnector().getConnection()) {
            deletePieces(chessGameId, connection);
            deleteChessGame(chessGameId, connection);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deletePieces(final int chessGameId, final Connection connection) throws SQLException {
        final String query = "DELETE FROM piece WHERE chess_game_id = ?";
        final PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, chessGameId);
        ps.executeUpdate();
    }

    private static void deleteChessGame(final int chessGameId, final Connection connection) throws SQLException {
        final String query = "DELETE FROM chess_game WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, chessGameId);
        ps.executeUpdate();
    }
}