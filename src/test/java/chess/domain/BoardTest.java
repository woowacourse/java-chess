package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.WhitePawn;
import chess.domain.piece.character.Team;
import chess.exception.ImpossibleMoveException;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {
    @DisplayName("위치에 있는 기물이 입력된 팀과 같은 팀인지 검증한다.")
    @Test
    void validateOppositeTeamByPosition() {
        Board board = new Board(BoardFactory.generateStartBoard());
        assertThatCode(() ->
                board.validateSameTeamByPosition(Position.of(2, 2), Team.WHITE))
                .doesNotThrowAnyException();
    }

    @DisplayName("위치에 있는 기물이 입력된 팀과 다른 팀일 시 예외가 발생한다.")
    @Test
    void validateSameTeamByPositionThrowsException() {
        Board board = new Board(BoardFactory.generateStartBoard());
        assertThatThrownBy(() ->
                board.validateSameTeamByPosition(Position.of(2, 2), Team.BLACK))
                .isInstanceOf(ImpossibleMoveException.class)
                .hasMessage("흑팀이 움직일 차례입니다");
    }

    @DisplayName("시작 위치에 piece가 없으면 예외가 발생한다.")
    @Test
    void invalidSourcePositionMovePiece() {
        Board board = new Board(BoardFactory.generateStartBoard());
        assertThatThrownBy(() -> board.move(new Movement(
                Position.of(3, 1),
                Position.of(2, 2))))
                .isInstanceOf(ImpossibleMoveException.class)
                .hasMessage("해당 위치에 기물이 존재하지 않습니다.");
    }

    @DisplayName("이동 경로에 piece가 있으면 예외가 발생한다.")
    @Test
    void betweenPositionHasPiece() {
        Board board = new Board(BoardFactory.generateStartBoard());
        assertThatThrownBy(() -> board.move(new Movement(
                Position.of(1, 3),
                Position.of(3, 5))))
                .isInstanceOf(ImpossibleMoveException.class)
                .hasMessage("이동을 가로막는 기물이 존재합니다.");
    }

    @DisplayName("해당 위치에 아군 기물이 있으면 예외가 발생한다.")
    @Test
    void targetPositionHasTeamPiece() {
        Board board = new Board(BoardFactory.generateStartBoard());
        assertThatThrownBy(() -> board.move(new Movement(
                Position.of(1, 1),
                Position.of(1, 2))))
                .isInstanceOf(ImpossibleMoveException.class)
                .hasMessage("해당 위치에 아군 기물이 존재합니다.");
    }

    @DisplayName("팀의 포인트를 계산하여 반환한다.")
    @Test
    void calculatePoint() {
        Board board = new Board(Map.of(
                Position.of(1, 8), new King(Team.WHITE),
                Position.of(1, 6), new Bishop(Team.WHITE),
                Position.of(1, 4), new Knight(Team.WHITE)
        ));

        assertThat(board.calculateScore(Team.WHITE)).isEqualTo(5.5);
    }

    @DisplayName("폰이 두개 이상 세로로 겹쳐져 있는 경우, 폰 개수당 0.5점을 감점한다.")
    @Test
    void calculatePointWithDoubledPawn() {
        Board board = new Board(Map.of(
                Position.of(1, 8), new King(Team.WHITE),
                Position.of(2, 6), new WhitePawn(),
                Position.of(3, 6), new WhitePawn(),
                Position.of(4, 6), new WhitePawn(),
                Position.of(2, 5), new WhitePawn()
        ));

        assertThat(board.calculateScore(Team.WHITE)).isEqualTo(2.5);
    }

    @DisplayName("왕이 공격받고 있으면, 체크이다.")
    @Test
    void isChecked() {
        Board board = new Board(Map.of(
                Position.of(1, 8), new King(Team.WHITE),
                Position.of(1, 6), new Queen(Team.BLACK)
        ));

        assertThat(board.isChecked(Team.WHITE)).isTrue();
    }

    @DisplayName("왕이 체크된 상태에서 공격받지 않는 곳으로 움직일 수 없을 때, 체크메이트이다.")
    @Test
    void isCheckmate() {
        Board board = new Board(Map.of(
                Position.of(1, 8), new King(Team.WHITE),
                Position.of(2, 8), new WhitePawn(),
                Position.of(1, 6), new Queen(Team.BLACK)
        ));

        assertThat(board.isCheckmate(Team.WHITE)).isTrue();
    }

    @DisplayName("더블 체크인 경우, 왕이 공격받지 않는 곳으로 움직일 수 없을 때, 체크메이트이다.")
    @Test
    void isCheckmateWhenDoubleCheck() {
        Board board = new Board(Map.of(
                Position.of(1, 8), new King(Team.WHITE),
                Position.of(1, 7), new WhitePawn(),
                Position.of(4, 5), new Queen(Team.BLACK),
                Position.of(4, 8), new Rook(Team.BLACK)
        ));

        assertThat(board.isCheckmate(Team.WHITE)).isTrue();
    }

    @DisplayName("체크된 상태에서 왕이 체크하는 기물을 제거할 수 있으면, 체크메이트가 아니다.")
    @Test
    void isNotCheckmateKingAttackAttackPiece() {
        Board board = new Board(Map.of(
                Position.of(1, 8), new King(Team.WHITE),
                Position.of(1, 7), new Knight(Team.WHITE),
                Position.of(2, 8), new WhitePawn(),
                Position.of(2, 7), new Bishop(Team.BLACK)
        ));

        assertThat(board.isCheckmate(Team.WHITE)).isFalse();
    }

    @DisplayName("체크된 상태에서 체크하는 경로를 막을 수 있으면, 체크메이트가 아니다.")
    @Test
    void isNotCheckmatePieceBlockAttackRoute() {
        Board board = new Board(Map.of(
                Position.of(1, 8), new King(Team.WHITE),
                Position.of(1, 7), new WhitePawn(),
                Position.of(2, 8), new WhitePawn(),
                Position.of(3, 6), new Bishop(Team.BLACK)
        ));

        assertThat(board.isCheckmate(Team.WHITE)).isFalse();
    }

    @DisplayName("체크된 상태에서 체크하는 기물을 제거할 수 있으면, 체크메이트가 아니다.")
    @Test
    void isNotCheckmateAttackingAttackPiece() {
        Board board = new Board(Map.of(
                Position.of(1, 8), new King(Team.WHITE),
                Position.of(1, 7), new WhitePawn(),
                Position.of(2, 7), new Knight(Team.WHITE),
                Position.of(4, 8), new Rook(Team.BLACK)
        ));

        assertThat(board.isCheckmate(Team.WHITE)).isFalse();
    }

    @DisplayName("체크된 상태에서 왕만 공격 기물을 공격할 수 있으며, 공격 기물이 보호되고 있을 때 체크메이트이다.")
    @Test
    void isCheckmateKingAttackingProtectedAttackPiece() {
        Board board = new Board(Map.of(
                Position.of(1, 8), new King(Team.WHITE),
                Position.of(1, 7), new Queen(Team.BLACK),
                Position.of(2, 5), new Knight(Team.BLACK)
        ));

        assertThat(board.isCheckmate(Team.WHITE)).isTrue();
    }
}
