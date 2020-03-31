package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *    class description
 *
 *    @author AnHyungJu
 */
class BlackPiecesFactoryTest {
	@DisplayName("정상적으로 검은색 말들이 생성되었는지 확인")
	@Test
	void createTest() {
		assertThat(BlackPiecesFactory.create())
			.isInstanceOf(Pieces.class);
	}
}