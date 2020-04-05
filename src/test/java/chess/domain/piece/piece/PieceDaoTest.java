package chess.domain.piece.piece;

import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceDao;
import chess.domain.piece.position.Position;
import chess.domain.piece.team.BlackTeam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PieceDaoTest {
    private PieceDao pieceDao;

    @BeforeEach
    void setUp() {
        pieceDao = new PieceDao();
    }

    @Test
    void connection() {
        Connection con = pieceDao.getConnection();
        assertNotNull(con);
    }

    @Test
    @DisplayName("datebase에 piece가 저장 된다")
    void addPieceTest() {
        Piece piece = new Pawn(Position.of("a1"), new BlackTeam());
        try {
            pieceDao.addPiece(piece);
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("좌표 값이 update된다.")
    void updatePieceTest() {
        Piece knight = new Knight(Position.of("c1"), new BlackTeam());
        Piece pawn = new Pawn(Position.of("c2"), new BlackTeam());
        Position sourcePosition = Position.of("c1");
        Position targetPosition = Position.of("b3");
        try {
            pieceDao.addPiece(knight);
            pieceDao.addPiece(pawn);
            pieceDao.updatePiece(sourcePosition, targetPosition);
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("선택한 piece가 삭제된다.")
    void deletePieceTest() {

        Position deletePosition = Position.of("a1");
        try {
            pieceDao.deletePiece(deletePosition);
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("모든 pieces가 삭제된다.")
    void deleteAll() {
        try {
            pieceDao.deleteAll();
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("database에 남아있는 pieces의 정보를 가져온다.")
    void readPieces() {
        try {
            ResultSet rs = (ResultSet) pieceDao.readPieces();
            if (rs.next()) {
                System.out.println(rs.getObject(4));
            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }
}
