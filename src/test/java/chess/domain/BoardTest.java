package chess.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.domain.position.Square;

public class BoardTest {
    private static final Piece WHITE_QUEEN = Piece.from(File.D, Rank.ONE);
    private static final Piece BLACK_QUEEN = Piece.from(File.D, Rank.EIGHT);

    @Test
    @DisplayName("목표하는 위치에 같은 팀의 말이 있으면 에러를 반환한다")
    void errorPosition_SameTeam() {
        Map<Square, Piece> board = createBoard();
        board.put(new Square("c3"), WHITE_QUEEN);
        board.put(new Square("d4"), WHITE_QUEEN);
        Board chessBoard = new Board(board,Color.WHITE);
        assertThatThrownBy(() -> chessBoard.move(new Square("c3"), new Square(File.D, Rank.FOUR)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 아군의 말이 있는 곳으로는 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("목표한 위치가 이동할 수 없는 곳이면 에러를 반환한다")
    void errorPosition_Incapable() {
        Map<Square, Piece> board = createBoard();
        board.put(new Square("c3"), WHITE_QUEEN);
        Board chessBoard = new Board(board,Color.WHITE);
        assertThatThrownBy(() -> chessBoard.move(new Square(File.C, Rank.THREE), new Square(File.E, Rank.FOUR)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동할 수 없는 위치입니다.");
    }

    @Test
    @DisplayName("가는 길에 다른 피스가 있으면 에러를 반환한다")
    void errorDirection_Blocked() {
        Map<Square, Piece> board = createBoard();
        board.put(new Square("c3"), WHITE_QUEEN);
        board.put(new Square("d4"), WHITE_QUEEN);
        Board chessBoard = new Board(board,Color.WHITE);
        assertThatThrownBy(() -> chessBoard.move(new Square("c3"), new Square("e5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 가는 길에 다른 피스가 있습니다");
    }

    @Test
    @DisplayName("흰 말로 시작하지 않으면 에러를 반환한다")
    void errorTurn_Start() {
        Map<Square, Piece> board = createBoard();
        board.put(new Square("c3"), WHITE_QUEEN);
        board.put(new Square("d4"), BLACK_QUEEN);
        Board chessBoard = new Board(board,Color.WHITE);
        assertThatThrownBy(() -> chessBoard.move(new Square("d4"), new Square("e5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 순서 지키시지?!");
    }

    @Test
    @DisplayName("흰 말 다음 검은 말 순서로 진행하지 않으면 에러를 반환한다")
    void errorTurn() {
        Map<Square, Piece> board = createBoard();
        board.put(new Square("c3"), WHITE_QUEEN);
        Board chessBoard = new Board(board,Color.WHITE);
        chessBoard.move(new Square("c3"), new Square("d4"));
        assertThatThrownBy(() -> chessBoard.move(new Square("d4"), new Square("e5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 순서 지키시지?!");
    }

    private Map<Square, Piece> createBoard() {
        Map<Square, Piece> board = new LinkedHashMap<>();
        for (Rank rank : Rank.values()) {
            createRow(board, rank);
        }
        return board;
    }

    private void createRow(Map<Square, Piece> board, Rank rank) {
        for (File file : File.values()) {
            board.put(new Square(file, rank), Piece.from(File.A, Rank.THREE));
        }
    }
}
