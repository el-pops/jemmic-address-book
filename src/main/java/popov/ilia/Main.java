package popov.ilia;


import popov.ilia.gui.GuiConsoleImpl;
import popov.ilia.gui.Gui;
import popov.ilia.reposiroty.ContactDao;
import popov.ilia.reposiroty.ContactDaoInMemoryImpl;
import popov.ilia.service.ContactService;
import popov.ilia.service.ContactServiceImpl;
import popov.ilia.utils.FileUtils;
import popov.ilia.utils.MappingUtils;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ContactDao contactDao = new ContactDaoInMemoryImpl(MappingUtils.mapStringsToContacts(FileUtils.readFromFile()));
        ContactService contactService = new ContactServiceImpl(contactDao, scanner);
        Gui gui = new GuiConsoleImpl(scanner,contactDao,contactService);
        gui.run();
        }
    }

