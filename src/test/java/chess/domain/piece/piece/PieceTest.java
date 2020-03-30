package chess.domain.piece.piece;

import chess.domain.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.team.BlackTeam;
import chess.domain.team.WhiteTeam;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {
    @Test
    void teamTestBlack1() {
        Piece blackPawn = new Pawn(Position.of("a3"), new BlackTeam());
        Piece WhitePawn = new Pawn(Position.of("a4"), new WhiteTeam());
        assertThat(blackPawn.isSameTeam(WhitePawn)).isFalse();
    }

    @Test
    void teamTestBlack2() {
        BlackTeam blackTeam = new BlackTeam();
        Piece blackPawn = new Pawn(Position.of("a3"), blackTeam);
        Piece blackBishop = new Bishop(Position.of("a4"), blackTeam);
        assertThat(blackPawn.isSameTeam(blackBishop)).isTrue();
    }

    @Test
    void teamTestWhite1() {
        Piece whitePawn = new Pawn(Position.of("a3"), new WhiteTeam());
        Piece blackPawn = new Pawn(Position.of("a4"), new BlackTeam());
        assertThat(whitePawn.isSameTeam(blackPawn)).isFalse();
    }

    @Test
    void teamTestWhite2() {
        WhiteTeam whiteTeam = new WhiteTeam();
        Piece blackPawn = new Pawn(Position.of("a3"), whiteTeam);
        Piece blackBishop = new Bishop(Position.of("a4"), whiteTeam);
        assertThat(blackPawn.isSameTeam(blackBishop)).isTrue();
    }
}