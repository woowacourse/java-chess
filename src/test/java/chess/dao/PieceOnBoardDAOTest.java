package chess.dao;

import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.domain.position.Position;
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
        pieceOnBoards.add(new PieceOnBoard(Position.of("a2"), PieceType.valueOf("PAWN"),
                Team.valueOf("WHITE"), 10));
        pieceOnBoards.add(new PieceOnBoard(Position.of("a1"), PieceType.valueOf("ROOK"),
                Team.valueOf("WHITE"), 10));
        pieceOnBoards.add(new PieceOnBoard(Position.of("a7"), PieceType.valueOf("PAWN"),
                Team.valueOf("BLACK"), 10));
        ChessBoard chessBoard = new ChessBoard(10);

        pieceOnBoardDAO.addPiece(chessBoard, pieceOnBoards);
    }

    @Disabled
    @DisplayName("피스 삭제")
    @Test
    void deletePieceTest() {
        PieceOnBoard a2WhitePawn = new PieceOnBoard(166, Position.of("a1"), PieceType.valueOf("ROOK"),
                Team.valueOf("WHITE"), 10);

        pieceOnBoardDAO.deletePiece(a2WhitePawn);
    }

    @Disabled
    @DisplayName("저장되어 있는 피스 정보 불러오기")
    @Test
    void findPieceTest() {
        ChessBoard chessBoard = new ChessBoard(10);
        List<PieceOnBoard> pieceOnBoards = pieceOnBoardDAO.findPiece(chessBoard);

        Assertions.assertThat(pieceOnBoards).contains(
                new PieceOnBoard(165, Position.of("a2"), PieceType.valueOf("PAWN"),
                        Team.valueOf("WHITE"), 10),
                new PieceOnBoard(167, Position.of("a7"), PieceType.valueOf("PAWN"),
                        Team.valueOf("BLACK"), 10)
        );
    }

    @Disabled
    @DisplayName("피스 정보 업데이트")
    @Test
    void updatePieceTest() {
        PieceOnBoard pieceOnBoard = new PieceOnBoard(165, Position.of("a2"), PieceType.valueOf("PAWN"),
                Team.valueOf("WHITE"), 10);

        pieceOnBoardDAO.updatePiece(pieceOnBoard, "a3");
    }
}
