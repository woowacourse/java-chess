package chess.domain.manager;

import chess.domain.board.Board;
import chess.domain.board.InitBoardInitializer;
import chess.domain.board.LoadBoardInitializer;
import chess.domain.board.Square;
import chess.domain.piece.ColoredPieces;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.statistics.ChessGameStatistics;
import chess.mapper.PieceMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static chess.domain.piece.attribute.Color.WHITE;
import static java.util.stream.Collectors.*;

public class ChessGameManagerFactory {
    private ChessGameManagerFactory() {
    }

    public static ChessGameManager createRunningGame(long id) {
        Board board = InitBoardInitializer.getBoard();
        return new RunningGameManager(id, InitBoardInitializer.getBoard(), createColoredPieces(board), WHITE);
    }

    public static ChessGameManager createEndGame(long id, ChessGameStatistics chessGameStatistics) {
        return new EndChessGameManager(id, chessGameStatistics);
    }

    public static ChessGameManager createNotStartedGameManager(long id) {
        return new NotStartedChessGameManager(id);
    }

    public static ChessGameManager loadingGame(boolean isRunning, Long id, String pieces, Color nextTurn) {
        Board loadedBoard = LoadBoardInitializer.getBoard(convertSquares(pieces));
        if (isRunning) {
            return new RunningGameManager(id, loadedBoard, createColoredPieces(loadedBoard), nextTurn);
        }
        return createEndGame(id, ChessGameStatistics.createNotStartGameResult());
    }

    private static List<ColoredPieces> createColoredPieces(Board board) {
        Map<Color, List<Piece>> coloredPiecesMap = board.getColoredPieces();
        return coloredPiecesMap.keySet().stream()
                .map(color -> new ColoredPieces(coloredPiecesMap.get(color), color))
                .collect(toList());
    }

    private static List<Square> convertSquares(String pieces) {
        final Map<Position, Piece> positionPieceMap = convertPositionMap(pieces);
        return positionPieceMap.keySet().stream()
                .map(key -> new Square(key, positionPieceMap.get(key)))
                .collect(toList());
    }

    private static Map<Position, Piece> convertPositionMap(String pieces) {
        List<Piece> pieceList = parsePiecesString(pieces);
        ArrayList<Position> positionList = Arrays.stream(Rank.values())
                .flatMap(rank ->
                        Arrays.stream(File.values())
                                .map(file -> Position.of(file, rank))
                )
                .collect(collectingAndThen(toList(), ArrayList::new));

        return IntStream.range(0, pieceList.size())
                .boxed()
                .collect(toMap(positionList::get, pieceList::get));
    }

    private static List<Piece> parsePiecesString(String pieces) {
        return Arrays.stream(pieces.split(""))
                .map(PieceMapper::of)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
    }
}
