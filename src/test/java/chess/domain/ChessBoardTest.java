package chess.domain;

import static chess.domain.piece.PieceFixture.BLACK_KING;
import static chess.domain.piece.PieceFixture.WHITE_PAWN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    private static final String WRONG_START_ERROR_MESSAGE = "시작 위치에 말이 없습니다.";
    private static final String OBSTACLE_IN_PATH_ERROR_MESSAGE = "경로에 다른 말이 있어서 이동할 수 없습니다.";
    private static final String WRONG_PIECE_COLOR_ERROR_MESSAGE = "자신 팀의 말만 이동시킬 수 있습니다.";
    private static final String WRONG_ATTACK_ERROR_MESSAGE = "선택한 말로 공격할 수 없습니다.";
    private static final String WRONG_DESTINATION_ERROR_MESSAGE = "해당 말이 갈 수 없는 위치입니다.";

    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = new ChessBoard();
    }

    @DisplayName("체스판에 초기 말을 세팅한다.")
    @Test
    void 체스판_초기화() {
        Map<Position, Piece> positionPieces = chessBoard.piecesByPosition();

        assertThat(positionPieces).hasSize(64);
    }

    @DisplayName("정해진 게임 규칙에 따라, 말을 움직일 때마다 진영 턴을 전환한다.")
    @Test
    void 진영_턴_전환() {
        chessBoard = new ChessBoard();

        chessBoard.move(Position.of(1, 2), Position.of(1, 4));

        assertThatThrownBy(() -> chessBoard.move(Position.of(1, 4), Position.of(1, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(WRONG_PIECE_COLOR_ERROR_MESSAGE);
    }

    @DisplayName("시작 위치에 말이 없으면 예외를 던진다.")
    @Test
    void 시작위치_말없음_예외() {
        assertThatThrownBy(() -> chessBoard.move(Position.of(1, 3), Position.of(1, 4)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(WRONG_START_ERROR_MESSAGE);
    }

    @DisplayName("경로에 장애물이 있으면 예외를 던진다.")
    @Test
    void 경로_장애물_예외() {
        assertThatThrownBy(() -> chessBoard.move(Position.of(1, 1), Position.of(3, 1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(OBSTACLE_IN_PATH_ERROR_MESSAGE);
    }

    @DisplayName("말이 갈 수 없는 위치로 이동 요청 시 예외를 던진다.")
    @Test
    void 갈수없는_위치_예외() {
        assertThatThrownBy(() -> chessBoard.move(Position.of(1, 2), Position.of(1, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(WRONG_DESTINATION_ERROR_MESSAGE);
    }

    @DisplayName("이동 시키려는 말이 상대 팀의 말이면 예외를 던진다.")
    @Test
    void 상대_말_이동_예외() {
        assertThatThrownBy(() -> chessBoard.move(Position.of(1, 7), Position.of(1, 6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(WRONG_PIECE_COLOR_ERROR_MESSAGE);
    }

    @DisplayName("공격 대상이 같은 팀의 말이면 예외를 던진다.")
    @Test
    void 같은_팀_공격_예외() {
        chessBoard.move(Position.of(2, 2), Position.of(2, 3));
        chessBoard.move(Position.of(2, 7), Position.of(2, 5));

        assertThatThrownBy(() -> chessBoard.move(Position.of(1, 2), Position.of(2, 3)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(WRONG_ATTACK_ERROR_MESSAGE);
    }

    @DisplayName("말은 도착지가 이동할 수 있는 위치이면, 이동하고 이전의 위치는 비워준다.")
    @Test
    void 말_이동_위치_반영() {
        chessBoard.move(Position.of(1, 2), Position.of(1, 4));

        Map<Position, Piece> piecesByPosition = chessBoard.piecesByPosition();

        assertThat(piecesByPosition.get(Position.of(1, 2)))
                .isEqualTo(Empty.getInstance());
        assertThat(piecesByPosition.get(Position.of(1, 4)))
                .isEqualTo(new Pawn(Camp.WHITE));
    }

    @DisplayName("진영 별 남아있는 말에 따른 점수를 합산한다.")
    @Test
    void 진영_별_점수_합산() {
        assertThat(chessBoard.calculateScoreByCamp(Camp.WHITE))
                .isEqualTo(38);
        assertThat(chessBoard.calculateScoreByCamp(Camp.BLACK))
                .isEqualTo(38);
    }

    @DisplayName("상대 진영의 킹을 잡으면 해당 체스판은 종료된다.")
    @Test
    void 킹_체크_시_종료() {
        Position source = Position.of(1, 1);
        Position destination = Position.of(2, 2);
        ChessBoard fakeChessBoard = new ChessBoard(new HashMap<>(Map.of(
                source, WHITE_PAWN,
                destination, BLACK_KING)
        ));

        boolean isOver = fakeChessBoard.move(source, destination);

        assertThat(isOver).isTrue();
    }

}
