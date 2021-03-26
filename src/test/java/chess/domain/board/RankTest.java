package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.position.Position;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    private Rank rank;

    @BeforeEach
    void setUp() {
        Map<Position, Piece> map = new HashMap<>();
        map.put(Position.of("a7"), Pawn.createBlack());
        map.put(Position.of("b7"), Pawn.createBlack());
        map.put(Position.of("c7"), Pawn.createBlack());
        this.rank = new Rank(map);
    }

    @Test
    @DisplayName("체스판 가로줄 Rank 생성 테스트")
    void testCreate() {
        assertThat(new Rank(new HashMap<>())).isInstanceOf(Rank.class);
    }

    @Test
    @DisplayName("체스판 가로줄 Rank에 원하는 좌표가 있는지 확인 테스트")
    void testFindPosition() {
        assertThat(this.rank.hasPosition(Position.of("a7"))).isTrue();
        assertThat(this.rank.hasPosition(Position.of("a8"))).isFalse();
    }

    @Test
    @DisplayName("체스판 가로줄 Rank에서 원하는 좌표에 존재하는 체스말 가져오기")
    void testGetPieceOfPosition() {
        assertThat(this.rank.piece(Position.of("a7"))).isEqualTo(Pawn.createBlack());
    }

    @Test
    @DisplayName("체스판 출력을 위한 squares 반환")
    void testSquares() {
        assertThat(this.rank.pieces()).containsExactly(
            Pawn.createBlack(),
            Pawn.createBlack(),
            Pawn.createBlack()
        );
    }

    @Test
    @DisplayName("체스판 Rank 좌표에 존재하는 체스말 바꾸기")
    void testReplacePiece() {
        assertThat(this.rank.piece(Position.of("a7"))).isEqualTo(Pawn.createBlack());
        this.rank.replacePiece(Position.of("a7"), King.createBlack());
        assertThat(this.rank.piece(Position.of("a7"))).isEqualTo(King.createBlack());
    }
}