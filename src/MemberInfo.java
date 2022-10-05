public class MemberInfo  extends Item {

    private Musician musician;
    private Band band;
    private int yearJoined;
    private String instrument;

    public MemberInfo(Musician musician,Band band, int yearJoined, String instrument) {
        this.musician = musician;
        this.band = band;
        this.yearJoined = yearJoined;
        this.instrument = instrument;
        ItemStore.add(this);
    }
}

