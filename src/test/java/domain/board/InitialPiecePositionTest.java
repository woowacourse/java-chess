package domain.board;

import domain.piece.PieceType;
import domain.position.File;
import domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

class InitialPiecePositionTest {


    @Nested
    class PawnTest {

        @ParameterizedTest
        @EnumSource(File.class)
        @DisplayName("랭크가 2일 경우 폰을 반환한다.")
        void findPieceByPosition_RankTwo_WhitePawn(File file) {
            assertThat(InitialPiecePosition.find(Rank.TWO, file))
                    .isEqualTo(PieceType.PAWN);
        }
    }

    @Nested
    class KingTest {

        @Test
        @DisplayName("랭크가 1, 파일이 e일 경우 킹을 반환한다.")
        void findPieceByPosition_RankOneFileE_WhiteKing() {
            assertThat(InitialPiecePosition.find(Rank.ONE, File.E))
                    .isEqualTo(PieceType.KING);
        }
    }

    @Nested
    class QueenTest {

        @Test
        @DisplayName("랭크가 1, 파일이 d일 경우 퀸을 반환한다.")
        void findPieceByPosition_RankOneFileD_WhiteQueen() {
            assertThat(InitialPiecePosition.find(Rank.ONE, File.D))
                    .isEqualTo(PieceType.QUEEN);
        }
    }

    @Nested
    class BishopTest {

        @ParameterizedTest
        @EnumSource(names = {"C", "F"})
        @DisplayName("랭크가 1, 파일이 c 또는 f일 경우 비숍을 반환한다.")
        void findPieceByPosition_RankOneFileCF_WhiteBishop(File file) {
            assertThat(InitialPiecePosition.find(Rank.ONE, file))
                    .isEqualTo(PieceType.BISHOP);
        }
    }

    @Nested
    class KnightTest {

        @ParameterizedTest
        @EnumSource(names = {"B", "G"})
        @DisplayName("랭크가 1, 파일이 b 또는 g일 경우 나이트를 반환한다.")
        void findPieceByPosition_RankOneFileBG_WhiteKnight(File file) {
            assertThat(InitialPiecePosition.find(Rank.ONE, file))
                    .isEqualTo(PieceType.KNIGHT);
        }
    }

    @Nested
    class RookTest {

        @ParameterizedTest
        @EnumSource(names = {"A", "H"})
        @DisplayName("랭크가 1, 파일이 a 또는 h일 경우 룩을 반환한다.")
        void findPieceByPosition_RankOneFileAH_WhiteRook(File file) {
            assertThat(InitialPiecePosition.find(Rank.ONE, file))
                    .isEqualTo(PieceType.ROOK);
        }
    }
}