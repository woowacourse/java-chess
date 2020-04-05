package chess.controller.dao;

import chess.controller.dto.TileDto;
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

        pieceDAO.addPiece(1, tileDtos);
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
}
