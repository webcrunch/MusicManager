public class MemberInfo  extends Item {

    private Musician musician;
    private Band band;
    private int yearJoined;
    private int yearLeft;
    private String instrument;

    public Musician getMusician() {
        return musician;
    }

    public Band getBand() {
        return band;
    }

    public int getYearJoined() {
        return yearJoined;
    }

    public String getInstrument() {
        return instrument;
    }

    public int getYearLeft() {
        return yearLeft;
    }

    public void setYearLeft(int yearLeft) {
        this.yearLeft = yearLeft;
    }

    public MemberInfo(Musician musician, Band band, int yearJoined, String instrument) {
        this.musician = musician;
        this.band = band;
        this.yearJoined = yearJoined;
        this.instrument = instrument;
        ItemStore.add(this);
    }
}
