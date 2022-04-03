package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.piece.ChessPiece;
import chess.domain.piece.King;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    @Test
    @DisplayName("sourcePosition에서 targetPosition으로 체스 기물을 이동시킨다.")
    void move() {
        ChessBoardPosition sourcePosition = ChessBoardPosition.from("a7");
        ChessBoardPosition targetPosition = ChessBoardPosition.from("a5");
        Map<ChessBoardPosition, ChessPiece> board = new HashMap<>();
        board.put(sourcePosition, new Rook(Team.BLACK));
        ChessBoard chessBoard = new ChessBoard(board);
        chessBoard.move(sourcePosition, targetPosition);
        assertThat(chessBoard).containsOnly(targetPosition);
    }

    @Test
    @DisplayName("주어진 위치에 존재하는 체스 피스를 삭제한다.")
    void removeChessPieceAt() {
        ChessBoardPosition removedPosition = ChessBoardPosition.from("a7");
        ChessBoardPosition savedPosition = ChessBoardPosition.from("a5");
        Map<ChessBoardPosition, ChessPiece> board = new HashMap<>();
        board.put(removedPosition, new Rook(Team.BLACK));
        board.put(savedPosition, new Rook(Team.BLACK));
        ChessBoard chessBoard = new ChessBoard(board);
        chessBoard.removeChessPieceAt(removedPosition);
        assertThat(chessBoard).containsOnly(savedPosition);
    }

    @Test
    @DisplayName("체스 피스의 점수 합을 구한다.")
    void calculateScore() {
        ChessBoard chessBoard = ChessBoard.create();
        double result = chessBoard.calculateScore(Team.BLACK);
        assertThat(result).isEqualTo(38.0);
    }

    @Test
    @DisplayName("킹이 한 개라도 경우 true 반환한다.")
    void isKingDead() {
        Map<ChessBoardPosition, ChessPiece> board = new HashMap<>();
        board.put(ChessBoardPosition.from("a5"), new King(Team.WHITE));
        ChessBoard chessBoard = new ChessBoard(board);
        assertTrue(chessBoard.isKingDead());
    }

    @Test
    @DisplayName("킹 두개가 모두 존재하는 경우 false 반환한다.")
    void isKingDead2() {
        ChessBoard chessBoard = ChessBoard.create();
        assertFalse(chessBoard.isKingDead());
    }

    @Test
    @DisplayName("입력한 위치에 해당하는 체스 기물이 존재하는 경우 true 반환한다.")
    void existChessPieceAt() {
        Map<ChessBoardPosition, ChessPiece> board = new HashMap<>();
        ChessBoardPosition position = ChessBoardPosition.from("a5");
        board.put(position, new King(Team.WHITE));
        ChessBoard chessBoard = new ChessBoard(board);
        assertTrue(chessBoard.existChessPieceAt(position));
    }

    @Test
    @DisplayName("입력한 위치와 팀에 해당하는 체스 기물이 존재하는 경우 true 반환한다.")
    void existChessPieceOf() {
        Map<ChessBoardPosition, ChessPiece> board = new HashMap<>();
        ChessBoardPosition position = ChessBoardPosition.from("a5");
        board.put(position, new King(Team.WHITE));
        ChessBoard chessBoard = new ChessBoard(board);
        assertTrue(chessBoard.existChessPieceOf(position, Team.WHITE));
    }

    @Test
    @DisplayName("우승자를 반환한다.")
    void judgeWinner() {
        ChessBoard chessBoard = ChessBoard.create();

        chessBoard.move(ChessBoardPosition.of('a', 2), ChessBoardPosition.of('a', 4));
        chessBoard.move(ChessBoardPosition.of('f', 7), ChessBoardPosition.of('f', 6));

        chessBoard.move(ChessBoardPosition.of('a', 4), ChessBoardPosition.of('a', 5));
        chessBoard.move(ChessBoardPosition.of('f', 6), ChessBoardPosition.of('f', 5));

        chessBoard.move(ChessBoardPosition.of('a', 5), ChessBoardPosition.of('a', 6));
        chessBoard.move(ChessBoardPosition.of('f', 5), ChessBoardPosition.of('f', 4));

        chessBoard.move(ChessBoardPosition.of('a', 6), ChessBoardPosition.of('b', 7));
        chessBoard.move(ChessBoardPosition.of('f', 4), ChessBoardPosition.of('f', 3));

        chessBoard.move(ChessBoardPosition.of('b', 7), ChessBoardPosition.of('c', 8));
        chessBoard.move(ChessBoardPosition.of('h', 7), ChessBoardPosition.of('h', 6));
        assertThat(chessBoard.judgeWinner()).isEqualTo(Team.WHITE);
    }
}
