package chess.repository.exchanger;

import chess.domain.chessGame.Space;
import chess.domain.chessGame.generator.SpaceGenerator;
import chess.domain.piece.Bishop;
import chess.domain.piece.BlackPawn;
import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.WhitePawn;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class StringSpaceGenerator implements SpaceGenerator {

    private static final int BOARD_SIZE = 64;
    private final String chessBoardStates;

    public StringSpaceGenerator(String chessBoardStates) {
        validateLength(chessBoardStates);
        this.chessBoardStates = chessBoardStates;
    }

    private void validateLength(String chessBoardStates) {
        if (chessBoardStates.length() == BOARD_SIZE) {
            return;
        }
        throw new IllegalArgumentException("잘못된 체스판 데이터입니다.");
    }

    @Override
    public List<Space> generateSpaces() {
        List<Space> spaces = new ArrayList<>();
        Iterator<Piece> pieceIterator = makeAllPieces().iterator();
        for (Rank rank : backwardRanks()) {
            spaces.addAll(makeLineSpaces(rank, pieceIterator));
        }

        return spaces;
    }

    private List<Piece> makeAllPieces() {
        List<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < chessBoardStates.length(); i++) {
            pieces.add(Mapper.stringToPiece(String.valueOf(chessBoardStates.charAt(i))));
        }

        return pieces;
    }

    private List<Rank> backwardRanks() {
        List<Rank> ranks = Arrays.asList(Rank.values());
        Collections.reverse(ranks);
        return ranks;
    }

    private List<Space> makeLineSpaces(Rank rank, Iterator<Piece> pieceIterator) {
        List<Space> rankSpaces = new ArrayList<>();
        for (File file : File.values()) {
            rankSpaces.add(new Space(pieceIterator.next(), new Position(file, rank)));
        }
        return rankSpaces;
    }

    public String getChessBoardStates() {
        return chessBoardStates;
    }

    private enum Mapper {
        BlackPawnMapper("P", BlackPawn::of),
        BlackRookMapper("R", Rook::of),
        BlackKnightMapper("N", Knight::of),
        BlackBishopMapper("B", Bishop::of),
        BlackQueenMapper("Q", Queen::of),
        BlackKingMapper("K", King::of),
        WhitePawnMapper("p", WhitePawn::of),
        WhiteRookMapper("r", Rook::of),
        WhiteKnightMapper("n", Knight::of),
        WhiteBishopMapper("b", Bishop::of),
        WhiteQueenMapper("q", Queen::of),
        WhiteKingMapper("k", King::of),
        EmptyMapper(".", EmptyPiece::of);


        private final String mark;
        private final Function<Color, ? extends Piece> pieceFunction;

        Mapper(String mark, Function<Color, ? extends Piece> pieceFunction) {
            this.mark = mark;
            this.pieceFunction = pieceFunction;
        }

        private static Piece stringToPiece(String pieceMark) {
            if (pieceMark.equals(pieceMark.toUpperCase())) {
                return stringToPiece(pieceMark, Color.BLACK);
            }
            return stringToPiece(pieceMark, Color.WHITE);
        }

        private static Piece stringToPiece(String pieceMark, Color color) {
            return Arrays.stream(values())
                    .filter(value -> value.mark.equals(pieceMark))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(
                            String.format("잘못된 피스 문자입니다: %s", pieceMark)))
                    .pieceFunction
                    .apply(color);
        }
    }
}
