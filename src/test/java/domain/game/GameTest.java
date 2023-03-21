package domain.game;

import static domain.piece.Side.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.piece.Piece;
import domain.piece.Position;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
    private Map<Position, Piece> chessBoard;
    private Game game;

    @BeforeEach
    void generateGame() {
        this.chessBoard = new ChessBoardGenerator().generate();
        this.game = new Game(this.chessBoard, WHITE);
    }

    @DisplayName("움직일 수 있는 source postion과 target position을 입력받은 경우," +
            "source position에 EmptyPiece가 저장되고," +
            "target position에는 source piece가 저장된다")
    @Test
    void shouldMoveCorrectlyWhenInputMovablePositions() {
        Position sourcePosition = Position.of("b", "2");
        Position targetPosition = Position.of("b", "4");
        Piece sourcePiece = chessBoard.get(sourcePosition);

        game.move(sourcePosition, targetPosition);    // Pawn을 위로 2칸 이동

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
        game.move(Position.of("b", "2"), Position.of("b", "4"));
        // 2. Black pawn을 아래로 2칸 이동
        game.move(Position.of("c", "7"), Position.of("c", "5"));
        // 3. 1에서 움직인 White pawn이 2의 Black pawn을 잡는다.
        game.move(Position.of("b", "4"), Position.of("c", "5"));

        assertAll(
                () -> assertThat(chessBoard.get(Position.of("c", "5"))).isEqualTo(sourcePiece),
                () -> assertThat(chessBoard.get(Position.of("b", "2")).isEmptyPiece()).isTrue()
        );
    }

    @DisplayName("잘못된 움직임을 입력 받으면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenInvalidMovement() {
        game.move(Position.of("e", "2"), Position.of("e", "4"));    // 1. White pawn이 위로 2칸 이동한다.

        assertThatThrownBy(() ->
                game.move(Position.of("e", "1"), Position.of("e", "3"))) // 2. King의 2칸 이동 시도로 인해 예외가 발생한다.
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 움직임이 아닙니다.");
    }

    @DisplayName("이동 경로에 아군 말이 있을 경우 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenPathIncludeSameSidePiece() {
        assertThatThrownBy(() ->
                // 1. Bishop의 이동 경로에 pawn이 존재하므로 예외가 발생한다.
                game.move(Position.of("c", "1"), Position.of("e", "3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로에 다른 말이 있습니다.");
    }

    @DisplayName("이동 경로에 적군 말이 있을 경우 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenPathIncludeOpponentSidePiece() {
        // 1. e,7에 있는 Black pawn을 e,3로 이동시킨다.
        game.move(Position.of("e", "7"), Position.of("e", "5"));
        game.move(Position.of("e", "5"), Position.of("e", "4"));
        game.move(Position.of("e", "4"), Position.of("e", "3"));

        // 2. d,2에 있는 White pawn을 d,4로 이동시킨다.
        game.move(Position.of("d", "2"), Position.of("d", "4"));

        assertThatThrownBy(() ->
                // 3. White bishop이 f,4로 가는 경로인 e,3에 black pawn이 존재하기 때문에 예외가 발생한다.
                game.move(Position.of("c", "1"), Position.of("f", "4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로에 다른 말이 있습니다.");
    }

    @DisplayName("knight는 이동 경로에 말이 있어도 뛰어넘어서 target position으로 이동할 수 있다.")
    @Test
    void shouldMoveWhenExistPieceInPathOfKnightMovement() {
        Piece sourcePieceOfWhiteKnight = chessBoard.get(Position.of("b", "1"));
        // 1. d,7에 있는 black pawn을 d,3로 이동시킨다.
        game.move(Position.of("d", "7"), Position.of("d", "5"));
        game.move(Position.of("d", "5"), Position.of("d", "4"));
        game.move(Position.of("d", "4"), Position.of("d", "3"));
        // 2. d,3에 있는 black pawn이 c,2에 있는 white pawn을 잡는다.
        game.move(Position.of("d", "3"), Position.of("c", "2"));
        // 3. b,1에 있는 white knight가 c,3로 이동할 때, b,2에 있는 white pawn과, c,2에 있는 black pawn을 뛰어 넘는다.
        game.move(Position.of("b", "1"), Position.of("c", "3"));

        assertThat(chessBoard.get(Position.of("c", "3"))).isEqualTo(sourcePieceOfWhiteKnight);
    }

    @DisplayName("상대편 말을 움직이면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenMovePieceOfOpponentSide() {
        assertThatThrownBy(() -> game.move(Position.of("c", "2"), Position.of("c", "4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("다른 진영의 말은 움직일 수 없습니다.");
    }

    @DisplayName("Source position에 말이 없으면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenSourcePositionisEmpty() {
        assertThatThrownBy(() -> game.move(Position.of("c", "3"), Position.of("c", "4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("source위치에 말이 없습니다.");
    }
}
