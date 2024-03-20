package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.position.Rank;
import chess.dto.ChessBoardDto;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoard {
    private final Map<Position, Piece> chessBoard = new LinkedHashMap<>();

    public ChessBoard() {
    }

    public void initialBoard() {
        initializeBlackPieces();
        initializeWhitePieces();
    }

    public ChessBoardDto convertToDto() {
        return new ChessBoardDto(chessBoard);
    }

    private void initializeBlackPieces() {
        initializeEdgeRank(Rank.EIGHT, Color.BLACK);
        initializePawnRank(Rank.SEVEN, Color.BLACK);
    }

    private void initializeWhitePieces() {
        initializePawnRank(Rank.TWO, Color.WHITE);
        initializeEdgeRank(Rank.ONE, Color.WHITE);
    }

    private void initializePawnRank(Rank rank, Color color) {
        for (File file : File.values()) {
            chessBoard.put(Positions.of(rank, file), new Pawn(color));
        }
    }

    private void initializeEdgeRank(Rank rank, Color color) {
        chessBoard.put(Positions.of(rank, File.A), new Rook(color));
        chessBoard.put(Positions.of(rank, File.B), new Knight(color));
        chessBoard.put(Positions.of(rank, File.C), new Bishop(color));
        chessBoard.put(Positions.of(rank, File.D), new Queen(color));
        chessBoard.put(Positions.of(rank, File.E), new King(color));
        chessBoard.put(Positions.of(rank, File.F), new Bishop(color));
        chessBoard.put(Positions.of(rank, File.G), new Knight(color));
        chessBoard.put(Positions.of(rank, File.H), new Rook(color));
    }
}
