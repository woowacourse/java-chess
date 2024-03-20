package domain.board;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    /*
     * 출발점, 도착점
     * == move(sourcePos, targetPos) 테스트 케이스 ===
     * (공통) sourcePos에 말이 없으면 예외.
     * 모든 말은 targetPos에 자기 팀의 말이 있으면 안 됨.
     * (공통) 출발 위치 == 도착 위치면 예외
     * 모든 말은 말의 규칙에 맞는 위치로 이동해야 한다. (sourcePos == targetPos이면 안 됨) 포함
     * rook, bishop, queen은 경로에 말이 있으면 안 됨.
     * knight는 경로에 말이 있어도 움직일 수 있음.
     * pawn은 2칸 이동 시 경로에 말이 있으면 안 됨.
     * pawn은 바로 앞 칸에 기물이 있으면 이동 X
     * pawn은 대각선 앞 칸에 상대 기물이 있으면 이동 O
     */

    @Test
    @DisplayName("출발점에 말이 없으면 이동 불가")
    void move_NoPieceAtSourcePosition() {
        Board board = Board.generatedBy(new InitialBoardGenerator());
        Position sourcePosition = new Position(new File(4), new Rank(4));
        Position targetPosition = new Position(new File(4), new Rank(5));

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("source 위치에 말이 없습니다.");
    }

    @Test
    @DisplayName("도착점에 본인의 말이 있으면 이동 불가")
    void move_OwnPieceExistAtTargetPosition() {
        Board board = Board.generatedBy(new InitialBoardGenerator());
        Position sourcePosition = new Position(new File(1), new Rank(1));
        Position targetPosition = new Position(new File(1), new Rank(2));

        assertThatThrownBy(() -> board.move(sourcePosition, targetPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("한 칸에 말이 2개 존재할 수 없습니다.");
    }
}
