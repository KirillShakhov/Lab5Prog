package lab.classes;

class Coordinates implements Comparable<Coordinates>{
    private double x; //Значение поля должно быть больше -687
    private Float y; //Поле не может быть null
    Coordinates(double x, Float y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "x: (" + x + "), y: (" + y + ")";
    }

    @Override
    public int compareTo(Coordinates otherCoordinates) {
        if(this.x == otherCoordinates.x && this.y == otherCoordinates.x){
            return 0;
        }
        else if(this.x+this.y < otherCoordinates.x+otherCoordinates.y){
            return -1;
        }
        else{
            return 1;
        }
    }
}
