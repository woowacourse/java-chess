package chess.dao;

import chess.dto.TileDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PieceDAOTest {
    private PieceDAO pieceDAO;

    @BeforeEach
    private void setUp() {
        pieceDAO = new PieceDAO();
    }

    @Disabled
    @DisplayName("피스 정보 추가")
    @Test
    void addPieceTest() {
        TileDTO a2WhitePawn = new TileDTO("a2");
        a2WhitePawn.setPieceImageUrl("p_white");
        TileDTO a6BlackPawn = new TileDTO("a6");
        a6BlackPawn.setPieceImageUrl("P_black");
        List<TileDTO> tileDtos = new ArrayList<>(Arrays.asList(
                a2WhitePawn, a6BlackPawn
        ));
        ChessBoard chessBoard = new ChessBoard(1);

        pieceDAO.addPiece(chessBoard, tileDtos);
    }

    @Disabled
    @DisplayName("피스 삭제")
    @Test
    void deletePieceTest() {
        PieceOnBoard a2WhitePawn = new PieceOnBoard(29, "a1", "r_white", 1);

        pieceDAO.deletePiece(a2WhitePawn);
    }

    @Disabled
    @DisplayName("저장되어 있는 피스 정보 불러오기")
    @Test
    void findPieceTest() {
        ChessBoard chessBoard = new ChessBoard(1);
        List<PieceOnBoard> pieceOnBoards = pieceDAO.findPiece(chessBoard);

        Assertions.assertThat(pieceOnBoards).contains(
                new PieceOnBoard(33, "a2", "p_white", 1),
                new PieceOnBoard(26, "a6", "P_black", 1)
        );
    }

    @Disabled
    @DisplayName("피스 정보 업데이트")
    @Test
    void updatePieceTest() {
        PieceOnBoard pieceOnBoard = new PieceOnBoard(33, "a2",
                "p_white", 1);

        pieceDAO.updatePiece(pieceOnBoard, "a3");
    }
}
