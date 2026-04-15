// Problem 1: Adapter Pattern
// Unified interface for flight search
interface FlightAPI {
    void searchFlights(String destination);
}

// Existing Airline API classes with different methods
class AirlineA {
    public void getFlightDetails(String destination) {
        System.out.println("AirlineA: Getting flight details for " + destination);
    }
}

class AirlineB {
    public void fetchFlights(String destination) {
        System.out.println("AirlineB: Fetching flights for " + destination);
    }
}

class AirlineC {
    public void retrieveFlightInfo(String destination) {
        System.out.println("AirlineC: Retrieving flight info for " + destination);
    }
}

// Adapters to unify the interfaces
class AirlineAAdapter implements FlightAPI {
    private AirlineA airlineA;

    public AirlineAAdapter(AirlineA airlineA) {
        this.airlineA = airlineA;
    }

    @Override
    public void searchFlights(String destination) {
        airlineA.getFlightDetails(destination);
    }
}

class AirlineBAdapter implements FlightAPI {
    private AirlineB airlineB;

    public AirlineBAdapter(AirlineB airlineB) {
        this.airlineB = airlineB;
    }

    @Override
    public void searchFlights(String destination) {
        airlineB.fetchFlights(destination);
    }
}

class AirlineCAdapter implements FlightAPI {
    private AirlineC airlineC;

    public AirlineCAdapter(AirlineC airlineC) {
        this.airlineC = airlineC;
    }

    @Override
    public void searchFlights(String destination) {
        airlineC.retrieveFlightInfo(destination);
    }
}

// Client code
public class TravelBookingSystem {
    public static void main(String[] args) {
        FlightAPI api1 = new AirlineAAdapter(new AirlineA());
        FlightAPI api2 = new AirlineBAdapter(new AirlineB());
        FlightAPI api3 = new AirlineCAdapter(new AirlineC());

        api1.searchFlights("New York");
        api2.searchFlights("London");
        api3.searchFlights("Tokyo");
    }
}