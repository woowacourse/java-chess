package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import chess.domain.piece.type.EmptyPiece;
import chess.domain.piece.type.Piece;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {

    private final Map<Position, Piece> chessBoard;

    public ChessBoard() {
        this.chessBoard = new LinkedHashMap<>();
    }

    public void initialize(Map<Position, Piece> chessBoard) {
        this.chessBoard.putAll(chessBoard);
    }

    public Piece findPieceInBoardByPosition(final Position position) {
        if (!chessBoard.containsKey(position)) {
            throw new IllegalArgumentException("해당하는 위치가 체스 보드에 존재하지 않습니다");
        }
        return chessBoard.get(position);
    }

    public void move(Position start, Position end) {
        checkIfMoveToSamePosition(start, end);
        Piece pieceToMove = findPieceInBoardByPosition(start);
        checkIfPieceToMoveEmpty(pieceToMove);
        checkIfPieceMovable(start, end, pieceToMove);
        checkIfOtherPiecesOnPath(start, end);

        movePiece(start, end, pieceToMove);
    }

    private static void checkIfMoveToSamePosition(Position start, Position end) {
        if (start.equals(end)) {
            throw new IllegalArgumentException("제자리로는 이동할 수 없습니다");
        }
    }

    private void checkIfPieceToMoveEmpty(final Piece pieceToMove) {
        if (pieceToMove.getPieceType().equals(PieceType.EMPTY_PIECE)) {
            throw new IllegalArgumentException("이동할 수 있는 기물이 없습니다");
        }
    }

    private void checkIfPieceMovable(final Position start, final Position end, final Piece pieceToMove) {
        Color colorOfDestination = findPieceInBoardByPosition(end).getColor();
        if (!pieceToMove.isMovable(start, end, colorOfDestination)) {
            throw new IllegalArgumentException("기물이 이동 할 수 있는 위치가 아닙니다");
        }
    }

    private void checkIfOtherPiecesOnPath(final Position start, final Position end) {
        if (start.findRouteTo(end).stream()
                .anyMatch(position -> !position.equals(end) && chessBoard.get(position).getColor() != Color.NONE)) {
            throw new IllegalArgumentException("이동 경로에 기물이 있으므로 이동할 수 없습니다");
        }
    }

    private void movePiece(final Position start, final Position end, final Piece pieceToMove) {
        chessBoard.replace(start, EmptyPiece.of());
        chessBoard.replace(end, pieceToMove);
    }

    private Map<Column, List<Piece>> findColumnToPieceListByColor(Color color) {
        return chessBoard.entrySet().stream()
                .filter(entry -> entry.getValue().getColor().isSameColor(color))
                .map(Map.Entry::getKey)
                .collect(Collectors.groupingBy(Position::getColumn,
                                Collectors.mapping(chessBoard::get, Collectors.toList())
                        )
                );
    }

    public double findScoreOfPiecesByColor(Color color) {
        return findColumnToPieceListByColor(color).values().stream()
                .mapToDouble(piecesInSameColumn -> piecesInSameColumn.stream()
                        .mapToDouble(piece -> piece.getScore(piecesInSameColumn))
                        .sum())
                .sum();
    }

    public Map<Position, Piece> getChessBoard() {
        return new HashMap<>(chessBoard);
    }

}
//    //원안
//    public Map<Color, Double> findStatus() {
//        Map<Color, Map<Column, List<Piece>>> SameColorPiecesInSameColumn = chessBoard.keySet().stream()
//                .collect(Collectors.groupingBy(key -> chessBoard.get(key).getColor(),
//                        Collectors.groupingBy(Position::getColumn,
//                                Collectors.mapping(chessBoard::get, Collectors.toList()))));
//
//        Map<Column, List<Piece>> black = SameColorPiecesInSameColumn.get(Color.BLACK);
//        double blackScore = black.values().stream()
//                .mapToDouble(piecesInSameColumn -> piecesInSameColumn.stream()
//                        .mapToDouble(piece -> piece.getScore(piecesInSameColumn))
//                        .sum())
//                .sum();
//        Map<Column, List<Piece>> white = SameColorPiecesInSameColumn.get(Color.WHITE);
//        double whiteScore = white.values().stream()
//                .mapToDouble(piecesInSameColumn -> piecesInSameColumn.stream()
//                        .mapToDouble(piece -> piece.getScore(piecesInSameColumn))
//                        .sum())
//                .sum();
//
//        return Map.of(Color.BLACK, blackScore, Color.WHITE, whiteScore);
//    }