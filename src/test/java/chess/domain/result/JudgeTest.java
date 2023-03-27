package chess.domain.result;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Square;
import chess.domain.piece.Piece;
import chess.domain.piece.Role;
import chess.domain.side.Color;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class JudgeTest {
    @Test
    @DisplayName("각 진영의 점수를 계산한다.")
    void calculateScore() {
        //given
        Board board = BoardFactory.createInitial();
        //when
        Map<Color, Score> scores = Judge.calculateScore(board);
        //expected
        Assertions.assertThat(scores.get(Color.WHITE)).isEqualTo(new Score(38));
        Assertions.assertThat(scores.get(Color.BLACK)).isEqualTo(new Score(38));
    }

    @Test
    @DisplayName("폰이 같은 파일일 떄는 0.5점으로 계산")
    void calculateScorePawnSameFile() {
        //given
        Map<Square, Piece> squarePiece = Map.of(
                Square.from("a1"), Role.PAWN.create(Color.BLACK),
                Square.from("a2"), Role.PAWN.create(Color.BLACK),
                Square.from("a3"), Role.PAWN.create(Color.BLACK),
                Square.from("a4"), Role.PAWN.create(Color.BLACK),
                Square.from("a5"), Role.PAWN.create(Color.BLACK),
                Square.from("b1"), Role.PAWN.create(Color.WHITE),
                Square.from("b2"), Role.PAWN.create(Color.WHITE),
                Square.from("b3"), Role.PAWN.create(Color.WHITE),
                Square.from("b4"), Role.PAWN.create(Color.WHITE),
                Square.from("b5"), Role.PAWN.create(Color.WHITE)
        );
        Board board = BoardFactory.create(squarePiece, Color.WHITE);
        //when
        Map<Color, Score> scores = Judge.calculateScore(board);
        //expected
        Assertions.assertThat(scores.get(Color.WHITE)).isEqualTo(new Score(2.5));
        Assertions.assertThat(scores.get(Color.BLACK)).isEqualTo(new Score(2.5));
    }

    @Test
    @DisplayName("왕이 잡히지 않았다.")
    void findSidKindDied() {
        //given
        Board board = BoardFactory.createInitial();
        //when
        Color color = Judge.findSideKingDied(board);
        //expected
        Assertions.assertThat(color).isEqualTo(Color.NOTHING);
    }

    @Test
    @DisplayName("백의 왕이 잡혔다.")
    void findWhiteKindDied() {
        //given
        Board board = BoardFactory.createInitial();
        board.makeMove(Square.from("a2"), Square.from("a3"));//무의미한 백의 움직임
        board.makeMove(Square.from("b8"), Square.from("c6"));
        board.makeMove(Square.from("a3"), Square.from("a4"));//무의미한 백의 움직임
        board.makeMove(Square.from("c6"), Square.from("b4"));
        board.makeMove(Square.from("a4"), Square.from("a5"));//무의미한 백의 움직임
        board.makeMove(Square.from("b4"), Square.from("c2"));
        board.makeMove(Square.from("a5"), Square.from("a6"));//무의미한 백의 움직임
        board.makeMove(Square.from("c2"), Square.from("e1"));//여기서 백 왕이 잡힘
        //when
        Color color = Judge.findSideKingDied(board);
        //expected
        Assertions.assertThat(color).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("흑의 왕이 잡혔다.")
    void findBlackKindDied() {
        //given
        Board board = BoardFactory.createInitial();
        board.makeMove(Square.from("b1"), Square.from("c3"));
        board.makeMove(Square.from("a7"), Square.from("a6"));//무의미한 흑의 움직임
        board.makeMove(Square.from("c3"), Square.from("b5"));
        board.makeMove(Square.from("a6"), Square.from("a5"));//무의미한 흑의 움직임
        board.makeMove(Square.from("b5"), Square.from("c7"));
        board.makeMove(Square.from("a5"), Square.from("a4"));//무의미한 흑의 움직임
        board.makeMove(Square.from("c7"), Square.from("e8"));//여기서 흑 왕이 잡힘
        //when
        Color color = Judge.findSideKingDied(board);
        //expected
        Assertions.assertThat(color).isEqualTo(Color.BLACK);
    }
}
