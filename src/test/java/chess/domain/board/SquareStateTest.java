package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.MoveVector;
import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SquareStateTest {

    @Test
    @DisplayName("스퀘어가 제대로 캐시되어 있는지 확인")
    void getCachedSquareState() {
        assertThat(SquareState.of(Piece.KING, Team.WHITE))
            .isSameAs(SquareState.of(Piece.KING, Team.WHITE));
    }

    @ParameterizedTest
    @DisplayName("source 에서 destination 으로 가기위한 MovementVector 반환(PAWN)")
    @CsvSource(value = {"c7,f4:1,-1", "c7,c2:0,-1"}, delimiter = ':')
    void movableVectorOfPawn(String input, String expectedOutput) {
        SquareState squareState = SquareState.of(Piece.PAWN, Team.BLACK);
        List<Point> points = Arrays.stream(input.split(","))
            .map(Point::of)
            .collect(Collectors.toList());

        List<Integer> vectorValues = Arrays.stream(expectedOutput.split(","))
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        assertThat(squareState.movableVector(points.get(0), points.get(1)))
            .isEqualTo(MoveVector.moveVectorByValues(vectorValues.get(0), vectorValues.get(1)));
    }

    @ParameterizedTest
    @DisplayName("source 에서 destination 으로 가기위한 MovementVector 가 없는 경우 (PAWN)")
    @CsvSource(value = {"c7,e4", "c7,b2", "c7,c8"})
    void notMovableVectorOfPawn(String source, String destination) {
        Point sourcePoint = Point.of(source);
        Point destinationPoint = Point.of(destination);
        SquareState squareState = SquareState.of(Piece.PAWN, Team.BLACK);

        assertThat(squareState.hasMovableVector(sourcePoint, destinationPoint)).isFalse();
    }

    @ParameterizedTest
    @DisplayName("source 에서 destination 으로 가기위한 MovementVector 반환(KING, QUEEN)")
    @CsvSource(value = {"d5,d3:0,-1", "d5,a5:-1,0", "d5,h5:1,0", "d5,d8:0,1", "d5,f3:1,-1"}
        , delimiter = ':')
    void movableVectorOfKingAndQueen(String input, String expectedOutput) {
        SquareState kingState = SquareState.of(Piece.KING, Team.BLACK);
        SquareState queenState = SquareState.of(Piece.QUEEN, Team.BLACK);
        List<Point> points = Arrays.stream(input.split(","))
            .map(Point::of)
            .collect(Collectors.toList());

        List<Integer> vectorValues = Arrays.stream(expectedOutput.split(","))
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        assertThat(kingState.movableVector(points.get(0), points.get(1)))
            .isEqualTo(MoveVector.moveVectorByValues(vectorValues.get(0), vectorValues.get(1)));
        assertThat(queenState.movableVector(points.get(0), points.get(1)))
            .isEqualTo(MoveVector.moveVectorByValues(vectorValues.get(0), vectorValues.get(1)));
    }

    @ParameterizedTest
    @DisplayName("source 에서 destination 으로 가기위한 MovementVector 가 없는 경우 (KING, QUEEN)")
    @CsvSource(value = {"d5,e2", "d5,f4"})
    void notMovableVectorOfKingAndQueen(String source, String destination) {
        SquareState squareState = SquareState.of(Piece.PAWN, Team.BLACK);
        SquareState queenState = SquareState.of(Piece.QUEEN, Team.BLACK);
        Point sourcePoint = Point.of(source);
        Point destinationPoint = Point.of(destination);

        assertThat(squareState.hasMovableVector(sourcePoint, destinationPoint)).isFalse();
        assertThat(queenState.hasMovableVector(sourcePoint, destinationPoint)).isFalse();
    }

    @ParameterizedTest
    @DisplayName("source 에서 destination 으로 가기위한 MovementVector 반환(ROOK)")
    @CsvSource(value = {"d5,d3:0,-1", "d5,a5:-1,0", "d5,h5:1,0", "d5,d8:0,1"}, delimiter = ':')
    void movableVectorOfRook(String input, String expectedOutput) {
        SquareState squareState = SquareState.of(Piece.ROOK, Team.BLACK);
        List<Point> points = Arrays.stream(input.split(","))
            .map(Point::of)
            .collect(Collectors.toList());

        List<Integer> vectorValues = Arrays.stream(expectedOutput.split(","))
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        assertThat(squareState.movableVector(points.get(0), points.get(1)))
            .isEqualTo(MoveVector.moveVectorByValues(vectorValues.get(0), vectorValues.get(1)));
    }

    @ParameterizedTest
    @DisplayName("source 에서 destination 으로 가기위한 MovementVector 가 없는 경우 (ROOK)")
    @CsvSource(value = {"c7,d6", "c7,b6"})
    void notMovableVectorOfRook(String source, String destination) {
        SquareState squareState = SquareState.of(Piece.ROOK, Team.BLACK);
        Point sourcePoint = Point.of(source);
        Point destinationPoint = Point.of(destination);

        assertThat(squareState.hasMovableVector(sourcePoint, destinationPoint)).isFalse();
    }

    @ParameterizedTest
    @DisplayName("source 에서 destination 으로 가기위한 MovementVector 반환(BISHOP)")
    @CsvSource(value = {"d5,e4:1,-1", "d5,e6:1,1", "d5,c4:-1,-1", "d5,c6:-1,1"}, delimiter = ':')
    void movableVectorOfBishop(String input, String expectedOutput) {
        SquareState squareState = SquareState.of(Piece.BISHOP, Team.BLACK);
        List<Point> points = Arrays.stream(input.split(","))
            .map(Point::of)
            .collect(Collectors.toList());

        List<Integer> vectorValues = Arrays.stream(expectedOutput.split(","))
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        assertThat(squareState.movableVector(points.get(0), points.get(1)))
            .isEqualTo(MoveVector.moveVectorByValues(vectorValues.get(0), vectorValues.get(1)));
    }

    @ParameterizedTest
    @DisplayName("source 에서 destination 으로 가기위한 MovementVector 가 없는 경우 (BISHOP)")
    @CsvSource(value = {"c7,c5", "c7,a7"})
    void notMovableVectorOfBishop(String source, String destination) {
        SquareState squareState = SquareState.of(Piece.BISHOP, Team.BLACK);
        Point sourcePoint = Point.of(source);
        Point destinationPoint = Point.of(destination);

        assertThat(squareState.hasMovableVector(sourcePoint, destinationPoint)).isFalse();
    }

    @ParameterizedTest
    @DisplayName("source 에서 destination 으로 가기위한 MovementVector 반환(KNIGHT)")
    @CsvSource(value = {"d5,f4:2,-1", "d5,c3:-1,-2", "d5,b6:-2,1", "d5,e7:1,2"}, delimiter = ':')
    void movableVectorOfKnight(String input, String expectedOutput) {
        SquareState squareState = SquareState.of(Piece.KNIGHT, Team.BLACK);
        List<Point> points = Arrays.stream(input.split(","))
            .map(Point::of)
            .collect(Collectors.toList());

        List<Integer> vectorValues = Arrays.stream(expectedOutput.split(","))
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        assertThat(squareState.movableVector(points.get(0), points.get(1)))
            .isEqualTo(MoveVector.moveVectorByValues(vectorValues.get(0), vectorValues.get(1)));
    }

    @ParameterizedTest
    @DisplayName("source 에서 destination 으로 가기위한 MovementVector 가 없는 경우 (KNIGHT)")
    @CsvSource(value = {"c7,c5", "c7,b6"})
    void notMovableVectorOfKnight(String source, String destination) {
        SquareState squareState = SquareState.of(Piece.KNIGHT, Team.BLACK);
        Point sourcePoint = Point.of(source);
        Point destinationPoint = Point.of(destination);

        assertThat(squareState.hasMovableVector(sourcePoint, destinationPoint)).isFalse();
    }
}