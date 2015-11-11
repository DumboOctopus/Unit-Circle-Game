
/**
 * Created by Neily on 11/7/15.
 */
public class UnitCirclePoint {
    private int deg;
    private static int rangeOfAnglesGenerated = 4;

    //========CONSTRUCTOR
    public UnitCirclePoint(int deg)
    {
        this.deg = deg;
    }

    //=======METHODS
    public int getDeg()
    {
        return deg;
    }
    public int getSimplifiedDeg()
    {
        int tmp = deg;
        while(tmp >= 360)
        {
            tmp -= 360;
        }
        while(tmp <  0) {
            tmp += 360;
        }

        return tmp;
    }

    public static UnitCirclePoint generateRandomPoint()
    {
        int randomAngle = (int) (rangeOfAnglesGenerated*24*Math.random());
        randomAngle *= 15;
        randomAngle -= 360*(rangeOfAnglesGenerated/2); //so we can have negative angles :P
        //System.out.println("raw: " + randomAngle);
        if(randomAngle % 30 != 0 && randomAngle %45 != 0)
        {
            randomAngle += 15;
        }
        return new UnitCirclePoint(randomAngle);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof UnitCirclePoint)) return false;
        UnitCirclePoint other = (UnitCirclePoint) obj;
        //System.out.println(this.getDeg());
        return (other.getSimplifiedDeg() == this.getSimplifiedDeg());
    }

    public String toString(boolean isDeg) {
        if(isDeg)
        {
            return this.deg + " degrees";
        } else
        {
            if(deg == 0) return "0";
            if(deg%180 == 0)
            {
                return (deg/180) + "*pi";
            }else if(deg%60== 0)
            {
                return (deg/60) + "*pi/3";
            } else if(deg%90 == 0)
            {
                return (deg/90) + "*pi/2";
            } else if(deg%45 == 0)
            {
                return (deg/45) + "*pi/4";
            } else
            {
                //means its a multiple of 30...
                return (deg/30) + "*pi/6";

            }
        }
    }
}
