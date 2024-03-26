package domain.board;

import domain.piece.Color;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.PieceType;
import domain.position.File;
import domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

@Nested
public class SettingRuleTest {

    private final SettingRule settingRule = new SettingRule();

    @Nested
    class PawnTest {

        @Test
        @DisplayName("랭크가 2일 경우 흰색 폰을 반환한다.")
        void findPieceByPosition_RankTwo_WhitePawn() {
            assertThat(settingRule.findPieceByPosition(Rank.TWO, File.A))
                    .isEqualTo(new Pawn(PieceType.PAWN, Color.WHITE));
        }

        @Test
        @DisplayName("랭크가 2일 경우 검정색 폰을 반환한다.")
        void findPieceByPosition_RankSeven_BlackPawn() {
            assertThat(settingRule.findPieceByPosition(Rank.SEVEN, File.A))
                    .isEqualTo(new Pawn(PieceType.PAWN, Color.BLACK));
        }
    }

    @Nested
    class KingTest {

        @Test
        @DisplayName("랭크가 1, 파일이 e일 경우 흰색 킹을 반환한다.")
        void findPieceByPosition_RankOneFileE_WhiteKing() {
            assertThat(settingRule.findPieceByPosition(Rank.ONE, File.E))
                    .isEqualTo(new Piece(PieceType.KING, Color.WHITE));
        }

        @Test
        @DisplayName("랭크가 8, 파일이 e일 경우 검정색 킹을 반환한다.")
        void findPieceByPosition_RankEightFileE_BlackKing() {
            assertThat(settingRule.findPieceByPosition(Rank.EIGHT, File.E))
                    .isEqualTo(new Piece(PieceType.KING, Color.BLACK));
        }
    }

    @Nested
    class QueenTest {

        @Test
        @DisplayName("랭크가 1, 파일이 d일 경우 흰색 퀸을 반환한다.")
        void findPieceByPosition_RankOneFileD_WhiteQueen() {
            assertThat(settingRule.findPieceByPosition(Rank.ONE, File.D))
                    .isEqualTo(new Piece(PieceType.QUEEN, Color.WHITE));
        }

        @Test
        @DisplayName("랭크가 8, 파일이 d일 경우 검정색 퀸을 반환한다.")
        void findPieceByPosition_RankEightFileD_BlackQueen() {
            assertThat(settingRule.findPieceByPosition(Rank.EIGHT, File.D))
                    .isEqualTo(new Piece(PieceType.QUEEN, Color.BLACK));
        }
    }

    @Nested
    class BishopTest {

        @ParameterizedTest
        @EnumSource(names = {"C", "F"})
        @DisplayName("랭크가 1, 파일이 c 또는 f일 경우 흰색 비숍을 반환한다.")
        void findPieceByPosition_RankOneFileCF_WhiteBishop(File file) {
            assertThat(settingRule.findPieceByPosition(Rank.ONE, file))
                    .isEqualTo(new Piece(PieceType.BISHOP, Color.WHITE));
        }

        @ParameterizedTest
        @EnumSource(names = {"C", "F"})
        @DisplayName("랭크가 8, 파일이 c 또는 f일 경우 검정색 비숍을 반환한다.")
        void findPieceByPosition_RankEightFileCF_BlackBishop(File file) {
            assertThat(settingRule.findPieceByPosition(Rank.EIGHT, file))
                    .isEqualTo(new Piece(PieceType.BISHOP, Color.BLACK));
        }
    }

    @Nested
    class KnightTest {

        @ParameterizedTest
        @EnumSource(names = {"B", "G"})
        @DisplayName("랭크가 1, 파일이 b 또는 g일 경우 흰색 나이트를 반환한다.")
        void findPieceByPosition_RankOneFileBG_WhiteKnight(File file) {
            assertThat(settingRule.findPieceByPosition(Rank.ONE, file))
                    .isEqualTo(new Piece(PieceType.KNIGHT, Color.WHITE));
        }

        @ParameterizedTest
        @EnumSource(names = {"B", "G"})
        @DisplayName("랭크가 8, 파일이 b 또는 g일 경우 검정색 나이트를 반환한다.")
        void findPieceByPosition_RankEightFileBG_BlackKnight(File file) {
            assertThat(settingRule.findPieceByPosition(Rank.EIGHT, file))
                    .isEqualTo(new Piece(PieceType.KNIGHT, Color.BLACK));
        }
    }

    @Nested
    class RookTest {

        @ParameterizedTest
        @EnumSource(names = {"A", "H"})
        @DisplayName("랭크가 1, 파일이 a 또는 h일 경우 흰색 룩을 반환한다.")
        void findPieceByPosition_RankOneFileAH_WhiteRook(File file) {
            assertThat(settingRule.findPieceByPosition(Rank.ONE, file))
                    .isEqualTo(new Piece(PieceType.ROOK, Color.WHITE));
        }

        @ParameterizedTest
        @EnumSource(names = {"A", "H"})
        @DisplayName("랭크가 8, 파일이 a 또는 h일 경우 검정색 룩을 반환한다.")
        void findPieceByPosition_RankEightFileAH_BlackRook(File file) {
            assertThat(settingRule.findPieceByPosition(Rank.EIGHT, file))
                    .isEqualTo(new Piece(PieceType.ROOK, Color.BLACK));
        }
    }
}
