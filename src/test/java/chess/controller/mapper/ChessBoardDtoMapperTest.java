package chess.controller.mapper;

import chess.controller.dto.ChessBoardDto;
import chess.domain.chess.CampType;
import chess.domain.chess.ChessGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ChessBoardDtoMapperTest {

    @Test
    @DisplayName("체스판 정보가 주어지면, 체스판 dto를 생성한다.")
    void createChessBoardDto() {
        // given
        final ChessGame chessGame = new ChessGame(CampType.WHITE);

        // when, then
        final ChessBoardDto chessBoardDto = assertDoesNotThrow(() ->
                ChessBoardDtoMapper.createChessBoardDto(chessGame.getChessBoard()));
        assertThat(chessBoardDto)
                .isInstanceOf(ChessBoardDto.class);
    }
}
