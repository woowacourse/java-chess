package chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import chess.piece.Piece;
import chess.piece.Pieces;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PiecesTest {

    @Test
    @DisplayName("기물 32개를 생성한다.")
    void generateInitialPieces() {
        // given
        final Pieces pieces = new Pieces();

        // when, then
        assertThat(pieces.getPieces().size()).isEqualTo(32);
    }

    @Test
    @DisplayName("해당 위치에 존재하는 기물을 반환한다.")
    void findPieceByPosition_success() {
        // given
        final Pieces pieces = new Pieces();
        final Position position = new Position(File.A, Rank.ONE);

        // when
        final Piece pieceByPosition = pieces.findPieceByPosition(position);

        // then
        assertThat(pieceByPosition.isSamePosition(position)).isTrue();
    }

    @Test
    @DisplayName("해당 위치에 존재하는 기물이 없으면 예외를 던진다.")
    void findPieceByPosition_throws() {
        // given
        final Pieces pieces = new Pieces();
        final Position position = new Position(File.C, Rank.FOUR);

        // when, then
        assertThatThrownBy(() -> pieces.findPieceByPosition(position))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 위치에 존재하는 기물이 없습니다.");
    }

    @Test
    @DisplayName("해당 위치에 기물이 존재하는지 여부를 반환한다.")
    void isPieceExistOnPosition() {
        // given
        final Pieces pieces = new Pieces();
        final Position existPosition = new Position(File.A, Rank.ONE);
        final Position nonExistPosition = new Position(File.C, Rank.FOUR);

        // when, then
        assertThat(pieces.isPieceExistOnPosition(existPosition)).isTrue();
        assertThat(pieces.isPieceExistOnPosition(nonExistPosition)).isFalse();
    }
}
