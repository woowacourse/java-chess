package chess.piece;

import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Rook 클래스")
class RookTest {

    @Nested
    @DisplayName("of 메서드는")
    class of {
        @Nested
        @DisplayName("진영이 주어지면")
        class given_team {
            @Test
            @DisplayName("해당 진영의 Rook을 2개 생성한다")
            void it_returns_rooks() {
                assertThat(Rook.getRooksOf(Color.BLACK)).hasSize(2);
            }
        }
    }

    @Nested
    @DisplayName("isMovable 메서드는")
    class isMovable {
        @Nested
        @DisplayName("자신의 위치와 이동하려는 위치, 해당 위치에 존재하는 기물이 주어지면")
        class given_another_piece {
            Rook whiteRook = Rook.getRooksOf(Color.WHITE)
                                 .get(0);
            Position from = Position.of(Rank.ONE, File.A);
            Position movablePosition1 = Position.of(Rank.ONE, File.C);
            Position movablePosition2 = Position.of(Rank.THREE, File.A);
            Position unable = Position.of(Rank.TWO, File.B);
            Rook whiteRook2 = Rook.getRooksOf(Color.WHITE)
                                  .get(1);
            Queen blackQueen = Queen.getQueenOf(Color.BLACK);

            @Test
            @DisplayName("갈 수 있고 해당 위치의 기물이 아군 기물이 아닌 경우 true를 반환한다")
            void it_returns_movable() {
                assertAll(
                        () -> assertThat(whiteRook.isValidMove(from, movablePosition1, blackQueen)).isTrue(),
                        () -> assertThat(whiteRook.isValidMove(from, movablePosition2, blackQueen)).isTrue(),
                        () -> assertThat(whiteRook.isValidMove(from, movablePosition1, EmptyPiece.getInstance())).isTrue()
                );
            }

            @Test
            @DisplayName("갈 수 있고 해당 위치의 기물이 같은 진영인 경우 false를 반환한다")
            void it_returns_not_movable1() {
                assertThat(whiteRook.isValidMove(from, movablePosition1, whiteRook2)).isFalse();
            }

            @Test
            @DisplayName("갈 수 없고 해당 위치의 기물이 상대 진영인 경우 false를 반환한다")
            void it_returns_not_movable2() {
                assertThat(whiteRook.isValidMove(from, unable, blackQueen)).isFalse();
            }
        }
    }
}
