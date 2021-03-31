package chess.dao;

import chess.domain.game.ChessGameEntity;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Shape;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PieceDAOTest {

    private ChessGameDAO chessGameDAO;
    private PieceDAO pieceDAO;

    @BeforeEach
    void setUp() throws SQLException {
        chessGameDAO = new ChessGameDAO();
        pieceDAO = new PieceDAO();

        chessGameDAO.create();
    }

    @AfterEach
    void tearDown() throws SQLException {
        chessGameDAO.deleteById(chessGameDAO.findLatestOne().getId());
    }

    @DisplayName("모든 피스를 조회하는 기능 ")
    @Test
    void testFindAllPiecesByChessGameId() throws SQLException {
        //given
        ChessGameEntity chessGame = chessGameDAO.findLatestOne();

        //when
        pieceDAO.save(chessGame.getId(), Piece.createBishop(Color.BLACK, 1, 2));
        List<Piece> pieces = pieceDAO.findAllPiecesByChessGameId(chessGame.getId());

        //then
        assertAll(
                () -> assertThat(pieces).hasSize(1),
                () -> assertThat(pieces.get(0).getColor()).isEqualTo(Color.BLACK),
                () -> assertThat(pieces.get(0).getShape()).isEqualTo(Shape.BISHOP),
                () -> assertThat(pieces.get(0).getRow()).isEqualTo(1),
                () -> assertThat(pieces.get(0).getColumn()).isEqualTo(2)
        );
    }

    @DisplayName("chess게임이 만들어진 처음 상태일 때, 모든 피스를 조회하는 기능 ㄴ")
    @Test
    void testFindAllPiecesByChessGameIdWhenStart() throws SQLException {
        //given
        ChessGameEntity chessGame = chessGameDAO.findLatestOne();

        //when
        List<Piece> pieces = pieceDAO.findAllPiecesByChessGameId(chessGame.getId());

        //then
        assertAll(
                () -> assertThat(pieces).hasSize(0)
        );
    }

    @DisplayName("piece 삭제 기능")
    @Test
    void testDelete() throws SQLException {
        //given
        ChessGameEntity chessGame = chessGameDAO.findLatestOne();
        pieceDAO.save(chessGame.getId(), Piece.createBishop(Color.BLACK, 0, 0));

        //when
        pieceDAO.delete(chessGame.getId(), 0, 0);

        //then
        List<Piece> pieces = pieceDAO.findAllPiecesByChessGameId(chessGame.getId());
        assertThat(pieces).isEmpty();
    }

    @DisplayName("가로 세로 위치로 피스를 조회하는 기능 ")
    @Test
    void testFindOneByPosition() throws SQLException {
        //given
        ChessGameEntity chessGame = chessGameDAO.findLatestOne();
        Piece piece = Piece.createBishop(Color.BLACK, 0, 0);
        pieceDAO.save(chessGame.getId(), piece);

        //when
        Piece findPiece = pieceDAO.findOneByPosition(chessGame.getId(), 0, 0);

        //then
        assertThat(findPiece).isEqualTo(piece);
    }

    @DisplayName("피스 업데이트 기능")
    @Test
    void update() throws SQLException {
        //given
        ChessGameEntity chessGame = chessGameDAO.findLatestOne();
        Piece piece = Piece.createBishop(Color.BLACK, 0, 0);
        pieceDAO.save(chessGame.getId(), piece);

        //when
        pieceDAO.update(chessGame.getId(), 0, 0, 2, 1);

        //then
        Piece findPiece = pieceDAO.findOneByPosition(chessGame.getId(), 2, 1);
        assertThat(findPiece).isEqualTo(Piece.createBishop(Color.BLACK, 2, 1));
    }

}
