package ee.neotech.services;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import ee.neotech.exceptions.NeotechCustomException;

public class Application {

    public static void execute(String[] args) {
        final Options options = getOptions();

        CommandLine cmd;
        try {
            cmd = getCommandLine(options, args);
        } catch (NeotechCustomException ex) {
            HelpFormatter usage = new HelpFormatter();
            String header = "Issue is: " + ex.getLocalizedMessage();
            String footer = "There is application writen for Neotech company";
            usage.printHelp("gradlew run [-p]", header, options, footer);
            return;
        }

        performCommand(cmd);
    }

    private static CommandLine getCommandLine(Options options, String[] args) {
        optionValidator(options);
        CommandLine cmd;
        try {
            cmd = new DefaultParser().parse(options, args);
        } catch (ParseException ex) {
            throw new NeotechCustomException(ex.getLocalizedMessage());
        }

        return cmd;
    }

    private static void optionValidator(Options options) {
        if (options.getOptions().size()>1){
            throw new NeotechCustomException("Could be only one option");
        }
    }

    private static void performCommand(CommandLine cmd) {
        if (cmd.hasOption("p")) {
            DataReporter.show();
        } else {
            DataGenerator.process();
        }
    }

    private static Options getOptions() {
        final Options options = new Options();
        options.addOption(Option.builder("p")
                .longOpt("print")
                .desc("Print out all stored records from Database")
                .build());

        return options;
    }

}