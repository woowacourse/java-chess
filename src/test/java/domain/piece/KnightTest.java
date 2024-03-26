package domain.piece;

import domain.board.Board;
import domain.board.File;
import domain.board.Position;
import domain.board.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightTest {

    @DisplayName("도착지에 아군 기물이 존재하면 예외를 발생시킨다.")
    @Test
    void throwExceptionWhenDestinationHasTeamPieceTest() {
        // Given
        Position source = new Position(File.C, Rank.FOUR);
        Position destination = new Position(File.D, Rank.SIX);
        Knight knight = new Knight(PieceColor.WHITE);
        Map<Position, Piece> piecePositions = Map.of(destination, new Rook(PieceColor.WHITE));
        Board board = new Board(piecePositions);

        // When & Then
        assertThatThrownBy(() -> knight.move(source, destination, board))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("아군 기물이 위치한 칸으로는 이동할 수 없습니다.");
    }
}
