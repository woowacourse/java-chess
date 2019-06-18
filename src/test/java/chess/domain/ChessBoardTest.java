package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardTest {
    List<Character> aToH = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g');


    @Test
    void 초기_체스판_폰위치_확인() {
        for (char chr : aToH) {
            assertThat(Board.at(new Position(new Coordinate(chr), new Coordinate(2))))
                    .isEqualTo(new Pawn(Team.BLACK));
            assertThat(Board.at(new Position(new Coordinate(chr), new Coordinate(7))))
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
            assertThat(Board.at(new Position(new Coordinate(aToH.get(i)), new Coordinate(1))))
                    .isEqualTo(piecesTeamBlack.get(i));
            assertThat(Board.at(new Position(new Coordinate(aToH.get(i)), new Coordinate(8))))
                    .isEqualTo(piecesTeamWhite.get(i));
        }
    }

    @Test
    void 말_이동_테스트() {
        Position target = new Position(new Coordinate('b'), new Coordinate(3));
        Board.move(new Position(new Coordinate('b'), new Coordinate(2)), target);

        assertThat(Board.at(target)).isEqualTo(new Pawn(Team.BLACK));
    }
}
