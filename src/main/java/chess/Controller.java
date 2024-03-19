package chess;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import chess.domain.Board;
import chess.domain.Coordinate;
import chess.domain.Piece;
import chess.domain.PieceType;
import chess.domain.Team;
import chess.view.InputView;
import chess.view.OutputView;

class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        boolean isPlayable = inputView.readStartCommand();
        if (isPlayable) {
            play(new Board());
        }
    }

    private void play(Board board) {
        List<List<String>> viewData = new ArrayList<>();

        for (int rankValue = 8; rankValue >= 1; rankValue--) {
            List<String> rowDate = new ArrayList<>();
            for (char fileValue = 'a'; fileValue <= 'h'; fileValue++) {
                Piece piece = board.findByCoordinate(new Coordinate(rankValue, fileValue));
                if (piece == null) {
                    rowDate.add(".");
                    continue;
                }
                rowDate.add(convert(piece));
            }
            viewData.add(rowDate);
        }

        outputView.printBoard(viewData);
    }

    private String convert(Piece piece) {
        Map<PieceType, String> pieceTypeResolver = new EnumMap<>(PieceType.class);
        pieceTypeResolver.put(PieceType.KING, "k");
        pieceTypeResolver.put(PieceType.QUEEN, "q");
        pieceTypeResolver.put(PieceType.BISHOP, "b");
        pieceTypeResolver.put(PieceType.KNIGHT, "n");
        pieceTypeResolver.put(PieceType.ROOK, "r");
        pieceTypeResolver.put(PieceType.PAWN, "p");

        Map<Team, Function<String, String>> teamCaseResolver = new EnumMap<>(Team.class);
        teamCaseResolver.put(Team.BLACK, String::toUpperCase);
        teamCaseResolver.put(Team.WHITE, String::toLowerCase);

        String shape = pieceTypeResolver.get(piece.getType());
        Function<String, String> caseTranslator = teamCaseResolver.get(piece.getTeam());

        return caseTranslator.apply(shape);
    }
}
