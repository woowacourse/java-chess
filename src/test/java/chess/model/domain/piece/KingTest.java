package chess.model.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.domain.board.BoardSquare;
import chess.model.domain.board.CastlingSetting;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class KingTest {

    @Test
    @DisplayName("Null이 of에 들어갔을 때 예외 발생")
    void validNotNull() {
        assertThatThrownBy(() -> King.getPieceInstance(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Null");
    }

    @ParameterizedTest
    @ValueSource(strings = {"e6", "e8", "f6", "f7", "f8", "d6", "d7", "d8"})
    @DisplayName("말의 위치(king)를 받고 말의 종류에 따라 이동할 수 있는 칸 리스트 반환")
    void calculateScopeKing(String input) {
        Piece piece = King.getPieceInstance(Color.BLACK);
        Set<BoardSquare> availableBoardSquares = piece.getAllCheatSheet(BoardSquare.of("e7"));
        assertThat(availableBoardSquares.contains(BoardSquare.of(input))).isTrue();
        assertThat(availableBoardSquares.size()).isEqualTo(8);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a5", "a7", "b5", "b7"})
    @DisplayName("판의 정보를 가져와서 king이 갈 수 있는 칸에 장애물이 있는지 판단하여 이동할 수 있는 리스트 반환하는 테스트")
    void movableKingSquareTest(String input) {
        Map<BoardSquare, Piece> board = new HashMap<>();
        board.put(BoardSquare.of("a5"), Pawn.getPieceInstance(Color.WHITE));
        board.put(BoardSquare.of("b6"), Pawn.getPieceInstance(Color.BLACK));
        Piece piece = King.getPieceInstance(Color.BLACK);
        Set<BoardSquare> availableBoardSquares = piece.getCheatSheet(BoardSquare.of("a6"), board);

        assertThat(availableBoardSquares.contains(BoardSquare.of(input))).isTrue();
        assertThat(availableBoardSquares.size()).isEqualTo(4);
    }

    @DisplayName("KING Castling 가능한지 확인")
    @Test
    void checkCastling() {
        Map<BoardSquare, Piece> board = new HashMap<>();
        board.put(BoardSquare.of("a8"), Rook.getPieceInstance(Color.BLACK));
        board.put(BoardSquare.of("h8"), Rook.getPieceInstance(Color.BLACK));
        board.put(BoardSquare.of("a1"), Rook.getPieceInstance(Color.WHITE));
        board.put(BoardSquare.of("h1"), Rook.getPieceInstance(Color.WHITE));

        Piece piece = King.getPieceInstance(Color.BLACK);
        Set<BoardSquare> availableBoardSquares = piece.getCheatSheet(BoardSquare.of("e8"), board,
            CastlingSetting.getCastlingElements());

        assertThat(availableBoardSquares.contains(BoardSquare.of("c8"))).isTrue();
        assertThat(availableBoardSquares.contains(BoardSquare.of("g8"))).isTrue();

        piece = King.getPieceInstance(Color.WHITE);
        availableBoardSquares = piece.getCheatSheet(BoardSquare.of("e1"), board,
            CastlingSetting.getCastlingElements());

        assertThat(availableBoardSquares.contains(BoardSquare.of("c1"))).isTrue();
        assertThat(availableBoardSquares.contains(BoardSquare.of("g1"))).isTrue();

        board.put(BoardSquare.of("d8"), Rook.getPieceInstance(Color.BLACK));

        piece = King.getPieceInstance(Color.BLACK);
        availableBoardSquares = piece.getCheatSheet(BoardSquare.of("e8"), board,
            CastlingSetting.getCastlingElements());

        assertThat(availableBoardSquares.contains(BoardSquare.of("c8"))).isFalse();
        assertThat(availableBoardSquares.contains(BoardSquare.of("g8"))).isTrue();

        piece = King.getPieceInstance(Color.BLACK);
        availableBoardSquares = piece.getCheatSheet(BoardSquare.of("e7"), board,
            CastlingSetting.getCastlingElements());

        assertThat(availableBoardSquares.contains(BoardSquare.of("c7"))).isFalse();
        assertThat(availableBoardSquares.contains(BoardSquare.of("g7"))).isFalse();

        piece = King.getPieceInstance(Color.WHITE);
        Set<CastlingSetting> castlingSettings = CastlingSetting.getCastlingElements();
        castlingSettings.remove(CastlingSetting.WHITE_KING_BEFORE);
        availableBoardSquares = piece.getCheatSheet(BoardSquare.of("e1"), board,
            castlingSettings);

        assertThat(availableBoardSquares.contains(BoardSquare.of("c1"))).isFalse();
        assertThat(availableBoardSquares.contains(BoardSquare.of("g1"))).isFalse();
    }
}
