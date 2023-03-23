package chess.domain;

import chess.domain.piece.Camp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ChessBoardTest {
    BoardFixture boardFixture = new BoardFixture();

    @Test
    @DisplayName("처음 체스판의 전체 점수 계산")
    void calculateStartChessBoardTest() {
        ChessBoard chessBoard = ChessBoardMaker.create();

        double result = chessBoard.calculateTotalScoreByCamp(Camp.WHITE);

        assertThat(result).isEqualTo(38);
    }

    @Test
    @DisplayName("하나의 File에 같은편 팀 기물이 있을 때 0.5로 계산")
    void calculateOneFileMoreThanOnePawn() {
        ChessBoard chessBoard = new ChessBoard(boardFixture.setUpBoard());

        double result = chessBoard.calculateTotalScoreByCamp(Camp.WHITE);

        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("king의 개수 계산")
    void countKingTest() {
        ChessBoard chessBoard = ChessBoardMaker.create();

        double result = chessBoard.countKing();

        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("원하는 진영의 king이 살아있는지 판단")
    void isKingLiveByCampTest() {
        ChessBoard chessBoard = ChessBoardMaker.create();

        boolean result = chessBoard.isKingLiveByCamp(Camp.WHITE);

        assertThat(result).isTrue();
    }
}