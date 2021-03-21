package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PiecesTest {

    @DisplayName("CurrentPieces 객체 생성 확인")
    @Test
    void 현재_기물_객체_생성() {
        Pieces pieces = new Pieces(PieceFactory.initialPieces());
        assertThat(pieces.getPieces().size()).isEqualTo(32);
    }

    @DisplayName("해당 위치에 있는 기물을 찾는다.")
    @Test
    void 해당_위치에_있는_기물_찾기() {
        Pieces pieces = new Pieces(PieceFactory.initialPieces());
        Piece sourcePiece = pieces.findByPosition(Position.of('e', '8'));

        assertThat(sourcePiece).isInstanceOf(King.class);
    }

    @DisplayName("해당 위치에 있는 기물을 찾는다. - 없을 경우 Empty")
    @Test
    void 해당_위치에_있는_기물_찾기_EMPTY() {
        Pieces pieces = new Pieces(PieceFactory.initialPieces());
        Piece sourcePiece = pieces.findByPosition(Position.of('e', '4'));

        assertThat(sourcePiece).isInstanceOf(Empty.class);
    }

    @DisplayName("현재 기물들 중 해당 위치 기물 제거 확인")
    @Test
    void 해당_위치_기물_제거_확인() {
        Pieces pieces = new Pieces(PieceFactory.initialPieces());
        Position target = Position.of('g', '7');

        pieces.removePieceByPosition(target);

        assertThat(pieces.getPieces().size()).isEqualTo(31);
    }

    @DisplayName("색 별 기물들의 점수 총합을 구한다.")
    @Test
    void 팀별_점수_계산() {
        Pieces pieces = new Pieces(PieceFactory.initialPieces());
        Pieces blackPieces = pieces.piecesByColor(Color.BLACK);
        Pieces whitePieces = pieces.piecesByColor(Color.WHITE);

        double blackScore = blackPieces.totalScore().getValue();
        double whiteScore = whitePieces.totalScore().getValue();

        assertThat(blackScore).isEqualTo(38);
        assertThat(whiteScore).isEqualTo(38);
    }

    @DisplayName("세로줄에 같은 말이 몇개인지 구한다.")
    @Test
    void 세로줄_같은색_폰() {
        List<Piece> current = Arrays.asList(
                new Pawn(Position.of('a', '8'), Color.BLACK),
                new Pawn(Position.of('a', '7'), Color.BLACK),
                new Pawn(Position.of('d', '8'), Color.BLACK),
                new Pawn(Position.of('d', '7'), Color.BLACK),
                new Pawn(Position.of('c', '1'), Color.WHITE),
                new Pawn(Position.of('c', '2'), Color.WHITE));
        Pieces pieces = new Pieces(current);

        int pawnCountByX = pieces.pawnCountByX('a');

        assertThat(pawnCountByX).isEqualTo(2);
    }
}
