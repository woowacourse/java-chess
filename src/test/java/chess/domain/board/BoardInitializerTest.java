package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.InitPawn;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardInitializerTest {

    @Test
    @DisplayName("보드 생성 테스트")
    void createBoardTest() {
        Map<Position, Piece> pieces = new HashMap<>();
        makeBoard(pieces);

        Board initializerBoard = BoardInitializer.createBoard();
        Board board = new Board(pieces);

        for (Position position : pieces.keySet()) {
            Piece initializerPiece = initializerBoard.pieces().get(position);
            Piece piece = pieces.get(position);
            Assertions.assertThat(piece).isExactlyInstanceOf(initializerPiece.getClass());
        }
    }

    private void makeBoard(Map<Position, Piece> pieces) {
        pieces.put(Position.of(File.A, Rank.ONE), new Rook(Color.WHITE));
        pieces.put(Position.of(File.B, Rank.ONE), new Knight(Color.WHITE));
        pieces.put(Position.of(File.C, Rank.ONE), new Bishop(Color.WHITE));
        pieces.put(Position.of(File.D, Rank.ONE), new Queen(Color.WHITE));
        pieces.put(Position.of(File.E, Rank.ONE), new King(Color.WHITE));
        pieces.put(Position.of(File.F, Rank.ONE), new Bishop(Color.WHITE));
        pieces.put(Position.of(File.G, Rank.ONE), new Knight(Color.WHITE));
        pieces.put(Position.of(File.H, Rank.ONE), new Rook(Color.WHITE));
        pieces.put(Position.of(File.A, Rank.TWO), new InitPawn(Color.WHITE));
        pieces.put(Position.of(File.B, Rank.TWO), new InitPawn(Color.WHITE));
        pieces.put(Position.of(File.C, Rank.TWO), new InitPawn(Color.WHITE));
        pieces.put(Position.of(File.D, Rank.TWO), new InitPawn(Color.WHITE));
        pieces.put(Position.of(File.E, Rank.TWO), new InitPawn(Color.WHITE));
        pieces.put(Position.of(File.F, Rank.TWO), new InitPawn(Color.WHITE));
        pieces.put(Position.of(File.G, Rank.TWO), new InitPawn(Color.WHITE));
        pieces.put(Position.of(File.H, Rank.TWO), new InitPawn(Color.WHITE));
        pieces.put(Position.of(File.A, Rank.EIGHT), new Rook(Color.BLACK));
        pieces.put(Position.of(File.B, Rank.EIGHT), new Knight(Color.BLACK));
        pieces.put(Position.of(File.C, Rank.EIGHT), new Bishop(Color.BLACK));
        pieces.put(Position.of(File.D, Rank.EIGHT), new Queen(Color.BLACK));
        pieces.put(Position.of(File.E, Rank.EIGHT), new King(Color.BLACK));
        pieces.put(Position.of(File.F, Rank.EIGHT), new Bishop(Color.BLACK));
        pieces.put(Position.of(File.G, Rank.EIGHT), new Knight(Color.BLACK));
        pieces.put(Position.of(File.H, Rank.EIGHT), new Rook(Color.BLACK));
        pieces.put(Position.of(File.A, Rank.SEVEN), new InitPawn(Color.BLACK));
        pieces.put(Position.of(File.B, Rank.SEVEN), new InitPawn(Color.BLACK));
        pieces.put(Position.of(File.C, Rank.SEVEN), new InitPawn(Color.BLACK));
        pieces.put(Position.of(File.D, Rank.SEVEN), new InitPawn(Color.BLACK));
        pieces.put(Position.of(File.E, Rank.SEVEN), new InitPawn(Color.BLACK));
        pieces.put(Position.of(File.F, Rank.SEVEN), new InitPawn(Color.BLACK));
        pieces.put(Position.of(File.G, Rank.SEVEN), new InitPawn(Color.BLACK));
        pieces.put(Position.of(File.H, Rank.SEVEN), new InitPawn(Color.BLACK));
    }
}
