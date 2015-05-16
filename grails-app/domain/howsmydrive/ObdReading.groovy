package howsmydrive

class ObdReading {

    long tripId
    double latitude, longitude
    Date timestamp
    String readings
    int speed
    //String vin
    //Map<String, String> readings

    static constraints = {
    }

    static mapping = {
        readings type: 'text'
    }
}
