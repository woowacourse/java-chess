package chess.dao;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import chess.domain.board.position.Position;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.dto.PieceDto;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoTest {
    final PieceDao pieceDao = new PieceDao();

    @Test
    @DisplayName("위치에 따른 기물들을 받아 위치, 팀, 이름을 DB에 저장할 수 있다.")
    void save_findByPosition() {
        //given
        final Map<Position, Piece> board = Map.of(
                Position.from("a1"), new Pawn(WHITE),
                Position.from("a2"), new Knight(BLACK),
                Position.from("a3"), new Rook(WHITE)
        );
        pieceDao.saveAll(board);
        //when
        final Map<String, PieceDto> allPiecesByPosition = pieceDao.findAll();
        //then
        assertThat(allPiecesByPosition).contains(
                entry("a1", new PieceDto("WHITE", "Pawn")),
                entry("a2", new PieceDto("BLACK", "Knight")),
                entry("a3", new PieceDto("WHITE", "Rook")));
    }

    @Test
    @DisplayName("위치 값과 기물을 받아 DB에 저장한다.")
    void save() {
        //given
        final String position = "a2";
        final Piece piece = new Pawn(BLACK);
        pieceDao.save(position, piece);
        //actual
        final PieceDto actual = pieceDao.findAll().get(position);
        //when
        assertThat(actual).isEqualTo(new PieceDto("BLACK", "Pawn"));
    }

    @Test
    @DisplayName("위치 값을 받아 DB에서 해당 위치 값을 키로 가지는 데이터를 삭제한다.")
    void removeByPosition() {
        //given
        pieceDao.save("a2", new Pawn(BLACK));
        pieceDao.save("a3", new Knight(BLACK));
        pieceDao.removeByPosition("a2");
        //when
        final Map<String, PieceDto> actual = pieceDao.findAll();
        //then
        assertThat(actual).contains(entry("a3", new PieceDto("BLACK", "Knight")))
                .doesNotContain(entry("a2", new PieceDto("BLACK", "Pawn")));
    }

    @AfterEach
    void removeAll() {
        pieceDao.removeAll();
    }
}
