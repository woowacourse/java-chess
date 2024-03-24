package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.point.Point;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardFactoryTest {

    @Test
    @DisplayName("체스 초기 상태의 기물들을 생성할 수 있다.")
    void create() {
        Map<Point, Piece> pieces = BoardFactory.createInitialChessBoard();

        long emptyCount = pieces.values().stream()
                .filter(piece -> Piece.empty().equals(piece))
                .count();
        long WhiteKingCount = pieces.values().stream()
                .filter(piece -> Piece.kingFrom(Team.WHITE).equals(piece))
                .count();
        long BlackKingCount = pieces.values().stream()
                .filter(piece -> Piece.kingFrom(Team.BLACK).equals(piece))
                .count();


        Assertions.assertAll(
                () -> assertThat(pieces).hasSize(64),
                () -> assertThat(emptyCount).isEqualTo(32),
                () -> assertThat(WhiteKingCount).isEqualTo(1),
                () -> assertThat(BlackKingCount).isEqualTo(1)
        );
    }
}
