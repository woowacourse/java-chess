package chess.domain;

import chess.domain.dto.PieceResponse;
import chess.domain.exception.IllegalPieceMoveException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> piecePosition = new HashMap<>();

    public Board() {
        makeBlackPiece();
        makeWhitePiece();
    }

    private void makeWhitePiece() {
        makePiecesExceptPawns(Color.WHITE, Rank.ONE);
        makePawns(Color.WHITE, Rank.TWO);
    }

    private void makeBlackPiece() {
        makePiecesExceptPawns(Color.BLACK, Rank.EIGHT);
        makePawns(Color.BLACK, Rank.SEVEN);
    }

    private void makePiecesExceptPawns(Color color, Rank rank) {
        List<PieceType> highPieceOrder = List.of(
                PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP, PieceType.QUEEN,
                PieceType.KING, PieceType.BISHOP, PieceType.KNIGHT, PieceType.ROOK);
        int i = 0;
        for (File file : File.values()) {
            piecePosition.put(Position.of(file, rank), new Piece(highPieceOrder.get(i++), color));
        }
    }

    private void makePawns(Color color, Rank rank) {
        for (File file : File.values()) {
            piecePosition.put(Position.of(file, rank), new Piece(PieceType.PAWN, color));
        }
    }

    public void movePiece(Position origin, Position destination) {
        if (piecePosition.get(origin) == null) {
            throw new IllegalPieceMoveException();
        }

        if (!piecePosition.get(origin).canJump()) {
            checkPath(origin, destination);
        }

    }

    private void checkPath(Position origin, Position destination) {

    }

    public List<List<PieceResponse>> getPiecePosition() {
        List<List<PieceResponse>> response = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            List<PieceResponse> pieceResponses = new ArrayList<>();
            for (File file : File.values()) {
                pieceResponses.add(PieceResponse.from(piecePosition.getOrDefault(Position.of(file, rank), new Piece())));
            }
            response.add(pieceResponses);
        }
        return response;
    }
}
