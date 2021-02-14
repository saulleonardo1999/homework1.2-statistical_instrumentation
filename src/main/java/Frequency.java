import com.itextpdf.layout.element.IBlockElement;

public class Frequency {
    public float el;
    public int frequency;

    public Frequency(float el) {
        this.el = el;
        this.frequency = 1;
    }

    public void increment() {
        this.frequency++;
    }

    public float getEl() {
        return el;
    }

    public int getFrequency() {
        return frequency;
    }

    public float sortBy(Frequency a) {
        return this.getFrequency() < a.getFrequency() ? 1 : -1;
    }
}
