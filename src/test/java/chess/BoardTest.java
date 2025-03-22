package chess;

import static chess.Fixtures.*;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.piece.Team;

class BoardTest {

    @MethodSource
    @ParameterizedTest
    void 체스_보드를_생성하면_2선에_폰만존재한다(Position position) {
        Board board = new Board();

        assertThat(board.findByPosition(position)).isEqualTo(new Pawn(Team.A, position));
    }

    private static Stream<Arguments> 체스_보드를_생성하면_2선에_폰만존재한다() {
        return Stream.of(
                Arguments.of(A2), Arguments.of(B2), Arguments.of(C2), Arguments.of(D2),
                Arguments.of(E2), Arguments.of(F2), Arguments.of(G2), Arguments.of(H2),

                Arguments.of(A7), Arguments.of(B7), Arguments.of(C7), Arguments.of(D7),
                Arguments.of(E7), Arguments.of(F7), Arguments.of(G7), Arguments.of(H7)
        );
    }

    @MethodSource
    @ParameterizedTest
    void 체스_보드를_생성하면_일선에_기물들이_존재한다(Position position, Piece piece) {
        Board board = new Board();

        assertThat(board.findByPosition(position)).isEqualTo(piece);
    }

    private static Stream<Arguments> 체스_보드를_생성하면_일선에_기물들이_존재한다() {
        return Stream.of(
                Arguments.of(A1, new Rook(Team.A, A1)), Arguments.of(H1, new Rook(Team.A, H1)),
                Arguments.of(A8, new Rook(Team.B, A8)), Arguments.of(H8, new Rook(Team.B, H8)),
                Arguments.of(B1, new Knight(Team.A, B1)), Arguments.of(G1, new Knight(Team.A, G1)),
                Arguments.of(B8, new Knight(Team.B, B8)), Arguments.of(G8, new Knight(Team.B, G8)),
                Arguments.of(C1, new Bishop(Team.A, C1)), Arguments.of(F1, new Bishop(Team.A, F1)),
                Arguments.of(C8, new Bishop(Team.B, C8)), Arguments.of(F8, new Bishop(Team.B, F8)),
                Arguments.of(D1, new Queen(Team.A, D1)), Arguments.of(D8, new Queen(Team.B, D8)),
                Arguments.of(E1, new King(Team.A, E1)), Arguments.of(E8, new King(Team.B, E8))
        );
    }

}
