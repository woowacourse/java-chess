package chess.domain.piece;

import static chess.domain.BoardFixtures.generateEmptyChessBoard;
import static chess.domain.BoardFixtures.setPiece;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {

    // white
    @DisplayName("white pawn은 시작점인 경우 top 방향으로 한 칸 혹은 두 칸 이동 가능하다.")
    @ParameterizedTest
    @CsvSource({"a2,a4", "a2,a3"})
    void whitePawn_시작점_top방향_한칸_두칸_이동_가능하다(String source, String target) {
        ChessBoard chessBoard = generateEmptyChessBoard();
        List<List<Piece>> board = chessBoard.getBoard();

        setPiece(board, source, new Pawn(Color.WHITE));
        Pawn whitePawn = new Pawn(Color.WHITE);

        assertDoesNotThrow(() -> whitePawn.validateMove(board, new Position(source), new Position(target)));
    }

    @DisplayName("white pawn이 시작점일 때 세 칸 이상 움직이는 경우 예외가 발생한다.")
    @Test
    void whitePawn이_시작점일때_세칸이상_이동한_경우_예외가_발생한다() {
        ChessBoard chessBoard = generateEmptyChessBoard();
        List<List<Piece>> board = chessBoard.getBoard();

        setPiece(board, "c2", new Pawn(Color.WHITE));
        Pawn pawn = new Pawn(Color.WHITE);

        assertThatThrownBy(() -> pawn.validateMove(board, new Position("c2"), new Position("c5")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("white pawn이 시작점에서 두 칸 이동할 때 경로에 기물이 있는 경우 예외가 발생한다.")
    @Test
    void whitePawn이_시작점에서_이동할때_경로에_기물이_있는_경우_예외가_발생한다() {
        ChessBoard chessBoard = generateEmptyChessBoard();
        List<List<Piece>> board = chessBoard.getBoard();

        setPiece(board, "a3", new Pawn(Color.WHITE));
        setPiece(board, "a4", new Pawn(Color.WHITE));
        Pawn whitePawn = new Pawn(Color.WHITE);

        assertThatThrownBy(() -> whitePawn.validateMove(board, new Position("a3"), new Position("a4")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("white pawn은 top 방향으로 한 칸 이동 가능하다.")
    @ParameterizedTest
    @CsvSource({"a3,a4", "a4,a5"})
    void whitePawn은_top방향으로_한칸_이동_가능하다(String source, String target) {
        ChessBoard chessBoard = generateEmptyChessBoard();
        List<List<Piece>> board = chessBoard.getBoard();

        setPiece(board, source, new Pawn(Color.WHITE));
        Pawn pawn = new Pawn(Color.WHITE);

        assertDoesNotThrow(() -> pawn.validateMove(board, new Position(source), new Position(target)));
    }

    @DisplayName("white pawn이 두 칸 이동한 경우 예외가 발생한다.")
    @Test
    void whitePawn이_두칸_이동한_경우_예외가_발생한다() {
        ChessBoard chessBoard = generateEmptyChessBoard();
        List<List<Piece>> board = chessBoard.getBoard();

        setPiece(board, "a3", new Pawn(Color.WHITE));
        Pawn whitePawn = new Pawn(Color.WHITE);

        assertThatThrownBy(() -> whitePawn.validateMove(board, new Position("a3"), new Position("a5")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("white pawn이 공격하는 경우 대각선으로 이동 가능하다.")
    @ParameterizedTest
    @CsvSource({"b2,a3", "b2,c3", "c3,d4"})
    void whitePawn이_공격하는_경우_대각선으로_이동_가능하다(String source, String target) {
        ChessBoard chessBoard = generateEmptyChessBoard();
        List<List<Piece>> board = chessBoard.getBoard();
        Pawn whitePawn = new Pawn(Color.WHITE);

        setPiece(board, source, new Pawn(Color.WHITE));
        setPiece(board, target, new Pawn(Color.BLACK));

        assertDoesNotThrow(() -> whitePawn.validateMove(board, new Position(source), new Position(target)));
    }

    @DisplayName("white pawn이 대각선으로 이동할 때 기물이 없는 경우 예외가 발생한다.")
    @Test
    void whitePawn이_대각선으로_이동할때_기물이_없는_경우_예외가_발생한다() {
        ChessBoard chessBoard = generateEmptyChessBoard();
        List<List<Piece>> board = chessBoard.getBoard();

        setPiece(board, "c3", new Pawn(Color.WHITE));
        Pawn pawn = new Pawn(Color.WHITE);

        assertThatThrownBy(() -> pawn.validateMove(board, new Position("c3"), new Position("d4")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("white pawn이 대각선으로 이동할 때 기물이 같은 진영인 경우 예외가 발생한다.")
    @Test
    void whitePawn이_대각선으로_이동할때_기물이_같은진영인_경우_예외가_발생한다() {
        ChessBoard chessBoard = generateEmptyChessBoard();
        List<List<Piece>> board = chessBoard.getBoard();

        setPiece(board, "c3", new Pawn(Color.WHITE));
        setPiece(board, "d4", new Pawn(Color.WHITE));
        Pawn whitePawn = new Pawn(Color.WHITE);

        assertThatThrownBy(() -> whitePawn.validateMove(board, new Position("c3"), new Position("d4")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // black
    @DisplayName("black pawn이 시작점인 경우 bottom 방향으로 한 칸 혹은 두 칸 이동 가능하다.")
    @ParameterizedTest
    @CsvSource({"a7,a6", "a7,a5"})
    void blackPawn_시작점_bottom방향_한칸_두칸_이동_가능하다(String source, String target) {
        ChessBoard chessBoard = generateEmptyChessBoard();
        List<List<Piece>> board = chessBoard.getBoard();

        Pawn pawn = new Pawn(Color.BLACK);

        assertDoesNotThrow(() -> pawn.validateMove(board, new Position(source), new Position(target)));
    }

    @DisplayName("black pawn이 시작점에서 두 칸 이동할 때 경로에 기물이 있는 경우 예외가 발생한다.")
    @Test
    void blackPawn이_시작점에서_이동할때_경로에_기물이_있는_경우_예외가_발생한다() {
        ChessBoard chessBoard = generateEmptyChessBoard();
        List<List<Piece>> board = chessBoard.getBoard();

        setPiece(board, "d7", new Pawn(Color.BLACK));
        setPiece(board, "d6", new Pawn(Color.BLACK));
        Pawn pawn = new Pawn(Color.BLACK);


        assertThatThrownBy(() -> pawn.validateMove(board, new Position("d7"), new Position("d5")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("black pawn은 bottom 방향으로 한 칸 이동 가능하다.")
    @ParameterizedTest
    @CsvSource({"a6,a5", "a5,a4"})
    void blackPawn은_bottom방향으로_한칸_이동_가능하다(String source, String target) {
        ChessBoard chessBoard = generateEmptyChessBoard();
        List<List<Piece>> board = chessBoard.getBoard();

        setPiece(board, source, new Pawn(Color.BLACK));
        Pawn pawn = new Pawn(Color.BLACK);

        assertDoesNotThrow(() -> pawn.validateMove(board, new Position(source), new Position(target)));
    }

    @DisplayName("black pawn이 두 칸 이동한 경우 예외가 발생한다.")
    @Test
    void blackPawn이_두칸_이동한_경우_예외가_발생한다() {
        ChessBoard chessBoard = generateEmptyChessBoard();
        List<List<Piece>> board = chessBoard.getBoard();

        setPiece(board, "d6", new Pawn(Color.BLACK));
        Pawn pawn = new Pawn(Color.BLACK);

        assertThatThrownBy(() -> pawn.validateMove(board, new Position("d6"), new Position("d4")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("black pawn이 공격하는 경우 대각선으로 이동 가능하다.")
    @ParameterizedTest
    @CsvSource({"b7,a6", "b7,c6"})
    void blackPawn이_공격하는_경우_대각선으로_이동_가능하다(String source, String target) {
        ChessBoard chessBoard = generateEmptyChessBoard();
        List<List<Piece>> board = chessBoard.getBoard();

        setPiece(board, source, new Pawn(Color.BLACK));
        setPiece(board, target, new Pawn(Color.WHITE));
        Pawn pawn = new Pawn(Color.BLACK);

        assertDoesNotThrow(() -> pawn.validateMove(board, new Position(source), new Position(target)));
    }

    @DisplayName("black pawn이 대각선으로 이동할 때 기물이 없는 경우 예외가 발생한다.")
    @Test
    void blackPawn이_대각선으로_이동할때_기물이_없는_경우_예외가_발생한다() {
        ChessBoard chessBoard = generateEmptyChessBoard();
        List<List<Piece>> board = chessBoard.getBoard();

        setPiece(board, "d6", new Pawn(Color.BLACK));
        Pawn pawn = new Pawn(Color.BLACK);

        assertThatThrownBy(() -> pawn.validateMove(board, new Position("d6"), new Position("c5")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("black pawn이 대각선으로 이동할 때 기물이 같은 진영인 경우 예외가 발생한다.")
    @Test
    void blackPawn이_대각선으로_이동할때_기물이_같은진영인_경우_예외가_발생한다() {
        ChessBoard chessBoard = generateEmptyChessBoard();
        List<List<Piece>> board = chessBoard.getBoard();

        setPiece(board, "d6", new Pawn(Color.BLACK));
        setPiece(board, "c5", new Pawn(Color.BLACK));
        Pawn blackPawn = new Pawn(Color.BLACK);

        assertThatThrownBy(() -> blackPawn.validateMove(board, new Position("d6"), new Position("c5")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
