package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Rank;
import chess.domain.board.position.InitPosition;
import chess.domain.board.position.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
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
        assertThat(rank1.pieces()).containsExactly(
            Rook.createWhite(), Knight.createWhite(), Bishop.createWhite(),
            Queen.createWhite(), King.createWhite()
        );

        Rank rank2 = this.board.ranks().get(1);
        assertThat(rank2.pieces()).containsExactly(Pawn.createWhite(), Pawn.createWhite());
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

    @Test
    @DisplayName("현재 체스보드에 존재하는 말의 위치 이동")
    void testMoveIfValidPosition() {
        this.board = new Board(InitPosition.initRanks());

        assertThat(this.board.pieceByPosition(Position.of("a2"))).isEqualTo(Pawn.createWhite());
        assertThat(this.board.pieceByPosition(Position.of("a4"))).isEqualTo(Empty.create());

        this.board.moveIfValidPosition(Position.of("a2"), Position.of("a4"));

        assertThat(this.board.pieceByPosition(Position.of("a2"))).isEqualTo(Empty.create());
        assertThat(this.board.pieceByPosition(Position.of("a4"))).isEqualTo(Pawn.createWhite());
    }

    @Test
    @DisplayName("Board 생성자에 인자를 넣지 않을 시 자동으로 초기화된 체스보드가 생성되는지 테스트")
    void testInitChessBoardByNonParameterConstructor() {
        this.board = new Board();
        assertThat(board.pieceByPosition(Position.of("a8"))).isEqualTo(Rook.createBlack());
        assertThat(board.pieceByPosition(Position.of("b8"))).isEqualTo(Knight.createBlack());
        assertThat(board.pieceByPosition(Position.of("c8"))).isEqualTo(Bishop.createBlack());
        assertThat(board.pieceByPosition(Position.of("d8"))).isEqualTo(Queen.createBlack());
        assertThat(board.pieceByPosition(Position.of("e8"))).isEqualTo(King.createBlack());
        assertThat(board.pieceByPosition(Position.of("f8"))).isEqualTo(Bishop.createBlack());
        assertThat(board.pieceByPosition(Position.of("g8"))).isEqualTo(Knight.createBlack());
        assertThat(board.pieceByPosition(Position.of("h8"))).isEqualTo(Rook.createBlack());

        assertThat(board.pieceByPosition(Position.of("a7"))).isEqualTo(Pawn.createBlack());
        assertThat(board.pieceByPosition(Position.of("b7"))).isEqualTo(Pawn.createBlack());
        assertThat(board.pieceByPosition(Position.of("c7"))).isEqualTo(Pawn.createBlack());
        assertThat(board.pieceByPosition(Position.of("d7"))).isEqualTo(Pawn.createBlack());
        assertThat(board.pieceByPosition(Position.of("e7"))).isEqualTo(Pawn.createBlack());
        assertThat(board.pieceByPosition(Position.of("f7"))).isEqualTo(Pawn.createBlack());
        assertThat(board.pieceByPosition(Position.of("g7"))).isEqualTo(Pawn.createBlack());
        assertThat(board.pieceByPosition(Position.of("h7"))).isEqualTo(Pawn.createBlack());

        assertThat(board.pieceByPosition(Position.of("a6"))).isEqualTo(Empty.create());
        assertThat(board.pieceByPosition(Position.of("b5"))).isEqualTo(Empty.create());
        assertThat(board.pieceByPosition(Position.of("c4"))).isEqualTo(Empty.create());
        assertThat(board.pieceByPosition(Position.of("d3"))).isEqualTo(Empty.create());
        assertThat(board.pieceByPosition(Position.of("e4"))).isEqualTo(Empty.create());
        assertThat(board.pieceByPosition(Position.of("f5"))).isEqualTo(Empty.create());
        assertThat(board.pieceByPosition(Position.of("g6"))).isEqualTo(Empty.create());
        assertThat(board.pieceByPosition(Position.of("h5"))).isEqualTo(Empty.create());

        assertThat(board.pieceByPosition(Position.of("a1"))).isEqualTo(Rook.createWhite());
        assertThat(board.pieceByPosition(Position.of("b1"))).isEqualTo(Knight.createWhite());
        assertThat(board.pieceByPosition(Position.of("c1"))).isEqualTo(Bishop.createWhite());
        assertThat(board.pieceByPosition(Position.of("d1"))).isEqualTo(Queen.createWhite());
        assertThat(board.pieceByPosition(Position.of("e1"))).isEqualTo(King.createWhite());
        assertThat(board.pieceByPosition(Position.of("f1"))).isEqualTo(Bishop.createWhite());
        assertThat(board.pieceByPosition(Position.of("g1"))).isEqualTo(Knight.createWhite());
        assertThat(board.pieceByPosition(Position.of("h1"))).isEqualTo(Rook.createWhite());

        assertThat(board.pieceByPosition(Position.of("a2"))).isEqualTo(Pawn.createWhite());
        assertThat(board.pieceByPosition(Position.of("b2"))).isEqualTo(Pawn.createWhite());
        assertThat(board.pieceByPosition(Position.of("c2"))).isEqualTo(Pawn.createWhite());
        assertThat(board.pieceByPosition(Position.of("d2"))).isEqualTo(Pawn.createWhite());
        assertThat(board.pieceByPosition(Position.of("e2"))).isEqualTo(Pawn.createWhite());
        assertThat(board.pieceByPosition(Position.of("f2"))).isEqualTo(Pawn.createWhite());
        assertThat(board.pieceByPosition(Position.of("g2"))).isEqualTo(Pawn.createWhite());
        assertThat(board.pieceByPosition(Position.of("h2"))).isEqualTo(Pawn.createWhite());
    }
}
