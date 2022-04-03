package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Color;
import chess.domain.chesspiece.Rook;
import chess.domain.position.Position;
import chess.dto.ChessPieceDto;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessPieceDaoTest {

    @Test
    @DisplayName("포지션으로 기물을 조회한다.")
    void findByPosition() throws SQLException {
        // given
        final ChessPieceDao dao = new ChessPieceDao();
        final Position position = Position.from("a1");

        // when
        final ChessPieceDto dto = dao.findByPosition(position);
        final Position actualPosition = dto.getPosition();
        final ChessPiece actualChessPiece = dto.getChessPiece();

        // then
        assertThat(actualPosition).isEqualTo(position);
        assertThat(actualChessPiece).isEqualTo(Rook.from(Color.WHITE));
    }

    @Test
    @DisplayName("모든 기물을 조회한다.")
    void findAll() throws SQLException {
        // given
        final ChessPieceDao dao = new ChessPieceDao();

        // when
        final List<ChessPieceDto> dtos = dao.findAll();
        final int actual = dtos.size();

        // then
        assertThat(actual).isEqualTo(1);
    }
}