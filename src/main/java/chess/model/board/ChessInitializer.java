package chess.model.board;

import chess.model.PlayerType;
import chess.model.Point;
import chess.model.piece.Piece;
import chess.model.piece.PieceFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessInitializer implements BoardInitializer {
    private static final int WHITE_TEAM_FIRST_LINE_Y = 1;
    private static final int BLACK_TEAM_FIRST_LINE_Y = 8;
    private static final int WHITE_PAWN_FIRST_Y = 2;
    private static final int BLACK_PAWN_FIRST_Y = 7;

    private final Map<Point, Piece> chessBoard = new HashMap<>();

    @Override
    public Map<Point, Piece> initialize() {
        createChessPieces(PlayerType.WHITE);
        createPawn(PlayerType.WHITE);
        createChessPieces(PlayerType.BLACK);
        createPawn(PlayerType.BLACK);

        return chessBoard;
    }

    private void createChessPieces(PlayerType team) {
        List<String> pieces =
                Arrays.asList("rook", "knight", "bishop", "queen",
                        "king", "bishop", "knight", "rook");
        int pieceYPoint = team == PlayerType.WHITE ? WHITE_TEAM_FIRST_LINE_Y : BLACK_TEAM_FIRST_LINE_Y;
        for (int i = 1; i <= 8; i++) {
            Point point = Point.of(i, pieceYPoint);
            chessBoard.put(point, PieceFactory.create(pieces.get(i - 1), team, point));
        }
    }

    private void createPawn(PlayerType team) {
        int pawnYPoint = team == PlayerType.WHITE ? WHITE_PAWN_FIRST_Y : BLACK_PAWN_FIRST_Y;
        for (int i = 1; i <= 8; i++) {
            Point point = Point.of(i, pawnYPoint);
            chessBoard.put(point, PieceFactory.create("pawn", team, point));
        }
    }
}
