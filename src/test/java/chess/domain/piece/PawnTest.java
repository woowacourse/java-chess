package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.Board;
import chess.domain.BoardFixtures;
import chess.domain.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PawnTest {

    private static final Pawn WHITE_PAWN = new Pawn(Color.WHITE);
    private static final Pawn BLACK_PAWN = new Pawn(Color.BLACK);

    // white
    @DisplayName("white pawn은 시작점인 경우 top 방향으로 한 칸 혹은 두 칸 이동 가능하다.")
    @ParameterizedTest
    @ValueSource(strings = {"b3", "b4"})
    void whitePawn_시작점_top방향_한칸_두칸_이동_가능하다(String target) {
        Board board = BoardFixtures.generateEmptyChessBoard().getBoard();

        assertDoesNotThrow(() -> WHITE_PAWN.validateMove(board, new Position("b2"), new Position(target)));
    }

    @DisplayName("white pawn이 시작점일 때 세 칸 이상 움직이는 경우 예외가 발생한다.")
    @Test
    void whitePawn이_시작점일때_세칸이상_이동한_경우_예외가_발생한다() {
        Board board = BoardFixtures.generateEmptyChessBoard().getBoard();
        Position sourcePosition = new Position("c2");
        Position targetPosition = new Position("c5");

        assertThatThrownBy(() -> WHITE_PAWN.validateMove(board, sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("white pawn이 시작점에서 두 칸 이동할 때 경로에 기물이 있는 경우 예외가 발생한다.")
    @Test
    void whitePawn이_시작점에서_이동할때_경로에_기물이_있는_경우_예외가_발생한다() {
        Board board = BoardFixtures.generateEmptyChessBoard().getBoard();
        Position sourcePosition = new Position("c2");
        Position pathPosition = new Position("c3");
        Position targetPosition = new Position("c4");

        board.place(pathPosition, new Pawn(Color.BLACK));

        assertThatThrownBy(() -> WHITE_PAWN.validateMove(board, sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("white pawn은 top 방향으로 한 칸 이동 가능하다.")
    @Test
    void whitePawn은_top방향으로_한칸_이동_가능하다() {
        Board board = BoardFixtures.generateEmptyChessBoard().getBoard();
        Position sourcePosition = new Position("c3");
        Position targetPosition = new Position("c4");

        assertDoesNotThrow(() -> WHITE_PAWN.validateMove(board, sourcePosition, targetPosition));
    }

    @DisplayName("white pawn이 두 칸 이동한 경우 예외가 발생한다.")
    @Test
    void whitePawn이_두칸_이동한_경우_예외가_발생한다() {
        Board board = BoardFixtures.generateEmptyChessBoard().getBoard();
        Position sourcePosition = new Position("c3");
        Position targetPosition = new Position("c5");

        assertThatThrownBy(() -> WHITE_PAWN.validateMove(board, sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("white pawn이 공격하는 경우 대각선으로 이동 가능하다.")
    @Test
    void whitePawn이_공격하는_경우_대각선으로_이동_가능하다() {
        Board board = BoardFixtures.generateEmptyChessBoard().getBoard();
        Position sourcePosition = new Position("c3");
        Position targetPosition = new Position("d4");

        board.place(targetPosition, new Pawn(Color.BLACK));

        assertDoesNotThrow(() -> WHITE_PAWN.validateMove(board, sourcePosition, targetPosition));
    }

    @DisplayName("white pawn이 대각선으로 이동할 때 기물이 없는 경우 예외가 발생한다.")
    @Test
    void whitePawn이_대각선으로_이동할때_기물이_없는_경우_예외가_발생한다() {
        Board board = BoardFixtures.generateEmptyChessBoard().getBoard();
        Position sourcePosition = new Position("c3");
        Position targetPosition = new Position("d4");

        assertThatThrownBy(() -> WHITE_PAWN.validateMove(board, sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("white pawn이 대각선으로 이동할 때 기물이 같은 진영인 경우 예외가 발생한다.")
    @Test
    void whitePawn이_대각선으로_이동할때_기물이_같은진영인_경우_예외가_발생한다() {
        Board board = BoardFixtures.generateEmptyChessBoard().getBoard();
        Position sourcePosition = new Position("c3");
        Position targetPosition = new Position("d4");

        board.place(targetPosition, new Pawn(Color.WHITE));

        assertThatThrownBy(() -> WHITE_PAWN.validateMove(board, sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // black
    @DisplayName("black pawn이 시작점인 경우 bottom 방향으로 한 칸 혹은 두 칸 이동 가능하다.")
    @ParameterizedTest
    @ValueSource(strings = {"d6", "d5"})
    void blackPawn_시작점_bottom방향_한칸_두칸_이동_가능하다(String target) {
        Board board = BoardFixtures.generateEmptyChessBoard().getBoard();

        assertDoesNotThrow(() -> BLACK_PAWN.validateMove(board, new Position("d7"), new Position(target)));
    }

    @DisplayName("black pawn이 시작점에서 두 칸 이동할 때 경로에 기물이 있는 경우 예외가 발생한다.")
    @Test
    void blackPawn이_시작점에서_이동할때_경로에_기물이_있는_경우_예외가_발생한다() {
        Board board = BoardFixtures.generateEmptyChessBoard().getBoard();
        Position sourcePosition = new Position("d7");
        Position pathPosition = new Position("d6");
        Position targetPosition = new Position("d5");

        board.place(pathPosition, new Pawn(Color.BLACK));

        assertThatThrownBy(() -> BLACK_PAWN.validateMove(board, sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("black pawn은 bottom 방향으로 한 칸 이동 가능하다.")
    @Test
    void blackPawn은_bottom방향으로_한칸_이동_가능하다() {
        Board board = BoardFixtures.generateEmptyChessBoard().getBoard();
        Position sourcePosition = new Position("d6");
        Position targetPosition = new Position("d5");

        assertDoesNotThrow(() -> BLACK_PAWN.validateMove(board, sourcePosition, targetPosition));
    }

    @DisplayName("black pawn이 두 칸 이동한 경우 예외가 발생한다.")
    @Test
    void blackPawn이_두칸_이동한_경우_예외가_발생한다() {
        Board board = BoardFixtures.generateEmptyChessBoard().getBoard();
        Position sourcePosition = new Position("d6");
        Position targetPosition = new Position("d4");

        assertThatThrownBy(() -> BLACK_PAWN.validateMove(board, sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("black pawn이 공격하는 경우 대각선으로 이동 가능하다.")
    @Test
    void blackPawn이_공격하는_경우_대각선으로_이동_가능하다() {
        Board board = BoardFixtures.generateEmptyChessBoard().getBoard();
        Position sourcePosition = new Position("d6");
        Position targetPosition = new Position("c5");

        board.place(targetPosition, new Pawn(Color.WHITE));

        assertDoesNotThrow(() -> BLACK_PAWN.validateMove(board, sourcePosition, targetPosition));
    }

    @DisplayName("black pawn이 대각선으로 이동할 때 기물이 없는 경우 예외가 발생한다.")
    @Test
    void blackPawn이_대각선으로_이동할때_기물이_없는_경우_예외가_발생한다() {
        Board board = BoardFixtures.generateEmptyChessBoard().getBoard();
        Position sourcePosition = new Position("d6");
        Position targetPosition = new Position("c5");

        assertThatThrownBy(() -> BLACK_PAWN.validateMove(board, sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("black pawn이 대각선으로 이동할 때 기물이 같은 진영인 경우 예외가 발생한다.")
    @Test
    void blackPawn이_대각선으로_이동할때_기물이_같은진영인_경우_예외가_발생한다() {
        Board board = BoardFixtures.generateEmptyChessBoard().getBoard();
        Position sourcePosition = new Position("d6");
        Position targetPosition = new Position("c5");

        board.place(targetPosition, new Pawn(Color.BLACK));

        assertThatThrownBy(() -> BLACK_PAWN.validateMove(board, sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
