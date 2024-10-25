// Main.java
public class Main {
    public static void main(String[] args) {
        Person person = new Person("Гоша");

        Car car = new Car(60);
        Horse horse = new Horse(25);
        Bicycle bicycle = new Bicycle();
        AllTerrainVehicle allTerrainVehicle = new AllTerrainVehicle(120);

        person.setVehicle(car);
        person.move("густой лес", 18);
        person.move("болото", 6);

        person.setVehicle(bicycle);
        person.move("равнина", 12);
        person.move("болото", 35);

        person.setVehicle(horse);
        person.move("равнина", 5);
        person.move("болото", 15);

        person.setVehicle(allTerrainVehicle);
        person.move("болото", 100);
    }
}

// Person.java
class Person {
    private String name;
    private Vehicle currentVehicle;

    public Person(String name) {
        this.name = name;
        this.currentVehicle = null; // Изначально человек идет пешком
    }

    public void setVehicle(Vehicle vehicle) {
        if (vehicle != null) {
            System.out.println(name + " сел на " + vehicle.getType());
        } else {
            System.out.println(name + " слез с транспорта.");
        }
        this.currentVehicle = vehicle;
    }

    public void move(String terrain, int distance) {
        if (currentVehicle == null) {
            System.out.println(name + " идет пешком по " + terrain + " на расстояние " + distance + " км.");
        } else if (currentVehicle.move(terrain, distance)) {
            System.out.println(name + " переместился на " + currentVehicle.getType() + " по " + terrain + " на расстояние " + distance + " км.");
        } else {
            System.out.println("Невозможно переместиться на " + currentVehicle.getType() + " по " + terrain + ".");
        }
    }
}

// Vehicle.java
abstract class Vehicle {
    protected String type;

    public String getType() {
        return type;
    }

    public abstract boolean move(String terrain, int distance);
}

// Car.java
class Car extends Vehicle {
    private int fuel;

    public Car(int fuel) {
        this.type = "Машина";
        this.fuel = fuel;
    }

    @Override
    public boolean move(String terrain, int distance) {
        if (terrain.equals("густой лес") || terrain.equals("болото")) {
            return false;
        }
        if (fuel >= distance) {
            fuel -= distance;
            return true;
        } else {
            System.out.println("Недостаточно бензина.");
            return false;
        }
    }
}

// Horse.java
class Horse extends Vehicle {
    private int stamina;

    public Horse(int stamina) {
        this.type = "Лошадь";
        this.stamina = stamina;
    }

    @Override
    public boolean move(String terrain, int distance) {
        if (terrain.equals("болото")) {
            return false;
        }
        if (stamina >= distance) {
            stamina -= distance;
            return true;
        } else {
            System.out.println("Лошадь устала.");
            return false;
        }
    }
}

// Bicycle.java
class Bicycle extends Vehicle {
    public Bicycle() {
        this.type = "Велосипед";
    }

    @Override
    public boolean move(String terrain, int distance) {
        return !terrain.equals("болото");
    }
}

// AllTerrainVehicle.java
class AllTerrainVehicle extends Vehicle {
    private int fuel;

    public AllTerrainVehicle(int fuel) {
        this.type = "Вездеход";
        this.fuel = fuel;
    }

    @Override
    public boolean move(String terrain, int distance) {
        if (fuel >= distance) {
            fuel -= distance;
            return true;
        } else {
            System.out.println("Недостаточно бензина для вездехода.");
            return false;
        }
    }
}

