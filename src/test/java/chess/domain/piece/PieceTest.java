package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PieceTest {

    @ParameterizedTest
    @DisplayName("흑백팀 기물 이름을 올바르게 가져온다.")
    @MethodSource("checkNameParameter")
    void checkName(Piece piece, String name) {
        assertThat(piece.getName()).isEqualTo(name);
    }

    private static Stream<Arguments> checkNameParameter() {
        return Stream.of(
                Arguments.arguments(new King(Team.BLACK), "K"),
                Arguments.arguments(new King(Team.WHITE), "k"),
                Arguments.arguments(new Rook(Team.BLACK), "R"),
                Arguments.arguments(new Rook(Team.WHITE), "r"),
                Arguments.arguments(new Pawn(Team.BLACK), "P"),
                Arguments.arguments(new Pawn(Team.WHITE), "p"),
                Arguments.arguments(new Bishop(Team.BLACK), "B"),
                Arguments.arguments(new Bishop(Team.WHITE), "b"),
                Arguments.arguments(new Knight(Team.BLACK), "N"),
                Arguments.arguments(new Knight(Team.WHITE), "n"),
                Arguments.arguments(new Queen(Team.BLACK), "Q"),
                Arguments.arguments(new Queen(Team.WHITE), "q")
        );
    }

}
