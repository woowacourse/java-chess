package chess.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PieceOnBoardDAOTest {
    private PieceOnBoardDAO pieceOnBoardDAO;

    @BeforeEach
    private void setUp() {
        pieceOnBoardDAO = new PieceOnBoardDAO();
    }

    @Disabled
    @DisplayName("피스 정보 추가")
    @Test
    void addPieceTest() {
        List<PieceOnBoard> pieceOnBoards = new ArrayList<>();
        pieceOnBoards.add(new PieceOnBoard(0, "a2", "PAWN", "WHITE", 5));
        pieceOnBoards.add(new PieceOnBoard(0, "a1", "ROOK", "WHITE", 5));
        pieceOnBoards.add(new PieceOnBoard(0, "a7", "PAWN", "BLACK", 5));
        ChessBoard chessBoard = new ChessBoard(5);

        pieceOnBoardDAO.addPiece(chessBoard, pieceOnBoards);
    }

    @Disabled
    @DisplayName("피스 삭제")
    @Test
    void deletePieceTest() {
        PieceOnBoard a2WhitePawn = new PieceOnBoard(35, "a1", "ROOK",
                "WHITE", 5);

        pieceOnBoardDAO.deletePiece(a2WhitePawn);
    }

    @Disabled
    @DisplayName("저장되어 있는 피스 정보 불러오기")
    @Test
    void findPieceTest() {
        ChessBoard chessBoard = new ChessBoard(5);
        List<PieceOnBoard> pieceOnBoards = pieceOnBoardDAO.findPiece(chessBoard);

        Assertions.assertThat(pieceOnBoards).contains(
                new PieceOnBoard(34, "a2", "PAWN", "WHITE", 5),
                new PieceOnBoard(36, "a7", "PAWN", "BLACK", 5)
        );
    }

    @Disabled
    @DisplayName("피스 정보 업데이트")
    @Test
    void updatePieceTest() {
        PieceOnBoard pieceOnBoard = new PieceOnBoard(34, "a2", "PAWN",
                "WHITE", 5);

        pieceOnBoardDAO.updatePiece(pieceOnBoard, "a3");
    }
}
