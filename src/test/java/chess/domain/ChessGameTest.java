package chess.domain;

import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;
import static chess.domain.piece.PieceType.KNIGHT;
import static chess.domain.piece.PieceType.QUEEN;
import static chess.domain.square.File.A;
import static chess.domain.square.Rank.THREE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.square.Square;
import chess.domain.square.Squares;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ChessGameTest {
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(new Board(), WHITE);
    }

    @Test
    @DisplayName("체스 말을 이동시킨다.")
    void movePiece_success() {
        chessGame.movePiece("a2", "a3");
        Board board = chessGame.getBoard();
        Map<Square, Piece> pieces = board.getBoard();

        assertThat(pieces.containsKey(Squares.of(A, THREE)))
                .isTrue();
    }

    @Test
    @DisplayName("현재 차례가 아닌 팀의 말을 이동하려고 하면 예외가 발생한다.")
    void cannot_movePiece_By_Team() {
        chessGame.movePiece("a2", "a3");
        assertThatThrownBy(() -> chessGame.movePiece("a3", "a4"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("남아있는 기물들로 점수를 계산한다.")
    void calculateScoreBy() {
        List<Piece> pieces = List.of(Pieces.of(BLACK, QUEEN), Pieces.of(BLACK, KNIGHT));
        List<Integer> counts = List.of(2, 2, 1);
        double expected = 14.5;

        assertThat(chessGame.calculateScoreBy(pieces, counts)).isEqualTo(expected);
    }

    @ParameterizedTest(name = "점수를 비교해서 우승한 팀을 결정한다.")
    @MethodSource("scores")
    void determineWinningTeam(Map<Team, Double> scores, List<Team> winningTeam) {
        Assertions.assertThat(chessGame.determineWinningTeam(scores)).containsExactlyElementsOf(winningTeam);
    }

    private static Stream<Arguments> scores() {
        return Stream.of(
                Arguments.arguments(Map.of(BLACK, 10.0, WHITE, 15.0), List.of(WHITE)),
                Arguments.arguments(Map.of(BLACK, 15.0, WHITE, 10.0), List.of(BLACK)),
                Arguments.arguments(Map.of(BLACK, 10.0, WHITE, 10.0), List.of(BLACK, WHITE))
        );
    }
}
