package chess.domain;

import static chess.domain.square.File.A;
import static chess.domain.square.File.B;
import static chess.domain.square.File.C;
import static chess.domain.square.File.D;
import static chess.domain.square.File.E;
import static chess.domain.square.File.F;
import static chess.domain.square.File.G;
import static chess.domain.square.File.H;
import static chess.domain.square.Rank.EIGHT;
import static chess.domain.square.Rank.ONE;
import static chess.domain.square.Rank.SEVEN;
import static chess.domain.square.Rank.TWO;
import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;

import chess.domain.piece.ordinary.Bishop;
import chess.domain.piece.pawn.InitialPawn;
import chess.domain.piece.ordinary.King;
import chess.domain.piece.ordinary.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.ordinary.Queen;
import chess.domain.piece.ordinary.Rook;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import chess.domain.square.Squares;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BoardGenerator {
    private final Map<Square, Piece> pieces = new HashMap<>();

    private BoardGenerator() {
        generateBoard();
    }

    public static Map<Square, Piece> init() {
        BoardGenerator boardGenerator = new BoardGenerator();
        return new HashMap<>(boardGenerator.pieces);
    }

    private void generateBoard() {
        initPieceExceptPawn(ONE, WHITE);
        initPawn(SEVEN, BLACK);
        initPieceExceptPawn(EIGHT, BLACK);
        initPawn(TWO, WHITE);
    }

    private void initPieceExceptPawn(final Rank rank, final Team team) {
        pieces.put(Squares.getSquare(E, rank), new King(team));
        pieces.put(Squares.getSquare(D, rank), new Queen(team));
        pieces.put(Squares.getSquare(A, rank), new Rook(team));
        pieces.put(Squares.getSquare(H, rank), new Rook(team));
        pieces.put(Squares.getSquare(B, rank), new Knight(team));
        pieces.put(Squares.getSquare(G, rank), new Knight(team));
        pieces.put(Squares.getSquare(C, rank), new Bishop(team));
        pieces.put(Squares.getSquare(F, rank), new Bishop(team));
    }

    private void initPawn(final Rank rank, final Team team) {
        Arrays.stream(File.values())
                .forEach(file -> pieces.put(Squares.getSquare(file, rank), new InitialPawn(team)));
    }
}
