package chess.domain.dto;

import chess.domain.pieceInfo.PieceInfo;
import chess.domain.pieceInfo.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BoardDto {
    private final List<List<String>> board;

    private BoardDto(final List<List<String>> board) {
        this.board = board;
    }

    public static BoardDto of(final Map<Position, Piece> board) {
        List<List<String>> rawBoard = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            List<String> row = new ArrayList<>(Collections.nCopies(8, "."));
            rawBoard.add(row);
        }

        for (var entrySet : board.entrySet()) {
            Position position = entrySet.getKey();
            Piece piece = entrySet.getValue();
            makeBoardDtoPiece(position, piece, rawBoard);
        }
        return new BoardDto(rawBoard);
    }

    private static void makeBoardDtoPiece(final Position position, final Piece piece, final List<List<String>> rawBoard) {
        int realYPosition = position.getYPosition();
        int realXPosition = position.getXPosition();
        PieceType pieceType = piece.getType();
        PieceInfo pieceInfo = piece.getPieceInfo();

        rawBoard.get(realYPosition).set(realXPosition, pieceType.getPieceLetter(pieceInfo.getTeam()));
    }

    public List<List<String>> getBoard() {
        return board;
    }
}
