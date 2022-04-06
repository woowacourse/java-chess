package chess.service;

import chess.dao.FakeGameDao;
import chess.dao.FakePieceDao;
import chess.domain.command.MoveCommand;
import chess.domain.piece.PieceFactory;
import chess.dto.ChessResponseDto;
import chess.dto.PieceDto;
import chess.serviece.ChessService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ChessServiceTest {
    private ChessService chessService;

    @BeforeEach
    void setUp() {
        chessService = new ChessService(new FakePieceDao(), new FakeGameDao());
    }

    @Test
    @DisplayName("chess 게임 시작")
    void gameStart() {
        List<PieceDto> initPieceDtos = PieceFactory.createChessPieces().entrySet()
                .stream()
                .map(entry -> PieceDto.from(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        ChessResponseDto chessResponseDto = chessService.initializeGame();

        assertAll(
                () -> assertThat(chessResponseDto.getPieces().size()).isEqualTo(32),
                () -> assertThat(chessResponseDto.getPieces()).containsAll(initPieceDtos),
                () -> assertThat(chessResponseDto.getTurn()).isEqualTo("white"),
                () -> assertThat(chessResponseDto.getStatus()).isEqualTo("playing")
        );
    }

    @Test
    @DisplayName("기물 이동")
    void movePiece() {
        chessService.initializeGame();

        ChessResponseDto chessResponseDto = chessService.movePiece(MoveCommand.of("e2", "e4"));

        assertAll(
                () -> assertThat(chessResponseDto.getPieces().size()).isEqualTo(32),
                () -> assertThat(chessResponseDto.getPieces()).contains(PieceDto.of("e4", "white", "pawn")),
                () -> assertThat(chessResponseDto.getTurn()).isEqualTo("black"),
                () -> assertThat(chessResponseDto.getStatus()).isEqualTo("playing")
        );
    }

    @Test
    @DisplayName("모든 기물 가져오기")
    void getChess() {
        List<PieceDto> initPieceDtos = PieceFactory.createChessPieces().entrySet()
                .stream()
                .map(entry -> PieceDto.from(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        chessService.initializeGame();

        ChessResponseDto chessResponseDto = chessService.getChess();

        assertAll(
                () -> assertThat(chessResponseDto.getPieces().size()).isEqualTo(32),
                () -> assertThat(chessResponseDto.getPieces()).containsAll(initPieceDtos),
                () -> assertThat(chessResponseDto.getTurn()).isEqualTo("white"),
                () -> assertThat(chessResponseDto.getStatus()).isEqualTo("playing")
        );
    }
}
