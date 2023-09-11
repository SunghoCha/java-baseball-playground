package baseball;

public enum BallStatus {
    STRIKE,BALL,NOTHING;

    public boolean isNotNOTHING() { return this != BallStatus.NOTHING;}

    public boolean isSTRIKE() { return this == BallStatus.STRIKE;}

    public boolean isBALL() {return this == BallStatus.BALL; }
}
