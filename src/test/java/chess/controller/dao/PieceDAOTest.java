package chess.controller.dao;

import chess.controller.dto.TileDto;
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
    void addPieceTest() throws Exception {
        TileDto a2WhitePawn = new TileDto("ATWO");
        a2WhitePawn.setPieceImageUrl("p_white");
        TileDto a6BlackPawn = new TileDto("ASIX");
        a6BlackPawn.setPieceImageUrl("P_black");
        List<TileDto> tileDtos = new ArrayList<>(Arrays.asList(
                a2WhitePawn, a6BlackPawn
        ));

        pieceDAO.addPiece(12, tileDtos);
    }

    @Disabled
    @DisplayName("피스 정보 업데이트")
    @Test
    void updatePieceTest() throws Exception {
        PieceOnBoard a2WhitePawn = new PieceOnBoard(4, "ATHREE", "p_white", 1);
        PieceOnBoard a6BlackPawn = new PieceOnBoard(5, "ASIX", "P_black", 1);
        List<PieceOnBoard> pieces = new ArrayList<>(Arrays.asList(
                a2WhitePawn, a6BlackPawn
        ));

        pieceDAO.updatePiece(pieces);
    }

    @Disabled
    @DisplayName("피스 삭제")
    @Test
    void deletePieceTest() throws Exception {
        PieceOnBoard a2WhitePawn = new PieceOnBoard(4, "ATHREE", "p_white", 1);

        pieceDAO.deletePiece(a2WhitePawn);
    }

    @Disabled
    @DisplayName("저장되어 있는 피스 정보 불러오기")
    @Test
    void findPieceTest() throws Exception {
        List<PieceOnBoard> pieceOnBoards = pieceDAO.findPiece(12);

        Assertions.assertThat(pieceOnBoards).containsExactly(
                new PieceOnBoard(6, "ATWO", "p_white", 12),
                new PieceOnBoard(7, "ASIX", "P_black", 12)
        );
    }

    @Disabled
    @DisplayName("피스를 받아서 업데이트")
    @Test
    void updatePiecePositionTest() throws Exception {
        PieceOnBoard pieceOnBoard = new PieceOnBoard(34, "ATHREE", "p_white", 4);

        pieceDAO.updatePiece(pieceOnBoard);
    }
}
