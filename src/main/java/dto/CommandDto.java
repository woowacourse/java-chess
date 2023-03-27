package dto;

import java.util.List;

public class CommandDto {
    private final String command;
    private String sourceFile = "";
    private String sourceRank = "";
    private String destinationFile = "";
    private String destinationRank = "";

    private CommandDto(final String... strings) {
        this.command = strings[0];
        this.sourceFile = strings[1];
        this.sourceRank = strings[2];
        this.destinationFile = strings[3];
        this.destinationRank = strings[4];
    }

    private CommandDto(final String string) {
        this.command = string;
    }

    public static CommandDto from(final List<String> commands) {
        if (commands.size() == 1) {
            return new CommandDto(commands.get(0));
        }
        return new CommandDto(commands.get(0),
                String.valueOf(commands.get(1)
                        .charAt(0)),
                String.valueOf(commands.get(1)
                        .charAt(1)),
                String.valueOf(commands.get(2)
                        .charAt(0)),
                String.valueOf(commands.get(2)
                        .charAt(1)));
    }

    public String getCommand() {
        return command;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public String getSourceRank() {
        return sourceRank;
    }

    public String getDestinationFile() {
        return destinationFile;
    }

    public String getDestinationRank() {
        return destinationRank;
    }
}
