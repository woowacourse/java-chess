package chess.dao;

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
import static org.junit.jupiter.api.Assertions.assertAll;

class PieceDAOTest {

    private ChessGameDAO chessGameDAO;
    private PieceDAO pieceDAO;
    private Long chessGameId;

    @BeforeEach
    void setUp() throws SQLException {
        chessGameDAO = new ChessGameDAO();
        pieceDAO = new PieceDAO();
        chessGameId = chessGameDAO.create();
    }

    @AfterEach
    void tearDown() throws SQLException {
        chessGameDAO.deleteById(chessGameId);
    }

    @DisplayName("모든 피스를 조회하는 기능 ")
    @Test
    void testFindAllPiecesByChessGameId() throws SQLException {
        //when
        pieceDAO.save(chessGameId, Piece.createBishop(Color.BLACK, 1, 2));
        List<Piece> pieces = pieceDAO.findAllPiecesByChessGameId(chessGameId);

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
        //when
        List<Piece> pieces = pieceDAO.findAllPiecesByChessGameId(chessGameId);

        //then
        assertAll(
                () -> assertThat(pieces).hasSize(0)
        );
    }

    @DisplayName("piece 삭제 기능")
    @Test
    void testDelete() throws SQLException {
        //given
        pieceDAO.save(chessGameId, Piece.createBishop(Color.BLACK, 0, 0));

        //when
        pieceDAO.delete(chessGameId, 0, 0);

        //then
        List<Piece> pieces = pieceDAO.findAllPiecesByChessGameId(chessGameId);
        assertThat(pieces).isEmpty();
    }

    @DisplayName("가로 세로 위치로 피스를 조회하는 기능 ")
    @Test
    void testFindOneByPosition() throws SQLException {
        //given
        Piece piece = Piece.createBishop(Color.BLACK, 0, 0);
        pieceDAO.save(chessGameId, piece);

        //when
        Piece findPiece = pieceDAO.findOneByPosition(chessGameId, 0, 0).get();

        //then
        assertThat(findPiece).isEqualTo(piece);
    }

    @DisplayName("피스 업데이트 기능")
    @Test
    void update() throws SQLException {
        //given
        Piece piece = Piece.createBishop(Color.BLACK, 0, 0);
        pieceDAO.save(chessGameId, piece);

        //when
        pieceDAO.update(chessGameId, 0, 0, 2, 1);

        //then
        Piece findPiece = pieceDAO.findOneByPosition(chessGameId, 2, 1).get();
        assertThat(findPiece).isEqualTo(Piece.createBishop(Color.BLACK, 2, 1));
    }

}
