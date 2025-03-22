package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Column;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import chess.domain.piece.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ChessBoard {

    private List<Piece> pieces;

    public ChessBoard(List<Piece> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public boolean isEnd() {
        int kingCount = (int) pieces.stream()
            .filter(piece -> piece.getPieceType() == PieceType.KING)
            .count();
        return kingCount != 2;
    }

    public void moveWhite(Map.Entry<Column, Row> currentPosition, Map.Entry<Column, Row> destination) {
        validateExistPiece(currentPosition, Color.WHITE);
        Piece choicedPiece = findChoicedPiece(currentPosition);
        validateStop(choicedPiece, destination);
        List<Piece> allPiecesExceptMovingPiece = getAllPiecesExceptMovingPiece(choicedPiece);
        choicedPiece.move(destination, allPiecesExceptMovingPiece);
        removeDestinationPiece(Color.WHITE, destination);
    }

    public void moveBlack(Map.Entry<Column, Row> currentPosition, Map.Entry<Column, Row> destination) {
        validateExistPiece(currentPosition, Color.BLACK);
        Piece choicedPiece = findChoicedPiece(currentPosition);
        validateStop(choicedPiece, destination);
        List<Piece> allPiecesExceptMovingPiece = getAllPiecesExceptMovingPiece(choicedPiece);
        choicedPiece.move(destination, allPiecesExceptMovingPiece);
        removeDestinationPiece(Color.BLACK, destination);
    }

    private void validateExistPiece(Map.Entry<Column, Row> position, Color color) {
        boolean isExist = pieces.stream()
            .anyMatch(piece -> piece.isExist(position.getKey(), position.getValue())
                && piece.getColor() == color);
        if (!isExist) {
            throw new IllegalArgumentException("해당 좌표에 움직일 수 있는 기물이 없습니다.");
        }
    }

    private void validateStop(Piece choicedPiece, Map.Entry<Column, Row> destination) {
        if (choicedPiece.isExist(destination.getKey(), destination.getValue())) {
            throw new IllegalArgumentException("선택된 기물은 반드시 다른 위치로 이동해야 합니다.");
        }
    }

    private List<Piece> getAllPiecesExceptMovingPiece(Piece choicedPiece) {
        List<Piece> allPieces = new ArrayList<>(pieces);
        allPieces.remove(choicedPiece);
        return allPieces;
    }

    private Piece findChoicedPiece(Map.Entry<Column, Row> currentPosition) {
        return pieces.stream()
            .filter(piece -> piece.isExist(currentPosition.getKey(), currentPosition.getValue()))
            .findFirst().get();
    }

    private void removeDestinationPiece(Color color, Map.Entry<Column, Row> destination) {
        Optional<Piece> enemyPieceAtDestination = pieces.stream()
            .filter(piece -> piece.isExist(destination.getKey(), destination.getValue())
                && piece.getColor() == color.opposite())
            .findFirst();

        enemyPieceAtDestination.ifPresent(piece -> pieces.remove(piece));
    }
}
