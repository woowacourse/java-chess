package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.type.EmptyPiece;
import chess.domain.piece.type.Piece;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoard {
    private static final String OTHER_PIECE_IN_ROUTE_ERROR_GUIDE_MESSAGE = "이동 경로에 기물이 있으므로 이동할 수 없습니다";

    private final Map<Position, Piece> chessBoard;

    public ChessBoard() {
        this.chessBoard = new LinkedHashMap<>();
    }

    public void initialize(Map<Position, Piece> chessBoard) {
        this.chessBoard.putAll(chessBoard);
    }

    public Color findColorOfPieceInPosition(final Position position) {
        Piece pieceInPosition = findPieceInBoardByPosition(position);
        return pieceInPosition.getColor();
    }

    public void move(Position start, Position end) {
        checkIfMoveToSamePosition(start, end);
        Piece pieceToMove = findPieceInBoardByPosition(start);
        Color colorOfDestination = findPieceInBoardByPosition(end).getColor();
        pieceToMove.checkMovable(start, end, colorOfDestination);
        checkIfPiecesInRoute(start, end, pieceToMove);

        movePieceToDestination(start, end, pieceToMove);
    }

    private static void checkIfMoveToSamePosition(Position start, Position end) {
        if (start.equals(end)) {
            throw new IllegalArgumentException("제자리로는 이동할 수 없습니다");
        }
    }

    private Piece findPieceInBoardByPosition(final Position position) {
        if (!chessBoard.containsKey(position)) {
            throw new IllegalArgumentException("해당하는 위치가 체스 보드에 존재하지 않습니다");
        }
        return chessBoard.get(position);
    }

    private void checkIfPiecesInRoute(final Position start, final Position end, final Piece pieceToMove) {
        if(pieceToMove.findRoute(start, end).stream()
                .anyMatch(position -> !position.equals(end) &&chessBoard.get(position).getColor() != Color.NONE)) {
            throw new IllegalArgumentException("이동경로에 기물이 있어 이동할 수 없습니다");//엔드가 포함안되게 함~~
        }
    }

    private void movePieceToDestination(Position start, Position end, Piece piece) {
        chessBoard.replace(start, EmptyPiece.of());
        chessBoard.replace(end, piece);
    }

    public boolean isInitialized() {
        return chessBoard.size() != 0;
    }

    public Map<Position, Piece> getChessBoard() {
        return new HashMap<>(chessBoard);
    }

}
