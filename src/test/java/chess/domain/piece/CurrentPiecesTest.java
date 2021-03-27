//package chess.domain.piece;
//
//import chess.domain.piece.info.Color;
//import chess.domain.piece.info.Position;
//import chess.domain.piece.info.Score;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//
//public class CurrentPiecesTest {
//
//    @DisplayName("CurrentPieces 객체 생성 확인")
//    @Test
//    void 현재_기물_객체_생성() {
//        CurrentPieces currentPieces = CurrentPieces.generate();
//        assertThat(currentPieces.getCurrentPieces().size()).isEqualTo(32);
//    }
//
//    @DisplayName("해당 위치에 있는 기물을 찾는다.")
//    @Test
//    void 해당_위치에_있는_기물_찾기() {
//        CurrentPieces currentPieces = CurrentPieces.generate();
//        Piece sourcePiece = currentPieces.findByPosition(Position.of('e', '8'));
//
//        assertThat(sourcePiece).isInstanceOf(King.class);
//    }
//
//    @DisplayName("해당 위치에 있는 기물을 찾는다. - 없을 경우 Empty")
//    @Test
//    void 해당_위치에_있는_기물_찾기_EMPTY() {
//        CurrentPieces currentPieces = CurrentPieces.generate();
//        Piece sourcePiece = currentPieces.findByPosition(Position.of('e', '4'));
//
//        assertThat(sourcePiece).isInstanceOf(Empty.class);
//    }
//
//    @DisplayName("현재 기물들 중 해당 위치 기물 제거 확인")
//    @Test
//    void 해당_위치_기물_제거_확인() {
//        CurrentPieces currentPieces = CurrentPieces.generate();
//        Position target = Position.of('g', '7');
//
//        currentPieces.removePieceIfNotEmpty(currentPieces.findByPosition(target));
//
//        assertThat(currentPieces.getCurrentPieces().size()).isEqualTo(31);
//    }
//
//    @DisplayName("현재 기물들의 팀별 점수를 계산한다.")
//    @Test
//    void 팀별_점수_계산() {
//        CurrentPieces currentPieces = CurrentPieces.generate();
//
//        assertThat(currentPieces.sumScoreByColor(Color.WHITE)).isEqualTo(38);
//        assertThat(currentPieces.sumScoreByColor(Color.BLACK)).isEqualTo(38);
//    }
//
//    @DisplayName("현재 기물들의 팀별 점수를 계산한다. - 세로줄에 같은 색 폰이 있는 경우")
//    @Test
//    void 팀별_점수_계산_세로줄_같은색_폰() {
//        List<Piece> current = Arrays.asList(
//                new Pawn(Position.of('a', '8'), "P", Color.BLACK, new Score(1)),
//                new Pawn(Position.of('a', '7'), "P", Color.BLACK, new Score(1)),
//                new Pawn(Position.of('d', '8'), "P", Color.BLACK, new Score(1)),
//                new Pawn(Position.of('d', '7'), "P", Color.BLACK, new Score(1)),
//                new Pawn(Position.of('c', '1'), "p", Color.WHITE, new Score(1)),
//                new Pawn(Position.of('c', '2'), "p", Color.WHITE, new Score(1)));
//        CurrentPieces currentPieces = new CurrentPieces(current);
//
//        assertThat(currentPieces.sumScoreByColor(Color.BLACK)).isEqualTo(2);
//        assertThat(currentPieces.sumScoreByColor(Color.WHITE)).isEqualTo(1);
//    }
//}
