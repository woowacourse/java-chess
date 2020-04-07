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
        TileDto a2WhitePawn = new TileDto("a2");
        a2WhitePawn.setPieceImageUrl("p_white");
        TileDto a6BlackPawn = new TileDto("a6");
        a6BlackPawn.setPieceImageUrl("P_black");
        List<TileDto> tileDtos = new ArrayList<>(Arrays.asList(
                a2WhitePawn, a6BlackPawn
        ));
        ChessBoard chessBoard = new ChessBoard(12);

        pieceDAO.addPiece(chessBoard, tileDtos);
    }

    @Disabled
    @DisplayName("피스 삭제")
    @Test
    void deletePieceTest() throws Exception {
        PieceOnBoard a2WhitePawn = new PieceOnBoard(4, "a3", "p_white", 1);

        pieceDAO.deletePiece(a2WhitePawn);
    }

    @Disabled
    @DisplayName("저장되어 있는 피스 정보 불러오기")
    @Test
    void findPieceTest() throws Exception {
        ChessBoard chessBoard = new ChessBoard(12);
        List<PieceOnBoard> pieceOnBoards = pieceDAO.findPiece(chessBoard);

        Assertions.assertThat(pieceOnBoards).containsExactly(
                new PieceOnBoard(6, "a2", "p_white", 12),
                new PieceOnBoard(7, "a6", "P_black", 12)
        );
    }

    @Disabled
    @DisplayName("피스 정보 업데이트")
    @Test
    void updatePieceTest() throws Exception {
        PieceOnBoard pieceOnBoard = new PieceOnBoard(141, "a1",
                "r_white", 17);

        pieceDAO.updatePiece(pieceOnBoard, "a2");
    }
}
