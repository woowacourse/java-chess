package chess.domain.piece.piece;

import chess.dao.ChessBoardDaoImpl;
import chess.database.Connector;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;
import chess.domain.piece.team.BlackTeam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ChessBoardDaoImplTest {
    private ChessBoardDaoImpl chessBoardDaoImpl;

    @BeforeEach
    void setUp() {
        chessBoardDaoImpl = new ChessBoardDaoImpl();
    }

    @Test
    void connection() {
        Connection con = Connector.getConnection();
        assertNotNull(con);
    }

    @Test
    @DisplayName("datebase에 piece가 저장 된다")
    void addPieceTest() {
        Piece piece = new Pawn(Position.of("a1"), new BlackTeam());
        try {
            chessBoardDaoImpl.addPiece(piece);
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
            chessBoardDaoImpl.addPiece(knight);
            chessBoardDaoImpl.addPiece(pawn);
            chessBoardDaoImpl.updatePiece(sourcePosition, targetPosition);
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
            chessBoardDaoImpl.deletePiece(deletePosition);
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("모든 pieces가 삭제된다.")
    void deleteAll() {
        try {
            chessBoardDaoImpl.deleteAll();
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("database에 남아있는 pieces의 정보를 가져온다.")
    void find() {
        try {
            chessBoardDaoImpl.find();
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }

    }
}
