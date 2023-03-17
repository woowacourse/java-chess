package chess.domain;

import chess.domain.dto.PieceResponse;
import chess.domain.exception.IllegalPieceMoveException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {

    public static final Piece EMPTY_PIECE = Piece.empty();
    private static final int RANK_SIZE = 8;
    private final Map<Position, Piece> piecePosition = new HashMap<>();

    public Board() {
        makeBlackPiece();
        makeWhitePiece();
        makeEmptyPiece();
    }

    private void makeBlackPiece() {
        makePiecesExceptPawns(Color.BLACK, Rank.EIGHT);
        makePawns(Color.BLACK, Rank.SEVEN);
    }

    private void makeEmptyPiece() {
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                piecePosition.computeIfAbsent(Position.of(file, rank), (ignored) -> Piece.empty());
            }
        }
    }

    private void makeWhitePiece() {
        makePiecesExceptPawns(Color.WHITE, Rank.ONE);
        makePawns(Color.WHITE, Rank.TWO);
    }

    private void makePiecesExceptPawns(Color color, Rank rank) {
        List<PieceType> highPieceOrder = orderedPiece();
        for (int i = 0; i < RANK_SIZE; i++) {
            Position position = Position.of(File.from(i + 1), rank);
            Piece piece = new Piece(highPieceOrder.get(i), color);
            piecePosition.put(position, piece);
        }
    }

    private List<PieceType> orderedPiece() {
        return List.of(
                PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP, PieceType.QUEEN,
                PieceType.KING, PieceType.BISHOP, PieceType.KNIGHT, PieceType.ROOK);
    }

    private void makePawns(Color color, Rank rank) {
        for (File file : File.values()) {
            piecePosition.put(Position.of(file, rank), new Piece(PieceType.PAWN, color));
        }
    }

    public void movePiece(Position origin, Position destination) {
        validateMoveRequest(origin, destination);
        Piece targetPiece = piecePosition.get(origin);
        targetPiece.move(origin.getFileDifference(destination),
                origin.getRankDifference(destination),
                piecePosition.get(destination));
        piecePosition.put(destination, targetPiece);
        piecePosition.put(origin, Piece.empty());
    }

    private void validateMoveRequest(Position origin, Position destination) {
        if (piecePosition.get(origin) == EMPTY_PIECE) {
            throw new IllegalPieceMoveException();
        }
        if (!piecePosition.get(origin).canJump()) {
            checkPath(origin, destination);
        }
    }

    private void checkPath(Position origin, Position destination) {
        List<Position> straightPath = origin.createStraightPath(destination);
        for (Position position : straightPath) {
            if (piecePosition.get(position) != EMPTY_PIECE) {
                throw new IllegalPieceMoveException();
            }
        }
    }

    public List<List<PieceResponse>> getPiecePosition() {
        List<List<PieceResponse>> response = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            List<PieceResponse> pieceResponses = Arrays.stream(File.values())
                    .map(file -> Position.of(file, rank))
                    .map(piecePosition::get)
                    .map(PieceResponse::from)
                    .collect(Collectors.toList());
            response.add(pieceResponses);
        }
        return response;
    }
}
