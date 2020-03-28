package domain.point;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Distance {
	ONE(DistanceFilter::isOne),
	TWO_VERTICAL(DistanceFilter::isVerticalTwo),
	ELSE(DistanceFilter::isElse);

	private final BiFunction<Integer, Integer, Boolean> distanceFilter;

	Distance(BiFunction<Integer, Integer, Boolean> distanceFilter) {
		this.distanceFilter = distanceFilter;
	}

	public static Distance of(Point a, Point b) {
		int rowDifference = Math.abs(b.getRowIndex() - a.getRowIndex());
		int columnDifference = Math.abs(b.getColumnIndex() - a.getColumnIndex());

		return Arrays.stream(values())
				.filter(distance -> distance.distanceFilter.apply(rowDifference, columnDifference))
				.findFirst()
				.orElseThrow(RuntimeException::new);
	}
}
