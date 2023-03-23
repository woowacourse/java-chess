package chess.controller.mapper;

import chess.controller.dto.PieceDto;
import chess.domain.chess.CampType;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PieceDtoMapperTest {

    @Test
    @DisplayName("체스말 정보가 주어지면, 체스말에 대한 dto를 생성한다.")
    void createPieceDto() {
        // given
        final PieceDtoMapper pieceDtoMapper = new PieceDtoMapper();
        final Piece piece = new Piece(PieceType.ROOK, CampType.WHITE, new Rook());

        // when, then
        final PieceDto pieceDto = assertDoesNotThrow(() -> pieceDtoMapper.createPieceDto(piece));
        assertThat(pieceDto)
                .isInstanceOf(PieceDto.class);
    }
}
