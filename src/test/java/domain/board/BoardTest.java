package domain.board;

import domain.Board;
import domain.exception.TargetPieceNotFoundException;
import domain.piece.Empty;
import domain.piece.Piece;
import domain.piece.bishop.BlackBishop;
import domain.piece.bishop.WhiteBishop;
import domain.piece.king.BlackKing;
import domain.piece.king.WhiteKing;
import domain.piece.knight.BlackKnight;
import domain.piece.knight.WhiteKnight;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.OnceMovedBlackPawn;
import domain.piece.pawn.WhitePawn;
import domain.piece.queen.BlackQueen;
import domain.piece.queen.WhiteQueen;
import domain.piece.rook.BlackRook;
import domain.piece.rook.WhiteRook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class BoardTest {
    @Test
    @DisplayName("체스판의 현재 상태를 조회할 수 있다.")
    void initialize() {
        Board board = Board.initialize();
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
                () -> assertThat(pieces.get(3)).isEqualTo(new WhiteQueen()),
                () -> assertThat(pieces.get(4)).isEqualTo(new WhiteKing()),
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
                () -> assertThat(pieces.get(3)).isEqualTo(new BlackQueen()),
                () -> assertThat(pieces.get(4)).isEqualTo(new BlackKing()),
                () -> assertThat(pieces.get(5)).isEqualTo(new BlackBishop()),
                () -> assertThat(pieces.get(6)).isEqualTo(new BlackKnight()),
                () -> assertThat(pieces.get(7)).isEqualTo(new BlackRook())
        );
    }

    private void assertSecondBlackRank(List<Piece> pieces) {
        assertThat(pieces).containsOnly(new BlackPawn());
    }

    @Nested
    @DisplayName("장기말 이동과 관련한 메서드 테스트")
    class moveTest {
        @Test
        @DisplayName("출발 좌표, 도착 좌표가 주어지면 출발 좌표에 있는 말이 이동한다.")
        void move() {
            List<List<Piece>> boardStatus = Arrays.asList(
                    Arrays.asList(new Empty(), new Empty(), new Empty()), // a3, b3, c3
                    Arrays.asList(new BlackPawn(), new Empty(), new Empty()), // a2, b2, c2
                    Arrays.asList(new Empty(), new Empty(), new Empty()) // a1, b2, c3
            );
            Board board = new Board(boardStatus);

            board.move("a2", "a3");

            assertThat(boardStatus.get(1).get(0)).isEqualTo(new Empty());
            assertThat(boardStatus.get(2).get(0)).isEqualTo(new OnceMovedBlackPawn());
        }

        @Test
        @DisplayName("출발 좌표에 아무 장기말이 없으면 예외가 발생한다.")
        void moveFromEmptyPoint() {
            List<List<Piece>> boardStatus = Arrays.asList(
                    Arrays.asList(new Empty(), new Empty(), new Empty()), // a3, b3, c3
                    Arrays.asList(new BlackPawn(), new Empty(), new Empty()), // a2, b2, c2
                    Arrays.asList(new Empty(), new Empty(), new Empty()) // a1, b2, c3
            );

            Board board = new Board(boardStatus);

            assertThatThrownBy(() -> board.move("a1", "a3"))
                    .isInstanceOf(TargetPieceNotFoundException.class);
        }
    }
}