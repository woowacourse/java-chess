package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.*;
import java.util.stream.Collectors;

public class ChessBoard {

    private final Map<Position, Piece> board;

    public ChessBoard(List<Piece> pieces) {
        this.board = pieces.stream()
            .collect(Collectors.toMap(Piece::getPosition, piece -> piece));
    }

    public static ChessBoard createChessBoard() {
        return new ChessBoard(BoardInitializer.init());
    }

    public void move(Position from, Position to, Color color) {
        if (!isCurrentColorPiece(from, color)) {
            throw new IllegalArgumentException(String.format(
                "%s 색깔의 기물을 움직일 수 있습니다.", color));
        }
        movePickedPiece(from, to);
    }

    private boolean isCurrentColorPiece(Position position, Color color) {
        Piece piece = findPieceByPosition(position);
        return piece.isSameColor(color);
    }

    private Piece findPieceByPosition(Position position) {
        if (!board.containsKey(position)) {
            throw new IllegalArgumentException(String.format("%s에는 기물이 없습니다.", position));
        }
        return board.get(position);
    }

    private void movePickedPiece(Position from, Position to) {
        Piece pickedPiece = findPieceByPosition(from);
        Piece transferredPiece = pickedPiece.transfer(to, getPieces());

        board.remove(from);
        board.put(to, transferredPiece);
    }

    public boolean isGameEnd() {
        return !hasKingByColor(Color.WHITE) || !hasKingByColor(Color.BLACK);
    }

    private boolean hasKingByColor(Color color) {
        return getPieces().stream()
            .filter(Piece::isKing)
            .anyMatch(piece -> piece.isSameColor(color));
    }

    public Collection<Piece> getPieces() {
        return board.values();
    }



    public Color getWinner() {
        if (!isGameEnd()) {
            throw new IllegalStateException("체스 게임이 종료되지 않았습니다.");
        }
        if (hasKingByColor(Color.WHITE)) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
