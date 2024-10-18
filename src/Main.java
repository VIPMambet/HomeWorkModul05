import java.util.Scanner;

// Интерфейс для всех транспортных средств
interface IVehicle {
    void drive();
    void refuel();
}

// Класс для автомобилей
class Car implements IVehicle {
    private String brand;
    private String model;
    private String fuelType;

    public Car(String brand, String model, String fuelType) {
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
    }

    @Override
    public void drive() {
        System.out.println("Автомобиль " + brand + " " + model + " движется.");
    }

    @Override
    public void refuel() {
        System.out.println("Заправка автомобиля " + brand + " " + model + " типом топлива: " + fuelType);
    }
}

// Класс для мотоциклов
class Motorcycle implements IVehicle {
    private String type;
    private double engineCapacity;

    public Motorcycle(String type, double engineCapacity) {
        this.type = type;
        this.engineCapacity = engineCapacity;
    }

    @Override
    public void drive() {
        System.out.println("Мотоцикл типа " + type + " с объемом двигателя " + engineCapacity + " движется.");
    }

    @Override
    public void refuel() {
        System.out.println("Заправка мотоцикла типа " + type + " объемом двигателя " + engineCapacity);
    }
}

// Класс для грузовиков
class Truck implements IVehicle {
    private double payloadCapacity;
    private int axleCount;

    public Truck(double payloadCapacity, int axleCount) {
        this.payloadCapacity = payloadCapacity;
        this.axleCount = axleCount;
    }

    @Override
    public void drive() {
        System.out.println("Грузовик с грузоподъемностью " + payloadCapacity + " тонн и количеством осей " + axleCount + " движется.");
    }

    @Override
    public void refuel() {
        System.out.println("Заправка грузовика с грузоподъемностью " + payloadCapacity + " тонн.");
    }
}

// Класс для автобусов
class Bus implements IVehicle {
    private int passengerCapacity;

    public Bus(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    @Override
    public void drive() {
        System.out.println("Автобус с вместимостью " + passengerCapacity + " пассажиров движется.");
    }

    @Override
    public void refuel() {
        System.out.println("Заправка автобуса с вместимостью " + passengerCapacity + " пассажиров.");
    }
}

// Абстрактный класс фабрики транспортных средств
abstract class VehicleFactory {
    public abstract IVehicle createVehicle();
}

// Фабрика для создания автомобилей
class CarFactory extends VehicleFactory {
    private String brand;
    private String model;
    private String fuelType;

    public CarFactory(String brand, String model, String fuelType) {
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
    }

    @Override
    public IVehicle createVehicle() {
        return new Car(brand, model, fuelType);
    }
}

// Фабрика для создания мотоциклов
class MotorcycleFactory extends VehicleFactory {
    private String type;
    private double engineCapacity;

    public MotorcycleFactory(String type, double engineCapacity) {
        this.type = type;
        this.engineCapacity = engineCapacity;
    }

    @Override
    public IVehicle createVehicle() {
        return new Motorcycle(type, engineCapacity);
    }
}

// Фабрика для создания грузовиков
class TruckFactory extends VehicleFactory {
    private double payloadCapacity;
    private int axleCount;

    public TruckFactory(double payloadCapacity, int axleCount) {
        this.payloadCapacity = payloadCapacity;
        this.axleCount = axleCount;
    }

    @Override
    public IVehicle createVehicle() {
        return new Truck(payloadCapacity, axleCount);
    }
}

// Фабрика для создания автобусов
class BusFactory extends VehicleFactory {
    private int passengerCapacity;

    public BusFactory(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    @Override
    public IVehicle createVehicle() {
        return new Bus(passengerCapacity);
    }
}

// Главный класс
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите тип транспорта: 1 - Автомобиль, 2 - Мотоцикл, 3 - Грузовик, 4 - Автобус");
        int choice = scanner.nextInt();
        IVehicle vehicle = null;

        switch (choice) {
            case 1: // Автомобиль
                System.out.println("Введите марку:");
                String brand = scanner.next();
                System.out.println("Введите модель:");
                String model = scanner.next();
                System.out.println("Введите тип топлива:");
                String fuelType = scanner.next();
                VehicleFactory carFactory = new CarFactory(brand, model, fuelType);
                vehicle = carFactory.createVehicle();
                break;

            case 2: // Мотоцикл
                System.out.println("Введите тип мотоцикла (спортивный, туристический):");
                String type = scanner.next();
                System.out.println("Введите объем двигателя:");
                double engineCapacity = scanner.nextDouble();
                VehicleFactory motorcycleFactory = new MotorcycleFactory(type, engineCapacity);
                vehicle = motorcycleFactory.createVehicle();
                break;

            case 3: // Грузовик
                System.out.println("Введите грузоподъемность (в тоннах):");
                double payloadCapacity = scanner.nextDouble();
                System.out.println("Введите количество осей:");
                int axleCount = scanner.nextInt();
                VehicleFactory truckFactory = new TruckFactory(payloadCapacity, axleCount);
                vehicle = truckFactory.createVehicle();
                break;

            case 4: // Автобус
                System.out.println("Введите вместимость (количество пассажиров):");
                int passengerCapacity = scanner.nextInt();
                VehicleFactory busFactory = new BusFactory(passengerCapacity);
                vehicle = busFactory.createVehicle();
                break;

            default:
                System.out.println("Неверный выбор.");
                break;
        }

        if (vehicle != null) {
            vehicle.drive();
            vehicle.refuel();
        }
    }
}
