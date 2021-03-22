package chess.domain.piece;

import static chess.domain.TeamColor.BLACK;
import static chess.domain.TeamColor.WHITE;

import chess.domain.Position;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceFactoryTest {

    @Test
    @DisplayName("팀색 별 폰 초기 자리 확인")
    void createInitialPawn() {
        List<Piece> pawns = PieceFactory.initialPieces(8, 0, 7)
            .stream()
            .filter(piece -> piece instanceof Pawn)
            .collect(Collectors.toList());

        List<Position> whitePawnPositions = pawns.stream()
            .filter(piece -> piece.isSameColor(WHITE))
            .map(Piece::currentPosition)
            .collect(Collectors.toList());

        List<Position> blackPawnPositions = pawns.stream()
            .filter(piece -> piece.isSameColor(BLACK))
            .map(Piece::currentPosition)
            .collect(Collectors.toList());

        Assertions.assertThat(whitePawnPositions)
            .hasSameElementsAs(ExpectedInitialPieces.whitePawnsPositions());
        Assertions.assertThat(blackPawnPositions)
            .hasSameElementsAs(ExpectedInitialPieces.blackPawnPositions());
    }

    @Test
    @DisplayName("팀색 별 비숍 초기 자리 확인")
    void createInitialBishop() {
        List<Piece> bishops = PieceFactory.initialPieces(8, 0, 7)
            .stream()
            .filter(piece -> piece instanceof Bishop)
            .collect(Collectors.toList());

        List<Position> whiteBishopPositions = bishops.stream()
            .filter(piece -> piece.isSameColor(WHITE))
            .map(Piece::currentPosition)
            .collect(Collectors.toList());

        List<Position> blackBishopPositions = bishops.stream()
            .filter(piece -> piece.isSameColor(BLACK))
            .map(Piece::currentPosition)
            .collect(Collectors.toList());

        Assertions.assertThat(whiteBishopPositions)
            .hasSameElementsAs(ExpectedInitialPieces.whiteBishopPositions());
        Assertions.assertThat(blackBishopPositions)
            .hasSameElementsAs(ExpectedInitialPieces.blackBishopPositions());
    }

    @Test
    @DisplayName("팀색 별 나이트 초기 자리 확인")
    void createInitialKnight() {
        List<Piece> knights = PieceFactory.initialPieces(8, 0, 7)
            .stream()
            .filter(piece -> piece instanceof Knight)
            .collect(Collectors.toList());

        List<Position> whiteKnightPositions = knights.stream()
            .filter(piece -> piece.isSameColor(WHITE))
            .map(Piece::currentPosition)
            .collect(Collectors.toList());

        List<Position> blackKnightPositions = knights.stream()
            .filter(piece -> piece.isSameColor(BLACK))
            .map(Piece::currentPosition)
            .collect(Collectors.toList());

        Assertions.assertThat(whiteKnightPositions)
            .hasSameElementsAs(ExpectedInitialPieces.whiteKnightPositions());
        Assertions.assertThat(blackKnightPositions)
            .hasSameElementsAs(ExpectedInitialPieces.blackKnightPositions());
    }

    @Test
    @DisplayName("팀색 별 룩 초기 자리 확인")
    void createInitialRook() {
        List<Piece> rooks = PieceFactory.initialPieces(8, 0, 7)
            .stream()
            .filter(piece -> piece instanceof Rook)
            .collect(Collectors.toList());

        List<Position> whiteRookPositions = rooks.stream()
            .filter(piece -> piece.isSameColor(WHITE))
            .map(Piece::currentPosition)
            .collect(Collectors.toList());

        List<Position> blackRookPositions = rooks.stream()
            .filter(piece -> piece.isSameColor(BLACK))
            .map(Piece::currentPosition)
            .collect(Collectors.toList());

        Assertions.assertThat(whiteRookPositions)
            .hasSameElementsAs(ExpectedInitialPieces.whiteRookPositions());
        Assertions.assertThat(blackRookPositions)
            .hasSameElementsAs(ExpectedInitialPieces.blackRookPositions());
    }

    @Test
    @DisplayName("팀색 별 퀸 초기 자리 확인")
    void createInitialQueen() {
        List<Piece> queens = PieceFactory.initialPieces(8, 0, 7)
            .stream()
            .filter(piece -> piece instanceof Queen)
            .collect(Collectors.toList());

        List<Position> whiteQueenPositions = queens.stream()
            .filter(piece -> piece.isSameColor(WHITE))
            .map(Piece::currentPosition)
            .collect(Collectors.toList());

        List<Position> blackQueenPositions = queens.stream()
            .filter(piece -> piece.isSameColor(BLACK))
            .map(Piece::currentPosition)
            .collect(Collectors.toList());

        Assertions.assertThat(whiteQueenPositions)
            .hasSameElementsAs(ExpectedInitialPieces.whiteQueenPositions());
        Assertions.assertThat(blackQueenPositions)
            .hasSameElementsAs(ExpectedInitialPieces.blackQueenPositions());
    }

    @Test
    @DisplayName("팀색 별 킹 초기 자리 확인")
    void createInitialKing() {
        List<Piece> kings = PieceFactory.initialPieces(8, 0, 7)
            .stream()
            .filter(piece -> piece instanceof King)
            .collect(Collectors.toList());

        List<Position> whiteKingPositions = kings.stream()
            .filter(piece -> piece.isSameColor(WHITE))
            .map(Piece::currentPosition)
            .collect(Collectors.toList());

        List<Position> blackKingPositions = kings.stream()
            .filter(piece -> piece.isSameColor(BLACK))
            .map(Piece::currentPosition)
            .collect(Collectors.toList());

        Assertions.assertThat(whiteKingPositions)
            .hasSameElementsAs(ExpectedInitialPieces.whiteKingPositions());
        Assertions.assertThat(blackKingPositions)
            .hasSameElementsAs(ExpectedInitialPieces.blackKingPositions());
    }
}