package popov.ilia;


import popov.ilia.gui.ConsoleGui;
import popov.ilia.gui.Gui;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Gui gui = new ConsoleGui();
        gui.run();
        }
    }

