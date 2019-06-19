package chess.domain;

import chess.domain.piece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChessBoardTest {
    List<Character> aToH = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g');
    Board board;

    @BeforeEach
    void setUp() {
        board = Board.init();
    }

    @Test
    void 초기_체스판_폰위치_확인() {
        for (char chr : aToH) {
            assertThat(board.at(new Position(new Coordinate(chr), new Coordinate(2)))) // Team.BLACK Pawns
                    .isEqualTo(new Pawn(Team.BLACK));
            assertThat(board.at(new Position(new Coordinate(chr), new Coordinate(7)))) // Team.WHITE Pawns
                    .isEqualTo(new Pawn(Team.WHITE));
        }
    }

    @Test
    void 초기_체스판_폰_이외의_말_위치_확인() {
        List<Piece> piecesTeamBlack = Arrays.asList(new Rook(Team.BLACK), new Knight(Team.BLACK), new Bishop(Team.BLACK), new Queen(Team.BLACK),
                new King(Team.BLACK), new Bishop(Team.BLACK), new Knight(Team.BLACK), new Rook(Team.BLACK));
        List<Piece> piecesTeamWhite = Arrays.asList(new Rook(Team.WHITE), new Knight(Team.WHITE), new Bishop(Team.WHITE), new Queen(Team.WHITE),
                new King(Team.WHITE), new Bishop(Team.WHITE), new Knight(Team.WHITE), new Rook(Team.WHITE));
        for (int i = 0; i < aToH.size(); i++) {
            assertThat(board.at(new Position(new Coordinate(aToH.get(i)), new Coordinate(1))))
                    .isEqualTo(piecesTeamBlack.get(i));
            assertThat(board.at(new Position(new Coordinate(aToH.get(i)), new Coordinate(8))))
                    .isEqualTo(piecesTeamWhite.get(i));
        }
    }

    @Test
    void 말_이동_테스트() {
        Position target = new Position(new Coordinate('b'), new Coordinate(3));
        board.move(new Position(new Coordinate('b'), new Coordinate(2)), target); // Team.WHITE Pawn

        assertThat(board.at(target)).isEqualTo(new Pawn(Team.WHITE));
    }

    @Test
    void 말이_없는_경우_예외_테스트() {
        Position source = new Position(new Coordinate('b'), new Coordinate(4));
        Position target = new Position(new Coordinate('b'), new Coordinate(5));

        assertThrows(IllegalArgumentException.class, () -> {
            board.move(source, target);
        });
    }

    @Test
    void source위치와_target의_말_팀이_같은_경우_테스트() {
        Position source = new Position(new Coordinate('b'), new Coordinate(4));
        Position target = new Position(new Coordinate('b'), new Coordinate(5));

        assertThrows(IllegalArgumentException.class, () -> {
            board.move(source, target);
        });
    }

    @Test
    void 룩_경로에_말이_있는지_테스트() {
        Position source = new Position(new Coordinate('a'), new Coordinate(8)); // Team.WHITE Rook
        Position target = new Position(new Coordinate('a'), new Coordinate(1));

        assertThrows(IllegalArgumentException.class, () -> {
            board.move(source, target);
        });
    }

    @Test
    void 비숍_경로에_말이_있는지_테스트() {
        Position source = new Position(new Coordinate('c'), new Coordinate(1)); // Team.WHITE Bishop
        Position target = new Position(new Coordinate('e'), new Coordinate(3));

        assertThrows(IllegalArgumentException.class, () -> {
            board.move(source, target);
        });
    }

    @Test
    void 퀸_경로에_말이_있는지_테스트() {
        Position source = new Position(new Coordinate('d'), new Coordinate(1)); // Team.WHITE Queen
        Position target = new Position(new Coordinate('d'), new Coordinate(3));

        assertThrows(IllegalArgumentException.class, () -> {
            board.move(source, target);
        });
    }

    @Test
    void 연속으로_같은_팀이_진행하는_경우_테스트() {
        Position source1 = new Position(new Coordinate('b'), new Coordinate(2)); // Team.WHITE Rook
        Position target1 = new Position(new Coordinate('b'), new Coordinate(3));
        Position source2 = new Position(new Coordinate('c'), new Coordinate(2)); // Team.WHITE Rook
        Position target2 = new Position(new Coordinate('c'), new Coordinate(3));

        board.move(source1, target1);
        assertThrows(IllegalArgumentException.class, () -> {
            board.move(source2, target2);
        });
    }

    @Test
    void 선공이_아닌_black팀이_먼저_진행하는_경우_테스트() {
        Position source = new Position(new Coordinate('b'), new Coordinate(7)); // Team.BLACK Rook
        Position target = new Position(new Coordinate('b'), new Coordinate(6));

        assertThrows(IllegalArgumentException.class, () -> {
            board.move(source, target);
        });
    }
}
