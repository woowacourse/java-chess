package chess.web.dao;

import chess.board.piece.Piece;
import chess.board.piece.Pieces;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PieceDaoTest {

    private final PieceDao pieceDao = new PieceDaoImpl();
    private final BoardDao boardDao = new BoardDaoImpl();
    private Long boardId;
    private Pieces pieces;

    @BeforeEach
    void setUp() {
        pieces = Pieces.createInit();
        boardId = boardDao.save();
        pieceDao.save(pieces.getPieces(), boardId);
    }

    @AfterEach
    void tearDown(){
        boardDao.deleteById(boardId);
        pieceDao.deleteByBoardId(boardId);
    }

    @Test
    @DisplayName("새로운 type과 team으로 바꾸면, db에 저장되고 다시 불러왔을 때 바뀐 type과 team이 나온다.")
    void updatePieceByPosition() {
        Piece piece = pieces.getPieces().get(0);
        String newType = "king";
        String newTeam = "black";

        pieceDao.updatePieceByPositionAndBoardId(newType, newTeam, piece.getPosition().name(), boardId);
        List<Piece> updatedPieces = pieceDao.findAllByBoardId(boardId);
        Piece updatedPiece = updatedPieces.get(0);

        assertThat(updatedPiece.getType()).isEqualTo(newType);
        assertThat(updatedPiece.getTeam().value()).isEqualTo(newTeam);
    }

    @Test
    @DisplayName("초기값인 체스말 64개가 모두 조회 되야한다.")
    void findAllByBoardId() {
        //when
        List<Piece> pieces = pieceDao.findAllByBoardId(boardId);
        //then
        assertThat(pieces.size()).isEqualTo(64);
    }

}
