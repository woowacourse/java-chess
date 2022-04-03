package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.chesspiece.Bishop;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Color;
import chess.domain.chesspiece.King;
import chess.domain.chesspiece.Knight;
import chess.domain.chesspiece.Queen;
import chess.domain.chesspiece.Rook;
import chess.domain.position.Position;
import chess.dto.ChessPieceDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessPieceDaoTest {

    @BeforeAll
    static void clearBeforeTest() {
        final ChessPieceDao dao = new ChessPieceDao();
        dao.deleteAll();
    }

    @AfterEach
    void clear() {
        final ChessPieceDao dao = new ChessPieceDao();
        dao.deleteAll();
    }

    @Test
    @DisplayName("모든 기물을 조회한다.")
    void findAll() {
        // given
        final Map<Position, ChessPiece> pieceByPosition = new HashMap<>();
        pieceByPosition.put(Position.from("a1"), King.from(Color.WHITE));
        pieceByPosition.put(Position.from("a2"), King.from(Color.BLACK));

        final ChessPieceDao dao = new ChessPieceDao();
        dao.saveAll(pieceByPosition);

        // when
        final List<ChessPieceDto> dtos = dao.findAll();
        final int actual = dtos.size();

        // then
        assertThat(actual).isEqualTo(2);
    }

    @Test
    @DisplayName("모든 기물을 DB에 저장한다.")
    void saveAll() {
        // given
        final Map<Position, ChessPiece> pieceByPosition = new HashMap<>();
        pieceByPosition.put(Position.from("a1"), Rook.from(Color.WHITE));
        pieceByPosition.put(Position.from("a2"), Knight.from(Color.WHITE));
        pieceByPosition.put(Position.from("a3"), Bishop.from(Color.WHITE));
        pieceByPosition.put(Position.from("a4"), Queen.from(Color.WHITE));

        final ChessPieceDao dao = new ChessPieceDao();

        // when
        final int actual = dao.saveAll(pieceByPosition);

        // then
        assertThat(actual).isEqualTo(4);
    }

    @Test
    @DisplayName("모든 기물을 삭제한다.")
    void deleteAll() {
        // given
        final Map<Position, ChessPiece> pieceByPosition = new HashMap<>();
        pieceByPosition.put(Position.from("a1"), Rook.from(Color.WHITE));
        pieceByPosition.put(Position.from("a2"), Knight.from(Color.WHITE));
        pieceByPosition.put(Position.from("a3"), Bishop.from(Color.WHITE));
        pieceByPosition.put(Position.from("a4"), Queen.from(Color.WHITE));

        final ChessPieceDao dao = new ChessPieceDao();
        dao.saveAll(pieceByPosition);

        // when
        final int actual = dao.deleteAll();

        // then
        assertThat(actual).isEqualTo(4);
    }
}