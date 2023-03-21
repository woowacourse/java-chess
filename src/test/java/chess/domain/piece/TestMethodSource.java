package chess.domain.piece;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

public class TestMethodSource {

	public static Stream<Arguments> providePawnAvailableRelativePositionSource() {
		return Stream.of(
			Arguments.of(0, 1),
			Arguments.of(1, 1),
			Arguments.of(-1, 1)
		);
	}

	public static Stream<Arguments> providePawnUnavailableRelativePositionSource() {
		return Stream.of(
			Arguments.of(1, 0),
			Arguments.of(0, -1),
			Arguments.of(-1, 0),
			Arguments.of(1, -1),
			Arguments.of(-1, -1),
			Arguments.of(1, 2),
			Arguments.of(2, 1),
			Arguments.of(2, -1),
			Arguments.of(1, -2),
			Arguments.of(-1, -2),
			Arguments.of(-2, -1),
			Arguments.of(-2, 1),
			Arguments.of(-1, 2)
		);
	}

	public static Stream<Arguments> provideCrossRelativePositionSource() {
		return Stream.of(
			Arguments.of(0, 1),
			Arguments.of(1, 0),
			Arguments.of(0, -1),
			Arguments.of(-1, 0)
		);
	}

	public static Stream<Arguments> provideDiagonalRelativePositionSource() {
		return Stream.of(
			Arguments.of(1, 1),
			Arguments.of(1, -1),
			Arguments.of(-1, -1),
			Arguments.of(-1, 1)
		);
	}

	public static Stream<Arguments> provideLShapedRelativePositionSource() {
		return Stream.of(
			Arguments.of(1, 2),
			Arguments.of(2, 1),
			Arguments.of(2, -1),
			Arguments.of(1, -2),
			Arguments.of(-1, -2),
			Arguments.of(-2, -1),
			Arguments.of(-2, 1),
			Arguments.of(-1, 2)
		);
	}

	public static Stream<Arguments> provideCrossAndDiagonalRelativePositionSource() {
		return Stream.of(
			Arguments.of(0, 1),
			Arguments.of(1, 0),
			Arguments.of(0, -1),
			Arguments.of(-1, 0),
			Arguments.of(1, 1),
			Arguments.of(1, -1),
			Arguments.of(-1, -1),
			Arguments.of(-1, 1)
		);
	}

	public static Stream<Arguments> provideAllDirectionRelativePositionSource() {
		return Stream.of(
			Arguments.of(0, 1),
			Arguments.of(1, 0),
			Arguments.of(0, -1),
			Arguments.of(-1, 0),
			Arguments.of(1, 1),
			Arguments.of(1, -1),
			Arguments.of(-1, -1),
			Arguments.of(-1, 1),
			Arguments.of(1, 2),
			Arguments.of(2, 1),
			Arguments.of(2, -1),
			Arguments.of(1, -2),
			Arguments.of(-1, -2),
			Arguments.of(-2, -1),
			Arguments.of(-2, 1),
			Arguments.of(-1, 2)
		);
	}
}
