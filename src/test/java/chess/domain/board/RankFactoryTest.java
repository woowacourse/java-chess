package chess.domain.board;

import static chess.domain.board.FileCoordinate.A;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class RankFactoryTest {

    @Test
    void Side_랭크의_체스_말을_생성한다() {
        Rank rank = RankFactory.createRank(RankType.SIDE_RANK, RankCoordinate.ONE, Color.BLACK);

        Square square = rank.getSquareByCoordinate(A);
        assertThat(square.getPiece().getPieceType()).isEqualTo(PieceType.ROOK);
    }

    @Test
    void Pawn_랭크의_체스_말을_생성한다() {
        Rank rank = RankFactory.createRank(RankType.PAWN_RANK, RankCoordinate.TWO, Color.BLACK);

        Square square = rank.getSquareByCoordinate(A);
        assertThat(square.getPiece().getPieceType()).isEqualTo(PieceType.PAWN);
    }

    @Test
    void EMPTY_랭크의_체스_말을_생성한다() {
        Rank rank = RankFactory.createRank(RankType.EMPTY_RANK, RankCoordinate.THREE, Color.BLACK);

        Square square = rank.getSquareByCoordinate(A);
        assertThat(square.getPiece().getPieceType()).isEqualTo(PieceType.EMPTY);
    }
}
