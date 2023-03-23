package domain.game;

import domain.piece.Piece;
import domain.piece.Position;
import domain.piece.Side;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class BoardTest {
    private Map<Position, Piece> chessBoard;
    private Board board;

    @BeforeEach
    void generateGame() {
        this.chessBoard = new ChessBoardGenerator().generate();
        this.board = new Board(chessBoard);
    }

    @DisplayName("움직일 수 있는 source postion과 target position을 입력받은 경우," +
            "source position에 EmptyPiece가 저장되고," +
            "target position에는 source piece가 저장된다")
    @Test
    void shouldMoveCorrectlyWhenInputMovablePositions() {
        Position sourcePosition = Position.of("b", "2");
        Position targetPosition = Position.of("b", "4");
        Piece sourcePiece = chessBoard.get(sourcePosition);

        board.move(sourcePosition, targetPosition);    // Pawn을 위로 2칸 이동

        assertAll(
                () -> assertThat(chessBoard.get(sourcePosition).isEmptyPiece()).isTrue(),
                () -> assertThat(chessBoard.get(targetPosition)).isEqualTo(sourcePiece)
        );
    }

    @DisplayName("상대편 말을 잡는 경우, target position이 source piece로 대체된다.")
    @Test
    void shouldKillWhenMoveToOpponentPiece() {
        Piece sourcePiece = chessBoard.get(Position.of("b", "2"));
        // 1. White pawn을 위로 2칸 이동
        board.move(Position.of("b", "2"), Position.of("b", "4"));
        // 2. Black pawn을 아래로 2칸 이동
        board.move(Position.of("c", "7"), Position.of("c", "5"));
        // 3. 1에서 움직인 White pawn이 2의 Black pawn을 잡는다.
        board.move(Position.of("b", "4"), Position.of("c", "5"));

        assertAll(
                () -> assertThat(chessBoard.get(Position.of("c", "5"))).isEqualTo(sourcePiece),
                () -> assertThat(chessBoard.get(Position.of("b", "2")).isEmptyPiece()).isTrue()
        );
    }

    @DisplayName("잘못된 움직임을 입력 받으면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenInvalidMovement() {
        board.isMovable(Position.of("e", "2"), Position.of("e", "4"), Side.WHITE);    // 1. White pawn이 위로 2칸 이동한다.

        assertThatThrownBy(() ->
                board.isMovable(Position.of("e", "1"), Position.of("e", "3"), Side.WHITE)) // 2. King의 2칸 이동 시도로 인해 예외가 발생한다.
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 움직임이 아닙니다.");
    }

    @DisplayName("이동 경로에 아군 말이 있을 경우 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenPathIncludeSameSidePiece() {
        assertThatThrownBy(() ->
                board.isMovable(Position.of("c", "1"), Position.of("e", "3"), Side.WHITE)) // 1. Bishop의 이동 경로에 pawn이 존재하므로 예외가 발생한다.
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로에 다른 말이 있습니다.");
    }

    @DisplayName("이동 경로에 적군 말이 있을 경우 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenPathIncludeOpponentSidePiece() {
        // 1. e,7에 있는 Black pawn을 e,3로 이동시킨다.
        board.move(Position.of("e", "7"), Position.of("e", "5"));
        board.move(Position.of("e", "5"), Position.of("e", "4"));
        board.move(Position.of("e", "4"), Position.of("e", "3"));

        // 2. d,2에 있는 White pawn을 d,4로 이동시킨다.
        board.move(Position.of("d", "2"), Position.of("d", "4"));

        assertThatThrownBy(() ->
                // 3. White bishop이 f,4로 가는 경로인 e,3에 black pawn이 존재하기 때문에 예외가 발생한다.
                board.isMovable(Position.of("c", "1"), Position.of("f", "4"), Side.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로에 다른 말이 있습니다.");
    }

    @DisplayName("knight는 이동 경로에 말이 있어도 뛰어넘어서 target position으로 이동할 수 있다.")
    @Test
    void shouldMoveWhenExistPieceInPathOfKnightMovement() {
        Piece sourcePieceOfWhiteKnight = chessBoard.get(Position.of("b", "1"));
        // 1. d,7에 있는 black pawn을 d,3로 이동시킨다.
        board.move(Position.of("d", "7"), Position.of("d", "5"));
        board.move(Position.of("d", "5"), Position.of("d", "4"));
        board.move(Position.of("d", "4"), Position.of("d", "3"));
        // 2. d,3에 있는 black pawn이 c,2에 있는 white pawn을 잡는다.
        board.move(Position.of("d", "3"), Position.of("c", "2"));
        // 3. b,1에 있는 white knight가 c,3로 이동할 때, b,2에 있는 white pawn과, c,2에 있는 black pawn을 뛰어 넘는다.
        board.move(Position.of("b", "1"), Position.of("c", "3"));

        assertThat(chessBoard.get(Position.of("c", "3"))).isEqualTo(sourcePieceOfWhiteKnight);
    }

    @DisplayName("source position에 emptyPiece가 위치할 경우 예외가 발생한다.")
    @Test
    void throwExceptionWhenMoveEmptyPiece() {
        Position sourcePosition = Position.of("c", "3");
        assertThatThrownBy(() -> board.isMovable(sourcePosition, Position.of("d", "5"), Side.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(sourcePosition + "에 움직일 수 있는 말이 없습니다.");
    }


    @DisplayName("target position에 킹 존재 여부를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"1, true", "2, false"})
    void isKingTest(String rank, boolean result) {
        //given
        assertThat(board.isKing(Position.of("e", rank))).isEqualTo(result);
    }

}