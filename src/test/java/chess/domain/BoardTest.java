package chess.domain;

import chess.domain.piece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static chess.domain.utils.InputParser.position;
import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {
    Board board;

    @BeforeEach
    void setUp() {
        board = Board.init();
    }

    @Test
    void 초기_체스판_폰위치_확인() {
        IntStream.rangeClosed(1, 8)
                .forEach(i -> {
                    assertThat(board.at(new Position(new Coordinate(i), new Coordinate(2)))) // Team.WHITE Pawns
                            .isEqualTo(new Pawn(Team.WHITE));
                    assertThat(board.at(new Position(new Coordinate(i), new Coordinate(7)))) // Team.BLACK Pawns
                            .isEqualTo(new Pawn(Team.BLACK));
                });
    }

    @Test
    void 초기_체스판_폰_이외의_말_위치_확인() {
        List<Piece> piecesTeamBlack = Arrays.asList(
                new Rook(Team.BLACK), new Knight(Team.BLACK), new Bishop(Team.BLACK), new Queen(Team.BLACK),
                new King(Team.BLACK), new Bishop(Team.BLACK), new Knight(Team.BLACK), new Rook(Team.BLACK)
        );
        List<Piece> piecesTeamWhite = Arrays.asList(
                new Rook(Team.WHITE), new Knight(Team.WHITE), new Bishop(Team.WHITE), new Queen(Team.WHITE),
                new King(Team.WHITE), new Bishop(Team.WHITE), new Knight(Team.WHITE), new Rook(Team.WHITE)
        );
        IntStream.rangeClosed(1, 8)
                .forEach(i -> {
                    assertThat(board.at(new Position(new Coordinate(i), new Coordinate(1))))
                            .isEqualTo(piecesTeamWhite.get(i - 1));
                    assertThat(board.at(new Position(new Coordinate(i), new Coordinate(8))))
                            .isEqualTo(piecesTeamBlack.get(i - 1));
                });
    }

    @Test
    void 말_이동_테스트() {
        Position target = position("b3");
        Position source = position("b2");
        board.move(source, target, board.at(source)); // Team.WHITE Pawn

        assertThat(board.at(target)).isEqualTo(new Pawn(Team.WHITE));
    }
}
