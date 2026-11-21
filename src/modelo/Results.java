package modelo;


public class Results {
    private String functionName;
    private long duration;   // duracion en nanosegundos
    private int sample;      // cantidad de elementos ordenados

    public Results(String functionName, long duration, int sample) {
        this.functionName = functionName;
        this.duration = duration;
        this.sample = sample;
    }

    // 1 milisegundo = 1 000 000 nanosegundos
    public double getDurationMs() {
        return duration / 1_000_000.0;
    }

    @Override
    public String toString() {
        return "Results [functionName=" + functionName + ", sample=" + sample
                + ", duration=" + duration + "ns, " + String.format("%.3f", getDurationMs()) + "ms]";
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getSample() {
        return sample;
    }

    public void setSample(int sample) {
        this.sample = sample;
    }
}
