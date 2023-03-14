package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardFactoryTest {

    @Test
    @DisplayName("체스판의 말들의 개수가 32개 이어야한다.")
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
                        new Square(new Piece(Role.ROOK, Team.WHITE), new Position(0, 0)),
                        new Square(new Piece(Role.KNIGHT, Team.WHITE), new Position(1, 0)),
                        new Square(new Piece(Role.BISHOP, Team.WHITE), new Position(2, 0)),
                        new Square(new Piece(Role.KING, Team.WHITE), new Position(3, 0)),
                        new Square(new Piece(Role.QUEEN, Team.WHITE), new Position(4, 0)),
                        new Square(new Piece(Role.BISHOP, Team.WHITE), new Position(5, 0)),
                        new Square(new Piece(Role.KNIGHT, Team.WHITE), new Position(6, 0)),
                        new Square(new Piece(Role.ROOK, Team.WHITE), new Position(7, 0)),

                        new Square(new Piece(Role.ROOK, Team.BLACK), new Position(0, 7)),
                        new Square(new Piece(Role.KNIGHT, Team.BLACK), new Position(1, 7)),
                        new Square(new Piece(Role.BISHOP, Team.BLACK), new Position(2, 7)),
                        new Square(new Piece(Role.KING, Team.BLACK), new Position(3, 7)),
                        new Square(new Piece(Role.QUEEN, Team.BLACK), new Position(4, 7)),
                        new Square(new Piece(Role.BISHOP, Team.BLACK), new Position(5, 7)),
                        new Square(new Piece(Role.KNIGHT, Team.BLACK), new Position(6, 7)),
                        new Square(new Piece(Role.ROOK, Team.BLACK), new Position(7, 7)),

                        new Square(new Piece(Role.PAWN, Team.WHITE), new Position(0, 1)),
                        new Square(new Piece(Role.PAWN, Team.WHITE), new Position(1, 1)),
                        new Square(new Piece(Role.PAWN, Team.WHITE), new Position(2, 1)),
                        new Square(new Piece(Role.PAWN, Team.WHITE), new Position(3, 1)),
                        new Square(new Piece(Role.PAWN, Team.WHITE), new Position(4, 1)),
                        new Square(new Piece(Role.PAWN, Team.WHITE), new Position(5, 1)),
                        new Square(new Piece(Role.PAWN, Team.WHITE), new Position(6, 1)),
                        new Square(new Piece(Role.PAWN, Team.WHITE), new Position(7, 1)),

                        new Square(new Piece(Role.PAWN, Team.BLACK), new Position(0, 6)),
                        new Square(new Piece(Role.PAWN, Team.BLACK), new Position(1, 6)),
                        new Square(new Piece(Role.PAWN, Team.BLACK), new Position(2, 6)),
                        new Square(new Piece(Role.PAWN, Team.BLACK), new Position(3, 6)),
                        new Square(new Piece(Role.PAWN, Team.BLACK), new Position(4, 6)),
                        new Square(new Piece(Role.PAWN, Team.BLACK), new Position(5, 6)),
                        new Square(new Piece(Role.PAWN, Team.BLACK), new Position(6, 6)),
                        new Square(new Piece(Role.PAWN, Team.BLACK), new Position(7, 6))
                );
    }


}
