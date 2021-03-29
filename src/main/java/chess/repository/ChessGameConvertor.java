package chess.repository;

import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.state.State;
import chess.domain.piece.ChessPiece;
import com.google.gson.Gson;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ChessGameConvertor {

    private static Gson gson = new Gson();

    static class Temp {

        private final String className;
        private final int row;
        private final int col;

        public Temp(String className, int row, int col) {
            this.className = className;
            this.row = row;
            this.col = col;
        }

    }

    static class Wrapper {
        private final String state;
        private final List<Temp> pieces;

        public Wrapper(String state, List<Temp> pieces) {
            this.state = state;
            this.pieces = pieces;
        }

        public List<Temp> getPieces() {
            return pieces;
        }

        public String getState() {
            return state;
        }
    }

    public static String chessGameToJson(ChessGame chessGame) {
        List<Temp> collect = chessGame.getBoard().getAllPieces().stream()
                .map(piece -> new Temp(piece.getClass().getName(), piece.getRow(), piece.getColumn()))
                .collect(toList());

        String stateName = chessGame.getState().getClass().getName();

        return gson.toJson(new Wrapper(stateName, collect));
    }

    public static ChessGame jsonToChessGame(String jsonData) {
        Wrapper wrapper = gson.fromJson(jsonData, Wrapper.class);

        List<ChessPiece> chessPieces = wrapper.getPieces().stream()
                .map(piece -> convertChessPiece(piece.className, piece.row, piece.col))
                .collect(toList());

        ChessGame chessGame = new ChessGame(new Board(chessPieces));

        State state = convertState(wrapper.getState(), chessGame);
        chessGame.changeState(state);

        return chessGame;
    }

    private static State convertState(String stateName, ChessGame chessGame) {
        try {
            Class<?> aClass = Class.forName(stateName);
            Constructor<?> constructor = aClass.getConstructor(ChessGame.class);
            return (State) constructor.newInstance(chessGame);
        } catch (ClassNotFoundException | NoSuchMethodException |
                IllegalAccessException |InstantiationException | InvocationTargetException e) {
            throw new IllegalArgumentException("불러오기에 실패했습니다.");
        }
    }

    private static ChessPiece convertChessPiece(String className, int row, int column) {
        try {
            Class<?> aClass = Class.forName(className);
            Method createWithCoordinate = aClass.getDeclaredMethod("createWithCoordinate", int.class, int.class);
            return (ChessPiece) createWithCoordinate.invoke(null, row, column);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("불러오기에 실패했습니다.");
        }
    }

}
