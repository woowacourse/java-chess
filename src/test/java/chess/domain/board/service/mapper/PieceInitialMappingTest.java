package chess.domain.board.service.mapper;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.jumper.King;
import chess.domain.piece.slider.Queen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PieceInitialMappingTest {

    @Test
    @DisplayName("mapToPieceInitialFrom() : Piece를 통해서 Piece 이니셜 알파벳을 알 수 있다.")
    void test_mapToPieceInitialFrom() throws Exception {
        //given
        final Piece whiteKing = new King(Color.WHITE);
        final Piece blackKing = new King(Color.BLACK);

        //when
        final String whiteKingInitial = PieceInitialMapping.mapToPieceInitialFrom(whiteKing);
        final String blackKingInitial = PieceInitialMapping.mapToPieceInitialFrom(blackKing);

        //then
        assertAll(
                () -> assertEquals(whiteKingInitial, "k"),
                () -> assertEquals(blackKingInitial, "K")
        );
    }

    @Test
    @DisplayName("mapToClassTypeFrom() : Piece initial 을 통해 클래스 타입을 알 수 있다.")
    void test_mapToClassTypeFrom() throws Exception {
        //given
        final String whiteQueenInitial = "q";
        final String blackKingInitial = "K";

        //when
        final Class<?> queenClass = PieceInitialMapping.mapToClassTypeFrom(whiteQueenInitial);
        final Class<?> kingClass = PieceInitialMapping.mapToClassTypeFrom(blackKingInitial);

        //then
        assertAll(
                () -> assertEquals(queenClass, Queen.class),
                () -> assertEquals(kingClass, King.class)
        );
    }

    @Test
    @DisplayName("mapToPieceFrom() : Piece initial 을 통해 Piece 를 생성할 수 있따.")
    void test_mapToPieceFrom() throws Exception {
        //given
        final String whiteQueenInitial = "q";
        final String blackKingInitial = "K";

        //when
        final Piece queen = PieceInitialMapping.mapToPieceFrom(whiteQueenInitial);
        final Piece king = PieceInitialMapping.mapToPieceFrom(blackKingInitial);

        //then
        assertAll(
                () -> assertEquals(queen.getClass(), Queen.class),
                () -> assertFalse(queen.isBlack()),
                () -> assertEquals(king.getClass(), King.class),
                () -> assertTrue(king.isBlack())
        );
    }
}
