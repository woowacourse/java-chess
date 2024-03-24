package view.dto;

import view.util.ColumnSymbol;

public record CoordinateRequest(int row, int column) {

    public static CoordinateRequest fromCommand(String command) {
        String[] split = command.split("");
        int column = ColumnSymbol.from(split[0]).getPosition();
        int row = Integer.parseInt(split[1]);

        return new CoordinateRequest(row, column);
    }
}
