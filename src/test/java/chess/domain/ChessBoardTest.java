package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    private static final String WRONG_START_ERROR_MESSAGE = "시작 위치에 말이 없습니다.";
    private static final String OBSTACLE_IN_PATH_ERROR_MESSAGE = "경로에 다른 말이 있어서 이동할 수 없습니다.";
    private static final String WRONG_PAWN_PATH_ERROR_MESSAGE = "폰은 공격 할 때만 대각선으로 이동할 수 있습니다.";
    private static final String WRONG_DESTINATION_ERROR_MESSAGE = "해당 말이 갈 수 없는 위치입니다.";
    private static final String WRONG_PIECE_COLOR_ERROR_MESSAGE = "자신 팀의 말만 이동시킬 수 있습니다.";
    private static final String WRONG_ATTACK_TARGET_ERROR_MESSAGE = "상대 팀의 말만 공격할 수 있습니다.";
    private static final Camp CURRENT_CAMP = Camp.WHITE;

    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = new ChessBoard();
    }

    @DisplayName("체스판에 초기 말을 세팅한다.")
    @Test
    void 체스판_초기화() {
        ChessBoard chessBoard = new ChessBoard();

        Map<Position, Piece> positionPieces = chessBoard.piecesByPosition();

        assertThat(positionPieces).hasSize(32);
    }

    @DisplayName("시작 위치에 말이 없으면 예외를 던진다.")
    @Test
    void 시작위치_말없음_예외() {
        assertThatThrownBy(() -> chessBoard.move(List.of(1, 3), List.of(1, 4), CURRENT_CAMP))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(WRONG_START_ERROR_MESSAGE);
    }

    @DisplayName("경로에 장애물이 있으면 예외를 던진다.")
    @Test
    void 경로_장애물_예외() {
        assertThatThrownBy(() -> chessBoard.move(List.of(1, 1), List.of(3, 1), CURRENT_CAMP))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(OBSTACLE_IN_PATH_ERROR_MESSAGE);
    }

    @DisplayName("폰은 대각선 이동 요청 시 공격 대상이 없으면 예외를 던진다.")
    @Test
    void 폰_공격_대상없음_예외() {
        assertThatThrownBy(() -> chessBoard.move(List.of(1, 2), List.of(2, 3), CURRENT_CAMP))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(WRONG_PAWN_PATH_ERROR_MESSAGE);
    }

    @DisplayName("말이 갈 수 없는 위치로 이동 요청 시 예외를 던진다.")
    @Test
    void 갈수없는_위치_예외() {
        assertThatThrownBy(() -> chessBoard.move(List.of(1, 2), List.of(1, 5), CURRENT_CAMP))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(WRONG_DESTINATION_ERROR_MESSAGE);
    }

    @DisplayName("이동 시키려는 말이 상대 팀의 말이면 예외를 던진다.")
    @Test
    void 상대_말_이동_예외() {
        assertThatThrownBy(() -> chessBoard.move(List.of(1, 7), List.of(1, 6), CURRENT_CAMP))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(WRONG_PIECE_COLOR_ERROR_MESSAGE);
    }

    @DisplayName("공격 대상이 같은 팀의 말이면 예외를 던진다.")
    @Test
    void 같은_팀_공격_예외() {
        chessBoard.move(List.of(2, 2), List.of(2, 3), CURRENT_CAMP);

        assertThatThrownBy(() -> chessBoard.move(List.of(1, 2), List.of(2, 3), CURRENT_CAMP))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(WRONG_ATTACK_TARGET_ERROR_MESSAGE);
    }

    @DisplayName("폰은 대각선에 상대 팀의 말이 있을 때 공격할 수 있다.")
    @Test
    void 폰_대각선_공격() {
        chessBoard.move(List.of(1, 2), List.of(1, 4), CURRENT_CAMP);
        chessBoard.move(List.of(1, 4), List.of(1, 5), CURRENT_CAMP);
        chessBoard.move(List.of(1, 5), List.of(1, 6), CURRENT_CAMP);
        chessBoard.move(List.of(1, 6), List.of(2, 7), CURRENT_CAMP);

        Map<Position, Piece> piecesByPosition = chessBoard.piecesByPosition();

        assertThat(piecesByPosition.get(Position.of(2, 7)))
                .isEqualTo(new Pawn(Camp.WHITE));
    }

    @DisplayName("말은 도착지가 이동할 수 있는 위치이면, 이동하고 이전의 위치는 비워준다.")
    @Test
    void 말_이동_위치_반영() {
        chessBoard.move(List.of(1, 2), List.of(1, 4), CURRENT_CAMP);

        Map<Position, Piece> piecesByPosition = chessBoard.piecesByPosition();

        assertThat(piecesByPosition.containsKey(Position.of(1, 2)))
                .isFalse();
        assertThat(piecesByPosition.get(Position.of(1, 4)))
                .isEqualTo(new Pawn(Camp.WHITE));
    }
}
