package chess.service;

import chess.dao.FakePieceDao;
import chess.domain.piece.PieceFactory;
import chess.dto.PieceDto;
import chess.serviece.ChessService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessServiceTest {

    @Test
    @DisplayName("chess 게임 시작")
    void gameStart() {
        ChessService chessService = new ChessService(new FakePieceDao());
        List<PieceDto> initPieceDtos = PieceFactory.createChessPieces().entrySet()
                .stream()
                .map(entry -> PieceDto.from(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        List<PieceDto> pieceDtos = chessService.gameStart();

        Assertions.assertAll(
                () -> assertThat(pieceDtos.size()).isEqualTo(32),
                () -> assertThat(pieceDtos).containsAll(initPieceDtos)
        );
    }
}
