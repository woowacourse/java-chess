package chess.fixture;

import chess.board.Board;
import chess.board.Color;
import chess.piece.Piece;
import chess.position.Position;
import java.util.Map;

public class BoardFixture {
    public static Board createBoardWithOneWhitePiece(Position position, Piece piece) {
        return new Board(Map.of(position, Color.WHITE), Map.of(position, piece));
    }

    public static Board createBoardWithTWoWhitePiece(Position position1, Position position2, Piece piece1,
                                                     Piece piece2) {
        return new Board(Map.of(position1, Color.WHITE, position2, Color.WHITE),
                Map.of(position1, piece1, position2, piece2));
    }

    public static Board createBoardWithTwoOppositePiece(Position whitePosition,
                                                        Position blackPosition,
                                                        Piece whitePiece,
                                                        Piece blackPiece) {
        return new Board(Map.of(whitePosition, Color.WHITE, blackPosition, Color.BLACK),
                Map.of(whitePosition, whitePiece, blackPosition, blackPiece));
    }
}
