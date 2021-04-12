package chess.dao;

import chess.dao.dto.ChessGame;
import chess.domain.board.Square;
import chess.domain.manager.ChessGameManager;
import chess.domain.manager.ChessGameManagerFactory;
import chess.domain.piece.Pawn;
import chess.domain.piece.attribute.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static chess.domain.piece.attribute.Color.BLACK;
import static chess.domain.piece.attribute.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

class MysqlChessDaoTest {
    MysqlChessDao dao = new MysqlChessDao();
    ChessGameManager sampleGame;

    @BeforeEach
    void dbSetup() throws SQLException {
        String sample = "RKBQKBKRPPPPPPPP........................p........ppppppprkbqkbkr"; // move a2 a3 한 번 진행
        sampleGame = ChessGameManagerFactory.loadingGame(new ChessGame(0, BLACK, true, sample));
    }

    @DisplayName("DB에 체스보드 내용이 저장되는지 확인")
    @Test
    void saveTest() throws SQLException {
        //given
        //when
        dao.save(new ChessGame(sampleGame));

        // then
        ChessGameManager expectedGameManager = getRecentGame().get();
        Square a3 = expectedGameManager.getBoard().findByPosition(Position.of("a3"));
        assertThat(a3.getPiece().getClass()).isEqualTo(Pawn.class);
        assertThat(a3.getPiece().getColor()).isEqualTo(WHITE);
    }

    @DisplayName("체스보드 업데이트 확인")
    @Test
    void update() throws SQLException {
        //given
        long gameId = dao.save(new ChessGame(ChessGameManagerFactory.createRunningGame(0)));
        ChessGameManager runningGame = ChessGameManagerFactory.createRunningGame(gameId);
        //when
        runningGame.move(Position.of("a2"), Position.of("a4"));  // b7 -> b6
        dao.update(new ChessGame(runningGame));

        //then
        ChessGameManager expectedGameManager = getRecentGame().get();
        Square a4 = expectedGameManager.getBoard().findByPosition(Position.of("a4"));
        assertThat(a4.getPiece().getClass()).isEqualTo(Pawn.class);
        assertThat(a4.getPiece().getColor()).isEqualTo(WHITE);
    }

    @DisplayName("체스게임 삭제 확인")
    @Test
    void deleteOneGame() {
        //given
        dao.save(new ChessGame(sampleGame));

        //when
        dao.delete(sampleGame.getId());

        //then
        assertThat(dao.findById(sampleGame.getId()).isPresent()).isFalse();
    }

    private Optional<ChessGameManager> getRecentGame() throws SQLException {
        String query = "SELECT * FROM CHESSGAME ORDER BY ID DESC LIMIT 1";
        try (Connection connection = ChessConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Long rowId = resultSet.getLong("id");
                boolean isRunning = resultSet.getBoolean("running");
                String pieces = resultSet.getString("pieces");
                Color nextTurn = Color.of(resultSet.getString("next_turn"));

                return Optional.of(ChessGameManagerFactory.loadingGame(new ChessGame(rowId, nextTurn, isRunning, pieces)));
            }
        }
        return Optional.empty();
    }
}