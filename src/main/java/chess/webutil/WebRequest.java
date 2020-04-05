package chess.webutil;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.judge.Judge;
import chess.domain.judge.WoowaJudge;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.exceptions.InvalidInputException;
import spark.Request;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum WebRequest {
    BLANK_BOARD("빈 판", (req, board) -> {
        Map<String, Object> model = parseBlankBoard();
        model.putAll(generateMovableModel(new ArrayList<>()));
        return model;
    }),
    NEW_GAME("새 게임", (req, board) -> {
        board.initialize();
        return parseBoard(board);
    }),
    LOAD_GAME("불러오기", (req, board) -> {
        return parseBoard(board);
    }),
    STATUS("현재점수", (req, board) -> {
        Judge judge = new WoowaJudge(board);
        Map<String, Object> model = parseBoard(board);
        model.put("player1_info", "WHITE: " + judge.calculateScore(Side.WHITE));
        model.put("player2_info", "BLACK: " + judge.calculateScore(Side.BLACK));
        return model;
    }),
    MOVE("이동", (req, board) -> {
        Position start = Position.of(req.queryParams("start"));
        Position end = Position.of(req.queryParams("end"));
        tryMove(start, end, board);
        return parseBoard(board);
    }),
    SHOW_MOVABLE("이동체크", (req, board) -> {
        Position start = Position.of(req.queryParams("start"));
        List<Position> movablePositions = tryFindMovablePositions(start, board);
        Map<String, Object> model = parseBoard(board, movablePositions);

        if (movablePositions.size() != 0) {
            model.put("start", req.queryParams("start"));
        }
        return model;
    });

    private String name;
    private ThrowingBiFunction generateModel;

    WebRequest(String name, ThrowingBiFunction generateModel) {
        this.name = name;
        this.generateModel = generateModel;
    }

    public static WebRequest of(String commandName) {
        return Stream.of(WebRequest.values())
                .filter(webRequest -> webRequest.name.equals(commandName))
                .findAny()
                .orElse(WebRequest.BLANK_BOARD);
    }

    private static Map<String, Object> parseBlankBoard() {
        return Position.getAllPositions()
                .stream()
                .collect(toMap(Position::toString, position -> "blank"));
    }

    private static Map<String, Object> parseBoard(Board board) throws SQLException {
        Map<String, Object> output = new HashMap<>();

        for (Position position : Position.getAllPositions()) {
            Optional<Piece> piece = board.findPieceOn(position);
            output.put(position.toString(), parsePiece(piece));
        }

        output.putAll(generateMovableModel(new ArrayList<>()));

        return output;
    }

    private static Map<String, Object> parseBoard(Board board, List<Position> movablePlaces) throws SQLException {
        Map<String, Object> output = new HashMap<>();

        for (Position position : Position.getAllPositions()) {
            Optional<Piece> piece = board.findPieceOn(position);
            output.put(position.toString(), parsePiece(piece));
        }

        output.putAll(generateMovableModel(movablePlaces));

        return output;
    }

    private static String parsePiece(Optional<Piece> piece) {
        if (piece.isPresent()) {
            return piece.get().toString();
        }
        return "blank";
    }

    private static Map<String, Object> generateMovableModel(List<Position> markingPlaces) {
        return Position.getAllPositions()
                .stream()
                .collect(toMap(position -> parseMarkPosition(position), position -> parseMovable(position, markingPlaces)));
    }

    private static String parseMarkPosition(Position position) {
        return position.toString() + "_mark";
    }

    private static String parseMovable(Position position, List<Position> positions) {
        if (positions.contains(position)) {
            return "movable";
        }
        return "blank";
    }

    private static void tryMove(Position start, Position end, Board board) throws SQLException {
        try {
            board.move(start, end);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<Position> tryFindMovablePositions(Position start, Board board) throws SQLException {
        try {
            return board.findMovablePositions(start);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Map<String, Object> generateModel(Request request, Board board) throws SQLException {
        return this.generateModel.action(request, board);
    }

    private interface ThrowingBiFunction {
        Map<String, Object> action(Request request, Board board) throws SQLException;
    }
}
