package chess;

import chess.piece.*;
import chess.position.Position;
import java.util.*;
import java.util.stream.Collectors;

public class ChessBoard {

    private final Map<Position, Piece> board;
    private final Color currentColor;

    public ChessBoard(List<Piece> pieces, Color currentColor) {
        this(toUnmodifiableMap(pieces), currentColor);
    }

    private static Map<Position, Piece> toUnmodifiableMap(List<Piece> pieces) {
        return pieces.stream()
            .collect(Collectors.toUnmodifiableMap(Piece::getPosition, piece -> piece));
    }

    private ChessBoard(Map<Position, Piece> board, Color currentColor) {
        this.board = board;
        this.currentColor = currentColor;
    }

    public ChessBoard transfer(Position from, Position to) {
        if (!isCurrentColorPiece(from)) {
            throw new IllegalArgumentException(String.format(
                "%s 색깔의 기물을 움직일 수 있습니다.", currentColor));
        }
        return new ChessBoard(createNewBoard(from, to), currentColor.reverse());
    }

    private Map<Position, Piece> createNewBoard(Position from, Position to) {
        Piece transferredPiece = transferPickedPiece(from, to);
        Map<Position, Piece> newBoard = new HashMap<>(board);
        newBoard.remove(from);
        newBoard.put(to, transferredPiece);
        return newBoard;
    }

    private Piece transferPickedPiece(Position from, Position to) {
        Piece pickedPiece = findPieceByPosition(from);
        return pickedPiece.transfer(to, new Pieces(getPieces()));
    }

    private boolean isCurrentColorPiece(Position position) {
        Piece piece = findPieceByPosition(position);
        return piece.isSameColor(currentColor);
    }

    private Piece findPieceByPosition(Position position) {
        if (!board.containsKey(position)) {
            throw new IllegalArgumentException(String.format("%s에는 기물이 없습니다.", position));
        }
        return board.get(position);
    }

    public boolean isFinished() {
        return !hasKingByColor(Color.WHITE) || !hasKingByColor(Color.BLACK);
    }

    private boolean hasKingByColor(Color color) {
        return getPieces().stream()
            .filter(Piece::isKing)
            .anyMatch(piece -> piece.isSameColor(color));
    }

    public List<Piece> getPieces() {
        return new ArrayList<>(board.values());
    }

    public Score getScore(Color color) {
        return new Score(getPiecesByColor(color));
    }

    private List<Piece> getPiecesByColor(Color color) {
        return getPieces().stream()
            .filter(piece -> piece.isSameColor(color))
            .collect(Collectors.toList());
    }

    public Color getWinner() {
        if (!isFinished()) {
            throw new IllegalStateException("체스 게임이 종료되지 않았습니다.");
        }
        if (hasKingByColor(Color.WHITE)) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }
}
