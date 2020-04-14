package domain.coordinate;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Distance {
	ONE(DistancePredicates::isOne),
	VERTICAL_TWO(DistancePredicates::isVerticalTwo),
	ELSE(DistancePredicates::isElse);

	private final BiPredicate<Integer, Integer> biPredicate;

	Distance(BiPredicate<Integer, Integer> biPredicate) {
		this.biPredicate = biPredicate;
	}

	public static Distance of(Coordinate from, Coordinate to) {
		int rowDifference = Math.abs(to.getRowIndex() - from.getRowIndex());
		int columnDifference = Math.abs(to.getColumnIndex() - from.getColumnIndex());

		return Arrays.stream(values())
				.filter(distance -> distance.biPredicate.test(rowDifference, columnDifference))
				.findFirst()
				.orElseThrow(RuntimeException::new);
	}
}
