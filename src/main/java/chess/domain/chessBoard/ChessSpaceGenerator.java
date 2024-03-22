package chess.domain.chessBoard;

import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.ArrayList;
import java.util.List;

public class ChessSpaceGenerator implements SpaceGenerator {

    @Override
    public List<Space> generateSpaces() {
        List<Space> spaces = new ArrayList<>();

        spaces.addAll(makeBlackSpaces());
        spaces.addAll(makeEmptySpaces());
        spaces.addAll(makeWhiteSpaces());

        return spaces;
    }

    private List<Space> makeBlackSpaces() {
        List<Space> spaces = new ArrayList<>();
        List<Piece> specialBlackPieces = PieceGenerator.makeSpecialPieces(Color.BLACK);
        List<Piece> pawnPieces = PieceGenerator.makePawnPieces(Color.BLACK, File.values().length);
        for (int i = 0; i < 8; i++) {
            spaces.add(new Space(specialBlackPieces.get(i), new Position(File.of(i + 1), Rank.EIGHT)));
        }
        for (int i = 0; i < File.values().length; i++) {
            spaces.add(new Space(pawnPieces.get(i), new Position(File.of(i + 1), Rank.SEVEN)));
        }
        return spaces;
    }

    private List<Space> makeEmptySpaces() {
        List<Space> spaces = new ArrayList<>();
        for (int i = 6; i >= 3; i--) {
            for (File file : File.values()) {
                spaces.add(new Space(new EmptyPiece(), new Position(file, Rank.of(i))));
            }
        }
        return spaces;
    }

    private List<Space> makeWhiteSpaces() {
        List<Space> spaces = new ArrayList<>();
        List<Piece> specialWhitePieces = PieceGenerator.makeSpecialPieces(Color.WHITE);
        List<Piece> pawnPieces = PieceGenerator.makePawnPieces(Color.WHITE, File.values().length);
        for (int i = 0; i < File.values().length; i++) {
            spaces.add(new Space(pawnPieces.get(i), new Position(File.of(i + 1), Rank.TWO)));
        }
        for (int i = 0; i < 8; i++) {
            spaces.add(new Space(specialWhitePieces.get(i), new Position(File.of(i + 1), Rank.ONE)));
        }
        return spaces;
    }
}
