package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.AbstractTestFixture;
import chess.domain.board.BoardMap;
import chess.domain.exception.IllegalMoveException;
import chess.domain.game.Team;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class PositionPieceTest {

    @DisplayName("움직인다")
    @Test
    void move() {
        var boardMap = BoardMap.from(AbstractTestFixture.INITIAL_BOARD);
        var piece = new PositionPiece(new Position(File.G, Rank.EIGHT), new Knight(Team.WHITE));

        Position target = new Position(File.F, Rank.SIX);
        piece.moveTo(target, boardMap);

        assertThat(piece.isAt(target)).isTrue();
    }

    @DisplayName("목표 위치에 아군이 있으면 예외를 던진다")
    @Test
    void sameTeamAtTarget_throws() {
        var boardMap = BoardMap.from(AbstractTestFixture.INITIAL_BOARD);
        var piece = new PositionPiece(new Position(File.A, Rank.EIGHT), new Rook(Team.WHITE));

        Position target = new Position(File.A, Rank.SEVEN);
        assertThatThrownBy(() -> piece.moveTo(target, boardMap))
                .isInstanceOf(IllegalMoveException.class)
                .hasMessage("목표 위치에 같은 색 말이 있습니다");
    }

    @DisplayName("가는 도중 장애물이 있으면 예외를 던진다")
    @Test
    void obstaclesInTheWay_throws() {
        var boardMap = BoardMap.from(AbstractTestFixture.INITIAL_BOARD);
        var piece = new PositionPiece(new Position(File.A, Rank.EIGHT), new Rook(Team.WHITE));

        var target = new Position(File.A, Rank.THREE);
        assertThatThrownBy(() -> piece.moveTo(target, boardMap))
                .isInstanceOf(IllegalMoveException.class)
                .hasMessage("다른 기물을 지나칠 수 없습니다");
    }

    @DisplayName("기물이 갈 수 없는 수는 예외를 던진다")
    @Test
    void invalidMove_throws() {
        var boardMap = BoardMap.from(AbstractTestFixture.INITIAL_BOARD);
        var piece = new PositionPiece(new Position(File.G, Rank.EIGHT), new Knight(Team.WHITE));

        var target = new Position(File.E, Rank.FOUR);
        assertThatThrownBy(() -> piece.moveTo(target, boardMap))
                .isInstanceOf(IllegalMoveException.class)
                .hasMessage("해당 기물이 이동할 수 없는 수입니다");
    }

    @DisplayName("폰이 한 파일 안에 있으면 0.5점으로 계산한다")
    @Test
    void score_withThreePawnsInAFile() {
        var boardMap = BoardMap.from(AbstractTestFixture.BOARD_WITH_THREE_PAWNS_IN_A_FILE);
        var piece = new PositionPiece(new Position(File.D, Rank.ONE), new Pawn(Team.WHITE));

        assertThat(piece.scoreConsidering(boardMap)).isEqualTo(0.5);
    }
}
