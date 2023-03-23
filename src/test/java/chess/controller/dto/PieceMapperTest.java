package chess.controller.dto;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PieceMapperTest {

    private static final Empty EMPTY = Empty.create();
    private static final Pawn WHITE_PAWN = Pawn.create(Color.WHITE);
    private static final Pawn BLACK_PAWN = Pawn.create(Color.BLACK);
    private static final Bishop WHITE_BISHOP = Bishop.create(Color.WHITE);
    private static final Bishop BLACK_BISHOP = Bishop.create(Color.BLACK);
    private static final Knight WHITE_KNIGHT = Knight.create(Color.WHITE);
    private static final Knight BLACK_KNIGHT = Knight.create(Color.BLACK);
    private static final Rook WHITE_ROOK = Rook.create(Color.WHITE);
    private static final Rook BLACK_ROOK = Rook.create(Color.BLACK);
    private static final Queen WHITE_QUEEN = Queen.create(Color.WHITE);
    private static final Queen BLACK_QUEEN = Queen.create(Color.BLACK);
    private static final King WHITE_KING = King.create(Color.WHITE);
    private static final King BLACK_KING = King.create(Color.BLACK);


    @ParameterizedTest
    @MethodSource("pieces")
    @DisplayName("EMPTY 타입의 Piece를 인자로 받으면 .를 반환한다")
    void empty_piece(Piece piece, String result) {
        assertThat(PieceMapper.map(piece)).isEqualTo(result);
    }

    static Stream<Arguments> pieces() {
        return Stream.of(
                Arguments.of(EMPTY, "."),
                Arguments.of(WHITE_PAWN, "p"),
                Arguments.of(BLACK_PAWN, "P"),
                Arguments.of(WHITE_BISHOP, "b"),
                Arguments.of(BLACK_BISHOP, "B"),
                Arguments.of(WHITE_KNIGHT, "n"),
                Arguments.of(BLACK_KNIGHT, "N"),
                Arguments.of(WHITE_ROOK, "r"),
                Arguments.of(BLACK_ROOK, "R"),
                Arguments.of(WHITE_QUEEN, "q"),
                Arguments.of(BLACK_QUEEN, "Q"),
                Arguments.of(WHITE_KING, "k"),
                Arguments.of(BLACK_KING, "K")
        );
    }

}
