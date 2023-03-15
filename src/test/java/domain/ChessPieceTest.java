//package domain;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.List;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//class ChessPieceTest {
//
//    @Test
//    @DisplayName("Piece를 받아서 검은색 ChessPiece를 생성한다")
//    public void testMakeBlack() {
//        //given
//        final Piece piece = Piece.KING;
//
//        //when
//        final ChessPiece chessPiece = ChessPiece.makeBlack(piece);
//
//        //then
//        assertThat(chessPiece).extracting("piece").isEqualTo(piece);
//        assertThat(chessPiece).extracting("color").isEqualTo(Color.BLACK);
//    }
//
//    @Test
//    @DisplayName("Piece를 받아서 하얀색 ChessPiece를 생성한다")
//    public void testMakeWhite() {
//        //given
//        final Piece piece = Piece.KING;
//
//        //when
//        final ChessPiece chessPiece = ChessPiece.makeWhite(piece);
//
//        //then
//        assertThat(chessPiece).extracting("piece").isEqualTo(piece);
//        assertThat(chessPiece).extracting("color").isEqualTo(Color.WHITE);
//    }
//
//    @Test
//    @DisplayName("시작 위치와 도착 위치가 주어졌을 때, 경로를 탐색한다.")
//    public void testNavigate() {
//        //given
//        final ChessPiece chessPiece = ChessPiece.makeWhite(Piece.PAWN);
//        final Location start = Location.of(2, 2);
//        final Location end = Location.of(2, 3)
//        final Move move = new Move();
//
//        //when
//        final List<Location> paths = chessPiece.navigate(start, end);
//
//        //then
//        assertThat(paths).hasSize(1);
//        assertThat(paths.get(0)).isEqualsTo(Location.of(2, 3));
//    }
//}
