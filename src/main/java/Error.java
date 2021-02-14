public class Error {
    public float absolute;
    public float relative;
    public String hour;

    public Error(float absolute, float relative, String hour) {
        this.absolute = absolute;
        this.relative = relative;
        this.hour = hour;
    }

    public float getAbsolute() {
        return absolute;
    }

    public float getRelative() {
        return relative;
    }

    public String getHour() {
        return hour;
    }
}
