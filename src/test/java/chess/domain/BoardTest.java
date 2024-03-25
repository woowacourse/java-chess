package chess.domain;

import chess.domain.piece.*;
import chess.domain.piece.character.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class BoardTest {
    @DisplayName("보드에 있는 piece의 위치를 움직일 수 있다.")
    @Test
    void movePiece() {
        Board board = new Board(BoardFactory.generateStartBoard());
        board.move(new Positions(
                Position.of(2, 1),
                Position.of(3, 1)));

        Piece piece = board.getPieces().get(Position.of(3, 1));
        assertThat(piece).isInstanceOf(Pawn.class);
    }

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
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("%s 팀이 움직일 차례입니다".formatted(Team.BLACK.name()));
    }

    @DisplayName("시작 위치에 piece가 없으면 예외가 발생한다.")
    @Test
    void invalidSourcePositionMovePiece() {
        Board board = new Board(BoardFactory.generateStartBoard());
        assertThatThrownBy(() -> board.move(new Positions(
                Position.of(3, 1),
                Position.of(2, 2))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 기물이 존재하지 않습니다.");
    }

    @DisplayName("이동 경로에 piece가 있으면 예외가 발생한다.")
    @Test
    void betweenPositionHasPiece() {
        Board board = new Board(BoardFactory.generateStartBoard());
        assertThatThrownBy(() -> board.move(new Positions(
                Position.of(1, 3),
                Position.of(3, 5))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동을 가로막는 기물이 존재합니다.");
    }

    @DisplayName("해당 위치에 아군 기물이 있으면 예외가 발생한다.")
    @Test
    void targetPositionHasTeamPiece() {
        Board board = new Board(BoardFactory.generateStartBoard());
        assertThatThrownBy(() -> board.move(new Positions(
                Position.of(1, 1),
                Position.of(1, 2))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 아군 기물이 존재합니다.");
    }

    @DisplayName("왕이 체크된 상태에서 공격받지 않는 곳으로 움직일 수 없을 때, 체크메이트이다.")
    @Test
    void checkmate() {
        Board board = new Board(Map.of(
                Position.of(1, 8), new King(Team.WHITE),
                Position.of(2, 8), new Pawn(Team.WHITE),
                Position.of(1, 6), new Queen(Team.BLACK)
        ));

        assertThat(board.findCheckState(Team.WHITE)).isEqualTo(CheckState.CHECK_MATE);
    }

    @DisplayName("더블 체크인 경우, 왕이 공격받지 않는 곳으로 움직일 수 없을 때, 체크메이트이다.")
    @Test
    void checkmateWhenDoubleCheck() {
        Board board = new Board(Map.of(
                Position.of(1, 8), new King(Team.WHITE),
                Position.of(1, 7), new Pawn(Team.WHITE),
                Position.of(4, 5), new Queen(Team.BLACK),
                Position.of(4, 8), new Rook(Team.BLACK)
        ));

        assertThat(board.findCheckState(Team.WHITE)).isEqualTo(CheckState.CHECK_MATE);
    }

    @DisplayName("체크된 상태에서 왕이 체크하는 기물을 제거할 수 있으면, 체크메이트가 아니다.")
    @Test
    void isNotCheckmateKingAttackAttackPiece() {
        Board board = new Board(Map.of(
                Position.of(1, 8), new King(Team.WHITE),
                Position.of(1, 7), new Knight(Team.WHITE),
                Position.of(2, 8), new Pawn(Team.WHITE),
                Position.of(2, 7), new Bishop(Team.BLACK)
        ));

        assertThat(board.findCheckState(Team.WHITE)).isEqualTo(CheckState.CHECK);
    }

    @DisplayName("체크된 상태에서 체크하는 경로를 막을 수 있으면, 체크메이트가 아니다.")
    @Test
    void isNotCheckmatePieceBlockAttackRoute() {
        Board board = new Board(Map.of(
                Position.of(1, 8), new King(Team.WHITE),
                Position.of(1, 7), new Pawn(Team.WHITE),
                Position.of(2, 8), new Pawn(Team.WHITE),
                Position.of(3, 6), new Bishop(Team.BLACK)
        ));

        assertThat(board.findCheckState(Team.WHITE)).isEqualTo(CheckState.CHECK);
    }

    @DisplayName("체크된 상태에서 체크하는 기물을 제거할 수 있으면, 체크메이트가 아니다.")
    @Test
    void isNotCheckmateAttackingAttackPiece() {
        Board board = new Board(Map.of(
                Position.of(1, 8), new King(Team.WHITE),
                Position.of(1, 7), new Pawn(Team.WHITE),
                Position.of(2, 7), new Knight(Team.WHITE),
                Position.of(4, 8), new Rook(Team.BLACK)
        ));

        assertThat(board.findCheckState(Team.WHITE)).isEqualTo(CheckState.CHECK);
    }
}
