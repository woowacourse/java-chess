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
import chess.domain.piece.EmptyPiece;
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

    @DisplayName("주어진 좌표의 기물이 존재하지 않으면 Empty 피스를 반환한다.")
    @Test
    void findByCoordinate() {
        Board emptyBoard = new Board(new HashMap<>());

        Piece result = emptyBoard.findByCoordinate(new Coordinate(2, 'a'));

        assertThat(result).isEqualTo(EmptyPiece.getInstance());
    }

    @DisplayName("source 좌표에 기물이 없으면 기물을 움직일 수 없다.")
    @Test
    void noSource() {
        Board emptyBoard = new Board(new HashMap<>());
        Coordinate source = new Coordinate(2, 'a');
        Coordinate target = new Coordinate(3, 'b');

        assertThatThrownBy(() -> emptyBoard.move(source, target))
                .isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("기물이 갈 수 있는 곳이라면, 보드를 업데이트한다.")
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

    @DisplayName("현재 턴에 해당하는 진영에 소속된 기물만 움직일 수 있다.")
    @Test
    void validateInvalidTurn() {
        HashMap<Coordinate, Piece> pieces = new HashMap<>();
        Piece sourcePiece = new Pawn(Team.WHITE);
        Coordinate source = new Coordinate(4, 'a');
        Coordinate middle = new Coordinate(5, 'a');
        Coordinate target = new Coordinate(6, 'a');
        pieces.put(source, sourcePiece);
        Board board = new Board(pieces);
        board.move(source, middle);

        assertThatThrownBy(() -> board.move(middle, target))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("상대방이 기물을 둘 차례입니다.");
    }
}
