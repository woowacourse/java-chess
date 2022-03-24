package chess.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.domain.position.Square;

public class BoardTest {
    private static final Piece WHITE_QUEEN = Piece.from(File.D, Rank.ONE);

    @Test
    @DisplayName("목표하는 위치에 같은 팀의 말이 있으면 에러를 반환한다")
    public void errorPosition_SameTeam() {
        Map<Square, Piece> board = createBoard();
        board.put(new Square(File.C, Rank.THREE), WHITE_QUEEN);
        board.put(new Square(File.D, Rank.FOUR), WHITE_QUEEN);
        Board chessBoard = new Board(board);
        assertThatThrownBy(() -> chessBoard.move(new Square(File.C, Rank.THREE), new Square(File.D, Rank.FOUR)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 아군의 말이 있는 곳으로는 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("목표한 위치가 이동할 수 없는 곳이면 에러를 반환한다")
    public void errorPosition_Incapable() {
        Map<Square, Piece> board = createBoard();
        board.put(new Square(File.C, Rank.THREE), WHITE_QUEEN);
        Board chessBoard = new Board(board);
        assertThatThrownBy(() -> chessBoard.move(new Square(File.C, Rank.THREE), new Square(File.E, Rank.FOUR)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동할 수 없는 위치입니다.");
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
