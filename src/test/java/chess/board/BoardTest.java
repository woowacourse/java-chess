package chess.board;

import java.util.List;
import java.util.stream.Stream;

import chess.domain.board.Board;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BoardTest {

    private Board board;

    @BeforeEach
    void init() {
        board = new Board(new Pieces());
    }

    @Test
    @DisplayName("기물이 해당 위치로 갈 수 없으면 예외를 던진다.")
    void checkPieceMoveCondition_throws() {
        // given
        final Position sourcePosition = new Position(File.B, Rank.TWO);
        final Position targetPosition = new Position(File.B, Rank.FIVE);

        // when, then
        Assertions.assertThatThrownBy(() -> board.checkPieceMoveCondition(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 기물은 대상 위치로 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @MethodSource("checkPieceMoveCondition_pieceExistOnPath_TestCases")
    @DisplayName("타겟 위치까지의 경로에 말이 존재하는 경우 예외를 던진다.")
    void checkPieceMoveCondition_pieceExistOnPath_throws(Position sourcePosition, Position targetPosition) {
        // when, then
        Assertions.assertThatThrownBy(() -> board.checkPieceMoveCondition(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 타겟 위치까지의 경로에 말이 존재합니다.");
    }

    static Stream<Arguments> checkPieceMoveCondition_pieceExistOnPath_TestCases() {
        return Stream.of(
                Arguments.arguments(new Position(File.A, Rank.ONE), new Position(File.A, Rank.FOUR)),
                Arguments.arguments(new Position(File.C, Rank.ONE), new Position(File.E, Rank.THREE)),
                Arguments.arguments(new Position(File.D, Rank.ONE), new Position(File.D, Rank.THREE))
        );
    }

    @ParameterizedTest
    @MethodSource("checkPieceMoveCondition_sameSidePieceExistOnTarget_TestCases")
    @DisplayName("타겟 위치에 아군 말이 존재하는 경우 예외를 던진다.")
    void checkPieceMoveCondition_sameSidePieceExistOnTarget_throws(Position sourcePosition, Position targetPosition) {
        // when, then
        Assertions.assertThatThrownBy(() -> board.checkPieceMoveCondition(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 타겟 위치에 아군 말이 존재합니다.");
    }

    static Stream<Arguments> checkPieceMoveCondition_sameSidePieceExistOnTarget_TestCases() {
        return Stream.of(
                Arguments.arguments(new Position(File.A, Rank.ONE), new Position(File.A, Rank.TWO)),
                Arguments.arguments(new Position(File.C, Rank.ONE), new Position(File.B, Rank.TWO)),
                Arguments.arguments(new Position(File.D, Rank.ONE), new Position(File.D, Rank.TWO))
        );
    }

    @ParameterizedTest
    @MethodSource("movePiece_Pawn1_TestCases")
    @DisplayName("폰이 대각선에 적팀 기물이 없을 때 대각선으로 이동하면 예외를 던진다.")
    void movePiece_Pawn_throws1(Position sourcePosition, Position targetPosition) {
        // when, then
        Assertions.assertThatThrownBy(() -> board.movePiece(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] Pawn은 대각선에 적팀 기물이 있을 때만 이동할 수 있습니다.");
    }

    static Stream<Arguments> movePiece_Pawn1_TestCases() {
        return Stream.of(
                Arguments.arguments(new Position(File.B, Rank.TWO), new Position(File.C, Rank.THREE)),
                Arguments.arguments(new Position(File.B, Rank.TWO), new Position(File.A, Rank.THREE))
        );
    }

    @Test
    @DisplayName("폰이 직선 상의 도착 위치 기물이 적군일 때 한칸 전진하려고 하면 예외를 던진다.")
    void movePiece_Pawn_throws2() {
        // given
        Position sourcePosition = new Position(File.B, Rank.TWO);
        Position targetPosition = new Position(File.B, Rank.THREE);

        // when
        board.movePiece(new Position(File.B, Rank.SEVEN), new Position(File.B, Rank.FIVE));
        board.movePiece(new Position(File.B, Rank.FIVE), new Position(File.B, Rank.FOUR));
        board.movePiece(new Position(File.B, Rank.FOUR), new Position(File.B, Rank.THREE));

        // then
        Assertions.assertThatThrownBy(() -> board.movePiece(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] Pawn은 직선 상의 도착 위치 기물이 적군이어도 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("타겟 위치에 말이 없을 때 기물을 이동시킨다.")
    void movePiece_onlyMove() {
        // given
        Position sourcePosition = new Position(File.B, Rank.ONE);
        Position targetPosition = new Position(File.C, Rank.THREE);
        Piece pieceBeforeMove = board.findPieceByPosition(sourcePosition);

        // when
        board.movePiece(sourcePosition, targetPosition);
        Piece findTargetPiece = board.findPieceByPosition(targetPosition);
        List<Piece> pieces = board.getPieces();
        int targetPieceIndex = pieces.indexOf(findTargetPiece);

        // then
        Assertions.assertThat(pieces.get(targetPieceIndex)).isEqualTo(findTargetPiece);
        Assertions.assertThat(pieces.get(targetPieceIndex)).isNotEqualTo(pieceBeforeMove);
    }

    @Test
    @DisplayName("타겟 위치 말이 적군일 때 기물을 잡고 이동한다.")
    void movePiece_takePiece() {
        // given
        Position sourcePosition = new Position(File.B, Rank.ONE);
        Position targetPosition = new Position(File.C, Rank.THREE);
        Position oppositeTargetPosition = new Position(File.C, Rank.THREE);
        Piece pieceBeforeMove = board.findPieceByPosition(sourcePosition);

        // when
        board.movePiece(new Position(File.C, Rank.SEVEN), new Position(File.C, Rank.FIVE));
        board.movePiece(new Position(File.C, Rank.FIVE), new Position(File.C, Rank.FOUR));
        board.movePiece(new Position(File.C, Rank.FOUR), oppositeTargetPosition);
        Piece oppositePiece = board.findPieceByPosition(oppositeTargetPosition);

        board.movePiece(sourcePosition, targetPosition);
        Piece findTargetPiece = board.findPieceByPosition(targetPosition);
        List<Piece> pieces = board.getPieces();
        int targetPieceIndex = pieces.indexOf(findTargetPiece);
        int oppositeTargetPieceIndex = pieces.indexOf(oppositePiece);

        // then
        Assertions.assertThat(pieces.get(targetPieceIndex)).isEqualTo(findTargetPiece);
        Assertions.assertThatThrownBy(() -> pieces.get(oppositeTargetPieceIndex));
        Assertions.assertThat(pieces.get(targetPieceIndex)).isNotEqualTo(pieceBeforeMove);
    }
}
