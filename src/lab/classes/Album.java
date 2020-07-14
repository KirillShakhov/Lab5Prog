package lab.classes;

@SuppressWarnings("ALL")
class Album implements Comparable<Album> {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private int tracks; //Значение поля должно быть больше 0
    private Integer length; //Поле не может быть null, Значение поля должно быть больше 0
    private int sales; //Значение поля должно быть больше 0

    Album(String name, int tracks, Integer length, int sales){
        this.name = name;
        this.tracks = tracks;
        this.length = length;
        this.sales = sales;
    }
    Album(String name, String tracks, String length, String sales){
        this.name = name;
        this.tracks = Integer.parseInt(tracks);
        this.length = Integer.parseInt(length);
        this.sales = Integer.parseInt(sales);
    }

    public int getSales() {
        return sales;
    }

    @Override
    public String toString() {
        return "(" + name + "), Количество треков(" + tracks + "), Длина(" + length + "), Продажи(" + sales + "$)";
    }

    @Override
    public int compareTo(Album otherAlbum) {
        if(this.sales == otherAlbum.sales){
            return 0;
        }
        else if(this.sales < otherAlbum.sales){
            return -1;
        }
        else{
            return 1;
        }
    }
}