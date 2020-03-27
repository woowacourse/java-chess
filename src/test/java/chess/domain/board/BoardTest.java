package chess.domain.board;

import chess.domain.Pieces;
import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @ParameterizedTest
    @MethodSource("createTeamAndPieces")
    void findPiecesOf(Team team, Pieces expected) {
        Board board = BoardFactory.createInitially();
        Pieces pieces = board.findPiecesOf(team);

        assertThat(pieces).isEqualTo(expected);
    }

    private static Stream<Arguments> createTeamAndPieces() {
        return Stream.of(
                Arguments.of(Team.BLACK, new Pieces(new HashSet<>(Arrays.asList(
                        new Piece(Team.BLACK, PieceType.PAWN),
                        new Piece(Team.BLACK, PieceType.PAWN),
                        new Piece(Team.BLACK, PieceType.PAWN),
                        new Piece(Team.BLACK, PieceType.PAWN),
                        new Piece(Team.BLACK, PieceType.PAWN),
                        new Piece(Team.BLACK, PieceType.PAWN),
                        new Piece(Team.BLACK, PieceType.PAWN),
                        new Piece(Team.BLACK, PieceType.PAWN),

                        new Piece(Team.BLACK, PieceType.KING),
                        new Piece(Team.BLACK, PieceType.QUEEN),
                        new Piece(Team.BLACK, PieceType.BISHOP),
                        new Piece(Team.BLACK, PieceType.BISHOP),
                        new Piece(Team.BLACK, PieceType.KNIGHT),
                        new Piece(Team.BLACK, PieceType.KNIGHT),
                        new Piece(Team.BLACK, PieceType.ROOK),
                        new Piece(Team.BLACK, PieceType.ROOK)
                )))),
                Arguments.of(Team.WHITE, new Pieces(new HashSet<>(Arrays.asList(
                        new Piece(Team.WHITE, PieceType.PAWN),
                        new Piece(Team.WHITE, PieceType.PAWN),
                        new Piece(Team.WHITE, PieceType.PAWN),
                        new Piece(Team.WHITE, PieceType.PAWN),
                        new Piece(Team.WHITE, PieceType.PAWN),
                        new Piece(Team.WHITE, PieceType.PAWN),
                        new Piece(Team.WHITE, PieceType.PAWN),
                        new Piece(Team.WHITE, PieceType.PAWN),

                        new Piece(Team.WHITE, PieceType.KING),
                        new Piece(Team.WHITE, PieceType.QUEEN),
                        new Piece(Team.WHITE, PieceType.BISHOP),
                        new Piece(Team.WHITE, PieceType.BISHOP),
                        new Piece(Team.WHITE, PieceType.KNIGHT),
                        new Piece(Team.WHITE, PieceType.KNIGHT),
                        new Piece(Team.WHITE, PieceType.ROOK),
                        new Piece(Team.WHITE, PieceType.ROOK)
                ))))
        );
    }
}