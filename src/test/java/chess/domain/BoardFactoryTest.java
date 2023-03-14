package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardFactoryTest {

    @Test
    @DisplayName("체스판에서 빈 칸을 포함한 말들의 개수가 64개다.")
    void create_Success() {
        // given
        List<Square> squares = BoardFactory.create();

        // expect
        assertThat(squares)
                .hasSize(64);
    }

    @Test
    @DisplayName("체스판에 말들이 정확하게 생성되어야 한다.")
    void create_Correctly() {
        // given
        List<Square> squares = BoardFactory.create();

        // expect
        assertThat(squares)
                .contains(
                        new Square(new Piece(Role.ROOK, Team.WHITE), Position.of(0, 0)),
                        new Square(new Piece(Role.KNIGHT, Team.WHITE), Position.of(1, 0)),
                        new Square(new Piece(Role.BISHOP, Team.WHITE), Position.of(2, 0)),
                        new Square(new Piece(Role.QUEEN, Team.WHITE), Position.of(3, 0)),
                        new Square(new Piece(Role.KING, Team.WHITE), Position.of(4, 0)),
                        new Square(new Piece(Role.BISHOP, Team.WHITE), Position.of(5, 0)),
                        new Square(new Piece(Role.KNIGHT, Team.WHITE), Position.of(6, 0)),
                        new Square(new Piece(Role.ROOK, Team.WHITE), Position.of(7, 0)),

                        new Square(new Piece(Role.ROOK, Team.BLACK), Position.of(0, 7)),
                        new Square(new Piece(Role.KNIGHT, Team.BLACK), Position.of(1, 7)),
                        new Square(new Piece(Role.BISHOP, Team.BLACK), Position.of(2, 7)),
                        new Square(new Piece(Role.QUEEN, Team.BLACK), Position.of(3, 7)),
                        new Square(new Piece(Role.KING, Team.BLACK), Position.of(4, 7)),
                        new Square(new Piece(Role.BISHOP, Team.BLACK), Position.of(5, 7)),
                        new Square(new Piece(Role.KNIGHT, Team.BLACK), Position.of(6, 7)),
                        new Square(new Piece(Role.ROOK, Team.BLACK), Position.of(7, 7)),

                        new Square(new Piece(Role.PAWN, Team.WHITE), Position.of(0, 1)),
                        new Square(new Piece(Role.PAWN, Team.WHITE), Position.of(1, 1)),
                        new Square(new Piece(Role.PAWN, Team.WHITE), Position.of(2, 1)),
                        new Square(new Piece(Role.PAWN, Team.WHITE), Position.of(3, 1)),
                        new Square(new Piece(Role.PAWN, Team.WHITE), Position.of(4, 1)),
                        new Square(new Piece(Role.PAWN, Team.WHITE), Position.of(5, 1)),
                        new Square(new Piece(Role.PAWN, Team.WHITE), Position.of(6, 1)),
                        new Square(new Piece(Role.PAWN, Team.WHITE), Position.of(7, 1)),

                        new Square(new Piece(Role.PAWN, Team.BLACK), Position.of(0, 6)),
                        new Square(new Piece(Role.PAWN, Team.BLACK), Position.of(1, 6)),
                        new Square(new Piece(Role.PAWN, Team.BLACK), Position.of(2, 6)),
                        new Square(new Piece(Role.PAWN, Team.BLACK), Position.of(3, 6)),
                        new Square(new Piece(Role.PAWN, Team.BLACK), Position.of(4, 6)),
                        new Square(new Piece(Role.PAWN, Team.BLACK), Position.of(5, 6)),
                        new Square(new Piece(Role.PAWN, Team.BLACK), Position.of(6, 6)),
                        new Square(new Piece(Role.PAWN, Team.BLACK), Position.of(7, 6))
                );
    }


}
