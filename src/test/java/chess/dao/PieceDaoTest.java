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

    @AfterEach
    void removeAll() {
        pieceDao.removeAll();
    }
}
