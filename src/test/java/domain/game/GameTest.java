package domain.game;

import static domain.game.File.A;
import static domain.game.File.B;
import static domain.game.File.C;
import static domain.game.File.D;
import static domain.game.File.E;
import static domain.game.File.F;
import static domain.game.File.G;
import static domain.game.File.H;
import static domain.game.Rank.EIGHT;
import static domain.game.Rank.FIVE;
import static domain.game.Rank.FOUR;
import static domain.game.Rank.ONE;
import static domain.game.Rank.SEVEN;
import static domain.game.Rank.SIX;
import static domain.game.Rank.THREE;
import static domain.game.Rank.TWO;
import static domain.game.Side.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.piece.Piece;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Game은")
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
        Position sourcePosition = Position.of(B, TWO);
        Position targetPosition = Position.of(B, FOUR);
        Piece sourcePiece = chessBoard.get(sourcePosition);
        game.move(sourcePosition, targetPosition);    // Pawn을 위로 2칸 이동
        assertAll(
                () -> assertThat(chessBoard.get(sourcePosition).isEmptyPiece()).isTrue(),
                () -> assertThat(chessBoard.get(targetPosition)).isEqualTo(sourcePiece)
        );
    }

    @DisplayName("상대편 말을 잡는 이동을 실행하는 경우, target position이 source piece로 대체된다.")
    @Test
    void shouldKillWhenMoveToOpponentPiece() {
        Piece sourcePiece = chessBoard.get(Position.of(B, TWO));
        // 1. White pawn을 위로 2칸 이동
        game.move(Position.of(B, TWO), Position.of(B, FOUR));
        // 2. Black pawn을 아래로 2칸 이동
        game.move(Position.of(C, SEVEN), Position.of(C, FIVE));
        // 3. 1에서 움직인 White pawn이 2의 Black pawn을 잡는다.
        game.move(Position.of(B, FOUR), Position.of(C, FIVE));

        assertAll(
                () -> assertThat(chessBoard.get(Position.of(C, FIVE))).isEqualTo(sourcePiece),
                () -> assertThat(chessBoard.get(Position.of(B, TWO)).isEmptyPiece()).isTrue()
        );
    }

    @DisplayName("잘못된 움직임을 입력 받으면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenInvalidMovement() {
        game.move(Position.of(E, TWO), Position.of(E, FOUR));    // 1. White pawn이 위로 2칸 이동한다.

        assertThatThrownBy(() ->
                game.move(Position.of(E, ONE), Position.of(E, THREE))) // 2. King의 2칸 이동 시도로 인해 예외가 발생한다.
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("다른 진영의 말은 움직일 수 없습니다.");
    }

    @DisplayName("이동 경로에 아군 말이 있을 경우 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenPathIncludeSameSidePiece() {
        assertThatThrownBy(() ->
                // 1. Bishop의 이동 경로에 pawn이 존재하므로 예외가 발생한다.
                game.move(Position.of(C, ONE), Position.of(E, THREE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로에 다른 말이 있습니다.");
    }

    @DisplayName("이동 경로에 적군 말이 있을 경우 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenPathIncludeOpponentSidePiece() {
        // 1. d,2에 있는 White pawn을 d,4로 이동시킨다.
        game.move(Position.of(D, TWO), Position.of(D, FOUR));
        // 2. e,7에 있는 Black pawn을 e,5로 이동시킨다.
        game.move(Position.of(E, SEVEN), Position.of(E, FIVE));
        // 3. d,4에 있는 White pawn을 d,5로 이동시킨다.
        game.move(Position.of(D, FOUR), Position.of(D, FIVE));
        // 4. e,5에 있는 Black pawn을 e,4로 이동시킨다.
        game.move(Position.of(E, FIVE), Position.of(E, FOUR));
        // 5. d,5에 있는 White pawn을 d,6로 이동시킨다.
        game.move(Position.of(D, FIVE), Position.of(D, SIX));
        // 6. e,4에 있는 Black pawn을 e,3로 이동시킨다.
        game.move(Position.of(E, FOUR), Position.of(E, THREE));

        assertThatThrownBy(() ->
                // 3. White bishop이 f,4로 가는 경로인 e,3에 black pawn이 존재하기 때문에 예외가 발생한다.
                game.move(Position.of(C, ONE), Position.of(F, FOUR)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로에 다른 말이 있습니다.");
    }

    @DisplayName("knight의 이동 경로에 말이 있어도 뛰어넘어서 target position으로 이동시킨다.")
    @Test
    void shouldMoveWhenExistPieceInPathOfKnightMovement() {
        Piece sourcePieceOfWhiteKnight = chessBoard.get(Position.of(B, ONE));
        game.move(Position.of(B, ONE), Position.of(C, THREE));
        assertThat(chessBoard.get(Position.of(C, THREE))).isEqualTo(sourcePieceOfWhiteKnight);
    }

    @DisplayName("상대편 말을 움직이면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenMovePieceOfOpponentSide() {
        assertThatThrownBy(() -> game.move(Position.of(C, SEVEN), Position.of(C, FIVE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("다른 진영의 말은 움직일 수 없습니다.");
    }

    @DisplayName("움직일 때 Source position에 말이 없으면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenSourcePositionisEmpty() {
        assertThatThrownBy(() -> game.move(Position.of(C, THREE), Position.of(C, FOUR)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("source위치에 말이 없습니다.");
    }

    @DisplayName("Pawn을 움직일 때, 알맞은 움직임이 아니면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenPawnMoveWrong() {
        assertThatThrownBy(() -> game.move(Position.of(B, TWO), Position.of(B, FIVE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 움직임이 아닙니다.");
    }

    @DisplayName("게임 시작 직후 Black 진영의 점수를 요청하면 38점을 반환한다.")
    @Test
    void shouldReturnScoreOf38WhenCalculateScoreOfWhiteRightAfterInitializeGame() {
        Score score = game.calculateBlackScore();
        assertThat(score.getNumber()).isEqualTo(38);
    }

    @DisplayName("Pawn 1개, Knight 1개를 잃고 Pawn 2개가 한 File에 존재하는 White 진영의 점수를 요청하면 33점을 반환한다.")
    @Test
    void shouldReturnScoreOf33AndHalfWhenCalculateScoreOfWhiteSideWhichLosePawnAndKnightAndTwoPawnIsOnSameFile() {
        game.move(Position.of(A, TWO), Position.of(A, THREE));
        game.move(Position.of(E, SEVEN), Position.of(E, FIVE));
        game.move(Position.of(A, THREE), Position.of(A, FOUR));
        game.move(Position.of(D, EIGHT), Position.of(H, FOUR));
        game.move(Position.of(A, FOUR), Position.of(A, FIVE));
        game.move(Position.of(H, FOUR), Position.of(F, TWO));
        game.move(Position.of(A, FIVE), Position.of(A, SIX));
        game.move(Position.of(F, TWO), Position.of(G, ONE));
        game.move(Position.of(A, SIX), Position.of(B, SEVEN));
        /* 이동 후 체스판 상태
        RNB.KBNR
        PpPP.PPP
        ........
        ....P...
        ........
        ........
        .pppp.pp
        rnbqkbQr
        */
        Score score = game.calculateWhiteScore();
        assertThat(score.getNumber()).isEqualTo(33.5);
    }

    @DisplayName("한 진영의 King이 죽었으면 true를 반환한다.")
    @Test
    void shouldReturnTrueWhenKingDie() {
        game.move(Position.of(E, TWO), Position.of(E, FOUR));
        game.move(Position.of(A, SEVEN), Position.of(A, SIX));
        game.move(Position.of(E, FOUR), Position.of(E, FIVE));
        game.move(Position.of(A, SIX), Position.of(A, FIVE));
        game.move(Position.of(E, FIVE), Position.of(E, SIX));
        game.move(Position.of(A, FIVE), Position.of(A, FOUR));
        game.move(Position.of(E, SIX), Position.of(D, SEVEN));
        game.move(Position.of(A, FOUR), Position.of(A, THREE));
        game.move(Position.of(D, SEVEN), Position.of(E, EIGHT));
        /* 이동 후 체스판 상태
        RNBQpBNR
        .PP.PPPP
        ........
        ........
        ........
        P.......
        pppp.ppp
        rnbqkbnr
        */
        assertThat(game.isEnd()).isTrue();
    }

    @DisplayName("King이 죽지 않았으면 false를 반환한다.")
    @Test
    void shouldReturnFalseWhenAllKingIsAlive() {
        // 체스판 초기화 직후 상태
        assertThat(game.isEnd()).isFalse();
    }
}x
