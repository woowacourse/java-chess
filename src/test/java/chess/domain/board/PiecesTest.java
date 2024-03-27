package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PiecesTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        Map<Coordinate, Piece> pieces = new HashMap<>();

        assertThatCode(() -> new Pieces(pieces))
                .doesNotThrowAnyException();
    }

    @DisplayName("주어진 좌표의 기물이 존재하지 않으면 Empty 피스를 반환한다.")
    @Test
    void findByCoordinate() {
        Pieces emptyPieces = new Pieces(new HashMap<>());

        Piece result = emptyPieces.findByCoordinate(new Coordinate(2, 'a'));

        assertThat(result).isEqualTo(EmptyPiece.getInstance());
    }

    @DisplayName("해당 위치의 기물이 존재하는지 판단할 수 있다.")
    @Test
    void isPiecePresent() {
        HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
        Piece sourcePiece = new Pawn(Team.WHITE);
        Coordinate source = new Coordinate(2, 'a');
        piecesMap.put(source, sourcePiece);
        Pieces pieces = new Pieces(piecesMap);

        Assertions.assertAll(
                () -> Assertions.assertTrue(pieces.isPiecePresent(source)),
                () -> Assertions.assertFalse(pieces.isPiecePresent(new Coordinate(3, 'a')))
        );
    }

    @DisplayName("두 좌표에 해당하는 값들을 서로 바꾼다.")
    @Test
    void swap() {
        HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
        Piece sourcePiece = new Pawn(Team.WHITE);
        Coordinate source = new Coordinate(2, 'a');
        Coordinate target = new Coordinate(3, 'a');
        piecesMap.put(source, sourcePiece);
        Pieces pieces = new Pieces(piecesMap);

        pieces.swap(source, target);

        Piece foundSourcePiece = piecesMap.get(source);
        Piece foundTargetPiece = piecesMap.get(target);
        assertThat(foundSourcePiece).isNull();
        assertThat(foundTargetPiece).isEqualTo(sourcePiece);
    }

    @DisplayName("백팀의 킹은 e1에 있다.")
    @Test
    void whiteKing() {
        Pieces pieces = PiecesFactory.createInitialPieces();

        Piece result = pieces.findByCoordinate(new Coordinate(1, 'e'));

        Piece expected = new King(Team.WHITE);
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("백팀의 퀸은 d1 있다.")
    @Test
    void whiteQueen() {
        Pieces pieces = PiecesFactory.createInitialPieces();

        Piece result = pieces.findByCoordinate(new Coordinate(1, 'd'));

        Piece expected = new Queen(Team.WHITE);
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("백팀의 비숍은 c1, f1에 있다.")
    @Test
    void whiteBishop() {
        Pieces pieces = PiecesFactory.createInitialPieces();

        Piece bishop1 = pieces.findByCoordinate(new Coordinate(1, 'c'));
        Piece bishop2 = pieces.findByCoordinate(new Coordinate(1, 'f'));

        Piece expected = new Bishop(Team.WHITE);
        assertThat(bishop1)
                .isEqualTo(bishop2)
                .isEqualTo(expected);
    }

    @DisplayName("백팀의 나이트는 b1, g1에 있다.")
    @Test
    void whiteKnight() {
        Pieces pieces = PiecesFactory.createInitialPieces();

        Piece knight1 = pieces.findByCoordinate(new Coordinate(1, 'b'));
        Piece knight2 = pieces.findByCoordinate(new Coordinate(1, 'g'));

        Piece expected = new Knight(Team.WHITE);
        assertThat(knight1)
                .isEqualTo(knight2)
                .isEqualTo(expected);
    }

    @DisplayName("백팀의 룩은 a1, h1에 있다.")
    @Test
    void whiteRook() {
        Pieces pieces = PiecesFactory.createInitialPieces();

        Piece rook1 = pieces.findByCoordinate(new Coordinate(1, 'a'));
        Piece rook2 = pieces.findByCoordinate(new Coordinate(1, 'h'));

        Piece expected = new Rook(Team.WHITE);
        assertThat(rook1)
                .isEqualTo(rook2)
                .isEqualTo(expected);
    }

    @DisplayName("백팀의 폰은 랭크가 2이고 파일은 A부터 H까지다.")
    @Test
    void whitePawn() {
        Pieces pieces = PiecesFactory.createInitialPieces();

        List<Piece> result = new ArrayList<>();
        for (char fileValue = 'a'; fileValue <= 'h'; fileValue++) {
            Piece piece = pieces.findByCoordinate(new Coordinate(2, fileValue));
            result.add(piece);
        }

        List<Pawn> expected = Collections.nCopies(8, new Pawn(Team.WHITE));
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("흑팀의 킹은 e8에 있다.")
    @Test
    void blackKing() {
        Pieces pieces = PiecesFactory.createInitialPieces();

        Piece result = pieces.findByCoordinate(new Coordinate(8, 'e'));

        Piece expected = new King(Team.BLACK);
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("흑팀의 퀸은 d8 있다.")
    @Test
    void blackQueen() {
        Pieces pieces = PiecesFactory.createInitialPieces();

        Piece result = pieces.findByCoordinate(new Coordinate(8, 'd'));

        Piece expected = new Queen(Team.BLACK);
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("흑팀의 비숍은 c8, f8에 있다.")
    @Test
    void blackBishop() {
        Pieces pieces = PiecesFactory.createInitialPieces();

        Piece bishop1 = pieces.findByCoordinate(new Coordinate(8, 'c'));
        Piece bishop2 = pieces.findByCoordinate(new Coordinate(8, 'f'));

        Piece expected = new Bishop(Team.BLACK);
        assertThat(bishop1)
                .isEqualTo(bishop2)
                .isEqualTo(expected);
    }

    @DisplayName("흑팀의 나이트는 b8, g8에 있다.")
    @Test
    void blackKnight() {
        Pieces pieces = PiecesFactory.createInitialPieces();

        Piece knight1 = pieces.findByCoordinate(new Coordinate(8, 'b'));
        Piece knight2 = pieces.findByCoordinate(new Coordinate(8, 'g'));

        Piece expected = new Knight(Team.BLACK);
        assertThat(knight1)
                .isEqualTo(knight2)
                .isEqualTo(expected);
    }

    @DisplayName("흑팀의 룩은 a8, h8에 있다.")
    @Test
    void blackRook() {
        Pieces pieces = PiecesFactory.createInitialPieces();

        Piece rook1 = pieces.findByCoordinate(new Coordinate(8, 'a'));
        Piece rook2 = pieces.findByCoordinate(new Coordinate(8, 'h'));

        Piece expected = new Rook(Team.BLACK);
        assertThat(rook1)
                .isEqualTo(rook2)
                .isEqualTo(expected);
    }

    @DisplayName("흑팀의 폰은 랭크가 7이고 파일은 A부터 H까지다.")
    @Test
    void blackPawn() {
        Pieces pieces = PiecesFactory.createInitialPieces();

        List<Piece> result = new ArrayList<>();
        for (char i = 'a'; i <= 'h'; i++) {
            Piece piece = pieces.findByCoordinate(new Coordinate(7, i));
            result.add(piece);
        }

        List<Pawn> expected = Collections.nCopies(8, new Pawn(Team.BLACK));
        assertThat(result).isEqualTo(expected);
    }
}
