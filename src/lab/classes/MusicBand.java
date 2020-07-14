package lab.classes;

import lab.Console;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Объект, который будет сохраняться в базе данных.
 * @autor Шахов Кирилл Андреевич P3132
 * @version 1.1
 */


public class MusicBand implements Comparable<MusicBand> {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int numberOfParticipants; //Значение поля должно быть больше 0
    private String description; //Поле не может быть null
    private Date establishmentDate; //Поле может быть null
    private MusicGenre genre; //Поле не может быть null
    private Album bestAlbum; //Поле может быть null

    /**Конструктор создания объекта
     *
     * @param id Long - ID
     * @param name String - Имя
     * @param coordinates Coordinates - Координаты
     * @param createdate LocalDateTime - Дата создания
     * @param numberOfParticipants int - Количество людей в группе
     * @param description String - Описание
     * @param establishmentDate Date - дата создания группы
     * @param genre MusicGenre - жанр(PSYCHEDELIC_ROCK, RAP, POP, POST_ROCK, POST_PUNK)
     * @param bestAlbum Album - лучший альбом
     */
    public MusicBand(Long id, String name, Coordinates coordinates, LocalDateTime createdate, int numberOfParticipants, String description, Date establishmentDate, MusicGenre genre, Album bestAlbum){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = createdate;
        this.numberOfParticipants = numberOfParticipants;
        this.description = description;
        this.establishmentDate = establishmentDate;
        this.genre = genre;
        this.bestAlbum = bestAlbum;
    }

    /** Метод, возвращает количество людей в мезыкальной группе.
     *
     * @return int - количество людей.
     */
    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    /** Метод, возвращает ID.
     *
     * @return long - id.
     */
    public long getID(){
        return this.id;
    }

    /** Метод, возвращает количество продаж из Album.
     *
     * @return long - продажи.
     */
    public long getSales(){
        return this.bestAlbum.getSales();
    }

    /** Метод, возвращает имя музыкальной группы.
     *
     * @return String - name.
     */
    public String getName() {
        return name;
    }
    /** Метод, возвращает описание.
     *
     * @return String - описание.
     */
    public String getDescription(){
        return this.description;
    }

