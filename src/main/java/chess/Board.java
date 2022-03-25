package chess;

import static chess.PieceColor.*;
import static chess.Rank.*;

import chess.turndecider.TurnDecider;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.function.Function;

import chess.piece.Bishop;
import chess.piece.EmptyPiece;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;

public class Board {

    static final String SOURCE_POSITION_SHOULD_HAVE_PIECE_MESSAGE = "[ERROR] 출발 위치에는 말이 있어야 합니다.";

    private static final EmptyPiece EMPTY_PIECE = new EmptyPiece(EMPTY);
    private final Map<Position, Piece> values;

    private final TurnDecider turnDecider;

    private static final Function<PieceColor, List<Piece>> createPieceFunction = (PieceColor pieceColor) -> List.of(
        new Rook(pieceColor), new Knight(pieceColor), new Bishop(pieceColor),
        new Queen(pieceColor), new King(pieceColor), new Bishop(pieceColor),
        new Knight(pieceColor), new Rook(pieceColor));

    public Board(TurnDecider turnDecider) {
        this.values = initBoard();
        this.turnDecider = turnDecider;
    }

    private Map<Position, Piece> initBoard() {
        Map<Position, Piece> result = new HashMap<>();

        putAllEmptyPieces(result);
        putBlackPieces(result);
        putWhitePieces(result);

        return result;
    }

    private void putAllEmptyPieces(Map<Position, Piece> result) {
        for (Rank rank : Rank.reverseValues()) {
            for (File file : File.values()) {
                result.put(new Position(rank, file), EMPTY_PIECE);
            }
        }
    }

    private void putWhitePieces(Map<Position, Piece> result) {
        putPawns(result, WHITE, TWO);
        putRemainPiecesExceptPawn(result, WHITE, ONE);
    }

    private void putBlackPieces(Map<Position, Piece> result) {
        putPawns(result, BLACK, SEVEN);
        putRemainPiecesExceptPawn(result, BLACK, EIGHT);
    }

    private void putRemainPiecesExceptPawn(Map<Position, Piece> result, PieceColor color, Rank rank) {
        ListIterator<Piece> piecesIterator = createPieceFunction.apply(color).listIterator();

        for (File file : File.values()) {
            result.put(new Position(rank, file), piecesIterator.next());
        }
    }

    private void putPawns(Map<Position, Piece> result, PieceColor color, Rank rank) {
        for (File file : File.values()) {
            result.put(new Position(rank, file), new Pawn(color));
        }
    }

    public boolean move(Position source, Position target) {

        turnDecide(source);
        validateSourceNotEmpty(source);
        boolean isFinished = values.get(target) instanceof King;
        changePieces(source, target);
        return isFinished;
    }

    private void turnDecide(Position source) {
        if (!turnDecider.isCorrectTurn(values.get(source))) {
            throw new IllegalArgumentException("[ERROR] 현재 올바르지 않은 팀 선택입니다. ");
        }
    }

    private void changePieces(Position source, Position target) {
        Piece sourcePiece = values.get(source);
        Piece targetPiece = values.get(target);

        if (!sourcePiece.isMovable(source, target) || isBlocked(source, target) || targetPiece.isMyTeam(sourcePiece)) {
            throw new IllegalArgumentException("[ERROR] 이동할 수 없는 위치입니다.");
        }

        values.put(target, sourcePiece);
        values.put(source, EMPTY_PIECE);
    }

    private boolean isBlocked(Position source, Position target) {
        if (values.get(source) instanceof Knight) {
            return false;
        }

        for (Position position : source.positionsToMove(target)) {
            if (!values.get(position).equals(EMPTY_PIECE)) {
                return true;
            }
        }
        return false;
    }

    private void validateSourceNotEmpty(Position source) {
        if (values.get(source).equals(EMPTY_PIECE)) {
            throw new IllegalArgumentException(SOURCE_POSITION_SHOULD_HAVE_PIECE_MESSAGE);
        }
    }

    public Map<Position, Piece> getValues() {
        return values;
    }
}
