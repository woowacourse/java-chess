package chess.domain.board;

import static chess.domain.fixture.CoordinateFixture.A1;
import static chess.domain.fixture.CoordinateFixture.A2;
import static chess.domain.fixture.CoordinateFixture.A3;
import static chess.domain.fixture.CoordinateFixture.A4;
import static chess.domain.fixture.CoordinateFixture.A5;
import static chess.domain.fixture.CoordinateFixture.A7;
import static chess.domain.fixture.CoordinateFixture.A8;
import static chess.domain.fixture.CoordinateFixture.B1;
import static chess.domain.fixture.CoordinateFixture.B2;
import static chess.domain.fixture.CoordinateFixture.B3;
import static chess.domain.fixture.CoordinateFixture.B7;
import static chess.domain.fixture.CoordinateFixture.B8;
import static chess.domain.fixture.CoordinateFixture.C1;
import static chess.domain.fixture.CoordinateFixture.C2;
import static chess.domain.fixture.CoordinateFixture.C3;
import static chess.domain.fixture.CoordinateFixture.C7;
import static chess.domain.fixture.CoordinateFixture.C8;
import static chess.domain.fixture.CoordinateFixture.D1;
import static chess.domain.fixture.CoordinateFixture.D2;
import static chess.domain.fixture.CoordinateFixture.D4;
import static chess.domain.fixture.CoordinateFixture.D7;
import static chess.domain.fixture.CoordinateFixture.D8;
import static chess.domain.fixture.CoordinateFixture.E1;
import static chess.domain.fixture.CoordinateFixture.E2;
import static chess.domain.fixture.CoordinateFixture.E7;
import static chess.domain.fixture.CoordinateFixture.E8;
import static chess.domain.fixture.CoordinateFixture.F1;
import static chess.domain.fixture.CoordinateFixture.F2;
import static chess.domain.fixture.CoordinateFixture.F7;
import static chess.domain.fixture.CoordinateFixture.F8;
import static chess.domain.fixture.CoordinateFixture.G1;
import static chess.domain.fixture.CoordinateFixture.G2;
import static chess.domain.fixture.CoordinateFixture.G7;
import static chess.domain.fixture.CoordinateFixture.G8;
import static chess.domain.fixture.CoordinateFixture.H1;
import static chess.domain.fixture.CoordinateFixture.H2;
import static chess.domain.fixture.CoordinateFixture.H7;
import static chess.domain.fixture.CoordinateFixture.H8;
import static chess.domain.fixture.PieceFixture.BLACK_BISHOP;
import static chess.domain.fixture.PieceFixture.BLACK_KING;
import static chess.domain.fixture.PieceFixture.BLACK_KNIGHT;
import static chess.domain.fixture.PieceFixture.BLACK_PAWN;
import static chess.domain.fixture.PieceFixture.BLACK_QUEEN;
import static chess.domain.fixture.PieceFixture.BLACK_ROOK;
import static chess.domain.fixture.PieceFixture.WHITE_BISHOP;
import static chess.domain.fixture.PieceFixture.WHITE_KING;
import static chess.domain.fixture.PieceFixture.WHITE_KNIGHT;
import static chess.domain.fixture.PieceFixture.WHITE_PAWN;
import static chess.domain.fixture.PieceFixture.WHITE_QUEEN;
import static chess.domain.fixture.PieceFixture.WHITE_ROOK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(Board::new)
                .doesNotThrowAnyException();
    }

    @DisplayName("백팀의 킹은 e1에 있다.")
    @Test
    void whiteKing() {
        Board board = new Board();

        Piece result = board.findByCoordinate(E1);

        assertThat(result).isEqualTo(WHITE_KING);
    }

    @DisplayName("백팀의 퀸은 d1 있다.")
    @Test
    void whiteQueen() {
        Board board = new Board();

        Piece result = board.findByCoordinate(D1);

        assertThat(result).isEqualTo(WHITE_QUEEN);
    }

    @DisplayName("백팀의 비숍은 c1, f1에 있다.")
    @Test
    void whiteBishop() {
        Board board = new Board();

        Piece bishop1 = board.findByCoordinate(C1);
        Piece bishop2 = board.findByCoordinate(F1);

        assertThat(bishop1)
                .isEqualTo(bishop2)
                .isEqualTo(WHITE_BISHOP);
    }

    @DisplayName("백팀의 나이트는 b1, g1에 있다.")
    @Test
    void whiteKnight() {
        Board board = new Board();

        Piece knight1 = board.findByCoordinate(B1);
        Piece knight2 = board.findByCoordinate(G1);

        assertThat(knight1)
                .isEqualTo(knight2)
                .isEqualTo(WHITE_KNIGHT);
    }

    @DisplayName("백팀의 룩은 a1, h1에 있다.")
    @Test
    void whiteRook() {
        Board board = new Board();

        Piece rook1 = board.findByCoordinate(A1);
        Piece rook2 = board.findByCoordinate(H1);

        assertThat(rook1)
                .isEqualTo(rook2)
                .isEqualTo(WHITE_ROOK);
    }

    @DisplayName("백팀의 폰은 랭크가 2이고 파일은 A부터 H까지다.")
    @Test
    void whitePawn() {
        Board board = new Board();

        List<Piece> result = List.of(
                board.findByCoordinate(A2),
                board.findByCoordinate(B2),
                board.findByCoordinate(C2),
                board.findByCoordinate(D2),
                board.findByCoordinate(E2),
                board.findByCoordinate(F2),
                board.findByCoordinate(G2),
                board.findByCoordinate(H2)
        );

        assertThat(result).containsOnly(WHITE_PAWN);
    }

    @DisplayName("흑팀의 킹은 e8에 있다.")
    @Test
    void blackKing() {
        Board board = new Board();

        Piece result = board.findByCoordinate(E8);

        assertThat(result).isEqualTo(BLACK_KING);
    }

    @DisplayName("흑팀의 퀸은 d8 있다.")
    @Test
    void blackQueen() {
        Board board = new Board();

        Piece result = board.findByCoordinate(D8);

        assertThat(result).isEqualTo(BLACK_QUEEN);
    }

    @DisplayName("흑팀의 비숍은 c8, f8에 있다.")
    @Test
    void blackBishop() {
        Board board = new Board();

        Piece bishop1 = board.findByCoordinate(C8);
        Piece bishop2 = board.findByCoordinate(F8);

        assertThat(bishop1)
                .isEqualTo(bishop2)
                .isEqualTo(BLACK_BISHOP);
    }

    @DisplayName("흑팀의 나이트는 b8, g8에 있다.")
    @Test
    void blackKnight() {
        Board board = new Board();

        Piece knight1 = board.findByCoordinate(B8);
        Piece knight2 = board.findByCoordinate(G8);

        assertThat(knight1)
                .isEqualTo(knight2)
                .isEqualTo(BLACK_KNIGHT);
    }

    @DisplayName("흑팀의 룩은 a8, h8에 있다.")
    @Test
    void blackRook() {
        Board board = new Board();

        Piece rook1 = board.findByCoordinate(A8);
        Piece rook2 = board.findByCoordinate(H8);

        assertThat(rook1)
                .isEqualTo(rook2)
                .isEqualTo(BLACK_ROOK);
    }

    @DisplayName("흑팀의 폰은 랭크가 7이고 파일은 A부터 H까지다.")
    @Test
    void blackPawn() {
        Board board = new Board();

        List<Piece> result = List.of(
                board.findByCoordinate(A7),
                board.findByCoordinate(B7),
                board.findByCoordinate(C7),
                board.findByCoordinate(D7),
                board.findByCoordinate(E7),
                board.findByCoordinate(F7),
                board.findByCoordinate(G7),
                board.findByCoordinate(H7)
        );

        assertThat(result).containsOnly(BLACK_PAWN);
    }

    @DisplayName("source 좌표에 기물이 없으면 예외를 발생한다.")
    @Test
    void noSource() {
        Board emptyBoard = new Board(new HashMap<>());

        assertThatThrownBy(() -> emptyBoard.move(A2, B3))
                .isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("target 좌표에 기물이 있으면 예외를 발생한다.")
    @Test
    void existTarget() {
        HashMap<Coordinate, Piece> pieces = new HashMap<>();
        Coordinate source = A1;
        Coordinate target = A2;
        pieces.put(source, WHITE_ROOK);
        pieces.put(target, WHITE_ROOK);
        Board board = new Board(pieces);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("목적지 좌표에 기물이 이미 존재합니다.");
    }

    @DisplayName("source 좌표에 있는 기물이 target 좌표로 이동 가능한 경로가 존재하지 않을 경우, 이동할 수 없다.")
    @Test
    void nonExistPath() {
        HashMap<Coordinate, Piece> pieces = new HashMap<>();
        Coordinate source = A1;
        Coordinate target = D4;
        pieces.put(source, WHITE_ROOK);
        Board board = new Board(pieces);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("해당 기물은 목적지 좌표에 갈 수 없습니다.");
    }

    @DisplayName("target 으로 가는 경로에 기물이 존재하면, 이동할 수 없다.")
    @Test
    void pathStuck() {
        HashMap<Coordinate, Piece> pieces = new HashMap<>();
        Coordinate source = A1;
        Coordinate between = B2;
        Coordinate target = C3;
        pieces.put(source, WHITE_BISHOP);
        pieces.put(between, WHITE_PAWN);
        Board board = new Board(pieces);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("경로 중간에 기물이 존재해 이동할 수 없습니다.");
    }

    @DisplayName("폰에 한에, 초기 좌표가 아니면 2칸 전진할 수 없다.")
    @Test
    void nonInitialPawnCantMove2() {
        HashMap<Coordinate, Piece> pieces = new HashMap<>();
        Coordinate source = A3;
        Coordinate target = A5;
        pieces.put(source, WHITE_PAWN);
        Board board = new Board(pieces);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("폰이 초기 위치가 아니면, 2칸 전진할 수 없습니다.");
    }

    @DisplayName("기물이 움직일 수 있다.")
    @Test
    void move() {
        HashMap<Coordinate, Piece> pieces = new HashMap<>();
        Piece sourcePiece = WHITE_PAWN;
        Coordinate source = A2;
        Coordinate target = A4;
        pieces.put(source, sourcePiece);
        Board board = new Board(pieces);

        board.move(source, target);

        Piece result = board.findByCoordinate(target);
        assertThat(result).isEqualTo(sourcePiece);
    }
}
