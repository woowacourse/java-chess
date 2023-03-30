package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.type.Knight;
import chess.domain.piece.type.Pawn;
import chess.domain.piece.type.Piece;
import chess.domain.piece.type.Queen;
import chess.domain.piece.type.Rook;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PiecesTest {

    private Pieces pieces;

    @BeforeEach
    void setUp() {
        pieces = new Pieces(new AllPiecesGenerator());
    }

    @Test
    @DisplayName("기물 32개를 생성한다.")
    void generateInitialPieces() {
        // when, then
        assertThat(pieces.getPieces().size()).isEqualTo(32);
    }

    @Test
    @DisplayName("해당 위치에 존재하는 기물을 반환한다.")
    void findPieceByPosition_success() {
        // given
        final Position position = new Position(File.A, Rank.ONE);

        // when
        final Piece pieceByPosition = pieces.findPieceByPosition(position);

        // then
        assertThat(pieceByPosition.isSamePosition(position)).isTrue();
    }

    @Test
    @DisplayName("해당 위치에 존재하는 기물이 없으면 예외를 던진다.")
    void findPieceByPosition_throws() {
        // given
        final Position position = new Position(File.C, Rank.FOUR);

        // when, then
        assertThatThrownBy(() -> pieces.findPieceByPosition(position))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 위치에 존재하는 기물이 없습니다.");
    }

    @Test
    @DisplayName("해당 위치에 기물이 존재하는지 여부를 반환한다.")
    void isPieceExistOnPosition() {
        // given
        final Position existPosition = new Position(File.A, Rank.ONE);
        final Position nonExistPosition = new Position(File.C, Rank.FOUR);

        // when, then
        assertThat(pieces.isPieceExistOnPosition(existPosition)).isTrue();
        assertThat(pieces.isPieceExistOnPosition(nonExistPosition)).isFalse();
    }

    @Test
    @DisplayName("기물의 위치가 바뀌면 기물을 관리하는 리스트에도 반영된다.")
    void synchronizeMovedPiece() {
        // given
        final List<Piece> beforeMovePieces = pieces.getPieces();
        final Piece pieceBeforeMove = pieces.findPieceByPosition(new Position(File.B, Rank.TWO));
        final Piece movedPiece = new Pawn(new Position(File.B, Rank.THREE), Side.WHITE);

        final int pieceBeforeMoveIndex = beforeMovePieces.indexOf(pieceBeforeMove);

        // when
        pieces.synchronizeMovedPiece(pieceBeforeMove, movedPiece);
        final List<Piece> afterMovePieces = pieces.getPieces();

        // then
        assertThat(afterMovePieces.get(pieceBeforeMoveIndex)).isEqualTo(movedPiece);
    }

    @Test
    @DisplayName("기물을 게임에서 제거한다.")
    void remove() {
        // given
        final Position position = new Position(File.A, Rank.SEVEN);
        final List<Piece> piecesBeforeRemove = pieces.getPieces();

        // when
        pieces.remove(pieces.findPieceByPosition(position));
        final List<Piece> piecesAfterRemove = pieces.getPieces();

        // then
        assertThat(piecesAfterRemove.size()).isEqualTo(piecesBeforeRemove.size() - 1);
    }

    @Test
    @DisplayName("흰색 진영의 남아 있는 기물들에 따라 점수를 계산한다.")
    void getSumOfScoreBySide() {
        // given
        final Pieces pieces = new Pieces(() -> List.of(
                new Rook(new Position(File.A, Rank.ONE), Side.WHITE),
                new Queen(new Position(File.E, Rank.ONE), Side.WHITE),
                new Knight(new Position(File.B, Rank.ONE), Side.WHITE),
                new Knight(new Position(File.B, Rank.EIGHT), Side.BLACK)));

        // when
        final double whiteSideScore = pieces.getSumOfScoreBySide(Side.WHITE);

        // then
        assertThat(whiteSideScore).isEqualTo(16.5);
    }

    @Test
    @DisplayName("파일 A 위에 있는 흰색 진영의 폰 개수를 반환한다.")
    void getPawnCountByFile() {
        // given
        final Pieces pieces = new Pieces(() -> List.of(
                new Pawn(new Position(File.A, Rank.ONE), Side.WHITE),
                new Pawn(new Position(File.A, Rank.TWO), Side.WHITE),
                new Pawn(new Position(File.A, Rank.FIVE), Side.WHITE),
                new Pawn(new Position(File.B, Rank.TWO), Side.WHITE),
                new Pawn(new Position(File.A, Rank.EIGHT), Side.BLACK)));

        // when
        final long countOfWhitePawnOnFileA = pieces.getPawnCountByFile(File.A, Side.WHITE);

        // then
        assertThat(countOfWhitePawnOnFileA).isEqualTo(3);
    }

    @Test
    @DisplayName("King이 존재하면 true를 반환한다.")
    void containsKing_true() {
        // when, then
        assertThat(pieces.containsKing(Side.WHITE)).isTrue();
    }

    @Test
    @DisplayName("King이 존재하지 않는다면 false를 반환한다.")
    void containsKing_false() {
        // given
        final Pieces pieces = new Pieces(() -> List.of(
                new Rook(new Position(File.A, Rank.ONE), Side.WHITE),
                new Queen(new Position(File.E, Rank.ONE), Side.WHITE),
                new Knight(new Position(File.B, Rank.ONE), Side.WHITE),
                new Knight(new Position(File.B, Rank.EIGHT), Side.BLACK)
        ));

        // when, then
        assertThat(pieces.containsKing(Side.WHITE)).isFalse();
    }
}
