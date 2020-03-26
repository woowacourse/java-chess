//package chess.board;
//
//import static org.assertj.core.api.Assertions.*;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//class LocationTest {
//
//	@DisplayName("로케이션 절대위치 이동")
//	@Test
//	void moveTo() {
//		Location location = new Location(1, 'a');
//		Location actual = location.moveTo(2, 'c');
//
//		Location expect = new Location(2, 'c');
//
//		assertThat(actual).isEqualTo(expect);
//	}
//
//	@DisplayName("로케이션의 로우를 value만큼 이동")
//	@Test
//	void moveRowBy() {
//		Location location = new Location(1, 'a');
//		Location actual = location.moveRowBy(1);
//
//		Location expect = new Location(2, 'a');
//
//		assertThat(actual).isEqualTo(expect);
//	}
//
//	@DisplayName("로케이션의 컬럼을 value만큼 이동")
//	@Test
//	void moveColBy() {
//		Location location = new Location(1, 'a');
//		Location actual = location.moveColBy(1);
//
//		Location expect = new Location(1, 'b');
//
//		assertThat(actual).isEqualTo(expect);
//	}
//}