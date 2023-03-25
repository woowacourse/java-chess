package domain;

import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Rook;
import domain.piece.TeamColor;

class KingFinderTest {

    private Map<Square, Piece> pieceLocations = Map.ofEntries(
            Map.entry(Square.of("f1"), new King(TeamColor.WHITE)),
            Map.entry(Square.of("g2"), new Pawn(TeamColor.WHITE)),
            Map.entry(Square.of("d7"), new Bishop(TeamColor.BLACK)),
            Map.entry(Square.of("c8"), new Rook(TeamColor.BLACK))
    );

    @Test
    @DisplayName("블랙팀 킹의 존재여부를 확인한다.")
    void isExistBlackKing() {
        KingFinder kingFinder = new KingFinder();
        boolean result = kingFinder.isExistKing(pieceLocations, piece -> piece.isBlack());
        Assertions.assertThat(result).isFalse();
    }

    @Test
    @DisplayName("화이트팀 킹의 존재여부를 확인한다.")
    void isExistWhiteKing() {
        KingFinder kingFinder = new KingFinder();
        boolean result = kingFinder.isExistKing(pieceLocations, piece -> piece.isWhite());
        Assertions.assertThat(result).isTrue();
    }

}