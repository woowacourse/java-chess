package web.dao;

import static chess.position.File.A;
import static chess.position.File.B;
import static chess.position.File.C;
import static chess.position.File.D;
import static chess.position.File.E;
import static chess.position.File.F;
import static chess.position.File.G;
import static chess.position.File.H;
import static chess.position.Rank.EIGHT;
import static chess.position.Rank.FIVE;
import static chess.position.Rank.FOUR;
import static chess.position.Rank.ONE;
import static chess.position.Rank.SEVEN;
import static chess.position.Rank.SIX;
import static chess.position.Rank.THREE;
import static chess.position.Rank.TWO;
import static java.util.stream.Collectors.toMap;

import chess.ChessBoard;
import chess.piece.Bishop;
import chess.piece.Color;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.position.File;
import chess.position.Position;
import chess.position.Rank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import web.dto.PieceDTO;
import web.dto.PieceType;

public class ChessBoardDao {

    private static final Map<String, Rank> RANKS = Map.of(
            "1", ONE, "2", TWO, "3", THREE, "4", FOUR, "5", FIVE, "6", SIX, "7", SEVEN, "8", EIGHT
    );
    private static final Map<String, File> FILES = Map.of(
            "A", A, "B", B, "C", C, "D", D, "E", E, "F", F, "G", G, "H", H
    );

    private Map<Position, Piece> board;
    private Color currentColor;

    public ChessBoardDao() {
        board = new HashMap<>();
        currentColor = Color.WHITE;
    }

    public ChessBoard findChessBoard() {
        List<PieceDTO> pieces = findPieces();
        Color currentColor = findCurrentColor();
        return new ChessBoard(createBoard(pieces), currentColor);
    }

    public List<PieceDTO> findPieces() {
        return board.keySet().stream()
                .map(position -> new PieceDTO(position, board.get(position)))
                .collect(Collectors.toList());
    }

    private Map<Position, Piece> createBoard(List<PieceDTO> pieces) {
        return pieces.stream()
                .collect(toMap(this::createPosition, this::createPiece));
    }

    public void deletePieceByPosition(Position position) {
        board.remove(position);
    }

    public void savePiece(PieceDTO pieceDTO) {
        board.put(createPosition(pieceDTO), createPiece(pieceDTO));
    }

    public void updatePiece(PieceDTO pieceDTO) {
        board.put(createPosition(pieceDTO), createPiece(pieceDTO));
    }

    public void savePieces(List<PieceDTO> pieceDTOS) {
        for (PieceDTO pieceDTO : pieceDTOS) {
            savePiece(pieceDTO);
        }
    }

    private Position createPosition(PieceDTO pieceDTO) {
        String position = pieceDTO.getPosition();
        File file = FILES.get(position.substring(0, 1));
        Rank rank = RANKS.get(position.substring(1, 2));
        return new Position(file, rank);
    }

    private Piece createPiece(PieceDTO pieceDTO) {
        if (pieceDTO.getType() == PieceType.PAWN) {
            return new Pawn(pieceDTO.getColor());
        }
        if (pieceDTO.getType() == PieceType.KING) {
            return new King(pieceDTO.getColor());
        }
        if (pieceDTO.getType() == PieceType.QUEEN) {
            return new Queen(pieceDTO.getColor());
        }
        if (pieceDTO.getType() == PieceType.ROOK) {
            return new Rook(pieceDTO.getColor());
        }
        if (pieceDTO.getType() == PieceType.KNIGHT) {
            return new Knight(pieceDTO.getColor());
        }
        if (pieceDTO.getType() == PieceType.BISHOP) {
            return new Bishop(pieceDTO.getColor());
        }
        throw new IllegalArgumentException("잘못된 piece type 입니다.");
    }

    public void deleteAll() {
        board.clear();
        currentColor = null;
    }

    public Color findCurrentColor() {
        return currentColor;
    }

    public void saveCurrentColor(Color color) {
        this.currentColor = color;
    }

    public void updateCurrentColor(Color color) {
        this.currentColor = color;
    }
}
