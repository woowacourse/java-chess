package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import chess.domain.piece.type.EmptyPiece;
import chess.domain.piece.type.Piece;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {

    private final Map<Position, Piece> chessBoard;

    public ChessBoard(Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
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
        Piece pieceAtDestination = findPieceInBoardByPosition(start);
        checkIfOtherPiecesOnPath(pieceToMove.findRoute(start, end, pieceAtDestination), end);

        movePiece(start, end, pieceToMove);
    }

    private static void checkIfMoveToSamePosition(Position start, Position end) {
        if (start.equals(end)) {
            throw new IllegalArgumentException("제자리로는 이동할 수 없습니다");
        }
    }
    private void checkIfPieceMovable(final Position start, final Position end, final Piece pieceToMove) {
        Piece destinationPiece = findPieceInBoardByPosition(end);
        if (!pieceToMove.isMovable(start, end, destinationPiece)) {
            throw new IllegalArgumentException("기물이 이동 할 수 있는 위치가 아닙니다");
        }
    }

    private void checkIfOtherPiecesOnPath(List<Position> route, Position end) {
        if (route.stream()
                .anyMatch(position -> !position.equals(end) && !chessBoard.get(position).isSameColor(Color.NONE))) {
            throw new IllegalArgumentException("이동 경로에 기물이 있으므로 이동할 수 없습니다");
        }
    }

    private void movePiece(final Position start, final Position end, final Piece pieceToMove) {
        chessBoard.replace(start, EmptyPiece.of());
        chessBoard.replace(end, pieceToMove);
    }

    public double findScoreOfPiecesByColor(Color color) {
        return chessBoard.values().stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::getScore)
                .sum();
    }

    public Map<Column, Long> findPieceCountOfColumn(Color color, PieceType pieceType) {
        return Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(Column.values()).map(column -> Position.of(column, rank)))
                .filter(position -> chessBoard.get(position).isSameColor(color)
                        && chessBoard.get(position).getPieceType() == pieceType)
                .collect(Collectors.groupingBy(Position::getColumn,
                        Collectors.counting()));
    }

    public Map<Position, Piece> getChessBoard() {
        return new HashMap<>(chessBoard);
    }

}