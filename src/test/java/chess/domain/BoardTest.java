package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.piece.Empty;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.util.List;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BoardTest {

    @Nested
    @DisplayName("생성 테스트")
    class CreateBoard {

        @ParameterizedTest
        @ValueSource(strings = {"a1", "d2", "h8"})
        @DisplayName("빈 보드 생성 테스트")
        void create(String position) {
            Board board = Board.create();
            assertThat(board).extracting("board").asInstanceOf(InstanceOfAssertFactories.MAP)
                    .containsEntry(Position.from(position), Empty.create());
        }

        @Test
        @DisplayName("보드 피스 생성 테스트")
        void initialize() {
            Board board = Board.create();
            board.initialize();
            List<String> positions = List.of("a1", "b1", "g7", "h8");
            List<Piece> pieces = List.of(Rook.create(Color.WHITE), Knight.create(Color.WHITE), Pawn.create(Color.BLACK),
                    Rook.create(Color.BLACK));
            for (int i = 0; i < 4; i++) {
                assertThat(board).extracting("board").asInstanceOf(InstanceOfAssertFactories.MAP)
                        .containsEntry(Position.from(positions.get(i)), pieces.get(i));
            }
        }
    }


    @Nested
    @DisplayName("특정 위치 피스 반환 테스트")
    class PieceAtPosition {

        @Test
        @DisplayName("특정 위치의 같은 색깔 피스 가져오는 테스트")
        void get_piece() {
            Board board = Board.create();
            board.initialize();
            Position source = Position.from("e1");
            Color white = Color.WHITE;
            board.validateSourcePiece(source, white);
            Piece piece = board.getPieceAtPosition(source);
            assertThat(piece.getType()).isEqualTo(PieceType.KING);
            assertThat(piece.isSameColor(white)).isTrue();
        }

        @Test
        @DisplayName("특정 위치에 피스가 없을 경우")
        void no_piece() {
            Board board = Board.create();
            board.initialize();
            Position source = Position.from("e4");
            Color white = Color.WHITE;
            assertThatThrownBy(() -> board.validateSourcePiece(source, white)).isInstanceOf(
                    IllegalArgumentException.class);
        }

        @Test
        @DisplayName("특정 위치의 피스가 색깔이 다른 경우")
        void not_same_color_piece() {
            Board board = Board.create();
            board.initialize();
            Position source = Position.from("e1");
            Color black = Color.BLACK;
            assertThatThrownBy(() -> board.validateSourcePiece(source, black)).isInstanceOf(
                    IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("피스 이동경로 테스트")
    class CheckPiece {

        @Test
        @DisplayName("이동 경로에 다른 피스가 존재하지 않는 경우 에러가 발생하지 않는다")
        void check_route() {
            Board board = Board.create();
            board.initialize();
            assertDoesNotThrow(() -> board.checkBetweenRoute(Position.from("a2"), Position.from("a3")));
        }

        @Test
        @DisplayName("이동 경로에 다른 피스가 존재하는 경우 에러가 발생한다")
        void other_piece_in_route_error() {
            Board board = Board.create();
            board.initialize();
            assertThatThrownBy(() -> board.checkBetweenRoute(Position.from("a1"), Position.from("a3")))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("목적지에 같은 색깔의 피스가 있습니다.")
        void same_color_piece_in_destination() {
            Board board = Board.create();
            board.initialize();
            assertThatThrownBy(() -> board.checkSameColor(Position.from("a2"), Color.WHITE))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("폰 움직임 제한사항")
    class PawnRestriction {

        @Test
        @DisplayName("위나 아래로 움직이는데, 경로에 다른 피스가 있을 경우")
        void checkOtherPieceInRoute() {
            Board board = Board.create();
            board.initialize();
            board.replace(Position.from("a2"), Position.from("a4"));
            board.replace(Position.from("a7"), Position.from("a5"));
            assertThatThrownBy(() -> {
                board.checkRestrictionForPawn(Position.from("a4"), Position.from("a5"), Color.WHITE);
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("경로에 다른 피스가 존재합니다.");
        }

        @Test
        @DisplayName("대각선 방향으로 움직이는데, 상대편 피스가 없는 경우 - 비어있는 경우")
        void checkOtherPieceInDiagonal1() {
            Board board = Board.create();
            board.initialize();
            assertThatThrownBy(() -> {
                board.checkRestrictionForPawn(Position.from("a2"), Position.from("b3"), Color.WHITE);
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("비어있기 때문에 대각선으로 이동할 수 없습니다.");
        }

        @Test
        @DisplayName("대각선 방향으로 움직이는데, 상대편 피스가 없는 경우 - 같은편 피스가 있는 경우")
        void checkOtherPieceInDiagonal2() {
            Board board = Board.create();
            board.initialize();
            board.replace(Position.from("b2"), Position.from("b3"));
            assertThatThrownBy(() -> {
                board.checkRestrictionForPawn(Position.from("a2"), Position.from("b3"), Color.WHITE);
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("목적지에 같은 색깔의 피스가 있습니다.");
        }
    }

    @Nested
    @DisplayName("진영의 점수를 계산한다")
    class CalculatePoint {
        @Test
        @DisplayName("Black 진영의 점수를 계산한다")
        void checkWhiteTeamPoint() {
            Board board = Board.create();
            board.initialize();
            board.replace(Position.from("a2"), Position.from("a8"));
            board.replace(Position.from("a8"), Position.from("b8"));
            board.replace(Position.from("b8"), Position.from("c8"));
            board.replace(Position.from("c8"), Position.from("g8"));

            /*
            ...QKBpR
            PPPPPPPP
            ........
            ........
            ........
            ........
            .ppppppp
            rnbqkbnr
             */
            double point = board.calculatePoint(Color.BLACK);
            assertThat(point).isEqualTo(25L);
        }
    }
}
