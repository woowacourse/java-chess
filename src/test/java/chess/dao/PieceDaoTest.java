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
    void saveAll() {
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
    @DisplayName("위치 값과 기물을 받아, 해당 위치 값 데이터를 기물 정보로 업데이트 시킨다.")
    void removeByPosition() {
        //given
        pieceDao.saveAll(Map.of(Position.from("a2"), new Pawn(BLACK)));
        pieceDao.removeByPosition("a2");
        //when
        final Map<String, PieceDto> actual = pieceDao.findAll();
        //then
        assertThat(actual).doesNotContain(entry("a2", new PieceDto("WHITE", "Knight")));
    }

    @Test
    @DisplayName("position에 해당 하는 기물 정보를 업데이트한다.")
    void update() {
        //given
        final Piece piece = new Pawn(BLACK);
        pieceDao.saveAll(Map.of(
                Position.from("a2"), new Pawn(BLACK),
                Position.from("a3"), new Knight(WHITE)));
        pieceDao.updatePiece("a3", piece);
        //when
        final PieceDto actual = pieceDao.findAll().get("a3");
        //then
        assertThat(actual).isEqualTo(new PieceDto("BLACK", "Pawn"));
    }

    @Test
    @DisplayName("턴 정보를 DB에 저장한다.")
    void saveTurn() {
        //given
        pieceDao.saveTurn("WHITE");
        //when
        final String actual = pieceDao.getTurn();
        //then
        assertThat(actual).isEqualTo("WHITE");
        pieceDao.removeGameState();
    }

    @Test
    @DisplayName("게임 상태를 DB에 저장한다.")
    void saveState() {
        //given
        pieceDao.saveState("playing");
        //when
        final String actual = pieceDao.getGameState();
        //then
        assertThat(actual).isEqualTo("playing");
        pieceDao.removeGameState();
    }

    @AfterEach
    void removeAll() {
        pieceDao.removeAllPieces();
    }
}
