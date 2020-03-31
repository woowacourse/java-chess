package chess.domains.board;

import org.junit.jupiter.api.BeforeEach;

class BoardTest {
    private static Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

//    @DisplayName("입력한 위치의 말을 찾는 기능 확인")
//    @Test
//    void findPiece_1() {
//        PlayingPiece piece = board.findPiece(Position.ofPositionName("a1"));
//
//        PlayingPiece playingPiece = new PlayingPiece(Position.ofPositionName("a1"), new Rook(PieceColor.WHITE));
//        assertThat(piece).isEqualTo(playingPiece);
//    }
}