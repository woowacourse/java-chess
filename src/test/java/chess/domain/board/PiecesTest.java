package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.HashMap;
import java.util.Map;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PiecesTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        Map<Coordinate, Piece> pieces = new HashMap<>();

        assertThatCode(() -> new Pieces(pieces))
                .doesNotThrowAnyException();
    }

    @DisplayName("주어진 좌표의 기물이 존재하지 않으면 Empty 피스를 반환한다.")
    @Test
    void findByCoordinate() {
        Pieces emptyPieces = new Pieces(new HashMap<>());

        Piece result = emptyPieces.findByCoordinate(new Coordinate(2, 'a'));

        assertThat(result).isEqualTo(EmptyPiece.getInstance());
    }

    @DisplayName("해당 위치의 기물이 존재하는지 판단할 수 있다.")
    @Test
    void isPiecePresent() {
        HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
        Piece sourcePiece = new Pawn(Team.WHITE);
        Coordinate source = new Coordinate(2, 'a');
        piecesMap.put(source, sourcePiece);
        Pieces pieces = new Pieces(piecesMap);

        Assertions.assertAll(
                () -> Assertions.assertTrue(pieces.isPiecePresent(source)),
                () -> Assertions.assertFalse(pieces.isPiecePresent(new Coordinate(3, 'a')))
        );
    }

    @DisplayName("두 좌표에 해당하는 값들을 서로 바꾼다.")
    @Test
    void swap() {
        HashMap<Coordinate, Piece> piecesMap = new HashMap<>();
        Piece sourcePiece = new Pawn(Team.WHITE);
        Coordinate source = new Coordinate(2, 'a');
        Coordinate target = new Coordinate(3, 'a');
        piecesMap.put(source, sourcePiece);
        Pieces pieces = new Pieces(piecesMap);

        pieces.swap(source, target);

        Piece foundSourcePiece = piecesMap.get(source);
        Piece foundTargetPiece = piecesMap.get(target);
        assertThat(foundSourcePiece).isNull();
        assertThat(foundTargetPiece).isEqualTo(sourcePiece);
    }
}
