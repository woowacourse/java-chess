package chess.board;

import chess.piece.Blank;
import chess.piece.Piece;
import chess.piece.PieceColor;
import chess.position.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BoardFactory {
    private static Set<PlayingPiece> board;

    static {
        List<Piece> whitePieces = Piece.getWhitePieces();
        List<Piece> blackPieces = Piece.getBlackPieces();
        Set<PlayingPiece> playingPieces = new HashSet<>();

        playingPieces.addAll(createPlayingPieceRow("1", whitePieces));
        playingPieces.addAll(createPlayingPieceRow("2", whitePieces));
        playingPieces.addAll(createPlayingPieceRow("8", blackPieces));
        playingPieces.addAll(createPlayingPieceRow("7", blackPieces));
        playingPieces.addAll(createBlankPieceRow("3"));
        playingPieces.addAll(createBlankPieceRow("4"));
        playingPieces.addAll(createBlankPieceRow("5"));
        playingPieces.addAll(createBlankPieceRow("6"));

        board = playingPieces;
    }

    public static Set<PlayingPiece> getBoard() {
        return board;
    }

    private static Set<PlayingPiece> createPlayingPieceRow(String row, List<Piece> pieces) {
        List<String> aRow = Position.fromRow(row);

        return aRow.stream()
                .map(location -> new PlayingPiece(Position.ofPositionName(location), pieces.remove(0)))
                .collect(Collectors.toSet());
    }

    private static Set<PlayingPiece> createBlankPieceRow(String row) {
        List<String> aRow = Position.fromRow(row);

        return aRow.stream()
                .map(location -> new PlayingPiece(Position.ofPositionName(location), new Blank(PieceColor.BLANK)))
                .collect(Collectors.toSet());
    }
}
