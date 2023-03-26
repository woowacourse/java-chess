package chess;

import chess.domain.BoardInitializer;
import chess.domain.board.Chessboard;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


class ChessboardTest {
    private Chessboard chessboard;

    @BeforeEach
    void setup() {
        chessboard = new Chessboard();
        BoardInitializer.initializeBoard(chessboard);
    }

    @DisplayName("체스판은 64개의 Square로 이루어진다.")
    @Test
    void createChessboardSuccessTest() {
        assertThat(new Chessboard())
                .extracting("board")
                .asInstanceOf(InstanceOfAssertFactories.map(Square.class, Piece.class))
                .hasSize(64);
    }

    @DisplayName("경로 사이에 기물이 있을 경우, false를 반환한다.")
    @Test
    void isExistPieceInRouteTest() {
        Square source = Square.getInstanceOf(File.A, Rank.TWO);
        Square target = Square.getInstanceOf(File.A, Rank.EIGHT);

        assertThat(chessboard.isEmptyInRoute(source, target))
                .isFalse();
    }

    @DisplayName("경로 사이가 비어있는 경우, false를 반환한다.")
    @Test
    void isEmptyInRouteTest() {
        Square source = Square.getInstanceOf(File.A, Rank.TWO);
        Square target = Square.getInstanceOf(File.A, Rank.SEVEN);

        assertThat(chessboard.isEmptyInRoute(source, target))
                .isTrue();
    }

    @DisplayName("대각선 경로 사이에 기물이 있을 경우, false를 반환한다.")
    @Test
    void isExistPieceInDiagonalRouteTest() {
        Square source = Square.getInstanceOf(File.A, Rank.TWO);
        Square target = Square.getInstanceOf(File.G, Rank.EIGHT);

        assertThat(chessboard.isEmptyInRoute(source, target))
                .isFalse();
    }

    @DisplayName("대각선 경로 사이가 비어있는 경우, true를 반환한다.")
    @Test
    void isEmptyInDiagonalRouteTest() {
        Square source = Square.getInstanceOf(File.A, Rank.TWO);
        Square target = Square.getInstanceOf(File.F, Rank.SEVEN);

        assertThat(chessboard.isEmptyInRoute(source, target))
                .isTrue();
    }

    @ParameterizedTest(name = "체스판 위에 존재하는 특정 기물의 수를 확인할 수 있다.")
    @MethodSource("pieceAndPieceCountProvider")
    void countSamePieceOnBoardSuccessTest(Piece piece, int pieceCount) {
        assertThat(chessboard.countSamePieceOnBoard(piece))
                .isEqualTo(pieceCount);
    }

    static Stream<Arguments> pieceAndPieceCountProvider() {
        return Stream.of(
                Arguments.arguments(PieceType.PAWN.createPiece(Camp.WHITE), 8),
                Arguments.arguments(PieceType.ROOK.createPiece(Camp.WHITE), 2),
                Arguments.arguments(PieceType.BISHOP.createPiece(Camp.WHITE), 2),
                Arguments.arguments(PieceType.KNIGHT.createPiece(Camp.WHITE), 2),
                Arguments.arguments(PieceType.QUEEN.createPiece(Camp.WHITE), 1),
                Arguments.arguments(PieceType.KING.createPiece(Camp.WHITE), 1),

                Arguments.arguments(PieceType.PAWN.createPiece(Camp.BLACK), 8),
                Arguments.arguments(PieceType.ROOK.createPiece(Camp.BLACK), 2),
                Arguments.arguments(PieceType.BISHOP.createPiece(Camp.BLACK), 2),
                Arguments.arguments(PieceType.KNIGHT.createPiece(Camp.BLACK), 2),
                Arguments.arguments(PieceType.QUEEN.createPiece(Camp.BLACK), 1),
                Arguments.arguments(PieceType.KING.createPiece(Camp.BLACK), 1)
        );
    }

    @ParameterizedTest(name = "체스판 위에 살아있는 기물과 기물의 수를 받아올 수 있다.")
    @MethodSource("alivePieceAndPieceCountProvider")
    void getAlivePieceAndCountMapTest(PieceType pieceType, int pieceCount) {
        Square whitePawnSquare = Square.getInstanceOf(File.A, Rank.TWO);
        Camp white = Camp.WHITE;
        chessboard.putPiece(whitePawnSquare, PieceType.EMPTY.createPiece(white));

        Map<PieceType, Integer> alivePieceAndCountMap = chessboard.getAlivePieceAndCountMap(white);

        assertThat(alivePieceAndCountMap.get(pieceType))
                .isEqualTo(pieceCount);
    }

    static Stream<Arguments> alivePieceAndPieceCountProvider() {
        return Stream.of(
                Arguments.arguments(PieceType.PAWN, 7),
                Arguments.arguments(PieceType.ROOK, 2),
                Arguments.arguments(PieceType.KNIGHT, 2),
                Arguments.arguments(PieceType.BISHOP, 2),
                Arguments.arguments(PieceType.KING, 1),
                Arguments.arguments(PieceType.QUEEN, 1)
        );
    }
}
