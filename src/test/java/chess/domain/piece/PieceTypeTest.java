package chess.domain.piece;

import chess.domain.piece.nonsliding.King;
import chess.domain.piece.nonsliding.Knight;
import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.pawn.BlackStartPawn;
import chess.domain.piece.pawn.WhitePawn;
import chess.domain.piece.pawn.WhiteStartPawn;
import chess.domain.piece.sliding.Bishop;
import chess.domain.piece.sliding.Queen;
import chess.domain.piece.sliding.Rook;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.fixture.CoordinateFixture.A_ONE;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PieceTypeTest {
    @Test
    void King_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceType.KING.makePiece(Team.WHITE);
        assertThat(piece).isEqualTo(new King(Team.WHITE));
    }

    @Test
    void Queen_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceType.QUEEN.makePiece(Team.WHITE);
        assertThat(piece).isEqualTo(new Queen(Team.WHITE));
    }

    @Test
    void Rook_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceType.ROOK.makePiece(Team.WHITE);
        assertThat(piece).isEqualTo(new Rook(Team.WHITE));
    }

    @Test
    void Knight_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceType.KNIGHT.makePiece(Team.WHITE);
        assertThat(piece).isEqualTo(new Knight(Team.WHITE));
    }

    @Test
    void Bishop_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceType.BISHOP.makePiece(Team.WHITE);
        assertThat(piece).isEqualTo(new Bishop(Team.WHITE));
    }
    
    @Test
    void BlackPawn_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceType.BLACK_PAWN.makePiece(Team.BLACK);
        assertThat(piece).isEqualTo(new BlackPawn(Team.BLACK));
    }
    
    @Test
    void BlackStartPawn_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceType.BLACK_START_PAWN.makePiece(Team.BLACK);
        assertThat(piece).isEqualTo(new BlackStartPawn(Team.BLACK));
    }
    
    @Test
    void WhitePawn_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceType.WHITE_PAWN.makePiece(Team.WHITE);
        assertThat(piece).isEqualTo(new WhitePawn(Team.WHITE));
    }

    @Test
    void WhiteStartPawn_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceType.WHITE_START_PAWN.makePiece(Team.WHITE);
        assertThat(piece).isEqualTo(new WhiteStartPawn(Team.WHITE));
    }

    @Test
    void Empty_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceType.EMPTY.makePiece(Team.EMPTY);
        assertThat(piece).isEqualTo(Empty.getInstance());
    }
    
    @ParameterizedTest(name = "pieceType : {0}, score : {1}")
    @CsvSource(value = {"KING,0", "QUEEN,9", "ROOK,5", "BISHOP,3", "KNIGHT,2.5", "WHITE_PAWN,1"})
    void 각_기물에_맞는_점수_반환(PieceType pieceType, double score) {
        assertThat(pieceType.score()).isEqualTo(score);
    }
}
