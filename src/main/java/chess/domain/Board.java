package chess.domain;

import chess.domain.dto.PieceResponse;
import chess.domain.exception.IllegalPieceMoveException;
import chess.domain.piece.BishopPiece;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.KingPiece;
import chess.domain.piece.KnightPiece;
import chess.domain.piece.PawnPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.QueenPiece;
import chess.domain.piece.RookPiece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
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
                piecePosition.computeIfAbsent(Position.of(file, rank), (ignored) -> EmptyPiece.getInstance());
            }
        }
    }

    private void makeWhitePiece() {
        makePiecesExceptPawns(Color.WHITE, Rank.ONE);
        makePawns(Color.WHITE, Rank.TWO);
    }

    private void makePiecesExceptPawns(Color color, Rank rank) {
        List<Piece> highPieceOrder = orderedPiece(color);
        for (int i = 0; i < RANK_SIZE; i++) {
            Position position = Position.of(File.from(i + 1), rank);
            piecePosition.put(position, highPieceOrder.get(i));
        }
    }

    private List<Piece> orderedPiece(Color color) {
        return List.of(
                new RookPiece(color), new KnightPiece(color), new BishopPiece(color), new QueenPiece(color),
                new KingPiece(color), new BishopPiece(color), new KnightPiece(color), new RookPiece(color));
    }

    private void makePawns(Color color, Rank rank) {
        for (File file : File.values()) {
            piecePosition.put(Position.of(file, rank), new PawnPiece(color));
        }
    }

    public void movePiece(Position origin, Position destination) {
        validateMoveRequest(origin, destination);
        Piece targetPiece = piecePosition.get(origin);
        if (!targetPiece.canMove(origin, destination, piecePosition.get(destination))) {
            throw new IllegalPieceMoveException();
        }
        piecePosition.put(destination, targetPiece);
        piecePosition.put(origin, EmptyPiece.getInstance());
    }

    private void validateMoveRequest(Position origin, Position destination) {
        if (piecePosition.get(origin) == EmptyPiece.getInstance()) {
            throw new IllegalPieceMoveException();
        }
        if (!piecePosition.get(origin).canJump()) {
            checkPath(origin, destination);
        }
    }

    private void checkPath(Position origin, Position destination) {
        List<Position> straightPath = origin.createStraightPath(destination);
        for (Position position : straightPath) {
            if (piecePosition.get(position) != EmptyPiece.getInstance()) {
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
