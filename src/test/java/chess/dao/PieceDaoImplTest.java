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

class PieceDaoImplTest {

    final PieceDaoImpl pieceDaoImpl = PieceDaoImpl.getInstance();

    @Test
    @DisplayName("위치에 따른 기물들을 받아 위치, 팀, 이름을 DB에 저장할 수 있다.")
    void saveAll() {
        //given
        final Map<Position, Piece> board = Map.of(
                Position.from("a1"), new Pawn(WHITE),
                Position.from("a2"), new Knight(BLACK),
                Position.from("a3"), new Rook(WHITE)
        );
        pieceDaoImpl.saveAllPieces(board);
        //when
        final Map<String, PieceDto> allPiecesByPosition = pieceDaoImpl.findAllPieces();
        //then
        assertThat(allPiecesByPosition).contains(
                entry("a1", new PieceDto("WHITE", "Pawn")),
                entry("a2", new PieceDto("BLACK", "Knight")),
                entry("a3", new PieceDto("WHITE", "Rook")));
    }

    @Test
    @DisplayName("위치 값과 기물을 받아, 해당 위치 값 데이터를 기물 정보로 업데이트 시킨다.")
    void removeByPosition() {
        //given
        pieceDaoImpl.saveAllPieces(Map.of(Position.from("a2"), new Pawn(BLACK)));
        pieceDaoImpl.removePieceByPosition("a2");
        //when
        final Map<String, PieceDto> actual = pieceDaoImpl.findAllPieces();
        //then
        assertThat(actual).doesNotContain(entry("a2", new PieceDto("WHITE", "Knight")));
    }

    @Test
    @DisplayName("position에 해당 하는 기물 정보를 업데이트한다.")
    void update() {
        //given
        final Piece piece = new Pawn(BLACK);
        pieceDaoImpl.saveAllPieces(Map.of(
                Position.from("a2"), new Pawn(BLACK),
                Position.from("a3"), new Knight(WHITE)));
        pieceDaoImpl.updatePiece("a3", piece);
        //when
        final PieceDto actual = pieceDaoImpl.findAllPieces().get("a3");
        //then
        assertThat(actual).isEqualTo(new PieceDto("BLACK", "Pawn"));
    }

    @AfterEach
    void remove() {
        pieceDaoImpl.removeAllPieces();
    }
}