    /** Метод, парсит аргументы из String в MusicBand
     *
     * @param id id, обычно генерируется с помощью giveID().
     * @param name Имя объекта. Поле не может быть null, Строка не может быть пустой.
     * @param x Координата X. Значение поля должно быть больше -687.
     * @param y Координата Y. Поле не может быть null.
     * @param numberOfParticipants Количество людей в музыкальной группе. Значение поля должно быть больше 0.
     * @param description Описание музыкальной группы. Поле не может быть null.
     * @param establishmentDate Дата создания музыкальной группы. Формат: yyyy-MM-dd. Поле может быть null.
     * @param genre Жанр(PSYCHEDELIC_ROCK, RAP, POP, POST_ROCK, POST_PUNK). Поле не может быть null.
     * @param albumName Название лучшего альбома.
     * @param tracks Количество треков в лучшем альбоме.
     * @param length Длина лучшего альбома.
     * @param sales Продажи лучшего альбома.
     * @return MusicBand - результат
     */
    public static MusicBand parseMusicBand(long id, String name, String x, String y, String numberOfParticipants, String description, String establishmentDate, String genre, String albumName, String tracks, String length, String sales) {
        if (name == null || name.isEmpty()) {
            while (true) {
                Console.sendln("Имя не может быть пустым. Укажите имя:");
                Console.send("?");
                String l = Console.receive();
                if (l != null && !l.equals("")) {
                    name = l;
                    break;
                }
            }
        }
        double xf;
        try {
            xf = Double.parseDouble(x);
            if (xf <= 391 || Double.isNaN(xf)) { throw new Exception(); }
        }
        catch (Exception e){
            while (true) {
                Console.sendln("X является числом и не может превышать 391. Укажите X:");
                Console.send("?");
                try {
                    xf = Double.parseDouble(Console.receive());
                    if (xf <= 391 && !Double.isNaN(xf)) {
                        break;
                    }
                }
                catch (NullPointerException | NumberFormatException ignored){ }
            }
        }
        Float yd;
        try{
            yd = Float.parseFloat(y);
            if(Float.isNaN(yd)){throw new Exception();}
        }
        catch (Exception e){
            while (true) {
                Console.sendln("Y является числом. Укажите Y:");
                Console.send("?");
                try {
                    yd = Float.parseFloat(Console.receive());
                    if (!Float.isNaN(yd)) {
                        break;
                    }
                }
                catch (NullPointerException | NumberFormatException ignored){ }
            }
        }
        int numberOfParticipants_long;
        try {
            numberOfParticipants_long = Integer.parseInt(numberOfParticipants);
            if (numberOfParticipants_long <= 0 || numberOfParticipants == null || numberOfParticipants.isEmpty()) {
                throw new NumberFormatException();
            }
        }
        catch (NumberFormatException e){
            while (true) {
                Console.sendln("Количество участников должно быть числом и должно быть больше нуля. Укажите количество участников:");
                Console.send("?");
                try {
                    numberOfParticipants_long = Integer.parseInt(Console.receive());
                    if (numberOfParticipants_long > 0) {
                        break;
                    }
                }
                catch (NullPointerException | NumberFormatException ignored){ }
            }
        }
        if(description == null){
            while (true){
                Console.sendln("Введите описание:");
                Console.send("?");
                description = Console.receive();
                if(description != null){ break;}
            }
        }
        Date time;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            time = format.parse(establishmentDate);
        }
        //TODO ctrl+D
        catch (Exception e){
            while (true) {
                try {
                    Console.sendln("Пример даты: 2009-12-31");
                    Console.sendln("Можно оставить пустое поле.");
                    Console.sendln("Укажите дату:");
                    Console.send("?");
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String line = Console.receive();
                    if(line.equals("")){
                        time = null;
                        break;
                    }
                    time = format.parse(line);
                    break;
                }
                catch (ParseException ignored){}
            }
        }
        MusicGenre mgenre;
        while (true){
            if(genre == null || genre.isEmpty()){
                Console.sendln("Укажите жанр(PSYCHEDELIC_ROCK, RAP, POP, POST_ROCK, POST_PUNK):");
                Console.send("?");
                genre = Console.receive();
            }
            else if (genre.equals("RAP") || genre.equals("rap") || genre.equals("Rap")) {
                mgenre = MusicGenre.RAP;
                break;
            }
            else if (genre.equals("POP") || genre.equals("pop") || genre.equals("Pop")) {
                mgenre = MusicGenre.POP;
                break;
            }
            else if (genre.equals("POST ROCK") || genre.equals("POST_ROCK") || genre.equals("post rock") || genre.equals("post_rock") || genre.equals("Post rock") || genre.equals("Post_rock")) {
                mgenre = MusicGenre.POST_ROCK;
                break;
            }
            else if (genre.equals("POST PUNK") || genre.equals("POST_PUNK") || genre.equals("post punk") || genre.equals("post_punk") || genre.equals("Post punk") || genre.equals("Post_punk")) {
                mgenre = MusicGenre.POST_PUNK;
                break;
            }
            else if (genre.equals("PSYCHEDELIC ROCK") || genre.equals("PSYCHEDELIC_ROCK") || genre.equals("psychedelic rock") || genre.equals("psychedelic_rock") || genre.equals("Psychedelic rock") || genre.equals("Psychedelic_rock")) {
                mgenre = MusicGenre.PSYCHEDELIC_ROCK;
                break;
            }
            else{
                Console.sendln("Укажите жанр(PSYCHEDELIC_ROCK, RAP, POP, POST_ROCK, POST_PUNK):");
                Console.send("?");
                genre = Console.receive();
            }
        }
        Album album;
        if((albumName == null && length == null && tracks == null && sales == null) && Console.getAnswer("Хотите добавить лучший альбом?")) {
            if (albumName == null || albumName.isEmpty()) {
                while (true) {
                    Console.sendln("Имя лучшего альбома(строка не должна быть пустой):");
                    Console.send("?");
                    albumName = Console.receive();
                    if (albumName != null && !albumName.equals("")) {
                        break;
                    }
                }
            }
            int tracks_int;
            try {
                tracks_int = Integer.parseInt(tracks);
                if (tracks_int <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                while (true) {
                    Console.sendln("Количество треков в альбоме должно быть больше 0. Укажите количество треков:");
                    Console.send("?");
                    try {
                        tracks_int = Integer.parseInt(Console.receive());
                        if (tracks_int > 0) {
                            break;
                        }
                    }
                    catch (NullPointerException | NumberFormatException ignored){ }
                }
            }
            Integer length_int;
            try {
                length_int = Integer.parseInt(length);
                if (length_int <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                while (true) {
                    Console.sendln("Длина альбома должна быть больше 0. Укажите длину альбома:");
                    Console.send("?");
                    try {
                        length_int = Integer.parseInt(Console.receive());
                        if (length_int > 0) {
                            break;
                        }
                    }
                    catch (NullPointerException | NumberFormatException ignored){ }
                }
            }
            int sales_int;
            try {
                sales_int = Integer.parseInt(sales);
                if (sales_int <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                while (true) {
                    Console.sendln("Продажи альбома должно быть больше 0. Укажите продажи альбома:");
                    Console.send("?");
                    try {
                        sales_int = Integer.parseInt(Console.receive());
                        if (sales_int > 0) {
                            break;
                        }
                    }
                    catch (NullPointerException | NumberFormatException ignored){ }
                }
            }
            album = new Album(albumName, tracks_int, length_int, sales_int);
        }
        else{
            album = null;
        }
        LocalDateTime time_now = LocalDateTime.now();
        return new MusicBand(id, name, new Coordinates(xf, yd), time_now, numberOfParticipants_long, description, time, mgenre, album);
    }

    /** Переопределенный метод toString
     *
     * @return возвращает объект в виде текста
     */
    @Override
    public String toString() {
        return  "________________________________________________________________" + "\n" +
                "|ID: " + id + "\n" +
                "|Имя: (" + name + ")\n" +
                "|Координаты: " + coordinates + "\n" +
                "|Дата добавления в базу: " + creationDate + " \n" +
                "|Число участников: " + numberOfParticipants + "\n" +
                "|Описание: (" + description + ")\n" +
                "|Дата создания: " + establishmentDate + "\n" +
                "|Жанр: " + genre + "\n" +
                "|Лучший альбом: " + bestAlbum + "\n" +
                "________________________________________________________________";
    }
    @Override
    public int compareTo(MusicBand anotherMusicBand)
    {
        if (this.id.equals(anotherMusicBand.id)) {
            return 0;
        } else if (this.id < anotherMusicBand.id) {
            return -1;
        } else {
            return 1;
        }
    }
}