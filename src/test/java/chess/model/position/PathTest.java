package chess.model.position;

import chess.model.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PathTest {
    @Test
    @DisplayName("이동량과 소스 위치로 소스 타겟 위치까지 직선 경로를 구한다.")
    void makeStraightPath() {
        // given
        Position sourcePosition = Position.of(File.A, Rank.FOUR);
        Difference fileDifference = Difference.from(2);
        Difference rankDifference = Difference.from(-2);
        Movement movement = new Movement(fileDifference, rankDifference);

        // when
        Path path = Path.makeStraightPath(sourcePosition, movement);

        // then
        assertThat(path.getPositions())
                .containsExactly(Position.of(File.B, Rank.THREE), Position.of(File.C, Rank.TWO));
    }

    @Test
    @DisplayName("이동 경로에 기물이 존재한다면 참을 반환한다.")
    void containsPieceExistingPiece() {
        // given
        Position kingPosition = Position.of(File.A, Rank.FIVE);
        Position bishopPosition = Position.of(File.A, Rank.FOUR);
        Map<Position, Piece> board = Map.of(kingPosition, King.from(Side.BLACK), bishopPosition, Bishop.from(Side.BLACK));
        Path path = new Path(List.of(kingPosition, bishopPosition));

        // when
        boolean isContained = path.containsPiece(board);

        // then
        assertThat(isContained).isTrue();
    }

    @Test
    @DisplayName("이동 경로에 기물이 존재하지 않는다면 거짓을 반환한다.")
    void containsPieceNotExistingPiece() {
        // given
        Position blankPosition = Position.of(File.A, Rank.FIVE);
        Position bishopPosition = Position.of(File.A, Rank.FOUR);
        Map<Position, Piece> board = Map.of(blankPosition, Blank.INSTANCE, bishopPosition, Bishop.from(Side.BLACK));
        Path path = new Path(List.of(blankPosition, bishopPosition));

        // when
        boolean isContained = path.containsPiece(board);

        // then
        assertThat(isContained).isFalse();
    }

    @Test
    @DisplayName("이동 경로에 타겟 위치만 포함되어 있다면 항상 이동 경로에 기물이 존재하지 않는다.")
    void containsPieceExistingOnlyTarget() {
        // given
        Position kingPosition = Position.of(File.A, Rank.FIVE);
        Map<Position, Piece> board = Map.of(kingPosition, King.from(Side.BLACK));
        Path path = new Path(List.of(kingPosition));

        // when
        boolean isContained = path.containsPiece(board);

        // then
        assertThat(isContained).isFalse();
    }
}
