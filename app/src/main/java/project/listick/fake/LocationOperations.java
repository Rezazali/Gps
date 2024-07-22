package project.listick.fake;

import android.app.Activity;
import android.location.Address;

import org.osmdroid.util.GeoPoint;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LocationOperations {

    /**
     * 3) this is for start activity RouteSettingsActivity for continue button
     *  and then i think so its give the info that get it from map activity to the RouteSettingsActivity
     */


    public static GeoPoint deviate(GeoPoint input, float accuracy) {
        double latError = ThreadLocalRandom.current().nextDouble(0, 90);
        double lngError = ThreadLocalRandom.current().nextDouble(0, 180);

        double tempLat = accuracy / (111_111 / Math.cos(input.getLatitude()));
        tempLat *= Math.cos(latError);
        if (ThreadLocalRandom.current().nextBoolean()) tempLat += input.getLatitude();
        else tempLat = input.getLatitude() - tempLat;

        double tempLng = accuracy / (111_111 / Math.cos(input.getLongitude()));
        tempLng *= Math.sin(lngError);
        if (ThreadLocalRandom.current().nextBoolean()) tempLng += input.getLongitude();
        else tempLng = input.getLongitude() - tempLng;

        return new GeoPoint(tempLat, tempLng);
    }

}
