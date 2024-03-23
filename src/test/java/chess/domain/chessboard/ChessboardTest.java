package chess.domain.chessboard;

import static java.util.Map.entry;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.piece.attribute.Color;
import chess.domain.chessboard.attribute.File;
import chess.domain.piece.attribute.Position;
import chess.domain.chessboard.attribute.Rank;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.StartingPawn;

class ChessboardTest {

    @DisplayName("체스판을 생성한다.")
    @Test
    void create() {
        Chessboard chessBoard = Chessboard.create();
        assertThat(chessBoard.getChessboard())
                .contains(
                        entry(Position.of(File.E, Rank.ONE), new King(Color.WHITE)),
                        entry(Position.of(File.E, Rank.EIGHT), new King(Color.BLACK)),
                        entry(Position.of(File.D, Rank.ONE), new Queen(Color.WHITE)),
                        entry(Position.of(File.D, Rank.EIGHT), new Queen(Color.BLACK)),
                        entry(Position.of(File.C, Rank.ONE), new Bishop(Color.WHITE)),
                        entry(Position.of(File.F, Rank.ONE), new Bishop(Color.WHITE)),
                        entry(Position.of(File.C, Rank.EIGHT), new Bishop(Color.BLACK)),
                        entry(Position.of(File.F, Rank.EIGHT), new Bishop(Color.BLACK)),
                        entry(Position.of(File.B, Rank.ONE), new Knight(Color.WHITE)),
                        entry(Position.of(File.G, Rank.ONE), new Knight(Color.WHITE)),
                        entry(Position.of(File.B, Rank.EIGHT), new Knight(Color.BLACK)),
                        entry(Position.of(File.G, Rank.EIGHT), new Knight(Color.BLACK)),
                        entry(Position.of(File.A, Rank.ONE), new Rook(Color.WHITE)),
                        entry(Position.of(File.H, Rank.ONE), new Rook(Color.WHITE)),
                        entry(Position.of(File.A, Rank.EIGHT), new Rook(Color.BLACK)),
                        entry(Position.of(File.H, Rank.EIGHT), new Rook(Color.BLACK)),
                        entry(Position.of(File.A, Rank.TWO), new StartingPawn(Color.WHITE)),
                        entry(Position.of(File.B, Rank.TWO), new StartingPawn(Color.WHITE)),
                        entry(Position.of(File.C, Rank.TWO), new StartingPawn(Color.WHITE)),
                        entry(Position.of(File.D, Rank.TWO), new StartingPawn(Color.WHITE)),
                        entry(Position.of(File.E, Rank.TWO), new StartingPawn(Color.WHITE)),
                        entry(Position.of(File.F, Rank.TWO), new StartingPawn(Color.WHITE)),
                        entry(Position.of(File.G, Rank.TWO), new StartingPawn(Color.WHITE)),
                        entry(Position.of(File.H, Rank.TWO), new StartingPawn(Color.WHITE)),
                        entry(Position.of(File.A, Rank.SEVEN), new StartingPawn(Color.BLACK)),
                        entry(Position.of(File.B, Rank.SEVEN), new StartingPawn(Color.BLACK)),
                        entry(Position.of(File.C, Rank.SEVEN), new StartingPawn(Color.BLACK)),
                        entry(Position.of(File.D, Rank.SEVEN), new StartingPawn(Color.BLACK)),
                        entry(Position.of(File.E, Rank.SEVEN), new StartingPawn(Color.BLACK)),
                        entry(Position.of(File.F, Rank.SEVEN), new StartingPawn(Color.BLACK)),
                        entry(Position.of(File.G, Rank.SEVEN), new StartingPawn(Color.BLACK)),
                        entry(Position.of(File.H, Rank.SEVEN), new StartingPawn(Color.BLACK))
                );
    }

    private static Stream<Arguments> move() {
        return Stream.of(
                Arguments.of(Position.of(File.A, Rank.TWO), Position.of(File.A, Rank.THREE), PieceType.PAWN),
                Arguments.of(Position.of(File.A, Rank.TWO), Position.of(File.A, Rank.FOUR), PieceType.PAWN),
                Arguments.of(Position.of(File.B, Rank.ONE), Position.of(File.C, Rank.THREE), PieceType.KNIGHT)
        );
    }

    @DisplayName("a2의 기물을 a3로 이동한다.")
    @MethodSource
    @ParameterizedTest
    void move(Position source, Position target, PieceType pieceType) {
        Chessboard chessBoard = Chessboard.create();
        chessBoard.move(source, target);

        assertThat(chessBoard.getChessboard().get(target).getPieceType()).isEqualTo(pieceType);
    }

    @DisplayName("서로 다른 색의 기물이 있는 곳은 이동할 수 있다.")
    @Test
    void canMoveToPositionOfDifferentColorPiece() {
        Chessboard chessBoard = Chessboard.create();
        chessBoard.move(Position.of(File.G, Rank.ONE), Position.of(File.F, Rank.THREE));
        chessBoard.move(Position.of(File.F, Rank.THREE), Position.of(File.G, Rank.FIVE));
        chessBoard.move(Position.of(File.G, Rank.FIVE), Position.of(File.F, Rank.SEVEN));

        assertThat(chessBoard.pieceIn(Position.of(File.F, Rank.SEVEN)).getPieceType())
                .isEqualTo(PieceType.KNIGHT);
    }

    @DisplayName("서로 같은 색의 기물이 있는 곳은 이동할 수 없다.")
    @Test
    void cannotMoveToPositionOfSameColorPiece() {
        Chessboard chessBoard = Chessboard.create();
        chessBoard.move(Position.of(File.G, Rank.ONE), Position.of(File.E, Rank.TWO));

        assertThat(chessBoard.getChessboard().get(Position.of(File.E, Rank.TWO)).getPieceType())
                .isEqualTo(PieceType.PAWN);
    }

    @DisplayName("칸을 이동할 수 없으면 예외가 발생한다.")
    @Test
    void cannotMoveException() {
        Chessboard chessBoard = Chessboard.create();

        assertThatThrownBy(() -> chessBoard.move(Position.of(File.E, Rank.ONE), Position.of(File.E, Rank.TWO)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("칸을 이동할 수 없으면 예외가 발생한다.")
    @Test
    void cannotMoveExceptionRook() {
        Chessboard chessBoard = Chessboard.create();

        assertThatThrownBy(() -> chessBoard.move(Position.of(File.A, Rank.ONE), Position.of(File.A, Rank.TWO)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰의 공격은 대각선만 가능하다.")
    @Test
    void pawnAttackOnlyByDiagonal() {
        Chessboard chessBoard = Chessboard.create();
        chessBoard.move(Position.of(File.C, Rank.TWO), Position.of(File.C, Rank.THREE));
        chessBoard.move(Position.of(File.C, Rank.THREE), Position.of(File.C, Rank.FOUR));
        chessBoard.move(Position.of(File.C, Rank.FOUR), Position.of(File.C, Rank.FIVE));
        chessBoard.move(Position.of(File.C, Rank.FIVE), Position.of(File.C, Rank.SIX));

        chessBoard.move(Position.of(File.C, Rank.SIX), Position.of(File.C, Rank.FIVE));

    }
}
