package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Rank;
import chess.domain.board.position.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {


    private Board board;

    @BeforeEach
    void setUp() {
        Map<Position, Piece> squares1 = new LinkedHashMap<>();
        squares1.put(Position.of("a1"), Rook.createWhite());
        squares1.put(Position.of("b1"), Knight.createWhite());
        squares1.put(Position.of("c1"), Bishop.createWhite());
        squares1.put(Position.of("d1"), Queen.createWhite());
        squares1.put(Position.of("e1"), King.createWhite());

        Map<Position, Piece> squares2 = new LinkedHashMap<>();
        squares2.put(Position.of("a2"), Pawn.createWhite());
        squares2.put(Position.of("b2"), Pawn.createWhite());

        List<Rank> ranks = new ArrayList<>(Arrays.asList(
            new Rank(squares1),
            new Rank(squares2)
        ));

        this.board = new Board(ranks);
    }

    @Test
    @DisplayName("체스보드 생성 테스트")
    void testCreate() {
        List<Rank> list = new ArrayList<>();
        assertThat(new Board(list)).isInstanceOf(Board.class);
    }

    @Test
    @DisplayName("좌표에 해당하는 체스말 찾아오기")
    void testFindPieceByPosition() {
        assertThat(this.board.pieceByPosition(Position.of("a1"))).isEqualTo(Rook.createWhite());
        assertThat(this.board.pieceByPosition(Position.of("b1"))).isEqualTo(Knight.createWhite());
        assertThat(this.board.pieceByPosition(Position.of("a2"))).isEqualTo(Pawn.createWhite());
        assertThat(this.board.pieceByPosition(Position.of("b2"))).isEqualTo(Pawn.createWhite());
    }

    @Test
    @DisplayName("체스보드 출력을 위한 전체 보드 반환")
    void testGetRanks() {
        assertThat(this.board.ranks()).hasSize(2);

        Rank rank1 = this.board.ranks().get(0);
        assertThat(rank1.squares()).containsExactly(
            Rook.createWhite(), Knight.createWhite(), Bishop.createWhite(),
            Queen.createWhite(), King.createWhite()
        );

        Rank rank2 = this.board.ranks().get(1);
        assertThat(rank2.squares()).containsExactly(Pawn.createWhite(), Pawn.createWhite());
    }

    @Test
    @DisplayName("현재 체스보드에 양쪽 King이 살아있는지 확인")
    void testIsAliveBothKings() {
        assertThat(this.board.isAliveBothKings()).isFalse();

        Map<Position, Piece> squares1 = new HashMap<>();
        squares1.put(Position.of("e8"), King.createBlack());
        Map<Position, Piece> squares2 = new HashMap<>();
        squares2.put(Position.of("e1"), King.createWhite());

        List<Rank> ranks = new ArrayList<>(Arrays.asList(
            new Rank(squares1),
            new Rank(squares2)
        ));

        this.board = new Board(ranks);

        assertThat(this.board.isAliveBothKings()).isTrue();

    }
}
