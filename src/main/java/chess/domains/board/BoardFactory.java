package chess.domains.board;

import chess.domains.piece.Blank;
import chess.domains.piece.Piece;
import chess.domains.position.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BoardFactory {
    public static final int FIRST_INDEX = 0;
    private static Set<PlayingPiece> board;

    static {
        List<Piece> whitePieces = Piece.getWhitePieces();
        List<Piece> blackPieces = Piece.getBlackPieces();
        Set<PlayingPiece> playingPieces = new HashSet<>();

        playingPieces.addAll(createPlayingPiecesByRow("1", whitePieces));
        playingPieces.addAll(createPlayingPiecesByRow("2", whitePieces));
        playingPieces.addAll(createPlayingPiecesByRow("8", blackPieces));
        playingPieces.addAll(createPlayingPiecesByRow("7", blackPieces));
        playingPieces.addAll(createBlankPieceRow("3"));
        playingPieces.addAll(createBlankPieceRow("4"));
        playingPieces.addAll(createBlankPieceRow("5"));
        playingPieces.addAll(createBlankPieceRow("6"));

        board = playingPieces;
    }

    public static Set<PlayingPiece> getBoard() {
        return board;
    }

    private static Set<PlayingPiece> createPlayingPiecesByRow(String row, List<Piece> pieces) {
        List<String> aRow = Position.fromRow(row);

        // TODO : parameter는 불변이어야 한다.
        return aRow.stream()
                .map(location -> new PlayingPiece(Position.ofPositionName(location), pieces.remove(FIRST_INDEX)))
                .collect(Collectors.toSet());
    }

    private static Set<PlayingPiece> createBlankPieceRow(String row) {
        List<String> aRow = Position.fromRow(row);

        return aRow.stream()
                .map(location -> new PlayingPiece(Position.ofPositionName(location), new Blank()))
                .collect(Collectors.toSet());
    }
}
