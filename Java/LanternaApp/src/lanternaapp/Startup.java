package lanternaapp;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class Startup
{

    public static void main(String[] args) throws IOException
    {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();

        terminal.setBackgroundColor(TextColor.ANSI.BLUE);
        terminal.setCursorPosition(5, 8);
        terminal.putCharacter('a');
        terminal.setCursorPosition(8, 5);
        terminal.putCharacter('b');
    }
    
}
