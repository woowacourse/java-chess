package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("보드 공장")
class BoardFactoryTest {

    @Test
    @DisplayName("기물들을 시작위치에 둔 보드를 생성한다.")
    void createBoardTest() {
        // given
        Board actual = BoardFactory.createBoard();
        List<BiFunction<PieceColor, Square, Piece>> piecesArrangement = List.of(
                Rook::new, Knight::new, Bishop::new, Queen::new,
                King::new, Bishop::new, Knight::new, Rook::new);
        Set<Piece> expected = new HashSet<>();

        // when
        for (int i = 0; i < piecesArrangement.size(); i++) {
            expected.add(piecesArrangement.get(i).apply(PieceColor.BLACK, Square.of(File.values()[i], Rank.EIGHT)));
            expected.add(piecesArrangement.get(i).apply(PieceColor.WHITE, Square.of(File.values()[i], Rank.ONE)));
        }
        for (File file : File.values()) {
            expected.add(new Pawn(PieceColor.BLACK, Square.of(file, Rank.SEVEN)));
            expected.add(new Pawn(PieceColor.WHITE, Square.of(file, Rank.TWO)));
        }

        // then
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(new Board(expected));
    }
}
