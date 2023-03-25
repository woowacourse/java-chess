package chess.domain.dao;

import chess.domain.Piece;
import chess.domain.Position;
import chess.domain.Shape;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.domain.Color.WHITE;
import static org.assertj.core.api.Assertions.*;

class DbPieceDaoTest {
    PieceDao pieceDao;

    @BeforeEach
    void setUp() {
        pieceDao = new PieceDaoImpl();
    }

    @AfterEach
    void remove() {
        Piece piece = Piece.from(1, 'a', Shape.PAWN);
        pieceDao.deletePieceByColor(piece, WHITE);
    }

    @DisplayName("기물 데이터를 생성할 수 있다.")
    @Test
    void create() {
        Piece piece = Piece.from(1, 'a', Shape.PAWN);
        pieceDao.create(piece, WHITE);
    }

    @DisplayName("색깔로 기물 데이터를 읽을 수 있다.")
    @Test
    void findByColor() {
        Piece added = Piece.from(1, 'a', Shape.PAWN);
        pieceDao.create(added, WHITE);
        List<Piece> pieces = pieceDao.findPieceByColor(WHITE);

        assertThat(pieces).contains(added);
    }

    @DisplayName("기물을 삭제할 수 있다.")
    @Test
    void delete() {
        //given
        Piece piece = Piece.from(1, 'a', Shape.PAWN);
        pieceDao.create(piece, WHITE);

        //when
        pieceDao.deletePieceByColor(piece, WHITE);

        //then
        List<Piece> selectedPieces = pieceDao.findPieceByColor(WHITE);
        assertThat(selectedPieces).doesNotContain(piece);
    }

    @DisplayName("기물의 위치를 변경할 수 있다.")
    @Test
    void updatePosition() {
        //given
        Position fromPosition = Position.from("a2");
        Piece piece = Piece.from(fromPosition.getRankValue(), fromPosition.getFileValue(), Shape.PAWN);
        pieceDao.create(piece, WHITE);

        //when
        Position targetPosition = Position.from("a4");
        piece.move(List.of(), targetPosition, WHITE);
        pieceDao.updatePosition(piece, fromPosition);

        //then
        pieceDao.findPieceByColor(WHITE);
    }
}
