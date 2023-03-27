package chess.domain;

import chess.domain.chesspiece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Piece 클래스")
public class PieceTest {
    @Nested
    @DisplayName("isNotSameSide 메서드는")
    class isNotSameSide {
        Piece whiteKing = King.from(Side.WHITE);

        @Nested
        @DisplayName("빈 진영의 기물이 들어왔을 때")
        class given_empty_side {
            @DisplayName("true를 반환한다.")
            @Test
            void it_returns_true1() {
                assertThat(whiteKing.isNotSameSide(EmptyPiece.getInstance())).isTrue();
            }
        }

        @Nested
        @DisplayName("반대 진영의 기물이 들어왔을 때")
        class given_opposite_side {
            @DisplayName("true를 반환한다.")
            @Test
            void it_returns_true2() {
                assertThat(whiteKing.isNotSameSide(King.from(Side.BLACK))).isTrue();
            }
        }

        @Nested
        @DisplayName("같은 진영의 기물이 들어왔을 때")
        class given_same_side {
            @DisplayName("false를 반환한다.")
            @Test
            void it_returns_false() {
                assertThat(whiteKing.isNotSameSide(Knight.from(Side.WHITE))).isFalse();
            }
        }
    }

    @Nested
    @DisplayName("isOppositeSide 메서드는")
    class isOppositeSide {
        Piece whiteKing = King.from(Side.WHITE);

        @Nested
        @DisplayName("빈 진영의 기물이 들어왔을 때")
        class given_empty_side {
            @DisplayName("false를 반환한다.")
            @Test
            void it_returns_false() {
                assertThat(whiteKing.isOppositeSide(EmptyPiece.getInstance())).isFalse();
            }
        }

        @Nested
        @DisplayName("반대 진영의 기물이 들어왔을 때")
        class given_opposite_side {
            @DisplayName("true를 반환한다.")
            @Test
            void it_returns_true() {
                assertThat(whiteKing.isOppositeSide(King.from(Side.BLACK))).isTrue();
            }
        }

        @Nested
        @DisplayName("같은 진영의 기물이 들어왔을 때")
        class given_same_side {
            @DisplayName("false를 반환한다.")
            @Test
            void it_returns_false2() {
                assertThat(whiteKing.isOppositeSide(Knight.from(Side.WHITE))).isFalse();
            }
        }
    }

    @Nested
    @DisplayName("addPieceScore 메서드는")
    class addPieceScore {
        final int score = 0;

        @Nested
        @DisplayName("점수가 들어오면")
        class given_score {
            @Test
            @DisplayName("빈 기물의 점수를 더한 값을 반환한다.")
            void it_returns_empty_piece_score() {
                assertThat(EmptyPiece.getInstance().addPieceScore(score)).isEqualTo(0);
            }

            @Test
            @DisplayName("폰의 점수를 더한 값을 반환한다.")
            void it_returns_pawn_score() {
                assertThat(Pawn.from(Side.BLACK).addPieceScore(score)).isEqualTo(1);
            }

            @Test
            @DisplayName("나이트의 점수를 더한 값을 반환한다.")
            void it_returns_knight_score() {
                assertThat(Knight.from(Side.BLACK).addPieceScore(score)).isEqualTo(2.5);
            }

            @Test
            @DisplayName("비숍의 점수를 더한 값을 반환한다.")
            void it_returns_bishop_score() {
                assertThat(Bishop.of(Side.BLACK).addPieceScore(score)).isEqualTo(3);
            }

            @Test
            @DisplayName("룩의 점수를 더한 값을 반환한다.")
            void it_returns_rook_score() {
                assertThat(Rook.from(Side.BLACK).addPieceScore(score)).isEqualTo(5);
            }

            @Test
            @DisplayName("퀸의 점수를 더한 값을 반환한다.")
            void it_returns_queen_score() {
                assertThat(Queen.from(Side.BLACK).addPieceScore(score)).isEqualTo(9);
            }

            @Test
            @DisplayName("킹의 점수를 더한 값을 반환한다.")
            void it_returns_king_score() {
                assertThat(King.from(Side.BLACK).addPieceScore(score)).isEqualTo(0);
            }
        }
    }
}
