//package chess.factory;
//
//import static org.assertj.core.api.Assertions.*;
//
//import java.util.List;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import chess.domain.chesspiece.ChessPiece;
//
//public class ChessPieceFactoryTest {
//	@Test
//	@DisplayName("ChessPieceFactory 생성 테스트")
//	void create() {
//		List<ChessPiece> blackChessPieces = RowFactory.blackTeamCreate();
//		List<ChessPiece> whiteChessPieces = RowFactory.whiteTeamCreate();
//		assertThat(blackChessPieces.size()).isEqualTo(16);
//		assertThat(whiteChessPieces.size()).isEqualTo(16);
//	}
//}
