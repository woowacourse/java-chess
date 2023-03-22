package chess.service;

import chess.controller.dto.ChessBoardDto;
import chess.domain.chess.ChessGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ChessBoardServiceTest {

    @Test
    @DisplayName("체스판 정보가 주어지면, 체스판 dto를 생성한다.")
    void createChessBoardDto() {
        // given
        final ChessBoardService chessBoardService = new ChessBoardService(
                new PieceService(), new PositionService());
        final ChessGame chessGame = new ChessGame();

        // when, then
        final ChessBoardDto chessBoardDto = assertDoesNotThrow(() ->
                chessBoardService.createChessBoardDto(chessGame.getChessBoard()));
        assertThat(chessBoardDto)
                .isInstanceOf(ChessBoardDto.class);
    }
}
