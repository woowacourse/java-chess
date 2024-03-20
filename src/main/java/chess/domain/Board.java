package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.strategy.BishopMoveStrategy;
import chess.domain.strategy.BlackPawnFirstMoveStrategy;
import chess.domain.strategy.EmptyMoveStrategy;
import chess.domain.strategy.KingMoveStrategy;
import chess.domain.strategy.KnightMoveStrategy;
import chess.domain.strategy.MoveStrategy;
import chess.domain.strategy.QueenMoveStrategy;
import chess.domain.strategy.RookMoveStrategy;
import chess.domain.strategy.WhitePawnFirstMoveStrategy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> board;

    private Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board initialize() {
        Map<Position, Piece> board = new HashMap<>();

        placeBlackPieces(board);
        placeEmptyPieces(board);
        placeWhitePieces(board);

        return new Board(board);
    }

    private static void placeBlackPieces(Map<Position, Piece> board) {
        placeRookPieces(board, List.of("a8", "h8"), Team.BLACK);
        placeKnightPieces(board, List.of("b8", "g8"), Team.BLACK);
        placeBishopPieces(board, List.of("c8", "f8"), Team.BLACK);
        placeQueenAndKingPiece(board, "d8", "e8", Team.BLACK);
        placePawnPieces(board, createPieceLocationsByIndex(48, 56), Team.BLACK, new BlackPawnFirstMoveStrategy());
    }

    private static void placeEmptyPieces(Map<Position, Piece> board) {
        placeEmptyPieces(board, createPieceLocationsByIndex(16, 48), Team.NONE);
    }

    private static void placeWhitePieces(Map<Position, Piece> board) {
        placeRookPieces(board, List.of("a1", "h1"), Team.WHITE);
        placeKnightPieces(board, List.of("b1", "g1"), Team.WHITE);
        placeBishopPieces(board, List.of("c1", "f1"), Team.WHITE);
        placeQueenAndKingPiece(board, "d1", "e1", Team.WHITE);
        placePawnPieces(board, createPieceLocationsByIndex(8, 16), Team.WHITE, new WhitePawnFirstMoveStrategy());
    }

    private static void placeRookPieces(Map<Position, Piece> board, List<String> locations, Team team) {
        for (String location : locations) {
            PieceInfo pieceInfo = new PieceInfo(Position.of(location), team);
            board.put(pieceInfo.getPosition(), new Rook(pieceInfo, new RookMoveStrategy()));
        }
    }

    private static void placeKnightPieces(Map<Position, Piece> board, List<String> locations, Team team) {
        for (String location : locations) {
            PieceInfo pieceInfo = new PieceInfo(Position.of(location), team);
            board.put(pieceInfo.getPosition(), new Knight(pieceInfo, new KnightMoveStrategy()));
        }
    }

    private static void placeBishopPieces(Map<Position, Piece> board, List<String> locations, Team team) {
        for (String location : locations) {
            PieceInfo pieceInfo = new PieceInfo(Position.of(location), team);
            board.put(pieceInfo.getPosition(), new Bishop(pieceInfo, new BishopMoveStrategy()));
        }
    }

    private static void placeQueenAndKingPiece(Map<Position, Piece> board, String queenLocation, String kingLocation,
                                               Team team) {
        PieceInfo queenInfo = new PieceInfo(Position.of(queenLocation), team);
        board.put(queenInfo.getPosition(), new Queen(queenInfo, new QueenMoveStrategy()));

        PieceInfo kingInfo = new PieceInfo(Position.of(kingLocation), team);
        board.put(kingInfo.getPosition(), new King(kingInfo, new KingMoveStrategy()));
    }

    private static void placePawnPieces(Map<Position, Piece> board, List<String> locations, Team team,
                                        MoveStrategy moveStrategy) {
        for (String location : locations) {
            PieceInfo pieceInfo = new PieceInfo(Position.of(location), team);
            board.put(pieceInfo.getPosition(), new Pawn(pieceInfo, moveStrategy));
        }
    }

    private static void placeEmptyPieces(Map<Position, Piece> board, List<String> locations, Team team) {
        for (String location : locations) {
            PieceInfo pieceInfo = new PieceInfo(Position.of(location), team);
            board.put(pieceInfo.getPosition(), new EmptyPiece(pieceInfo, new EmptyMoveStrategy()));
        }
    }

    private static List<String> createPieceLocationsByIndex(int startIndex, int endIndex) {
        List<String> positions = new ArrayList<>();

        for (int i = startIndex; i < endIndex; i++) {
            positions.add("" + (char) (i % 8 + 'a') + (char) (i / 8 + '1'));
        }

        return positions;
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
