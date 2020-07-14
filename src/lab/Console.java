package lab;
import lab.commands.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Класс, который отвечает за работу консоли.
 * @autor Шахов Кирилл Андреевич P3132
 * @version 1.1
 */
public class Console {
    /** Массив, который хранит команды, доступные пользователю. */
    private ArrayList<Command> commands = new ArrayList<>();
    /** Поле, которое хранит класс, который позволяет получать доступ к коллекции. */
    private BD data;

    private ArrayList<String> level_list = new ArrayList<>();
    private int level = -1;


    /** Конструктов консоли
     *
     * @param data Указываем базу данных, которую будем менять.
     *  @param commands Указываем команды. Их может быть несколько.*/
    public Console(BD data, Command... commands){
        this.data = data;
        Collections.addAll(this.commands, commands);
    }

    /** Метод, позволяет добавить команду для пользователя.
     *
     * @param commands Команды для добавления
     */
    public void addCommand(Command ... commands){
        Collections.addAll(this.commands, commands);
    }

    /** Метод, позволяет удалить команду и пользователь не сможет её вызывать
     *
     * @param command экземпляр команды
     */
    public void removeCommand(Command command){
        commands.removeIf(c -> c.equals(command));
    }

    /** Метод, который используется для проверки строки, которую ввёл пользователь
     *
     * @param line String - строка пользователя
     */
    public void update(String line){
        boolean wasСalled = false;
        for (Command command : commands) {
            if (command.execute(line)) {
                wasСalled = true;
            }
        }
        if (!wasСalled) {
            Console.sendln("Команда не верная. Введте help для получения справки.");
        }
    }
    /** Метод, который используется для запуска консоли. */
    public void start(){
        try {
            for(Command command : commands){
                command.initialization(data, this);
            }
            Scanner scanner = new Scanner(System.in);
            sendln("Введите help для получения списка доступных комманд:");
            send(">");
            while (scanner.hasNextLine()) {
                update(scanner.nextLine());
                send(">");
            }
            sendln("Завершение программы");
            System.exit(0);
        }
        catch (Exception e){
            try {
                Console.sendln(e.getCause().toString());
                Console.sendln(e.getMessage());
                Console.sendln(e.getLocalizedMessage());
                sendln("Произошла критическая ошибка. Перезапуск командной строки...");
                if (Console.getAnswer("Вы хотите сохранить данные? Они могут повредить данные.")) {
                    data.save();
                    start();
                }
            } catch (Exception ex) {
                sendln("Завершение программы");
                Console.sendln(e.getMessage());
                System.exit(0);
            }
        }
    }

    /** Метод, позволяет получить строку от пользователя
     *
     * @return String кракозябры пользователя
     */
    public static String receive(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    /** Метод, выводит в консоль информацию с новой строки
     *
     * @param info String - информация
     */
    public static void sendln(String info){
        System.out.println(info);
    }
    /** Метод, выводит в консоль
     *
     * @param info String - информация
     */
    public static void send(String info){ System.out.print(info); }

    /** Метод, позволяет прочитать файл
     *
     * @param FILE_PATH String - название файла
     * @return String - содержимое файла
     */
    public static String readFile(String FILE_PATH) throws FileNotFoundException{
        Scanner in = new Scanner(new File(FILE_PATH));
        StringBuffer data = new StringBuffer();
        while (in.hasNext())
            data.append(in.nextLine()).append("\n");
        return data.toString();
    }

    public static boolean getAnswer(String info){
        while (true){
            Console.sendln(info+"(Yes/No)");
            Console.send(">");
            String line = Console.receive();
            if (line.equals("Yes") || line.equals("Y") || line.equals("y") || line.equals("yes") || line.equals("да") || line.equals("Да") || line.equals("д")|| line.equals("Д")) {
                return true;
            } else if (line.equals("No") || line.equals("N") || line.equals("n") || line.equals("no") || line.equals("нет") || line.equals("Нет") || line.equals("н") || line.equals("Н")) {
                return false;
            }
        }
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public void addLevel_list(String s) {
        level_list.add(s);
        level += 1;
    }

    public void remove_list(){
        level_list.remove(level);
        level -= 1;
    }

    public boolean check_list(String s){
        return level_list.contains(s);
    }
}
