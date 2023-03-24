package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class ChessBoardTest {

    private static final String WRONG_START_ERROR_MESSAGE = "시작 위치에 말이 없습니다.";
    private static final String OBSTACLE_IN_PATH_ERROR_MESSAGE = "경로에 다른 말이 있어서 이동할 수 없습니다.";
    private static final String WRONG_DESTINATION_ERROR_MESSAGE = "해당 말이 갈 수 없는 위치입니다.";
    private static final String WRONG_PIECE_COLOR_ERROR_MESSAGE = "자신 팀의 말만 이동시킬 수 있습니다.";
    private static final TeamColor currentTeamColor = TeamColor.WHITE;
    private static final TeamColor otherTeamColor = TeamColor.BLACK;

    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = new ChessBoard(InitialPiece.getPiecesWithPosition());
    }

    @DisplayName("체스판에 초기 말을 세팅한다.")
    @Test
    void 체스판_초기화() {
        ChessBoard chessBoard = new ChessBoard(InitialPiece.getPiecesWithPosition());

        Map<Position, Piece> positionPieces = chessBoard.piecesByPosition();

        assertThat(positionPieces).hasSize(32);
    }

    @DisplayName("시작 위치에 말이 없으면 예외를 던진다.")
    @Test
    void 시작위치_말없음_예외() {
        assertThatThrownBy(
            () -> chessBoard.move(Position.from("A3"), Position.from("A4"), currentTeamColor))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(WRONG_START_ERROR_MESSAGE);
    }

    @DisplayName("경로에 장애물이 있으면 예외를 던진다.")
    @Test
    void 경로_장애물_예외() {
        assertThatThrownBy(
            () -> chessBoard.move(Position.from("A1"), Position.from("C1"), currentTeamColor))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(OBSTACLE_IN_PATH_ERROR_MESSAGE);
    }

    @DisplayName("말이 갈 수 없는 위치로 이동 요청 시 예외를 던진다.")
    @Test
    void 갈수없는_위치_예외() {
        assertThatThrownBy(
            () -> chessBoard.move(Position.from("A1"), Position.from("B3"), currentTeamColor))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(WRONG_DESTINATION_ERROR_MESSAGE);
    }

    @DisplayName("이동 시키려는 말이 상대 팀의 말이면 예외를 던진다.")
    @Test
    void 상대_말_이동_예외() {
        assertThatThrownBy(
            () -> chessBoard.move(Position.from("A7"), Position.from("A6"), currentTeamColor))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(WRONG_PIECE_COLOR_ERROR_MESSAGE);
    }

    @DisplayName("말은 도착지가 이동할 수 있는 위치이면, 이동하고 이전의 위치는 비워준다.")
    @Test
    void 말_이동_위치_반영() {
        chessBoard.move(Position.from("A2"), Position.from("A4"), currentTeamColor);

        Map<Position, Piece> piecesByPosition = chessBoard.piecesByPosition();

        assertThat(piecesByPosition.containsKey(Position.from("A2"))).isFalse();
        assertThat(piecesByPosition.get(Position.from("A4")))
            .isEqualTo(new Pawn(TeamColor.WHITE));
    }

    @DisplayName("말이 공격에 성공하면, 해당 위치를 차지한다.")
    @Test
    void 말_공격_이동_반영() {
        chessBoard.move(Position.from("A2"), Position.from("A4"), currentTeamColor);
        chessBoard.move(Position.from("A4"), Position.from("A5"), currentTeamColor);
        chessBoard.move(Position.from("A5"), Position.from("A6"), currentTeamColor);
        chessBoard.move(Position.from("B7"), Position.from("A6"), otherTeamColor);

        Map<Position, Piece> piecesByPosition = chessBoard.piecesByPosition();

        assertThat(piecesByPosition.containsKey(Position.from("B7"))).isFalse();
        assertThat(piecesByPosition.get(Position.from("A6")))
            .isEqualTo(new Pawn(otherTeamColor));
    }

    @DisplayName("보드에 2개의 King 이 있지 않으면, King 이 잡힘을 확인한다.")
    @Test
    void 킹_잡힘() {
        Map<Position, Piece> boardForTest = Map.of(
            Position.from("A2"), InitialPiece.BLACK_QUEEN.getPiece(),
            Position.from("C4"), InitialPiece.WHITE_KING.getPiece(),
            Position.from("B2"), InitialPiece.WHITE_PAWN.getPiece());
        chessBoard = new ChessBoard(boardForTest);

        assertThat(chessBoard.isKingDead()).isTrue();
    }

}
