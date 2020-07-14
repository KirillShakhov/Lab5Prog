package lab;

import lab.commands.*;

import java.util.Properties;

class Main {
    public static void main(String[] args) {
        BD bd = null;
        if(args.length > 0 && args[0].equals("test")) {
            bd = new BD("data.json");
        }
        else {
            try {
                Properties p = ReadEnv.getEnvVars();
                bd = new BD(p.getProperty("TEMP"));
            } catch (Throwable e) {
                System.out.println("Не указана переменная окружения TEMP");
            }
        }
        Console user = new Console(bd);
        user.addCommand(new AddCommand(), new ClearCommand(), new CountGreaterThanBestAlbum(), new ExecuteScriptCommand(), new ExitCommand());
        user.addCommand(new FilterContainsNameCommand(), new HelpCommand(), new HistoryCommand(), new InfoCommand());
        user.addCommand(new RemoveAllByDescriptionCommand(), new RemoveByIdCommand(), new RemoveLowerCommand(), new ReorderCommand());
        user.addCommand(new SaveCommand(), new ShowCommand(), new UpdateCommand(), new TestCommand());
        user.start();
    }
}