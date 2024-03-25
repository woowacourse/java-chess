package chess.domain.board;

import chess.domain.piece.Pawn;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static chess.domain.position.Fixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class ChessBoardTest {
    /*
    ........  8
    ........  7
    ........  6
    ........  5
    ........  4
    ...P....  3
    ........  2
    ........  1
    abcdefgh
    */
    @DisplayName("체스보드의 특정위치에 기물이 없는지 확인할 수 있다")
    @Test
    void should_CheckPositionEmptiness_When_GivenPosition() {
        ChessBoard board = new ChessBoard(Map.ofEntries(Map.entry(D3, new Pawn(Team.BLACK))));
        assertAll(
                () -> assertThat(board.positionIsEmpty(D3)).isFalse(),
                () -> assertThat(board.positionIsEmpty(D2)).isTrue()
        );
    }

    /*
    ........  8
    ........  7
    ........  6
    ........  5
    ........  4
    ...P....  3
    ........  2
    ........  1
    abcdefgh
    */
    @DisplayName("체스보드 특정 위치의 기물을 가져올 수 있다")
    @Test
    void should_GetPieceByPosition_When_GiveTargetPosition() {
        ChessBoard board = new ChessBoard(Map.ofEntries(Map.entry(D3, new Pawn(Team.BLACK))));

        assertThat(board.findPieceByPosition(D3)).isInstanceOf(Pawn.class);
    }

    @DisplayName("체스보드 특정 위치의 기물을 가져올 때, 위치에 기물이 없으면 예외를 발생시킨다")
    @Test
    void should_ThrowNoSuchElementException_When_TargetPositionIsEmpty() {
        ChessBoard board = new ChessBoard(Map.ofEntries(Map.entry(D3, new Pawn(Team.BLACK))));

        assertThatThrownBy(() -> board.findPieceByPosition(D2))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("해당 위치에 기물이 존재하지 않습니다.");
    }

    /*
    ........  8
    .......0  7
    ......x.  6
    .....x..  5
    ....x...  4
    ...b....  3
    ....p...  2
    .....x..  1
    abcdefgh
    */
    @DisplayName("체스보드 경유경로에 기물이 없는지 확인할 수 있다")
    @Test
    void should_CheckNoOtherPiecesInPath() {
        ChessBoard board = new ChessBoard(Map.ofEntries(Map.entry(E2, new Pawn(Team.BLACK))));
        List<Position> cleanPath = D3.findPath(H7);
        List<Position> blockedPath = D3.findPath(F1);

        assertAll(
                () -> assertThat(board.pathIsAllEmpty(cleanPath)).isTrue(),
                () -> assertThat(board.pathIsAllEmpty(blockedPath)).isFalse()
        );
    }
}
