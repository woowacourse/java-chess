package chess.dao;

import static org.assertj.core.api.Assertions.*;
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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessPieceDaoTest {

    @Test
    @DisplayName("포지션으로 기물을 조회한다.")
    void findByPosition() throws SQLException {
        // given
        final ChessPieceDao dao = new ChessPieceDao();
        final Position position = Position.from("a1");
        final Rook chessPiece = Rook.from(Color.WHITE);
        dao.save(position, chessPiece);

        // when
        final ChessPieceDto dto = dao.findByPosition(position);
        final Position actualPosition = dto.getPosition();
        final ChessPiece actualChessPiece = dto.getChessPiece();

        dao.deleteByPosition(position);

        // then
        assertThat(actualPosition).isEqualTo(position);
        assertThat(actualChessPiece).isEqualTo(chessPiece);
    }

    @Test
    @DisplayName("모든 기물을 조회한다.")
    void findAll() throws SQLException {
        // given
        final ChessPieceDao dao = new ChessPieceDao();
        dao.save(Position.from("a1"), King.from(Color.WHITE));
        dao.save(Position.from("a2"), King.from(Color.BLACK));

        // when
        final List<ChessPieceDto> dtos = dao.findAll();
        final int actual = dtos.size();

        dao.deleteByPosition(Position.from("a1"));
        dao.deleteByPosition(Position.from("a2"));

        // then
        assertThat(actual).isEqualTo(2);
    }

    @Test
    @DisplayName("기물을 DB에 저장한다.")
    void save() throws SQLException {
        // given
        final ChessPieceDao dao = new ChessPieceDao();
        final Position position = Position.from("a4");
        final ChessPiece chessPiece = Queen.from(Color.WHITE);

        // when
        final int actual = dao.save(position, chessPiece);

        dao.deleteByPosition(position);

        // then
        assertThat(actual).isEqualTo(1);
    }
    
    @Test
    @DisplayName("모든 기물을 DB에 저장한다.")
    void saveAll() throws SQLException {
        // given
        final ChessPieceDao dao = new ChessPieceDao();

        final Map<Position, ChessPiece> pieceByPosition = new HashMap<>();
        pieceByPosition.put(Position.from("a1"), Rook.from(Color.WHITE));
        pieceByPosition.put(Position.from("a2"), Knight.from(Color.WHITE));
        pieceByPosition.put(Position.from("a3"), Bishop.from(Color.WHITE));
        pieceByPosition.put(Position.from("a4"), Queen.from(Color.WHITE));

        // when
        final int actual = dao.saveAll(pieceByPosition);

        // then
        assertThat(actual).isEqualTo(4);
    }

    @Test
    @DisplayName("포지션에 해당하는 기물을 삭제한다.")
    void deleteByPosition() throws SQLException {
        // given
        final ChessPieceDao dao = new ChessPieceDao();
        final Position position = Position.from("a4");

        dao.save(position, King.from(Color.WHITE));

        // when
        final int actual = dao.deleteByPosition(position);

        // then
        assertThat(actual).isEqualTo(1);
    }
}