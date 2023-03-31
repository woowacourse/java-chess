package chess.domain.board;

import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.exception.IllegalPieceMoveException;
import chess.domain.piece.BishopPiece;
import chess.domain.piece.InitPawnPiece;
import chess.domain.piece.KingPiece;
import chess.domain.piece.KnightPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.QueenPiece;
import chess.domain.piece.RookPiece;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static chess.domain.PositionFixture.A1;
import static chess.domain.PositionFixture.A2;
import static chess.domain.PositionFixture.A3;
import static chess.domain.PositionFixture.A6;
import static chess.domain.PositionFixture.A7;
import static chess.domain.PositionFixture.C1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BoardTest {

    @Test
    void 첫_뻔쨰_줄_테스트() {
        //given
        Board board = BoardGenerator.makeBoard();

        //when
        Map<Position, Piece> boardData = board.getBoardData();
        List<Piece> pieces = boardData.keySet().stream()
                .filter(position -> position.getRank() == Rank.ONE)
                .map((position) -> boardData.get(position))
                .collect(Collectors.toList());

        //then
        assertThat(pieces)
                .allSatisfy((piece) -> assertThat(piece)
                        .isOfAnyClassIn(RookPiece.class, BishopPiece.class, KnightPiece.class, KingPiece.class, QueenPiece.class)
                ).hasSize(8);

    }

    @Test
    void 두_뻔쨰_줄_테스트() {
        //given
        Board board = BoardGenerator.makeBoard();

        //when
        Map<Position, Piece> boardData = board.getBoardData();
        List<Piece> pieces = boardData.keySet().stream()
                .filter(position -> position.getRank() == Rank.TWO)
                .map((position) -> boardData.get(position))
                .collect(Collectors.toList());

        //then
        assertThat(pieces)
                .allSatisfy((piece) -> assertThat(piece)
                        .isExactlyInstanceOf(InitPawnPiece.class)
                )
                .hasSize(8);

    }

    @Test
    void 같은편_말이_있는_곳으로_이동할_수_없다() {
        //given
        Board board = BoardGenerator.makeBoard();

        //expect
        assertThatThrownBy(() -> board.movePiece(C1, A3))
                .isInstanceOf(IllegalPieceMoveException.class);
    }

    @Test
    void 같은_색_말을_연속_해서_옮길_수_없다() {
        //expect
        Board board = BoardGenerator.makeBoard();
        board.movePiece(A2, A3);
        assertThatThrownBy(() -> board.movePiece(A1, A2))
                .isInstanceOf(IllegalPieceMoveException.class);
    }

    @Test
    void 색을_번갈아_가면서_이동할_수_있다() {
        //expect
        Assertions.assertDoesNotThrow(() -> {
            Board board = BoardGenerator.makeBoard();
            Board firstMovedBoard = board.movePiece(A2, A3);
            Board secondMovedBoard = firstMovedBoard.movePiece(A7, A6);
        });
    }
}
