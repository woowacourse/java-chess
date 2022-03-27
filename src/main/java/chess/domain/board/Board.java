package chess.domain.board;

import static chess.domain.piece.constant.PieceColor.*;
import static chess.domain.board.Rank.*;

import chess.constant.MoveType;
import chess.domain.piece.constant.PieceColor;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.function.Function;

import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.turndecider.TurnDecider;

public class Board {

    static final String SOURCE_POSITION_SHOULD_HAVE_PIECE_MESSAGE = "[ERROR] 출발 위치에는 말이 있어야 합니다.";
    private static final EmptyPiece EMPTY_PIECE = new EmptyPiece(EMPTY);
    private static final Function<PieceColor, List<Piece>> createPieceFunction = (PieceColor pieceColor) -> List.of(
        new Rook(pieceColor), new Knight(pieceColor), new Bishop(pieceColor),
        new Queen(pieceColor), new King(pieceColor), new Bishop(pieceColor),
        new Knight(pieceColor), new Rook(pieceColor));

    private final Map<Position, Piece> values;
    private final TurnDecider turnDecider;

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
        turnDecider.nextState();
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

        MoveType moveType = decideMoveType(targetPiece);
        if (!sourcePiece.isMovable(source, target, moveType) || isBlocked(source, target) || targetPiece.isMyTeam(
            sourcePiece)) {
            throw new IllegalArgumentException("[ERROR] 이동할 수 없는 위치입니다.");
        }

        values.put(target, sourcePiece);
        values.put(source, EMPTY_PIECE);
    }

    private MoveType decideMoveType(Piece targetPiece) {
        if (targetPiece.equals(EMPTY_PIECE)) {
            return MoveType.EMPTY;
        }
        if (turnDecider.isCorrectTurn(targetPiece)) {
            return MoveType.FRIENDLY;
        }
        return MoveType.ENEMY;
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

    public double calculateScore() {
        return values.values()
            .stream()
            .filter(turnDecider::isCorrectTurn)
            .mapToDouble(Piece::getScore)
            .sum() - adjustPawnScore();
    }

    public double adjustPawnScore() {
        int adjustingScore = 0;
        for (File file : File.values()) {
            long count = reverseValues().stream()
                .map(rank -> new Position(rank, file))
                .filter(position -> values.get(position) instanceof Pawn
                    && turnDecider.isCorrectTurn(values.get(position)))
                .count();

            if (count > 1) {
                adjustingScore += count * 0.5;
            }
        }
        return adjustingScore;
    }

    public Map<Position, Piece> getValues() {
        return values;
    }
}
