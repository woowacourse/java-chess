package model.chessboard.state;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.RankConverter.EIGHT;
import static util.RankConverter.FIVE;
import static util.RankConverter.FOUR;
import static util.RankConverter.ONE;
import static util.RankConverter.SEVEN;
import static util.RankConverter.SIX;
import static util.RankConverter.THREE;
import static util.RankConverter.TWO;

import java.util.HashMap;
import java.util.Map;
import model.piece.Color;
import model.piece.PieceHolder;
import model.piece.role.Bishop;
import model.piece.role.King;
import model.piece.role.Pawn;
import model.piece.role.Rook;
import model.piece.role.Square;
import model.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CurrentTurnTest {

    private final Map<Position, PieceHolder> chessBoard = new HashMap<>();
    private CurrentTurn currentTurn;

    @BeforeEach
    void setUp() {
        initBoard();
        PieceHolder blackKing = new PieceHolder(King.from(Color.BLACK));
        PieceHolder whiteKing = new PieceHolder(King.from(Color.WHITE));
        chessBoard.put(Position.of(5, 8), blackKing);
        chessBoard.put(Position.of(5, 1), whiteKing);
        currentTurn = new CurrentTurn(chessBoard, Color.WHITE);
    }

    @Test
    @DisplayName("현재 차례의 기물 이동을 시도할 경우 예외가 발생한다.")
    void move_ShouldThrowIllegalArgumentException_WhenMovingOpponentPiece() {
        Position source = Position.of(1, 2);
        Position destination = Position.of(1, 3);
        PieceHolder opponentPiece = new PieceHolder(Pawn.from(Color.BLACK));

        chessBoard.put(source, opponentPiece);

        assertThrows(IllegalArgumentException.class, () -> currentTurn.move(source, destination));
    }

    @Test
    @DisplayName("유효한 이동 시 체스판 상태가 올바르게 업데이트 됨")
    void move_ShouldUpdateBoard_WhenValidMove() {
        Position source = Position.of(2, 2);
        Position destination = Position.of(3, 3);
        PieceHolder ownPiece = new PieceHolder(Bishop.from(Color.WHITE));

        chessBoard.put(source, ownPiece);

        currentTurn.move(source, destination);

        assertNotNull(chessBoard.get(destination));
        assertInstanceOf(Bishop.class, chessBoard.get(destination)
                .getRole());
    }

    @Test
    @DisplayName("이동 후 자신의 킹이 체크 상태인 경우 예외를 던진다.")
    void move_ShouldRollback_WhenSelfInCheckAfterMove() {
        Position source = Position.of(5, 2);
        Position destination = Position.of(6, 2);
        PieceHolder rookBlockingCheck = new PieceHolder(Rook.from(Color.WHITE));

        Position blackRookPosition = Position.of(5, 7);
        PieceHolder rookFacingWhiteKing = new PieceHolder(Rook.from(Color.BLACK));
        chessBoard.put(blackRookPosition, rookFacingWhiteKing);
        chessBoard.put(source, rookBlockingCheck);

        currentTurn = new CurrentTurn(chessBoard, Color.WHITE);

        assertThrows(IllegalArgumentException.class, () -> currentTurn.move(source, destination));
    }

    @Test
    @DisplayName("호출 시 현재 턴 색상이 반대로 변경됨")
    void nextState_ShouldToggleCurrentColor_WhenCalled() {
        Color originalColor = currentTurn.currentColor;
        DefaultState nextState = currentTurn.nextState();

        assertNotEquals(originalColor, nextState.currentColor);
        assertEquals(Color.BLACK, nextState.currentColor);
    }

    @Test
    @DisplayName("isCheckedBy_적이 킹을 체크하고 있을 때 true 반환")
    void isCheckedBy_ShouldReturnTrue_WhenEnemyChecksKing() {
        Position source = Position.of(5, 2);
        PieceHolder rookAttackingBlackKing = new PieceHolder(Rook.from(Color.WHITE));

        chessBoard.put(source, rookAttackingBlackKing);

        assertTrue(currentTurn.isCheckedBy(Color.WHITE));
    }

    @Test
    @DisplayName("게임 진행 중에는 false 값 반환한다.")
    void isFinish_ShouldReturnFalse_WhenCurrentTurn() {
        assertFalse(currentTurn.isFinish());
    }

    @Test
    @DisplayName("다음 상태가 올바른 플레이어로 설정되었는지 확인")
    void nextState_ShouldSetProperPlayer_WhenSwitched() {
        DefaultState nextState = currentTurn.nextState();

        assertNotEquals(currentTurn.currentColor, nextState.currentColor);
    }

    private void initBoard() {
        for (int i = 1; i <= 8; i++) {
            chessBoard.put(Position.of(i, ONE.getValue()), new PieceHolder(new Square()));
            chessBoard.put(Position.of(i, TWO.getValue()), new PieceHolder(new Square()));
            chessBoard.put(Position.of(i, THREE.getValue()), new PieceHolder(new Square()));
            chessBoard.put(Position.of(i, FOUR.getValue()), new PieceHolder(new Square()));
            chessBoard.put(Position.of(i, FIVE.getValue()), new PieceHolder(new Square()));
            chessBoard.put(Position.of(i, SIX.getValue()), new PieceHolder(new Square()));
            chessBoard.put(Position.of(i, SEVEN.getValue()), new PieceHolder(new Square()));
            chessBoard.put(Position.of(i, EIGHT.getValue()), new PieceHolder(new Square()));
        }
    }
}
