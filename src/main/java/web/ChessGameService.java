package web;

import static chess.position.File.A;
import static chess.position.File.B;
import static chess.position.File.C;
import static chess.position.File.D;
import static chess.position.File.E;
import static chess.position.File.F;
import static chess.position.File.G;
import static chess.position.File.H;
import static chess.position.Rank.EIGHT;
import static chess.position.Rank.ONE;
import static chess.position.Rank.SEVEN;
import static chess.position.Rank.TWO;

import chess.ChessBoard;
import chess.Score;
import chess.piece.Bishop;
import chess.piece.Color;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessGameService {

    private ChessBoard chessBoard;

    public ChessGameService() {
        this.chessBoard = new ChessBoard(createBoard(), Color.WHITE);
    }

    private static Map<Position, Piece> createBoard() {
        Map<Position, Piece> board = new HashMap<>();
        board.putAll(createWhitePieces());
        board.putAll(createBlackPieces());
        return board;
    }

    private static Map<Position, Piece> createWhitePieces() {
        return Map.ofEntries(
                Map.entry(new Position(A, ONE), new Rook(Color.WHITE)),
                Map.entry(new Position(B, ONE), new Knight(Color.WHITE)),
                Map.entry(new Position(C, ONE), new Bishop(Color.WHITE)),
                Map.entry(new Position(D, ONE), new Queen(Color.WHITE)),
                Map.entry(new Position(E, ONE), new King(Color.WHITE)),
                Map.entry(new Position(F, ONE), new Bishop(Color.WHITE)),
                Map.entry(new Position(G, ONE), new Knight(Color.WHITE)),
                Map.entry(new Position(H, ONE), new Rook(Color.WHITE)),
                Map.entry(new Position(A, TWO), new Pawn(Color.WHITE)),
                Map.entry(new Position(B, TWO), new Pawn(Color.WHITE)),
                Map.entry(new Position(C, TWO), new Pawn(Color.WHITE)),
                Map.entry(new Position(D, TWO), new Pawn(Color.WHITE)),
                Map.entry(new Position(E, TWO), new Pawn(Color.WHITE)),
                Map.entry(new Position(F, TWO), new Pawn(Color.WHITE)),
                Map.entry(new Position(G, TWO), new Pawn(Color.WHITE)),
                Map.entry(new Position(H, TWO), new Pawn(Color.WHITE)));
    }

    private static Map<Position, Piece> createBlackPieces() {
        return Map.ofEntries(
                Map.entry(new Position(A, EIGHT), new Rook(Color.BLACK)),
                Map.entry(new Position(B, EIGHT), new Knight(Color.BLACK)),
                Map.entry(new Position(C, EIGHT), new Bishop(Color.BLACK)),
                Map.entry(new Position(D, EIGHT), new Queen(Color.BLACK)),
                Map.entry(new Position(E, EIGHT), new King(Color.BLACK)),
                Map.entry(new Position(F, EIGHT), new Bishop(Color.BLACK)),
                Map.entry(new Position(G, EIGHT), new Knight(Color.BLACK)),
                Map.entry(new Position(H, EIGHT), new Rook(Color.BLACK)),
                Map.entry(new Position(A, SEVEN), new Pawn(Color.BLACK)),
                Map.entry(new Position(B, SEVEN), new Pawn(Color.BLACK)),
                Map.entry(new Position(C, SEVEN), new Pawn(Color.BLACK)),
                Map.entry(new Position(D, SEVEN), new Pawn(Color.BLACK)),
                Map.entry(new Position(E, SEVEN), new Pawn(Color.BLACK)),
                Map.entry(new Position(F, SEVEN), new Pawn(Color.BLACK)),
                Map.entry(new Position(G, SEVEN), new Pawn(Color.BLACK)),
                Map.entry(new Position(H, SEVEN), new Pawn(Color.BLACK)));
    }

    public List<PieceDTO> queryPieces() {
        Map<Position, Piece> board = chessBoard.getBoard();
        return board.keySet().stream()
                .map(position -> new PieceDTO(position, board.get(position)))
                .collect(Collectors.toList());
    }

    public void movePiece(Movement movement) {
        chessBoard.move(movement.getFrom(), movement.getTo());
    }

    public Score queryScoreByColor(Color color) {
        return chessBoard.getScore(color);
    }
}
