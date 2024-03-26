package chess.domain;

import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.piece.blank.Blank;
import chess.domain.piece.nonsliding.King;
import chess.domain.piece.nonsliding.Knight;
import chess.domain.piece.pawn.BlackFirstPawn;
import chess.domain.piece.pawn.WhiteFirstPawn;
import chess.domain.piece.sliding.Bishop;
import chess.domain.piece.sliding.Queen;
import chess.domain.piece.sliding.Rook;
import chess.dto.PieceDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardFactory {
    private static final Map<Position, Piece> board = new HashMap<>();

    static {
        createInitialBoard();
    }

    private static void createInitialBoard() {
        createBlankBoard();
        initWhitePieces();
        initBlackPieces();
    }

    private static void createBlankBoard() {
        for (int x = 1; x <= 8; x++) {
            initLine(x);
        }
    }

    private static void initLine(int x) {
        for (int y = 1; y <= 8; y++) {
            Position position = new Position(x, y);
            board.put(position, new Blank());
        }
    }

    private static void initBlackPieces() {
        board.replace(new Position(1, 8), new Rook(Color.BLACK));
        board.replace(new Position(2, 8), new Knight(Color.BLACK));
        board.replace(new Position(3, 8), new Bishop(Color.BLACK));
        board.replace(new Position(4, 8), new Queen(Color.BLACK));
        board.replace(new Position(5, 8), new King(Color.BLACK));
        board.replace(new Position(6, 8), new Bishop(Color.BLACK));
        board.replace(new Position(7, 8), new Knight(Color.BLACK));
        board.replace(new Position(8, 8), new Rook(Color.BLACK));
        for (int x = 1; x <= 8; x++) {
            board.replace(new Position(x, 7), new BlackFirstPawn());
        }
    }

    private static void initWhitePieces() {
        board.replace(new Position(1, 1), new Rook(Color.WHITE));
        board.replace(new Position(2, 1), new Knight(Color.WHITE));
        board.replace(new Position(3, 1), new Bishop(Color.WHITE));
        board.replace(new Position(4, 1), new Queen(Color.WHITE));
        board.replace(new Position(5, 1), new King(Color.WHITE));
        board.replace(new Position(6, 1), new Bishop(Color.WHITE));
        board.replace(new Position(7, 1), new Knight(Color.WHITE));
        board.replace(new Position(8, 1), new Rook(Color.WHITE));
        for (int x = 1; x <= 8; x++) {
            board.replace(new Position(x, 2), new WhiteFirstPawn());
        }
    }

    public Map<Position, Piece> getInitialBoard() {
        return new HashMap<>(board);
    }

    public Map<Position, Piece> getPreviousBoard(List<PieceDto> pieces) {
        Map<Position, Piece> board = new HashMap<>(pieces.size());
        for (PieceDto piece : pieces) {
            board.put(piece.getPosition(), piece.getPiece());
        }
        return board;
    }
}
