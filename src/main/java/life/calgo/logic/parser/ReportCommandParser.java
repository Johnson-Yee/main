package life.calgo.logic.parser;

import static java.util.Objects.requireNonNull;

import life.calgo.logic.commands.ReportCommand;
import life.calgo.logic.parser.exceptions.ParseException;
import life.calgo.commons.core.Messages;

import java.time.LocalDate;
import java.util.stream.Stream;

import static life.calgo.logic.parser.CliSyntax.PREFIX_DATE;

/**
 * Parses input date in order to create a new ReportCommand object.
 */
public class ReportCommandParser implements Parser<ReportCommand> {

    public ReportCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    ReportCommand.MESSAGE_USAGE));
        }

        // at this breakpoint, PREFIX_DATE is present in args
        LocalDate queryDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        return new ReportCommand(queryDate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
