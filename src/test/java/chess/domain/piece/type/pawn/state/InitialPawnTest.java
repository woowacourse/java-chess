//package chess.domain.piece.state;
//
//import chess.domain.piece.position.Path;
//import chess.domain.piece.position.PiecePosition;
//import chess.domain.piece.type.pawn.state.InitialPawn;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.DisplayNameGeneration;
//import org.junit.jupiter.api.DisplayNameGenerator;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//
//@SuppressWarnings("NonAsciiCharacters")
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//@DisplayName("InitialPawn 은")
//class InitialPawnTest {
//
//    // TODO 마찬가지로 PawnTest 에 이미 존재하는데, 이곳에 테스트가 굳이 있어야 할까요?
//    @ParameterizedTest(name = "직진 한칸, 두칸 혹은 대각선으로 한 칸 이동이 가능하다. " +
//            "예를 들어 [{0}] 에서 [{1}]로 이동이 가능하다")
//    @CsvSource({
//            "b2,b3",
//            "b2,b4",
//            "b2,b3",
//            "b2,c3",
//            "b7,b6",
//            "b7,b5",
//            "b7,a6",
//            "b7,c6",
//    })
//    void 직선으로_한칸_두칸_대각선한칸_이동_가능하다(final PiecePosition from, final PiecePosition destination) {
//        // given
//        final InitialPawn initialPawn = new InitialPawn();
//
//        // when & then
//        assertDoesNotThrow(() -> initialPawn.validatePath(Path.of(from, destination)));
//    }
//
//    @ParameterizedTest(name = "직선인 경우 세칸부터는 이동할 수 없다. 예를 들어 [{0}] 에서 [{1}]로 이동이 가능하다")
//    @CsvSource({
//            "b2, b5",
//            "b7, b4"
//    })
//    void 직선인_경우_세칸부터는_이동할_수_없다(final PiecePosition from, final PiecePosition to) {
//        // given
//        final Path path = Path.of(from, to);
//        final InitialPawn initialPawn = new InitialPawn();
//
//        // when & then
//        assertThatThrownBy(() -> initialPawn.validatePath(path))
//                .isInstanceOf(IllegalArgumentException.class);
//    }
//
//    @ParameterizedTest(name = "대각선인 경우 두칸부터는 이동할 수 없다. 예를 들어 [{0}] 에서 [{1}]로 이동이 가능하다")
//    @CsvSource({
//            "c2, e4",
//            "c2, a4",
//            "c4, a1",
//            "c4, e6"
//    })
//    void 대각선인_경우_두칸부터_이동할_수_없다(final PiecePosition from, final PiecePosition to) {
//        // given
//        final Path path = Path.of(from, to);
//        final InitialPawn initialPawn = new InitialPawn();
//
//        // when & then
//        assertThatThrownBy(() -> initialPawn.validatePath(path))
//                .isInstanceOf(IllegalArgumentException.class);
//    }
//}
