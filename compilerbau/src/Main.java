import de.dhbw.blaaah.Statement;
import de.dhbw.blaaah.parser.MiniSqlParser;
import java_cup.runtime.Symbol;

import java.io.*;
import java.util.List;

/**
 * Author: Alexander Hausmann <hausmann.alex@gmail.com>
 * User: alexander
 * Creation date: 21.11.13 - 09:09
 */
public class Main
{
    public static void main(String[] args) {
        Symbol result;
        String input = "DROP TABLE bla;";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());

        try {
            result = MiniSqlParser.parse(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        Statement stmt = ((List<Statement>)result.value).get(0);

        int i = 0;
    }
}
