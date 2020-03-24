package chess.domain.chesspiece;

import chess.Position;
import chess.Team;
import chess.domain.ChessBoard;
import chess.factory.ChessPieceFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessPieceTest {
    @Test
    @DisplayName("ChessPiece 생성 테스트")
    void create() {
        assertThat(new ChessPiece(new Position(1, 'a'), Team.BLACK)).isInstanceOf(ChessPiece.class);
    }
}
