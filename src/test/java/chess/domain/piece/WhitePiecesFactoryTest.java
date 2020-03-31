package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 *    @author AnHyungJu
 */
class WhitePiecesFactoryTest {
	@DisplayName("정상적으로 흰 색말들이 생성되었는지 확인")
	@Test
	void createTest() {
		assertThat(WhitePiecesFactory.create())
			.isInstanceOf(Pieces.class);
	}
}