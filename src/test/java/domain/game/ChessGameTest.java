package domain.game;

import static domain.Fixture.Pieces.*;
import static domain.Fixture.Positions.*;
import static domain.Fixture.PredefinedBoardsOfEachScore.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ChessGameTest {
    @Test
    @DisplayName("게임은 흰색 팀의 차례로 시작한다.")
    void initialTeamEqualsWhiteTest() {
        // Given
        ChessGame chessGame = new ChessGame();

        // When
        TeamColor currentTeam = chessGame.currentPlayingTeam();

        // Then
        assertThat(currentTeam).isEqualTo(TeamColor.WHITE);
    }

    @Test
    @DisplayName("한쪽 팀이 기물을 이동하면 다음 팀으로 차례가 넘어간다.")
    void toggleTeamTest() {
        // Given
        ChessGame chessGame = new ChessGame();

        // When
        chessGame.move(A2, A4);

        // Then
        assertThat(chessGame.currentPlayingTeam()).isEqualTo(TeamColor.BLACK);
    }

    @Test
    @DisplayName("게임이 진행중인 상태에서는 전달받은 출발지와 목적지에 따라 말을 이동시킨다.")
    void movePieceTest() {
        // Given
        ChessGame chessGame = new ChessGame();

        // When
        Position source = A2;
        Position destination = A4;

        // Then
        assertThatCode(() -> chessGame.move(source, destination))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource("kingCaughtCase")
    @DisplayName("한 팀의 킹이 잡히면 게임을 종료하고 승자를 판단할 수 있다.")
    void gameEndTest(Map<Position, Piece> piecePositions, Position source, Position destination, TeamColor expectedWinner) {
        // Given
        Board board = new Board(new HashMap<>(piecePositions));
        TestableChessGame chessGame = piecePositions.get(source).hasColor(TeamColor.WHITE)
                ? TestableChessGame.whiteTurnOf(board)
                : TestableChessGame.blackTurnOf(board);

        // When
        chessGame.move(source, destination);

        // Then
        assertAll(
                () -> assertThat(chessGame.isGameEnd()).isTrue(),
                () -> assertThat(chessGame.getWinner()).isEqualTo(expectedWinner)
        );
    }

    static Stream<Arguments> kingCaughtCase() {
        return Stream.of(
                Arguments.of(Map.of(D4, BLACK_KING_PIECE, D3, WHITE_QUEEN_PIECE), D3, D4, TeamColor.WHITE),
                Arguments.of(Map.of(D4, WHITE_KING_PIECE, D3, BLACK_QUEEN_PIECE), D3, D4, TeamColor.BLACK)
        );
    }

    @ParameterizedTest
    @MethodSource("specificScoreCase")
    @DisplayName("각 팀별 현재 점수를 계산할 수 있다.")
    void calculateScoreOfEachTeamTest(Map<Position, Piece> piecePositions, double expectedWhiteScore, double expectedBlackScore) {
        // Given
        Board board = new Board(piecePositions);
        TestableChessGame chessGame = new TestableChessGame(null, board);

        // When
        double whiteScore = chessGame.currentScoreOf(TeamColor.WHITE);
        double blackScore = chessGame.currentScoreOf(TeamColor.BLACK);

        // Then
        assertAll(
                () -> assertThat(whiteScore).isEqualTo(expectedWhiteScore),
                () -> assertThat(blackScore).isEqualTo(expectedBlackScore)
        );
    }

    static Stream<Arguments> specificScoreCase() {
        return Stream.of(
                Arguments.of(BOARD_WHITE_19_5_BLACK_20, 19.5, 20),
                Arguments.of(BOARD_WHITE_20_5_BLACK_20, 20.5, 20),
                Arguments.of(BOARD_WHITE_2_BLACK_2_5, 2, 2.5),
                Arguments.of(BOARD_WHITE_0_BLACK_3_5, 0, 3.5)
        );
    }
}
