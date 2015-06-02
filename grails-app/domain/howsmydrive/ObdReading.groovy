package howsmydrive

class ObdReading {

    Long tripId
    Double latitude, longitude
    Date timestamp
    String readings
    Integer speed
    //String vin
    //Map<String, String> readings

    static constraints = {
    }

    static mapping = {
        readings type: 'text'
    }
}
