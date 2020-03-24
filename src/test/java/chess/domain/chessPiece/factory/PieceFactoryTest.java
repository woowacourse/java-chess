package chess.domain.chessPiece.factory;

import chess.domain.chessPiece.*;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.TeamStrategy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceFactoryTest {
    @Test
    void ofQueenTest() {
        TeamStrategy blackTeam = new BlackTeam();
        Piece queen = PieceFactory.of("queen", blackTeam);
        assertThat(queen).isInstanceOf(Queen.class);
    }
    @Test
    void ofPawnTest() {
        TeamStrategy blackTeam = new BlackTeam();
        Piece pawn = PieceFactory.of("pawn", blackTeam);
        assertThat(pawn).isInstanceOf(Pawn.class);
    }
    @Test
    void ofKnightTest() {
        TeamStrategy blackTeam = new BlackTeam();
        Piece knight = PieceFactory.of("knight", blackTeam);
        assertThat(knight).isInstanceOf(Knight.class);
    }
    @Test
    void ofbishopTest() {
        TeamStrategy blackTeam = new BlackTeam();
        Piece bishop = PieceFactory.of("bishop", blackTeam);
        assertThat(bishop).isInstanceOf(Bishop.class);
    }
    @Test
    void ofkingTest() {
        TeamStrategy blackTeam = new BlackTeam();
        Piece king = PieceFactory.of("king", blackTeam);
        assertThat(king).isInstanceOf(King.class);
    }
    @Test
    void ofrookTest() {
        TeamStrategy blackTeam = new BlackTeam();
        Piece rook = PieceFactory.of("rook", blackTeam);
        assertThat(rook).isInstanceOf(Rook.class);
    }

}