package chess.domain;

import static chess.constants.TestConstants.PARAMETERIZED_TEST_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.generator.NormalPiecesGenerator;
import chess.domain.piece.generator.PiecesGenerator;
import chess.domain.position.Column;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ChessBoardTest {

    public static final String NOT_MOVABLE_EXCEPTION_MESSAGE = "해당 Position으로 이동할 수 없습니다.";

    @Test
    @DisplayName("체스판을 생성한다.")
    void construct() {
        Map<Position, Piece> pieces = new HashMap<>(Map.ofEntries(
                Map.entry(Position.of("a1"), new Pawn(Color.WHITE)),
                Map.entry(Position.of("a2"), new Pawn(Color.BLACK))
        ));
        ChessBoard chessBoard = new ChessBoard(() -> pieces);
        assertThat(chessBoard.getPieces()).containsAllEntriesOf(pieces);
    }

    @Test
    @DisplayName("체스판을 생성할 때 빈 칸은 EmptyPiece를 삽입한다.")
    void constructEmptyPieces() {
        Map<Position, Piece> pieces = new HashMap<>(Map.ofEntries(
                Map.entry(Position.of("a1"), new Pawn(Color.WHITE)),
                Map.entry(Position.of("a2"), new Pawn(Color.BLACK))
        ));
        ChessBoard chessBoard = new ChessBoard(() -> pieces);
        assertThat(chessBoard.getPieces()).contains(Map.entry(Position.of("a3"), EmptyPiece.getInstance()));
    }

    @Test
    @DisplayName("위치가 들어왔을 때 해당 위치의 말이 어떤 말인지 확인한다.")
    void selectPiece() {
        PiecesGenerator piecesGenerator = new NormalPiecesGenerator();
        ChessBoard chessBoard = new ChessBoard(piecesGenerator);
        Piece piece = chessBoard.selectPiece(Position.of("a1"));
        assertThat(piece).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("한 컬럼의 흑팀 말들을 반환한다.")
    void getPiecesOnColumnByColor() {
        ChessBoard chessBoard = new ChessBoard(new NormalPiecesGenerator());
        Piece rook = chessBoard.selectPiece(Position.of("a8"));
        Piece pawn = chessBoard.selectPiece(Position.of("a7"));
        List<Piece> pieces = chessBoard.getPiecesOnColumn(Column.A, Color.BLACK);
        assertThat(pieces).containsExactly(pawn, rook);
    }

    @Test
    @DisplayName("여러 컬럼의 흑팀 말들을 반환한다.")
    void getPiecesOnColumnsByColor() {

        Map<Position, Piece> testPieces = new HashMap<>(Map.ofEntries(
                Map.entry(Position.of("a1"), new Queen(Color.WHITE)),
                Map.entry(Position.of("b3"), new Pawn(Color.WHITE)),
                Map.entry(Position.of("c4"), new Pawn(Color.WHITE)),
                Map.entry(Position.of("a4"), new Pawn(Color.BLACK)),
                Map.entry(Position.of("a7"), new Pawn(Color.BLACK)),
                Map.entry(Position.of("c5"), new Pawn(Color.BLACK)),
                Map.entry(Position.of("b8"), new Pawn(Color.BLACK))

        ));
        ChessBoard chessBoard = new ChessBoard(() -> testPieces);

        Piece a = chessBoard.selectPiece(Position.of("a4"));
        Piece a2 = chessBoard.selectPiece(Position.of("a7"));
        Piece b = chessBoard.selectPiece(Position.of("b8"));
        Piece c = chessBoard.selectPiece(Position.of("c5"));

        List<List<Piece>> pieces = chessBoard.getPiecesOnColumns(Color.BLACK);
        assertThat(pieces).contains(
                List.of(a, a2),
                List.of(b),
                List.of(c)
        );
    }

    @Test
    @DisplayName("킹이 1개일 때, 게임은 끝난다.")
    void isEndTrue() {
        Map<Position, Piece> testPieces = new HashMap<>(Map.ofEntries(
                Map.entry(Position.of("a1"), new King(Color.WHITE)),
                Map.entry(Position.of("b3"), new Pawn(Color.WHITE)),
                Map.entry(Position.of("c4"), new Pawn(Color.WHITE)),
                Map.entry(Position.of("a4"), new Pawn(Color.BLACK)),
                Map.entry(Position.of("a7"), new Pawn(Color.BLACK)),
                Map.entry(Position.of("c5"), new Pawn(Color.BLACK)),
                Map.entry(Position.of("b8"), new Pawn(Color.BLACK))
        ));
        ChessBoard chessBoard = new ChessBoard(() -> testPieces);

        assertThat(chessBoard.isEnd()).isTrue();
    }

    @Test
    @DisplayName("킹이 2개일 때, 게임은 끝나지 않는다.")
    void isEndFalse() {
        Map<Position, Piece> testPieces = new HashMap<>(Map.ofEntries(
                Map.entry(Position.of("a1"), new King(Color.WHITE)),
                Map.entry(Position.of("b3"), new Pawn(Color.WHITE)),
                Map.entry(Position.of("c4"), new Pawn(Color.WHITE)),
                Map.entry(Position.of("a4"), new King(Color.BLACK)),
                Map.entry(Position.of("a7"), new Pawn(Color.BLACK)),
                Map.entry(Position.of("c5"), new Pawn(Color.BLACK)),
                Map.entry(Position.of("b8"), new Pawn(Color.BLACK))
        ));
        ChessBoard chessBoard = new ChessBoard(() -> testPieces);

        assertThat(chessBoard.isEnd()).isFalse();
    }

    @Test
    @DisplayName("해당 Position의 컬러를 확인한다.")
    void getPositionColor() {
        ChessBoard chessBoard = new ChessBoard(
                () -> new HashMap<>(Map.ofEntries(
                        Map.entry(Position.of("a1"), new King(Color.WHITE)))));
        assertThat(chessBoard.getPositionColor(Position.of("a1")))
                .isEqualTo(Color.WHITE);
    }

    @Nested
    @DisplayName("move 메서드는")
    class MoveTest {

        @ParameterizedTest(name = PARAMETERIZED_TEST_NAME)
        @ValueSource(strings = {"c3", "c4", "d3", "d5", "e5"})
        @DisplayName("말을 움직인다.")
        void move(String toPosition) {
            King movingKing = new King(Color.WHITE);
            Map<Position, Piece> pieces = new HashMap<>(Map.ofEntries(
                    Map.entry(Position.of("d4"), movingKing),
                    Map.entry(Position.of("c4"), new Pawn(Color.BLACK)),
                    Map.entry(Position.of("d5"), new Pawn(Color.BLACK)),
                    Map.entry(Position.of("e3"), new Pawn(Color.WHITE)),
                    Map.entry(Position.of("e4"), new Pawn(Color.WHITE))
            ));
            ChessBoard chessBoard = new ChessBoard(() -> pieces);

            chessBoard.move(new GameCommand("move", "d4", toPosition));
            assertThat(chessBoard.selectPiece(Position.of(toPosition))).isSameAs(movingKing);
        }

        @ParameterizedTest(name = PARAMETERIZED_TEST_NAME)
        @CsvSource(value = {"e3", "e4"})
        @DisplayName("말이 해당하는 곳에 같은 편이 있을 경우 움직이지 못한다.")
        void moveExceptionBySameTeam(String toPosition) {
            Map<Position, Piece> pieces = new HashMap<>(Map.ofEntries(
                    Map.entry(Position.of("d4"), new King(Color.WHITE)),
                    Map.entry(Position.of("e3"), new Pawn(Color.WHITE)),
                    Map.entry(Position.of("e4"), new Pawn(Color.WHITE))
            ));
            ChessBoard chessBoard = new ChessBoard(() -> pieces);

            assertThatThrownBy(() -> chessBoard.move(new GameCommand("move", "d4", toPosition)))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage(NOT_MOVABLE_EXCEPTION_MESSAGE);
        }

        @ParameterizedTest(name = PARAMETERIZED_TEST_NAME)
        @CsvSource(value = {"e4", "d5", "c4", "d3"})
        @DisplayName("말이 움직이지 못하는 방향으로는 가지 못한다.")
        void moveExceptionByMovableDirection(String toPosition) {
            Map<Position, Piece> pieces = new HashMap<>(Map.ofEntries(
                    Map.entry(Position.of("d4"), new Bishop(Color.WHITE))
            ));
            ChessBoard chessBoard = new ChessBoard(() -> pieces);

            assertThatThrownBy(() -> chessBoard.move(new GameCommand("move", "d4", toPosition)))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage(NOT_MOVABLE_EXCEPTION_MESSAGE);
        }

        @ParameterizedTest(name = PARAMETERIZED_TEST_NAME)
        @CsvSource(value = {"g1", "h4"})
        @DisplayName("말이 움직일 수 있는 방향에 장애물이 있는 경우 가지 못한다.")
        void moveExceptionByBlock(String toPosition) {
            Map<Position, Piece> pieces = new HashMap<>(Map.ofEntries(
                    Map.entry(Position.of("d4"), new Queen(Color.WHITE)),
                    Map.entry(Position.of("g4"), new Pawn(Color.BLACK)),
                    Map.entry(Position.of("f2"), new Pawn(Color.WHITE))
            ));
            ChessBoard chessBoard = new ChessBoard(() -> pieces);

            assertThatThrownBy(() -> chessBoard.move(new GameCommand("move", "d4", toPosition)))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage(NOT_MOVABLE_EXCEPTION_MESSAGE);
        }

        @ParameterizedTest(name = PARAMETERIZED_TEST_NAME)
        @CsvSource(value = {"e5", "c5"})
        @DisplayName("폰은 대각선에 적이 있는 경우에만 공격 가능하다.")
        void attackPawn(String toPosition) {
            Pawn movingPawn = new Pawn(Color.WHITE);
            Map<Position, Piece> pieces = new HashMap<>(Map.ofEntries(
                    Map.entry(Position.of("d4"), movingPawn),
                    Map.entry(Position.of("c5"), new Pawn(Color.BLACK)),
                    Map.entry(Position.of("e5"), new Pawn(Color.BLACK))
            ));
            ChessBoard chessBoard = new ChessBoard(() -> pieces);

            chessBoard.move(new GameCommand("move", "d4", toPosition));
            assertThat(chessBoard.selectPiece(Position.of(toPosition))).isSameAs(movingPawn);
        }

        @ParameterizedTest
        @CsvSource(value = {"d3", "d4"})
        @DisplayName("폰은 정면으로 공격이 불가능하다.")
        void movePawnExceptionWithBlock(String toPosition) {
            Pawn movingPawn = new Pawn(Color.WHITE);
            Map<Position, Piece> pieces = new HashMap<>(Map.ofEntries(
                    Map.entry(Position.of("d2"), movingPawn),
                    Map.entry(Position.of(toPosition), new Pawn(Color.BLACK))
            ));
            ChessBoard chessBoard = new ChessBoard(() -> pieces);

            assertThatThrownBy(() ->chessBoard.move(new GameCommand("move", "d2", toPosition)))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage(NOT_MOVABLE_EXCEPTION_MESSAGE);
        }
    }
}
