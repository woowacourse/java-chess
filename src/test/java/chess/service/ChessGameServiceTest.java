package chess.service;

import chess.dao.ChessGameDAO;
import chess.dao.PieceDAO;
import chess.domain.game.ChessGameEntity;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ChessGameServiceTest {

    private ChessGameDAO chessGameDAO;
    private PieceDAO pieceDAO;
    private ChessGameService chessGameService;

    @BeforeEach
    void setUp() {
        chessGameDAO = new ChessGameDAO();
        pieceDAO = new PieceDAO();
        chessGameService = new ChessGameService(chessGameDAO, pieceDAO);
    }

    @DisplayName("새로운 체스게임을 생성하는 기능")
    @Test
    void testChessGameService() throws SQLException {
        //when
        chessGameService.createNewChessGame();

        //then
        ChessGameEntity findChessGame = chessGameDAO.findLatestOne().get();
        List<Piece> findPieces = pieceDAO.findAllPiecesByChessGameId(findChessGame.getId());

        assertAll(
                () -> assertThat(findChessGame).isNotNull(),
                () -> assertThat(findChessGame.getState()).isEqualTo("Ready"),
                () -> assertThat(findPieces).hasSize(32)
        );
    }

}
