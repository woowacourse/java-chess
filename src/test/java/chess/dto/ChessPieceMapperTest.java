package chess.dto;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Color;
import chess.domain.chesspiece.King;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessPieceMapperTest {

    @Test
    @DisplayName("기물의 이름과 색깔이 주어지면 그에 맞는 ChessPiece를 반환한다.")
    void findBy() {
        // given
        final String pieceType = "king";
        final String color = "white";

        // when
        final ChessPiece actual = ChessPieceMapper.toChessPiece(pieceType, color);

        // then
        assertThat(actual).isEqualTo(King.from(Color.WHITE));
    }
}