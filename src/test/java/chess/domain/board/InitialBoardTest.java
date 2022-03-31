package chess.domain.board;

import static chess.domain.board.coordinate.Column.A;
import static chess.domain.board.coordinate.Column.B;
import static chess.domain.board.coordinate.Column.C;
import static chess.domain.board.coordinate.Column.D;
import static chess.domain.board.coordinate.Column.E;
import static chess.domain.board.coordinate.Column.F;
import static chess.domain.board.coordinate.Column.G;
import static chess.domain.board.coordinate.Column.H;
import static chess.domain.board.coordinate.Row.EIGHT;
import static chess.domain.board.coordinate.Row.FIVE;
import static chess.domain.board.coordinate.Row.FOUR;
import static chess.domain.board.coordinate.Row.ONE;
import static chess.domain.board.coordinate.Row.SEVEN;
import static chess.domain.board.coordinate.Row.SIX;
import static chess.domain.board.coordinate.Row.THREE;
import static chess.domain.board.coordinate.Row.TWO;
import static chess.domain.piece.Symbol.BISHOP;
import static chess.domain.piece.Symbol.EMPTY;
import static chess.domain.piece.Symbol.KING;
import static chess.domain.piece.Symbol.KNIGHT;
import static chess.domain.piece.Symbol.PAWN;
import static chess.domain.piece.Symbol.QUEEN;
import static chess.domain.piece.Symbol.ROOK;
import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.NONE;
import static chess.domain.piece.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.coordinate.Column;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.board.coordinate.Row;
import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class InitialBoardTest {

    private Map<Coordinate, Piece> map = InitialBoard.initialize();

    @ParameterizedTest(name = "{0}{1}에 {2}가 위치한다.")
    @MethodSource("provideParameters")
    void initialize(List<Column> columns, List<Row> rows, Piece piece) {
        for (Column column : columns) {
            checkPieceByCoordinate(rows, piece, column);
        }
    }

    private void checkPieceByCoordinate(List<Row> rows, Piece piece, Column column) {
        for (Row row : rows) {
            assertThat(map.get(Coordinate.of(column, row))).isEqualTo(piece);
        }
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.arguments(Arrays.asList(A, H),
                        Arrays.asList(EIGHT), new Rook(ROOK, BLACK)),
                Arguments.arguments(Arrays.asList(A, H),
                        Arrays.asList(ONE), new Rook(ROOK, WHITE)),
                Arguments.arguments(Arrays.asList(B, G),
                        Arrays.asList(EIGHT), new Knight(KNIGHT, BLACK)),
                Arguments.arguments(Arrays.asList(B, G),
                        Arrays.asList(ONE), new Knight(KNIGHT, WHITE)),
                Arguments.arguments(Arrays.asList(C, F),
                        Arrays.asList(EIGHT), new Bishop(BISHOP, BLACK)),
                Arguments.arguments(Arrays.asList(C, F),
                        Arrays.asList(ONE), new Bishop(BISHOP, WHITE)),
                Arguments.arguments(Arrays.asList(D),
                        Arrays.asList(EIGHT), new Queen(QUEEN, BLACK)),
                Arguments.arguments(Arrays.asList(D),
                        Arrays.asList(ONE), new Queen(QUEEN, WHITE)),
                Arguments.arguments(Arrays.asList(E),
                        Arrays.asList(EIGHT), new King(KING, BLACK)),
                Arguments.arguments(Arrays.asList(E),
                        Arrays.asList(ONE), new King(KING, WHITE)),
                Arguments.arguments(Arrays.asList(Column.values()),
                        Arrays.asList(SEVEN), new Pawn(PAWN, BLACK)),
                Arguments.arguments(Arrays.asList(Column.values()),
                        Arrays.asList(TWO), new Pawn(PAWN, WHITE)),
                Arguments.arguments(Arrays.asList(Column.values()),
                        Arrays.asList(THREE, FOUR, FIVE, SIX), new Empty(EMPTY, NONE))

        );
    }
}

