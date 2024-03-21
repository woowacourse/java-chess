package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
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

        Piece result = board.findByCoordinate(new Coordinate(1, 'e'));

        Piece expected = new King(Team.WHITE);
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("백팀의 퀸은 d1 있다.")
    @Test
    void whiteQueen() {
        Board board = new Board();

        Piece result = board.findByCoordinate(new Coordinate(1, 'd'));

        Piece expected = new Queen(Team.WHITE);
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("백팀의 비숍은 c1, f1에 있다.")
    @Test
    void whiteBishop() {
        Board board = new Board();

        Piece bishop1 = board.findByCoordinate(new Coordinate(1, 'c'));
        Piece bishop2 = board.findByCoordinate(new Coordinate(1, 'f'));

        Piece expected = new Bishop(Team.WHITE);
        assertThat(bishop1)
                .isEqualTo(bishop2)
                .isEqualTo(expected);
    }

    @DisplayName("백팀의 나이트는 b1, g1에 있다.")
    @Test
    void whiteKnight() {
        Board board = new Board();

        Piece knight1 = board.findByCoordinate(new Coordinate(1, 'b'));
        Piece knight2 = board.findByCoordinate(new Coordinate(1, 'g'));

        Piece expected = new Knight(Team.WHITE);
        assertThat(knight1)
                .isEqualTo(knight2)
                .isEqualTo(expected);
    }

    @DisplayName("백팀의 룩은 a1, h1에 있다.")
    @Test
    void whiteRook() {
        Board board = new Board();

        Piece rook1 = board.findByCoordinate(new Coordinate(1, 'a'));
        Piece rook2 = board.findByCoordinate(new Coordinate(1, 'h'));

        Piece expected = new Rook(Team.WHITE);
        assertThat(rook1)
                .isEqualTo(rook2)
                .isEqualTo(expected);
    }

    @DisplayName("백팀의 폰은 랭크가 2이고 파일은 A부터 H까지다.")
    @Test
    void whitePawn() {
        Board board = new Board();

        List<Piece> result = new ArrayList<>();
        for (char fileValue = 'a'; fileValue <= 'h'; fileValue++) {
            Piece piece = board.findByCoordinate(new Coordinate(2, fileValue));
            result.add(piece);
        }

        List<Pawn> expected = Collections.nCopies(8, new Pawn(Team.WHITE));
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("흑팀의 킹은 e8에 있다.")
    @Test
    void blackKing() {
        Board board = new Board();

        Piece result = board.findByCoordinate(new Coordinate(8, 'e'));

        Piece expected = new King(Team.BLACK);
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("흑팀의 퀸은 d8 있다.")
    @Test
    void blackQueen() {
        Board board = new Board();

        Piece result = board.findByCoordinate(new Coordinate(8, 'd'));

        Piece expected = new Queen(Team.BLACK);
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("흑팀의 비숍은 c8, f8에 있다.")
    @Test
    void blackBishop() {
        Board board = new Board();

        Piece bishop1 = board.findByCoordinate(new Coordinate(8, 'c'));
        Piece bishop2 = board.findByCoordinate(new Coordinate(8, 'f'));

        Piece expected = new Bishop(Team.BLACK);
        assertThat(bishop1)
                .isEqualTo(bishop2)
                .isEqualTo(expected);
    }

    @DisplayName("흑팀의 나이트는 b8, g8에 있다.")
    @Test
    void blackKnight() {
        Board board = new Board();

        Piece knight1 = board.findByCoordinate(new Coordinate(8, 'b'));
        Piece knight2 = board.findByCoordinate(new Coordinate(8, 'g'));

        Piece expected = new Knight(Team.BLACK);
        assertThat(knight1)
                .isEqualTo(knight2)
                .isEqualTo(expected);
    }

    @DisplayName("흑팀의 룩은 a8, h8에 있다.")
    @Test
    void blackRook() {
        Board board = new Board();

        Piece rook1 = board.findByCoordinate(new Coordinate(8, 'a'));
        Piece rook2 = board.findByCoordinate(new Coordinate(8, 'h'));

        Piece expected = new Rook(Team.BLACK);
        assertThat(rook1)
                .isEqualTo(rook2)
                .isEqualTo(expected);
    }

    @DisplayName("흑팀의 폰은 랭크가 7이고 파일은 A부터 H까지다.")
    @Test
    void blackPawn() {
        Board board = new Board();

        List<Piece> result = new ArrayList<>();
        for (char i = 'a'; i <= 'h'; i++) {
            Piece piece = board.findByCoordinate(new Coordinate(7, i));
            result.add(piece);
        }

        List<Pawn> expected = Collections.nCopies(8, new Pawn(Team.BLACK));
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("source 좌표에 기물이 없으면 예외를 발생한다.")
    @Test
    void noSource() {
        Board emptyBoard = new Board(new HashMap<>());
        Coordinate source = new Coordinate(2, 'a');
        Coordinate target = new Coordinate(3, 'b');

        assertThatThrownBy(() -> emptyBoard.move(source, target))
                .isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("target 좌표에 기물이 있으면 예외를 발생한다.")
    @Test
    void existTarget() {
        HashMap<Coordinate, Piece> pieces = new HashMap<>();
        Coordinate source = new Coordinate(1, 'a');
        Coordinate target = new Coordinate(2, 'a');
        pieces.put(source, new Rook(Team.WHITE));
        pieces.put(target, new Rook(Team.WHITE));
        Board board = new Board(pieces);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("목적지 좌표에 기물이 이미 존재합니다.");
    }

    @DisplayName("source 좌표에 있는 기물이 target 좌표로 이동 가능한 경로가 존재하지 않을 경우, 이동할 수 없다.")
    @Test
    void nonExistPath() {
        HashMap<Coordinate, Piece> pieces = new HashMap<>();
        Coordinate source = new Coordinate(1, 'a');
        Coordinate target = new Coordinate(4, 'd');
        pieces.put(source, new Rook(Team.WHITE));
        Board board = new Board(pieces);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("해당 기물은 목적지 좌표에 갈 수 없습니다.");
    }

    @DisplayName("target 으로 가는 경로에 기물이 존재하면, 이동할 수 없다.")
    @Test
    void pathStuck() {
        HashMap<Coordinate, Piece> pieces = new HashMap<>();
        Coordinate source = new Coordinate(1, 'a');
        Coordinate between = new Coordinate(2, 'b');
        Coordinate target = new Coordinate(3, 'c');
        pieces.put(source, new Bishop(Team.WHITE));
        pieces.put(between, new Pawn(Team.WHITE));
        Board board = new Board(pieces);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("경로 중간에 기물이 존재해 이동할 수 없습니다.");
    }

    @DisplayName("폰에 한에, 초기 좌표가 아니면 2칸 전진할 수 없다.")
    @Test
    void nonInitialPawnCantMove2() {
        HashMap<Coordinate, Piece> pieces = new HashMap<>();
        Coordinate source = new Coordinate(3, 'a');
        Coordinate target = new Coordinate(5, 'a');
        pieces.put(source, new Pawn(Team.WHITE));
        Board board = new Board(pieces);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("폰이 초기 위치가 아니면, 2칸 전진할 수 없습니다.");
    }

    @DisplayName("기물이 움직일 수 있다.")
    @Test
    void move() {
        HashMap<Coordinate, Piece> pieces = new HashMap<>();
        Piece sourcePiece = new Pawn(Team.WHITE);
        Coordinate source = new Coordinate(2, 'a');
        Coordinate target = new Coordinate(4, 'a');
        pieces.put(source, sourcePiece);
        Board board = new Board(pieces);

        board.move(source, target);

        Piece result = board.findByCoordinate(target);
        assertThat(result).isEqualTo(sourcePiece);
    }
}
