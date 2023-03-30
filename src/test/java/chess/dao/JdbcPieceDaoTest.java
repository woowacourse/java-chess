package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import chess.dto.PieceInfoDto;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JdbcPieceDaoTest {
    private final JdbcPieceDao pieceDao = new JdbcPieceDao();

    @BeforeEach
    public void deleteAll() {
        pieceDao.deleteAll();
    }

    @Test
    public void save() {
        // given
        Position a1 = Position.from("a1");
        Pawn pawn = Pawn.create(Color.BLACK);
        PieceInfoDto a1_pawn = PieceInfoDto.create(a1, pawn);

        // when, then
        pieceDao.create(1, a1_pawn);
    }

    @Test
    public void findById() {
        // given
        Position a1 = Position.from("a1");
        Pawn pawn = Pawn.create(Color.BLACK);
        PieceInfoDto a1_pawn = PieceInfoDto.create(a1, pawn);

        Position b2 = Position.from("b2");
        Rook rook = Rook.create(Color.BLACK);
        PieceInfoDto b2_rook = PieceInfoDto.create(b2, rook);

        pieceDao.create(1, a1_pawn);
        pieceDao.create(1, b2_rook);

        // when
        List<PieceInfoDto> game1_pieces = pieceDao.findAllById(1);
        PieceInfoDto firstPiece = game1_pieces.get(0);
        PieceInfoDto secondPiece = game1_pieces.get(1);

        // then
        assertThat(firstPiece.getPosition()).isEqualTo(a1);
        assertThat(firstPiece.getPiece()).isEqualTo(pawn); // piece 캐싱한적 없는데 왜 성공?
        assertThat(secondPiece.getPosition()).isEqualTo(b2);
        assertThat(secondPiece.getPiece()).isEqualTo(rook);
    }

    @Test
    public void updateById() {
        // given
        Position a1 = Position.from("a1");
        Pawn pawn = Pawn.create(Color.BLACK);
        PieceInfoDto a1_pawn = PieceInfoDto.create(a1, pawn);

        Rook rook = Rook.create(Color.BLACK);
        PieceInfoDto a1_rook = PieceInfoDto.create(a1, rook);

        pieceDao.create(1, a1_pawn);

        // when
        pieceDao.updateById(1, a1_rook);

        List<PieceInfoDto> game1_pieces = pieceDao.findAllById(1);
        PieceInfoDto piece = game1_pieces.get(0);

        // then
        assertThat(piece.getPosition()).isEqualTo(a1);
        assertThat(piece.getPiece()).isEqualTo(rook); // piece 캐싱한적 없는데 왜 성공?
    }

    @Test
    public void deleteById() {
        // given
        Position a1 = Position.from("a1");
        Pawn pawn = Pawn.create(Color.BLACK);
        PieceInfoDto a1_pawn = PieceInfoDto.create(a1, pawn);

        Position b2 = Position.from("b2");
        Rook rook = Rook.create(Color.BLACK);
        PieceInfoDto b2_rook = PieceInfoDto.create(b2, rook);

        pieceDao.create(1, a1_pawn);
        pieceDao.create(1, b2_rook);

        // when
        pieceDao.deleteById(1);

        assertThat(pieceDao.findAllById(1)).isEmpty();
    }

}
