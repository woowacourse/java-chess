package domain;

import domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BoardTest {
    @Test
    @DisplayName("체스판의 현재 상태를 조회할 수 있다.")
    void initialize() {
        Board board = new Board();
        List<List<Piece>> status = board.findCurrentStatus();

        assertThat(status).hasSize(8);
        assertStatusOfPieces(status);
    }

    private void assertStatusOfPieces(List<List<Piece>> status) {
        assertFirstWhiteRank(status.get(0));
        assertSecondWhiteRank(status.get(1));
        assertEmptyRanks(status);
        assertSecondBlackRank(status.get(6));
        assertFirstBlackRank(status.get(7));
    }

    private void assertEmptyRanks(List<List<Piece>> status) {
        for (int rankIndex = 2; rankIndex < 5; rankIndex++) {
            assertEmptyRank(status.get(rankIndex));
        }
    }

    private void assertEmptyRank(List<Piece> pieces) {
        assertAll(
                () -> assertThat(pieces).hasSize(8),
                () -> assertThat(pieces).containsOnly(new Empty())
        );
    }

    private void assertFirstWhiteRank(List<Piece> pieces) {
        assertAll(
                () -> assertThat(pieces).hasSize(8),
                () -> assertThat(pieces.get(0)).isEqualTo(new WhiteRook()),
                () -> assertThat(pieces.get(1)).isEqualTo(new WhiteKnight()),
                () -> assertThat(pieces.get(2)).isEqualTo(new WhiteBishop()),
                () -> assertThat(pieces.get(3)).isEqualTo(new WhiteKing()),
                () -> assertThat(pieces.get(4)).isEqualTo(new WhiteQueen()),
                () -> assertThat(pieces.get(5)).isEqualTo(new WhiteBishop()),
                () -> assertThat(pieces.get(6)).isEqualTo(new WhiteKnight()),
                () -> assertThat(pieces.get(7)).isEqualTo(new WhiteRook())
        );
    }

    private void assertSecondWhiteRank(List<Piece> pieces) {
        assertAll(
                () -> assertThat(pieces).hasSize(8),
                () -> assertThat(pieces).containsOnly(new WhitePawn())
        );
    }

    private void assertFirstBlackRank(List<Piece> pieces) {
        assertAll(
                () -> assertThat(pieces).hasSize(8),
                () -> assertThat(pieces.get(0)).isEqualTo(new BlackRook()),
                () -> assertThat(pieces.get(1)).isEqualTo(new BlackKnight()),
                () -> assertThat(pieces.get(2)).isEqualTo(new BlackBishop()),
                () -> assertThat(pieces.get(3)).isEqualTo(new BlackKing()),
                () -> assertThat(pieces.get(4)).isEqualTo(new BlackQueen()),
                () -> assertThat(pieces.get(5)).isEqualTo(new BlackBishop()),
                () -> assertThat(pieces.get(6)).isEqualTo(new BlackKnight()),
                () -> assertThat(pieces.get(7)).isEqualTo(new BlackRook())
        );
    }

    private void assertSecondBlackRank(List<Piece> pieces) {
        assertThat(pieces).containsOnly(new BlackPawn());
    }
}