package chess.domain.piece;

import static chess.fixture.InitPiecesFixtures.*;
import static chess.fixture.PiecesFixtures.PAWN_WHITE_B3;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Score;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.piece.Side;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import chess.fixture.InitPiecesFixtures;
import chess.fixture.PiecesFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PiecesTest {

    @Test
    @DisplayName("기물 32개를 생성한다.")
    void generateInitialPieces() {
        // given
        final Pieces pieces = new Pieces();

        // when, then
        assertThat(pieces.getPieces().size()).isEqualTo(32);
    }

    @Test
    @DisplayName("해당 위치에 존재하는 기물을 반환한다.")
    void findPieceByPosition_success() {
        // given
        final Pieces pieces = new Pieces();
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
        final Pieces pieces = new Pieces();
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
        final Pieces pieces = new Pieces();
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
        final Pieces pieces = new Pieces();
        final List<Piece> beforeMovePieces = pieces.getPieces();
        final Piece pieceBeforeMove = pieces.findPieceByPosition(new Position(File.B, Rank.TWO));
        final Piece movedPiece = new Pawn(new Position(File.B, Rank.THREE), Side.WHITE);

        final int pieceBeforeMoveIndex = beforeMovePieces.indexOf(pieceBeforeMove);

        // when
        pieces.changePiece(pieceBeforeMove, movedPiece);
        final List<Piece> afterMovePieces = pieces.getPieces();


        // then
        assertThat(afterMovePieces.get(pieceBeforeMoveIndex)).isEqualTo(movedPiece);
    }
    @Test
    @DisplayName("화이트 진영 기물들을 반환한다.")
    void getWhitePieces() {
        // given
        final Pieces pieces = new Pieces();

        // when
        List<Piece> whitePieces = pieces.getPiecesBySide(Side.WHITE);

        // then
        assertThat(whitePieces).contains(
                PAWN_WHITE_A2,
                PAWN_WHITE_C2,
                PAWN_WHITE_D2,
                PAWN_WHITE_E2,
                PAWN_WHITE_F2,
                PAWN_WHITE_G2,
                ROOK_WHITE_A1,
                KNIGHT_WHITE_B1,
                BISHOP_WHITE_C1,
                QUEEN_WHITE_D1,
                KING_WHITE_E1,
                BISHOP_WHITE_F1,
                KNIGHT_WHITE_G1,
                ROOK_WHITE_H1
        );
    }

    @Test
    @DisplayName("블랙 진영 기물들을 반환한다.")
    void getBlackPieces() {
        // given
        final Pieces pieces = new Pieces();

        // when
        List<Piece> whitePieces = pieces.getPiecesBySide(Side.BLACK);

        // then
        assertThat(whitePieces).contains(
                PAWN_BLACK_A7,
                PAWN_BLACK_B7,
                PAWN_BLACK_C7,
                PAWN_BLACK_D7,
                PAWN_BLACK_E7,
                PAWN_BLACK_F7,
                PAWN_BLACK_G7,
                PAWN_BLACK_H7,
                ROOK_BLACK_A8,
                KNIGHT_BLACK_B8,
                BISHOP_BLACK_C8,
                QUEEN_BLACK_D8,
                KING_BLACK_E8,
                BISHOP_BLACK_F8,
                KNIGHT_BLACK_G8,
                ROOK_BLACK_H8
        );
    }

    @Test
    @DisplayName("같은 File을 가지는 File별 폰의 개수를 반환한다.")
    void getSameFilePawnCount() {
        // given
        Pieces pieces = new Pieces();
        pieces.changePiece(PAWN_WHITE_E2, PAWN_WHITE_B3);

        // when
        Map<File, Integer> sameFilePawnCount = pieces.getSameFilePawnCount(Side.WHITE);

        // then
        assertThat(sameFilePawnCount.get(File.B)).isEqualTo(2);
    }
}
