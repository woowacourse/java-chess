package chess.domain.board;

import chess.TestPiecesGenerator;
import chess.domain.Color;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.board.maker.PiecesGenerator;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

class BoardTest {

    @Test
    @DisplayName("초기 체스판이 정상적으로 생성된다")
    void initTest() {
        final PiecesGenerator piecesGenerator = new TestPiecesGenerator(List.of(
                new Pawn(File.A, Rank.SEVEN, Color.BLACK),
                new Rook(File.A, Rank.EIGHT, Color.BLACK),
                new Pawn(File.A, Rank.TWO, Color.WHITE),
                new Rook(File.A, Rank.ONE, Color.WHITE)
        ));
        final Board board = Board.createBoardWith(piecesGenerator);
        final List<Piece> pieces = board.getPieces();

        assertThat(pieces).extracting(Piece::getPosition, Piece::getColor, Piece::getClass)
                .contains(
                        tuple(new Position(File.A, Rank.SEVEN), Color.BLACK, Pawn.class),
                        tuple(new Position(File.A, Rank.EIGHT), Color.BLACK, Rook.class),

                        tuple(new Position(File.A, Rank.TWO), Color.WHITE, Pawn.class),
                        tuple(new Position(File.A, Rank.ONE), Color.WHITE, Rook.class)
                );
    }

}
