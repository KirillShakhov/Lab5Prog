package lab.commands;
import lab.Console;

import java.util.ArrayList;

public class AddCommand extends Command {
    {
        command_name = new String[]{"add"};
        description = "add name x y numberOfParticipants - стандартная команда с вводом примитивных данных\n" +
                      "Флаги: n - Имя\n" +
                      "       x - X\n" +
                      "       y - Y\n" +
                      "       p - Количество участников\n" +
                      "       d - Описание\n" +
                      "       у - Дата основания\n" +
                      "       g - Жанр\n" +
                      "       a - Имя лучшего альбома\n" +
                      "       t - Количеств треков в лучшем альбоме\n" +
                      "       l - Длина лучшего альбома\n" +
                      "       s - Продажи лучшего альбома(в долларах)\n" +
                      "Флаги выше выставляються в том же порядке, что и аргументы.\n" +
                      "       j - ввод в формате json. В случае присутствия, другие флаги игнорируються.";
    }
    @Override
    void start_with_arguments(ArrayList<String> args, ArrayList<Character> flags) {
        if(flags.contains('j')){
            try {
                char[] chars_old = origin_line.toCharArray();
                boolean isString;
                boolean isMas;
                ArrayList<Character> chars = new ArrayList<Character>();
                isMas = false;
                isString = false;
                boolean nameORvalue = false;
                StringBuilder tmpName = new StringBuilder();
                StringBuilder tmpValue = new StringBuilder();
                /////////////////////////////////
                for (char word : chars_old) {
                    //if(word == '"'){isString = !isString;}
                    if (word == ':') {
                        isString = true;
                    }
                    if (word == ',') {
                        isString = false;
                    }
                    if (word == '{') {
                        isMas = true;
                    }
                    if ((word != ' ' || isString) && isMas && word != '{' && word != '}' && word != '[' && word != ']') {
                        chars.add(word);
                    }
                }
                chars.add(',');
                /////////////////////////////////
                String name = null;
                String x = null;
                String y = null;
                String numberOfParticipants = null;
                String description = null;
                String establishmentDate = null;
                String genre = null;
                String albumName = null;
                String tracks = null;
                String length = null;
                String sales = null;
                /////////////////////////////////
                isString = false;
                //add {name:"name", coordinates:{x:100.0,y:12.0}, numberOfParticipants:1000, description:"description", establishmentDate:1986-04-08 12:30, genre:POP, bestAlbum:[name:bestAlbum_name, tracks:100, length:123, sales:1000000000]}
                //n a m e : n a m e , c o o r d i n a t e s : [ x : 1 0 0 . 0, ,, y, :, 1, 2, ., 0, ], ,, n, u, m, b, e, r, O, f, P, a, r, t, i, c, i, p, a, n, t, s, :, 1, 0, 0, 0, ,, d, e, s, c, r, i, p, t, i, o, n, :, d, e, s, c, r, i, p, t, i, o, n, ,, e, s, t, a, b, l, i, s, h, m, e, n, t, D, a, t, e, :, 1, 9, 8, 6, -, 0, 4, -, 0, 8,  , 1, 2, :, 3, 0, ,, g, e, n, r, e, :, P, O, P, ,, b, e, s, t, A, l, b, u, m, :, [, n, a, m, e, :, b, e, s, t, A, l, b, u, m, _, n, a, m, e, ,, t, r, a, c, k, s, :, 1, 0, 0, ,, l, e, n, g, t, h, :, 1, 2, 3, ,, s, a, l, e, s, :, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, ]
                for (char word : chars) {
                    if(word == '"'){isString = !isString;}
                    if (word == ':') {
                        nameORvalue = true;
                        Console.sendln("name: "+tmpName);
                        if (tmpName.toString().equals("coordinates")){
                            nameORvalue = false;
                            tmpValue = new StringBuilder();
                        }
                        if (tmpName.toString().equals("bestAlbum")){
                            nameORvalue = false;
                            tmpValue = new StringBuilder();
                        }
                        if(tmpName.toString().equals("establishmentDate")){ if(!tmpValue.toString().equals("")){tmpValue.append(":");} }
                        continue;
                    } else if (word == ',' && !isString) {
                        Console.sendln("value: "+tmpValue);
                        nameORvalue = false;
                        if (tmpName.toString().equals("name")) {
                            name = tmpValue.toString();
                            tmpName = new StringBuilder();
                            tmpValue = new StringBuilder();
                            continue;
                        }
                        if (tmpName.toString().indexOf("coordinates") == 0 && tmpName.toString().endsWith("x")){
                            x = tmpValue.toString();
                            tmpValue = new StringBuilder();
                            continue;
                        }
                        if (tmpName.toString().indexOf("coordinates") == 0 && tmpName.toString().endsWith("y")){
                            y = tmpValue.toString();
                            tmpName = new StringBuilder();
                            tmpValue = new StringBuilder();
                            nameORvalue = false;
                            continue;
                        }
                        if (tmpName.toString().equals("numberOfParticipants")){
                            numberOfParticipants = tmpValue.toString();
                            tmpName = new StringBuilder();
                            tmpValue = new StringBuilder();
                            continue;
                        }
                        if (tmpName.toString().equals("description")){
                            description = tmpValue.toString();
                            tmpName = new StringBuilder();
                            tmpValue = new StringBuilder();
                            continue;
                        }
                        if (tmpName.toString().equals("establishmentDate")){
                            establishmentDate = tmpValue.toString();
                            tmpName = new StringBuilder();
                            tmpValue = new StringBuilder();
                            continue;
                        }
                        if (tmpName.toString().equals("genre")){
                            genre = tmpValue.toString();
                            tmpName = new StringBuilder();
                            tmpValue = new StringBuilder();
                            continue;
                        }
                        if (tmpName.toString().indexOf("bestAlbum") == 0 && tmpName.toString().endsWith("name")){
                            albumName = tmpValue.toString();
                            tmpValue = new StringBuilder();
                            continue;
                        }
                        if (tmpName.toString().indexOf("bestAlbum") == 0 && tmpName.toString().endsWith("tracks")){
                            tracks = tmpValue.toString();
                            tmpValue = new StringBuilder();
                            continue;
                        }
                        if (tmpName.toString().indexOf("bestAlbum") == 0 && tmpName.toString().endsWith("length")){
                            length = tmpValue.toString();
                            tmpValue = new StringBuilder();
                            continue;
                        }
                        if (tmpName.toString().indexOf("bestAlbum") == 0 && tmpName.toString().endsWith("sales")){
                            sales = tmpValue.toString();
                            tmpName = new StringBuilder();
                            tmpValue = new StringBuilder();
                            nameORvalue = false;
                            continue;
                        }
                    }
                    if (!nameORvalue) {
                        tmpName.append(word);
                    } else {
                        tmpValue.append(word);
                    }
                }
                Console.sendln(name + "," + x + "," + y + "," + numberOfParticipants + "," + description + "," + establishmentDate + "," + genre + "," + albumName + "," + tracks + "," + length + "," + sales);
                if(data.add(name, x, y, numberOfParticipants, description, establishmentDate, genre, albumName, tracks, length, sales)){ Console.sendln("Объект добавлен");}
                else{ Console.sendln("Объект не добавлен."); }
            }
            catch (Exception e){ Console.sendln("Пример команды: add {name:\"name\", coordinates:[x:100.0,y:12.0], numberOfParticipants:1000, description:\"description\", establishmentDate:1986-04-08 12:30, genre:POP, bestAlbum:[name:bestAlbum_name, tracks:100, length:123, sales:1000000000]}"); }
        }
        else if(flags.contains('n') || flags.contains('x') || flags.contains('y') || flags.contains('p') || flags.contains('d') || flags.contains('e') || flags.contains('g') || flags.contains('a') || flags.contains('t') || flags.contains('l') || flags.contains('s') ){
            String name = null;
            String x = null;
            String y = null;
            String numberOfParticipants = null;
            String description = null;
            String establishmentDate = null;
            String genre = null;
            String albumName = null;
            String tracks = null;
            String length = null;
            String sales = null;
            for(int i = 0; i < args.size(); i++){
                if (flags.size() >= i) {
                    if (flags.get(i).equals('n')){
                        name = args.get(i);
                    }
                    else if (flags.get(i).equals('x')){
                        x = args.get(i);
                    }
                    else if (flags.get(i).equals('y')){
                        y = args.get(i);
                    }
                    else if (flags.get(i).equals('p')){
                        numberOfParticipants = args.get(i);
                    }
                    else if (flags.get(i).equals('d')){
                        description = args.get(i);
                    }
                    else if (flags.get(i).equals('e')){
                        establishmentDate = args.get(i);
                    }
                    else if (flags.get(i).equals('g')){
                        genre = args.get(i);
                    }
                    else if (flags.get(i).equals('a')){
                        albumName = args.get(i);
                    }
                    else if (flags.get(i).equals('t')){
                        tracks = args.get(i);
                    }
                    else if (flags.get(i).equals('l')){
                        length = args.get(i);
                    }
                    else if (flags.get(i).equals('s')){
                        sales = args.get(i);
                    }
                }
            }
            if(data.add(name, x, y, numberOfParticipants, description, establishmentDate, genre, albumName, tracks, length, sales)){ Console.sendln("Объект добавлен");}
            else{ Console.sendln("Объект не добавлен."); }
        }
        else if(!flags.isEmpty()){
            Console.sendln("Неправильные флаги. Ведите: help add");
        }
        else if(!args.isEmpty() && args.size()>= 4){
            if(data.add(args.get(0), args.get(1), args.get(2), args.get(3), null, null, null, null, null, null, null)){ Console.sendln("Объект добавлен");}
            else{ Console.sendln("Объект не добавлен."); }
        }
        else{
            start_without_arguments();
        }
    }

    @Override
    void start_without_arguments() {
        if(data.add(null, null, null, null, null, null, null, null, null, null, null)){ Console.sendln("Объект добавлен");}
        else{ Console.sendln("Объект не добавлен."); }
    }
}
