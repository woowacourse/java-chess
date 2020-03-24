package chess.factory;

import chess.Team;
import chess.domain.chesspiece.ChessPiece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class ChessPieceFactoryTest {
    @Test
    @DisplayName("ChessPieceFactory 생성 테스트")
    void create() {
        List<ChessPiece> blackChessPieces = ChessPieceFactory.create(Team.BLACK);
        List<ChessPiece> whiteChessPieces = ChessPieceFactory.create(Team.WHITE);
        assertThat(blackChessPieces.size()).isEqualTo(16);
        assertThat(whiteChessPieces.size()).isEqualTo(16);
    }
}
