package chess.domain.piece;

import static chess.domain.piece.Point.calculatePointByTeam;
import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.stream.Stream;

import chess.domain.Board;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PointTest {

    @Test
    @DisplayName("시작 보드 상황은 각각 38점을 가진다.")
    void each_team_has_38_point() {
        Board board = Board.create();
        assertThat(calculatePointByTeam(WHITE, board.getBoard())).isEqualTo(38);
        assertThat(calculatePointByTeam(BLACK, board.getBoard())).isEqualTo(38);
    }

    @Test
    @DisplayName("블랙은 폰이 하나 죽고 화이트는 2개의 폰이 겹쳤을 때 각각 37점을 가진다.")
    void black_37_white_37_when_black_pawn_died_and_2_white_pawn_() {
        Board board = Board.create();
        board.move(Square.of(File.D, Rank.TWO), Square.of(File.D, Rank.FOUR));
        board.move(Square.of(File.E, Rank.SEVEN), Square.of(File.E, Rank.FIVE));
        board.move(Square.of(File.D, Rank.FOUR), Square.of(File.E, Rank.FIVE));
        assertThat(calculatePointByTeam(WHITE, board.getBoard())).isEqualTo(37);
        assertThat(calculatePointByTeam(BLACK, board.getBoard())).isEqualTo(37);
    }

    @Test
    @DisplayName("같은 file에 폰이 있으면 0.5로 계산하고 같은 file에 폰이 없으면 1로 계산한다.")
    void black_37_white_38_when_black_pawn_died() {
        Map<Square, Piece> board = Map.of(
                Square.of(File.A, Rank.TWO), new Pawn(WHITE),
                Square.of(File.A, Rank.THREE), new Pawn(WHITE),
                Square.of(File.A, Rank.SEVEN), new Pawn(BLACK),
                Square.of(File.B, Rank.SEVEN), new Pawn(BLACK)
        );
        assertThat(calculatePointByTeam(WHITE, board)).isEqualTo(1);
        assertThat(calculatePointByTeam(BLACK, board)).isEqualTo(2);
    }

    @Test
    @DisplayName("비숍과 나이트가 있을 때 5.5점이다.")
    void bishop_knight() {
        Map<Square, Piece> board = Map.of(
                Square.of(File.A, Rank.TWO), new Bishop(WHITE),
                Square.of(File.A, Rank.THREE), new Knight(WHITE)
        );
        assertThat(calculatePointByTeam(WHITE, board)).isEqualTo(5.5);
    }

    @Test
    @DisplayName("룩과 퀸이 있을 때 14점이다.")
    void rook_queen() {
        Map<Square, Piece> board = Map.of(
                Square.of(File.A, Rank.SEVEN), new Rook(BLACK),
                Square.of(File.B, Rank.SEVEN), new Queen(BLACK)
        );
        assertThat(calculatePointByTeam(BLACK, board)).isEqualTo(14);
    }

    @ParameterizedTest
    @MethodSource("makePiece")
    @DisplayName("각각 기물의 점수를 확인한다.")
    void piece_point(Piece piece, double point) {
        Map<Square, Piece> board = Map.of(
                Square.of(File.A, Rank.ONE), piece
        );
        assertThat(calculatePointByTeam(WHITE, board)).isEqualTo(point);
    }

    private static Stream<Arguments> makePiece() {
        return Stream.of(
                Arguments.of(
                        new Pawn(Team.WHITE),
                        1
                ),
                Arguments.of(
                        new Rook(Team.WHITE),
                        5
                ),
                Arguments.of(
                        new Knight(Team.WHITE),
                        2.5
                ),
                Arguments.of(
                        new Bishop(Team.WHITE),
                        3
                ),
                Arguments.of(
                        new King(Team.WHITE),
                        0
                ),
                Arguments.of(
                        new Queen(Team.WHITE),
                        9
                )
        );
    }
}
