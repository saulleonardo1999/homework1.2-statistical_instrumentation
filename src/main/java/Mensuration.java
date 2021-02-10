public class Mensuration {
    private float volts;
    private int order;
    private String time;

    public Mensuration(float volts, int order, String time) {
        this.volts = volts;
        this.time = time;
        this.order = order;
        System.out.println("Mensuration with order " + order + " was created successfully" );
    }

    public float getVolts() {
        return this.volts;
    }

    public int getOrder() {
        return order;
    }

    public String getTime() {
        return time;
    }

    public float sortBy(Mensuration a) {
        return this.getVolts() > a.getVolts() ? 1 : -1;
    }
}

